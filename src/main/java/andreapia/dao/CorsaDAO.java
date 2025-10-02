package andreapia.dao;

import andreapia.entities.Corsa;
import andreapia.entities.Mezzi;
import andreapia.entities.Tratta;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalTime;
import java.util.UUID;

public class CorsaDAO {
    private EntityManager em;

    public CorsaDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva corsa
    public void saveCorsa(Corsa corsa) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(corsa);
            t.commit();
            System.out.println("Corsa " + corsa.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Corsa findById(UUID id) {

        Corsa found = em.find(Corsa.class, id);
        if (found == null) {
            throw new NotFoundException(id.toString());
        }
        return found;
    }

    //metodo per assegnare corsa
    public void assegnaCorsa(Tratta TrattaId, UUID mezzoId, LocalTime inizioCorsa, LocalTime fineCorsa) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
//            recuperiamo tratta con id passato nel parametro
            Tratta tratta = em.find(Tratta.class, TrattaId);
            // Recuperiamo il Mezzo
            Mezzi mezzo = em.find(Mezzi.class, mezzoId);
//            se la tratta esiste salva corsa con i dati passati
            if (tratta != null && mezzo != null) {
                Corsa corsa = new Corsa(tratta, mezzo, inizioCorsa, fineCorsa);
                em.persist(corsa);
                t.commit();
                System.out.println("corsa assegnata alla tratta: " + TrattaId);
//                altrimenti errore
            } else {
                if (tratta == null) {
                    System.out.println("Errore: Tratta con ID " + TrattaId + " non trovata.");
                }
                if (mezzo == null) {
                    System.out.println("Errore: Mezzo con ID " + mezzoId + " non trovato.");
                }
                t.rollback();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
