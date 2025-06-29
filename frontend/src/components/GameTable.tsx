// src/components/GameTable.tsx
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useGames } from "../contexts/GameContext";
import type { Game } from "../contexts/GameContext";
import { useAuth } from "../contexts/AuthContext";

/* ──────────── table & link styles ──────────── */
const th: React.CSSProperties = {
  border: "1px solid #ddd",
  padding: "8px",
  background: "#f2f2f2",
  textAlign: "left",
};

const td: React.CSSProperties = {
  border: "1px solid #ddd",
  padding: "8px",
};

const actionLink: React.CSSProperties = {
  marginRight: "0.5rem",
  color: "#007bff",
  cursor: "pointer",
};

/* ─────────────── Logout Button ─────────────── */
const LogoutButton: React.FC = () => {
  const { logout } = useAuth();
  const navigate   = useNavigate();
  return (
    <button
      onClick={() => {
        logout();
        navigate("/login");
      }}
      style={{
        position: "fixed",
        top: 20,
        right: 20,
        background: "#e63946",
        color: "#fff",
        border: "none",
        padding: "8px 16px",
        borderRadius: 6,
        cursor: "pointer",
      }}
    >
      Logout
    </button>
  );
};

/* ─────────────────── GameTable ─────────────────── */
const GameTable: React.FC = () => {
  const { games }  = useGames();
  const { loggedIn } = useAuth();
  const navigate     = useNavigate();

  /* search state */
  const [searchTerm, setSearchTerm] = useState("");

  /* filter list */
  const filteredGames = games.filter((g) =>
    g.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div style={{ padding: "2rem" }}>
      {loggedIn && <LogoutButton />}

      <h1 style={{ textAlign: "center", marginBottom: "1rem" }}>Game Table</h1>

      {/* top‑bar: search on left, auth links on right */}
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: "1rem",
          flexWrap: "wrap",
          gap: "0.5rem",
        }}
      >
        {/* search bar */}
        <input
          type="text"
          placeholder="Search game by name…"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={{
            padding: "0.5rem",
            width: "100%",
            maxWidth: "300px",
            borderRadius: "6px",
            border: "1px solid #ccc",
          }}
        />

        {/* auth‑dependent links */}
        {loggedIn ? (
          <Link to="/game/add" style={{ ...actionLink, fontWeight: 600 }}>
            ➕ Add Game
          </Link>
        ) : (
          <div style={{ whiteSpace: "nowrap" }}>
            <Link to="/login"  style={{ ...actionLink, marginRight: "1rem" }}>
              🔐 Login
            </Link>
            <Link to="/signup" style={actionLink}>
              📝 Sign Up
            </Link>
          </div>
        )}
      </div>

      {/* table */}
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
            {loggedIn && <th style={th}>Actions</th>}
          </tr>
        </thead>

        <tbody>
          {filteredGames.map((g: Game) => (
            <tr key={g.name}>
              {/* clickable name cell */}
              <td
                style={{ ...td, cursor: "pointer", color: "#007bff" }}
                onClick={() => navigate(`/game/${encodeURIComponent(g.name)}`)}
              >
                {g.name}
              </td>

              <td style={td}>{g.genre}</td>
              <td style={td}>{g.releasedDate}</td>
              <td style={td}>{g.copiesSold.toLocaleString()}</td>
              <td style={td}>{g.rating}</td>
              <td style={td}>{g.gameOfTheYear ? "Yes" : "No"}</td>
              <td style={td}>{g.gameStudios}</td>
              <td style={td}>${g.revenue.toLocaleString()}</td>

              {loggedIn && (
                <td style={td}>
                  <Link
                    to={`/game/edit/${encodeURIComponent(g.name)}`}
                    style={actionLink}
                  >
                    Edit
                  </Link>
                  |
                  <Link
                    to={`/game/delete/${encodeURIComponent(g.name)}`}
                    style={{ ...actionLink, color: "red", marginLeft: "0.5rem" }}
                  >
                    Delete
                  </Link>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GameTable;
