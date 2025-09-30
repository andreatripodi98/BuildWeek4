package andreapia.dao;

import andreapia.entities.Corsa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
