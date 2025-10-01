package andreapia.dao;

import andreapia.entities.StoricoMezzi;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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
    // Metodo storico by id
    public StoricoMezzi findById(String id){
        StoricoMezzi storico = em.find(StoricoMezzi.class, UUID.fromString(id));
        if (storico == null){
            throw new NotFoundException("L'id inserito non è valido ");
        }
        System.out.println("Storico trovato ");
        return storico;
    }

}
