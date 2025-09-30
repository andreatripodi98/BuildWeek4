package andreapia.dao;

import andreapia.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva utente
    public void saveTratta(Tratta tratta) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(tratta);
            t.commit();
            System.out.println("Tratta " + tratta.getId_tratta() + " creata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
