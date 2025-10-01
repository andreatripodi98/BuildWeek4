package andreapia.dao;

import andreapia.entities.BigliettiVidimati;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class BigliettiVidimatiDAO {
    private EntityManager em;

    public BigliettiVidimatiDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva biglietto vidimato
    public void saveBigliettiVidimati(BigliettiVidimati bigliettiVidimati) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(bigliettiVidimati);
            t.commit();
            System.out.println("Biglietto " + bigliettiVidimati.getId() + " vidimato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Metodo biglietti vidimati by id
    public BigliettiVidimati findById(String id){
        BigliettiVidimati vidimati = em.find(BigliettiVidimati.class, UUID.fromString(id));
        if ( vidimati == null){
            throw new NotFoundException(id);
        }
        System.out.println("Biglietto numero: " + vidimati.getId() + " trovato");
        return vidimati;
    }
}
