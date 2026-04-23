import { Toaster } from "sonner";
import "./App.css";
import LoginPage from "./pages/LoginPage";

function App() {
  return (
    <>
      <LoginPage />
      <Toaster richColors position="top-center" />
    </>
  );
}

export default App;
