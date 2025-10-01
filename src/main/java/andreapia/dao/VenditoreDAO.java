package andreapia.dao;

import andreapia.entities.Venditore;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class VenditoreDAO {
    private EntityManager em;

    public VenditoreDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva utente
    public void saveVenditore(Venditore venditore) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(venditore);
            t.commit();
            System.out.println("Venditore " + venditore.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Venditore findVenditoreById(String id) {
        Venditore ticketFound = em.find(Venditore.class, UUID.fromString(id));
        System.out.println("Venditore trovato: " + ticketFound.getId());
        if (ticketFound == null)
            throw new NotFoundException(id);
        return ticketFound;

    }
}
