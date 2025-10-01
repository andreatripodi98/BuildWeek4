package andreapia.dao;

import andreapia.entities.Abbonamenti;
import andreapia.entities.Ticket;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class TicketDAO {
    private EntityManager em;

    public TicketDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva ticket
    public void saveTicket(Ticket ticket) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(ticket);
            t.commit();
            System.out.println("Ticket " + ticket.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Abbonamenti findById(String id) {
        Abbonamenti ticketFound = em.find(Abbonamenti.class, UUID.fromString(id));
        if (ticketFound == null) {
            throw new NotFoundException(id);
        }
        System.out.println("Ticket trovato: " + ticketFound.getId());
        return ticketFound;
    }
}
