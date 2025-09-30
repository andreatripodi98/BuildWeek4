package andreapia.dao;

import andreapia.entities.StoricoMezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StoricoDAO {
    private EntityManager em;

    public StoricoDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva storico
    public void saveStorico(StoricoMezzi storicoMezzi) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(storicoMezzi);
            t.commit();
            System.out.println("Storico mezzo  " + storicoMezzi.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
