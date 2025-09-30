package andreapia.dao;

import andreapia.entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TesseraDAO {

    private EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva tessera
    public void saveTessera(Tessera tessera) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(tessera);
            t.commit();
            System.out.println(tessera.getUtente());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
