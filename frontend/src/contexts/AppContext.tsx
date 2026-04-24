import { Rol } from "@/types/Rol";
import { createContext, useState, useContext } from "react";

type AppContextType = {
  usuario: UsuarioType | null;
  setUsuario: (u: UsuarioType | null) => void;
};

type UsuarioType = {
  name: string;
  email: string;
  rol: Rol;
};

const AppContext = createContext<AppContextType | undefined>(undefined);

export const AppContextProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [usuarioLogeado, setUsuarioLogeado] = useState<UsuarioType | null>(
    () => {
      const savedUser = localStorage.getItem("usuario");
      if (savedUser && savedUser !== "undefined") {
        try {
          const parsed = JSON.parse(savedUser);
          return {
            name: parsed.name || parsed.nombre || "",
            email: parsed.email || "",
            rol: parsed.rol || "ROLE_VISITANTE",
          };
        } catch (error) {
          console.error("Error parsing usuario from localStorage:", error);
          return null;
        }
      }
      return null;
    },
  );

  return (
    <AppContext.Provider
      value={{ usuario: usuarioLogeado, setUsuario: setUsuarioLogeado }}
    >
      {children}
    </AppContext.Provider>
  );
};

export const useData = () => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error("useData debe ser usado dentro de un AppContext.Provider");
  }
  return context;
};
