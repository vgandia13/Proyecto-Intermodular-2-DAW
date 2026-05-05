import { useParams } from "react-router-dom";
import { EventoService } from "../services/eventoService";
import { toast } from "sonner";
import { useEffect, useState } from "react";
import { EventoDTO } from "../types/EventoDTO";
import { Badge } from "@/components/ui/badge";

const EventPage = () => {
  const { id } = useParams<{ id: string }>();
  const [event, setEvent] = useState<EventoDTO | null>(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const loadEvent = async () => {
      if (!id) return;

      setLoading(true);
      try {
        const data = await EventoService.obtenerPorId(Number(id));
        setEvent(data);
      } catch (error) {
        console.error("Error al cargar el evento", error);
        toast.error("No se pudo cargar el evento.");
      } finally {
        setLoading(false);
      }
    };

    loadEvent();
  }, [id]);

  if (loading) return <div>Cargando evento...</div>;
  if (!event) return <div>Evento no encontrado.</div>;

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold">{event.nombre}</h1>
      <img src={event.imagenUrl} alt={event.nombre} className="w-full h-64 object-cover mt-4 rounded-lg" />
      <p className="mt-4">{event.descripcion}</p>
      <p className="mt-2 font-semibold">Ubicación: <Badge>{event.ubicacion}</Badge></p>
      <p className="mt-2">Fecha: {new Date(event.fecha).toLocaleDateString()}</p>
    </div>
  );
}

export default EventPage;
