// src/components/DeletePrompt.tsx
import React from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { useGames } from "../contexts/GameContext";

const DeletePrompt: React.FC = () => {
  const { name } = useParams();
  const { deleteGame } = useGames();
  const navigate = useNavigate();

  if (!name) return <p>Game not specified</p>;

  const handleDelete = () => {
    deleteGame(name);
    navigate("/game/table");
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Delete Game</h2>
      <p>Are you sure you want to delete <strong>{name}</strong>?</p>
      <button onClick={handleDelete} style={{ marginRight: "1rem" }}>Yes, delete</button>
      <Link to="/game/table">Cancel</Link>
    </div>
  );
};

export default DeletePrompt;
