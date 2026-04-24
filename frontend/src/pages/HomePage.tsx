import { useData } from "@/contexts/AppContext";
import { Calendar } from "@/components/ui/calendar";
import { useState } from "react";

const HomePage = () => {
  const { usuario } = useData();
  const [date, setDate] = useState<Date | undefined>(new Date());
  const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;


  return (
    <div className="flex items-center justify-between gap-2 p-10">
      <h1>Bienvenido a la página de inicio</h1>
      {usuario ? (
        <p>
          Hola, {usuario.name}! Tu rol es:{" "}
          {usuario.rol.split("_").slice(1).join(" ")}.
        </p>
      ) : (
        <p>No has iniciado sesión.</p>
      )}

      <div className="flex flex-col items-center">
        <p>Selecciona una fecha:</p>

        <Calendar
          mode="single"
          selected={date}
          onSelect={setDate}
          className="rounded-lg border mt-4 mb-4"
          captionLayout="dropdown-years"
          timeZone={timeZone}
          ISOWeek={true}
        />

        <p>Fecha seleccionada: {date?.toLocaleDateString()}</p>
      </div>
    </div>
  );
};

export default HomePage;
