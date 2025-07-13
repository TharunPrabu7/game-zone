import pg from 'pg';
import pLimit from 'p-limit';
import { config } from 'dotenv';
import fs from 'fs/promises';

config();

const R2S_BASE = process.env.R2S_BASE || 'https://rawg2steam.phalco.de/api';
const MAX_WORKERS = Number(process.env.CONCURRENCY || 8);
const limit = pLimit(MAX_WORKERS);
const db = new pg.Pool({ connectionString: process.env.DATABASE_URL });

async function httpGet(url) {
  const res = await fetch(url, { headers: { 'User-Agent': 'game-seed/1.0' } });
  if (!res.ok) throw new Error(`${res.status} ${res.statusText}`);
  return res.json();
}

let processed = 0, inserted = 0, failed = 0;
const failedAppIds = [];
const processedIds = new Set();

try {
  const existing = await db.query('SELECT appid FROM games');
  for (const row of existing.rows) processedIds.add(row.appid);
} catch (err) {
  console.error("Error loading existing app IDs from DB:", err);
  process.exit(1);
}

console.time('Fetch app list');
const { applist: { apps } } =
  await httpGet('https://api.steampowered.com/ISteamApps/GetAppList/v2');
console.log(`Total apps: ${apps.length}`);
console.timeEnd('Fetch app list');

await Promise.all(
  apps.map(app => limit(async () => {
    if (!app.name?.trim()) return;
    if (processedIds.has(app.appid)) return;

    try {
      const game = await httpGet(`${R2S_BASE}/games/${app.appid}`);
      await db.query(
        `INSERT INTO games (appid, name, released_at, description, header_image, raw)
         VALUES ($1,$2,to_timestamp($3),$4,$5,$6)
         ON CONFLICT (appid) DO UPDATE
         SET name=$2, released_at=to_timestamp($3),
             description=$4, header_image=$5, raw=$6, updated_at=NOW();`,
        [
          game.id,
          game.name,
          Date.parse(game.released) / 1000 || null,
          game.description_raw?.slice(0, 30000),
          game.background_image,
          game
        ]
      );
      inserted++;
    } catch (err) {
      failed++;
      failedAppIds.push(`${app.appid}, ${err.message}`);
    } finally {
      processed++;
      if (processed % 1000 === 0) console.log(`→ ${processed}/${apps.length}`);
    }
  }))
);

await db.end();
await fs.writeFile("failed_appids.txt", failedAppIds.join("\n"));
console.log(`Done. OK=${inserted}, failed=${failed} (see failed_appids.txt)`);
