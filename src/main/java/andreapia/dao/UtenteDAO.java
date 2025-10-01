package andreapia.dao;

import andreapia.entities.Utente;
import andreapia.enums.TipoUtente;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
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
        if (userFound == null) {
            throw new NotFoundException(id);
        }
        System.out.println("Utente trovato: " + userFound.getNomeUtente() + " " + userFound.getCognomeUtente());
        return userFound;
    }

    public List<Utente> findByTipoUtente(TipoUtente tipo) {
        String listaTrovati = "SELECT u FROM Utente u WHERE u.tipoUtente = :tipo";

        TypedQuery<Utente> query = em.createQuery(listaTrovati, Utente.class);

        query.setParameter("tipo", tipo);

        return query.getResultList();
    }

}
