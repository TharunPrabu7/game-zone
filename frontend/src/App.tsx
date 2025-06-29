import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Signup from "./components/Signup";
import Login from "./components/Login";
import GameTable from "./components/GameTable.tsx";
import GameDetail from "./components/GameDetail.tsx";
import { AuthProvider } from "./contexts/AuthContext.tsx";
import { GameProvider } from "./contexts/GameContext";
import AddGame from "./components/AddGame.tsx";
import EditGame from "./components/EditGames.tsx";
import DeletePrompt from "./components/DeletePrompt.tsx";

export default function App() {
  return (
    <Router>
      <AuthProvider>
        <GameProvider>
          <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/game/table" element={<GameTable />} />
            <Route path="/game/add" element={<AddGame />} />
            <Route path="/game/edit/:name" element={<EditGame />} />
            <Route path="/game/delete/:name" element={<DeletePrompt />} />
            <Route path="/game/:name" element={<GameDetail />} />
            <Route path="*" element={<GameTable />} />
          </Routes>
        </GameProvider>
      </AuthProvider>
</Router>

  );
}
