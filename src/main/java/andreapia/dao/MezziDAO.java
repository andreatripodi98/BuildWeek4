package andreapia.dao;

import andreapia.entities.Mezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezziDAO {
    private EntityManager em;

    public MezziDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva mezzo
    public void saveMezzo(Mezzi mezzo) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(mezzo);
            t.commit();
            System.out.println("Mezzo " + mezzo.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
