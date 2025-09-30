package andreapia.dao;

import andreapia.entities.Utente;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva utente
    public void saveUser(Utente utente) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(utente);
            t.commit();
            System.out.println("Utente " + utente.getNomeUtente() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo utente by ID
    public Utente findById(String id) {
        Utente userFound = em.find(Utente.class, UUID.fromString(id));
        System.out.println("Utente trovato: " + userFound.getNomeUtente() + " " + userFound.getCognomeUtente());
        if (userFound == null)
            throw new NotFoundException(id);
        return userFound;
        
    }
}
