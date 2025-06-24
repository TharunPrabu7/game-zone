import React from "react";
import './Login.css';
import { useNavigate } from "react-router-dom";

const Signup: React.FC = () => {
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // TODO: Signup logic goes here
    navigate("/dashboard"); // Navigate after signup
  };

  return (
    <div className="login-wrapper">
      <form className="form" onSubmit={handleSubmit}>
        <p id="heading">Sign Up</p>

        <div className="field">
          <svg className="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
            <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3z"/>
            <path fillRule="evenodd" d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
          </svg>
          <input autoComplete="off" placeholder="Full Name" className="input-field" type="text" required />
        </div>

        <div className="field">
          <svg className="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
            <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v1H0V4zm0 2v6a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6H0z"/>
          </svg>
          <input placeholder="Email" className="input-field" type="email" required />
        </div>

        <div className="field">
          <svg className="input-icon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
          </svg>
          <input placeholder="Password" className="input-field" type="password" required />
        </div>

        <button type="submit" className="button1" style={{ width: "100%" }}>Create Account</button>

        <button
          type="button"
          className="button3"
          onClick={() => navigate("/home")}
        >
          Already have an account? Login
        </button>
      </form>
    </div>
  );
};

export default Signup;
