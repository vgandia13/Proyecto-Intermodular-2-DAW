import { Outlet } from "react-router-dom";
import Navbar from "@/components/Navbar";

const MainLayout = () => {
  return (
    <div className="min-h-screen bg-background">
      <Navbar /> 
      <main className="container mx-auto ">
        <Outlet />
      </main>
    </div>
  );
};

export default MainLayout;