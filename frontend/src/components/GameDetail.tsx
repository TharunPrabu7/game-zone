import { useParams, Link } from "react-router-dom";
import { useEffect, useState } from "react";
import type { Game } from "../types/Game";

const GameDetail: React.FC = () => {
  const { name }          = useParams();
  const [game, setGame]   = useState<Game | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/v1/game/${encodeURIComponent(name!)}`)
      .then(res => {
        if (!res.ok) throw new Error("Game not found");
        return res.json();
      })
      .then(setGame)
      .catch(err => setError(err.message));
  }, [name]);

  if (error) return <p style={{ color: "red" }}>{error}</p>;
  if (!game)  return <p>Loading…</p>;

  return (
    <div style={{ padding: "2rem" }}>
      <h2>{game.name}</h2>
      <p><strong>Genre:</strong> {game.genre}</p>
      <p><strong>Released:</strong> {game.releasedDate}</p>
      <p><strong>Copies Sold:</strong> {game.copiesSold}</p>
      <p><strong>Rating:</strong> {game.rating}</p>
      <p><strong>GOTY:</strong> {game.gameOfTheYear ? "Yes" : "No"}</p>
      <p><strong>Studio:</strong> {game.gameStudios}</p>
      <p><strong>Revenue:</strong> ${game.revenue.toLocaleString()}</p>

      <Link to="/game/table" style={{ marginTop: "1rem", display: "inline-block" }}>
        ← Back to table
      </Link>
    </div>
  );
};

export default GameDetail;
