// src/components/EditGame.tsx
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useGames } from "../contexts/GameContext";
import type { Game } from "../contexts/GameContext";

const EditGame: React.FC = () => {
  const { name } = useParams();
  const { games, updateGame } = useGames();
  const navigate = useNavigate();

  const original = games.find((g) => g.name === name);
  const [form, setForm] = useState<Game | null>(null);

  useEffect(() => {
    if (original) setForm(original);
  }, [original]);

  if (!form) return <p>Game not found</p>;

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) =>
      prev
        ? {
            ...prev,
            [name]:
              type === "checkbox"
                ? checked
                : name === "copiesSold" || name === "revenue"
                ? Number(value)
                : name === "rating"
                ? parseFloat(value)
                : value,
          }
        : prev
    );
  };

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    updateGame(form as Game);
    navigate("/game/table");
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Edit Game</h2>
      <form onSubmit={onSubmit}>
        <input name="name"          value={form.name}          onChange={onChange} required />
        <input name="genre"        value={form.genre}        onChange={onChange} required />
        <input name="releasedDate" value={form.releasedDate} onChange={onChange} required />
        <input name="copiesSold"   value={form.copiesSold}   onChange={onChange} type="number" />
        <input name="rating"       value={form.rating}       onChange={onChange} type="number" step="0.1" />
        <label>
          GOTY?
          <input name="gameOfTheYear" type="checkbox" checked={form.gameOfTheYear} onChange={onChange} />
        </label>
        <input name="gameStudios"  value={form.gameStudios}  onChange={onChange} />
        <input name="revenue"      value={form.revenue}      onChange={onChange} type="number" />
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default EditGame;
