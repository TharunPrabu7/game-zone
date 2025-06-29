// contexts/GameContext.tsx
import { createContext, useContext, useEffect, useState } from "react";

export interface Game {
  name: string;
  genre: string;
  releasedDate: string;
  copiesSold: number;
  rating: number;
  gameOfTheYear: boolean;
  gameStudios: string;
  revenue: number;
}

interface GameState {
  games: Game[];
  addGame: (g: Game) => void;
  updateGame: (g: Game) => void;
  deleteGame: (name: string) => void;
}

const GameContext = createContext<GameState>(null!);

export const GameProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [games, setGames] = useState<Game[]>([]);

  // initial fetch (real DB)
  useEffect(() => {
    fetch("http://localhost:8080/api/v1/game/table")
      .then((r) => r.json())
      .then(setGames)
      .catch(console.error);
  }, []);

  /* local‑only mutations */
  const addGame    = (g: Game) => setGames((prev) => [...prev, g]);
  const updateGame = (g: Game) =>
    setGames((prev) => prev.map((p) => (p.name === g.name ? g : p)));
  const deleteGame = (name: string) =>
    setGames((prev) => prev.filter((g) => g.name !== name));

  return (
    <GameContext.Provider value={{ games, addGame, updateGame, deleteGame }}>
      {children}
    </GameContext.Provider>
  );
};

export const useGames = () => useContext(GameContext);
