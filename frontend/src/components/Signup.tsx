import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Login.css";

const Signup: React.FC = () => {
  const navigate = useNavigate();

  // local component state
  const [fullName, setFullName]   = useState("");
  const [email, setEmail]         = useState("");
  const [password, setPassword]   = useState("");
  const [error, setError]         = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);                         // clear old error

    try {
      await axios.post("http://localhost:8080/api/v1/user/save", {
        userName: fullName,
        email,
        password
      });
      // on success → go to dashboard (or login)
      navigate("/dashboard");
    } catch (err: any) {
      setError(
        err.response?.data || "Registration failed, please try again."
      );
    }
  };

  return (
    <div className="login-wrapper">
      <form className="form" onSubmit={handleSubmit}>
        <p id="heading">Sign Up</p>

        <div className="field">
          {/* full name */}
          <input
            autoComplete="off"
            placeholder="Full Name"
            className="input-field"
            type="text"
            value={fullName}
            onChange={e => setFullName(e.target.value)}
            required
          />
        </div>

        <div className="field">
          {/* email */}
          <input
            placeholder="Email"
            className="input-field"
            type="email"
            value={email}
            onChange={e => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="field">
          {/* password */}
          <input
            placeholder="Password"
            className="input-field"
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
        </div>

        {error && <p style={{ color: "salmon", marginTop: 4 }}>{error}</p>}

        <button type="submit" className="button1" style={{ width: "100%" }}>
          Create Account
        </button>

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
