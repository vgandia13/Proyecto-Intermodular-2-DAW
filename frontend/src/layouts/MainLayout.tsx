import { Outlet } from "react-router-dom";
import Navbar from "@/components/Navbar";
import { Toaster } from "sonner";
import { AppContextProvider } from "@/contexts/AppContext";

const MainLayout = () => {
  return (
    <AppContextProvider>
      <div className="min-h-screen bg-background">
        <Navbar />
        <main className="container mx-auto ">
          <Outlet />
          <Toaster position="top-center" />
        </main>
      </div>
    </AppContextProvider>
  );
};

export default MainLayout;
