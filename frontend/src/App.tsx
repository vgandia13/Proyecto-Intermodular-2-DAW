import "./App.css";
import { useData } from "./contexts/AppContext";
import MainLayout from "./layouts/MainLayout";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import EventsPage from "./pages/EventsPage";

function App() {
  const { isLogged } = useData();
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<MainLayout />}>
          <Route path="/" element={isLogged ? <HomePage /> : <LoginPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/events" element={<EventsPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
