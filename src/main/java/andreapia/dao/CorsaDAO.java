package andreapia.dao;

import andreapia.entities.Corsa;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

    public Corsa findById(UUID id){

        Corsa found = em.find(Corsa.class, id);
        if(found == null) throw new NotFoundException(id.toString());
        return found;
    }
}
