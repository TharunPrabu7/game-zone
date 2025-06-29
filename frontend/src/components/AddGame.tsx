// src/components/AddGame.tsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Game } from "../contexts/GameContext";
import { useGames } from "../contexts/GameContext";

const emptyGame: Game = {
  name: "",
  genre: "",
  releasedDate: "",
  copiesSold: 0,
  rating: 0,
  gameOfTheYear: false,
  gameStudios: "",
  revenue: 0,
};

const AddGame: React.FC = () => {
  const { addGame } = useGames();
  const navigate = useNavigate();
  const [form, setForm] = useState<Game>(emptyGame);

  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]:
        type === "checkbox"
          ? checked
          : name === "copiesSold" || name === "revenue"
          ? Number(value)
          : name === "rating"
          ? parseFloat(value)
          : value,
    }));
  };

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    addGame(form);
    navigate("/game/table");
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Add Game</h2>
      <form onSubmit={onSubmit}>
        <input name="name"          value={form.name}          onChange={onChange} placeholder="Name" required />
        <input name="genre"        value={form.genre}        onChange={onChange} placeholder="Genre" required />
        <input name="releasedDate" value={form.releasedDate} onChange={onChange} placeholder="Released (YYYY‑MM‑DD)" required />
        <input name="copiesSold"   value={form.copiesSold}   onChange={onChange} placeholder="Copies Sold" type="number" />
        <input name="rating"       value={form.rating}       onChange={onChange} placeholder="Rating" type="number" step="0.1" />
        <label>
          GOTY?
          <input name="gameOfTheYear" type="checkbox" checked={form.gameOfTheYear} onChange={onChange} />
        </label>
        <input name="gameStudios"  value={form.gameStudios}  onChange={onChange} placeholder="Studio" />
        <input name="revenue"      value={form.revenue}      onChange={onChange} placeholder="Revenue" type="number" />
        <button type="submit">Add</button>
      </form>
    </div>
  );
};

export default AddGame;
