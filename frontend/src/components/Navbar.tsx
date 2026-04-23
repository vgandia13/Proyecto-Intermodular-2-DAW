import { Link, useNavigate } from "react-router-dom";
import { Button } from "./ui/button";
import { ThemeToggle } from "./ThemeToggle";

const Navbar = () => {
  const navigate = useNavigate();
  const isAuthenticated = !!localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <nav className="py-6 z-50 bg-primary text-primary-foreground dark:bg-zinc-800 dark:text-zinc-50 dark:border-b dark:border-border shadow-md transition-colors duration-200">
      <div className="container mx-auto flex items-center justify-between px-4">
        <Link to="/" className="text-xl font-bold tracking-tight">
          Feria+
        </Link>
        <div className="flex items-center space-x-6">
          <ThemeToggle />

          <div className="h-6 w-px bg-primary-foreground/20 hidden sm:block"></div>
          {isAuthenticated ? (
            <>
              <Button
                variant="ghost"
                asChild
                className="hover:bg-primary/90 hover:text-white"
              >
                <Link to="/dashboard">Mi Panel</Link>
              </Button>
              <Button variant="secondary" onClick={handleLogout}>
                Cerrar sesión
              </Button>
            </>
          ) : (
            <>
              <Button
                variant="ghost"
                asChild
                className="hover:bg-primary/90 hover:text-white hover:underline"
              >
                <Link to="/login">Iniciar sesión</Link>
              </Button>
              <Button variant="secondary" asChild>
                <Link to="/register">Registrarse</Link>
              </Button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
