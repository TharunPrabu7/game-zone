import React, { useEffect, useState } from "react";
import axios from "axios";

interface Game {
  name: string;
  genre: string;
  releaseDate: string;
  copiesSold: number;
  rating: number;
  goty: boolean;
  gameStudios: string;
  revenue: number;
}

const GameDashboard: React.FC = () => {
  const [games, setGames] = useState<Game[]>([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/v1/game")
      .then(response => setGames(response.data))
      .catch(error => console.error("Error fetching games:", error));
  }, []);

  return (
    <div className="table-container">
      <table className="game-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Genre</th>
            <th>Studio</th>
            <th>Release Date</th>
            <th>Rating</th>
            <th>Copies Sold</th>
            <th>Revenue</th>
            <th>GOTY</th>
          </tr>
        </thead>
        <tbody>
          {games.length === 0 ? (
            <tr><td colSpan={8}>No games available.</td></tr>
          ) : (
            games.map((game, index) => (
              <tr key={index}>
                <td>{game.name}</td>
                <td>{game.genre}</td>
                <td>{game.gameStudios}</td>
                <td>{game.releaseDate}</td>
                <td>{game.rating}</td>
                <td>{game.copiesSold.toLocaleString()}</td>
                <td>${game.revenue.toLocaleString()}</td>
                <td>{game.goty ? "Yes" : "No"}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};



export default GameDashboard;