import { Outlet } from "react-router-dom";
import Navbar from "@/components/Navbar";
import { Toaster } from "sonner";

const MainLayout = () => {
  return (
    <div className="min-h-screen bg-background">
      <Navbar />
      <main className="container mx-auto ">
        <Outlet />
        <Toaster position="top-center" />
      </main>
    </div>
  );
};

export default MainLayout;
