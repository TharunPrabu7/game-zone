import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Game } from "../types/Game";

const th: React.CSSProperties = {
  border: "1px solid #ddd",
  padding: "8px",
  background: "#f2f2f2",
  textAlign: "left"
};

const td: React.CSSProperties = {
  border: "1px solid #ddd",
  padding: "8px"
};

const GameTable: React.FC = () => {
  const [games, setGames]   = useState<Game[]>([]);
  const [error, setError]   = useState<string | null>(null);
  const navigate            = useNavigate();

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/game/table")
      .then(res => {
        if (!res.ok) throw new Error("Failed to fetch games");
        return res.json();
      })
      .then(setGames)
      .catch(err => setError(err.message));
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h1 style={{ textAlign: "center" }}>Game Table</h1>

      {error ? (
        <p style={{ color: "red" }}>{error}</p>
      ) : (
        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr>
              <th style={th}>Name</th>
              <th style={th}>Genre</th>
              <th style={th}>Released</th>
              <th style={th}>Copies Sold</th>
              <th style={th}>Rating</th>
              <th style={th}>GOTY</th>
              <th style={th}>Studio</th>
              <th style={th}>Revenue</th>
            </tr>
          </thead>
          <tbody>
            {games.map(game => (
              <tr
                key={game.name}
                style={{ cursor: "pointer" }}
                onClick={() => navigate(`/game/${encodeURIComponent(game.name)}`)}
              >
                <td style={td}>{game.name}</td>
                <td style={td}>{game.genre}</td>
                <td style={td}>{game.releasedDate}</td>
                <td style={td}>{game.copiesSold}</td>
                <td style={td}>{game.rating}</td>
                <td style={td}>{game.gameOfTheYear ? "Yes" : "No"}</td>
                <td style={td}>{game.gameStudios}</td>
                <td style={td}>${game.revenue.toLocaleString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default GameTable;
