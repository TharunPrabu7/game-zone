import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/login";
import Signup from "./components/Signup";
import GameDashboard from "./components/GameDashboard";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/home" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard" element={<GameDashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
