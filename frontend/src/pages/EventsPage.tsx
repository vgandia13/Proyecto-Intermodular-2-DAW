import { EventoService } from "../services/eventoService";
import { toast } from "sonner";
import { useEffect, useState } from "react";
import { EventoDTO } from "../types/EventoDTO";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  InputGroup,
  InputGroupInput,
  InputGroupButton,
} from "@/components/ui/input-group";
import { Button } from "@/components/ui/button";

const EventsPage = () => {
  const [events, setEvents] = useState<EventoDTO[]>([]);

  const loadEvents = async () => {
    try {
      const events: EventoDTO[] = await EventoService.getAll();

      setEvents(events);
    } catch (error) {
      console.error("Error de red al cargar eventos", error);
      toast.error(
        "No se pudieron cargar los eventos. Por favor, inténtalo de nuevo más tarde.",
      );
    }
  };

  useEffect(() => {
    loadEvents();
  }, []);

  return (
    <div className="container mx-auto px-4 py-8">
      <nav className="py-2 px-8">
        <InputGroup>
          <InputGroupInput placeholder="Buscar eventos" />
          <InputGroupButton asChild>
            <Button className="rounded-2xl p-3">Buscar</Button>
          </InputGroupButton>
        </InputGroup>
      </nav>
      <h1 className="text-3xl font-bold mb-8 text-center">
        Eventos Disponibles
      </h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {events.map((event) => (
          <Card
            key={event.id}
            className="overflow-hidden hover:shadow-lg transition-shadow duration-300"
          >
            <div className="aspect-video w-full overflow-hidden">
              <img
                src={event.imagenUrl}
                alt={event.nombre}
                className="w-full h-full object-cover"
              />
            </div>
            <CardHeader>
              <CardTitle className="text-xl">{event.nombre}</CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-muted-foreground line-clamp-3">
                {event.descripcion}
              </p>
            </CardContent>
            <CardFooter className="flex flex-col items-start gap-2 text-sm text-muted-foreground pt-4 border-t">
              <p>📍 {event.ubicacion}</p>
              <p>📅 {new Date(event.fecha).toLocaleDateString()}</p>
            </CardFooter>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default EventsPage;
