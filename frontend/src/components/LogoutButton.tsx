import React from "react";
import { useAuth } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

const LogoutButton: React.FC = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <button onClick={handleLogout} style={{
      position: "absolute",
      top: 20,
      right: 20,
      padding: "8px 16px",
      borderRadius: "6px",
      border: "none",
      backgroundColor: "#e63946",
      color: "white",
      cursor: "pointer"
    }}>
      Logout
    </button>
  );
};

export default LogoutButton;
