CREATE TABLE IF NOT EXISTS games (
  appid        INTEGER PRIMARY KEY,
  name         TEXT,
  released_at  TIMESTAMP,
  description  TEXT,
  header_image TEXT,
  raw          JSONB,
  updated_at   TIMESTAMP DEFAULT NOW()
);
