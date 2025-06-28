import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Signup from "./components/Signup";
import Login from "./components/Login";
import GameTable from "./components/GameTable";
import GameDetail from "./components/GameDetail.tsx";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/signup" element={<Signup />} />
        <Route path="/login"  element={<Login  />} />
        <Route path="/game/table" element={<GameTable />} />
        <Route path="/game/:name" element={<GameDetail />} />
        <Route path="*" element={<Login />} />   {/* default to table */}
      </Routes>
    </Router>
  );
}
