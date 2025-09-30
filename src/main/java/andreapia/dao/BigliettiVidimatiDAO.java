package andreapia.dao;

import andreapia.entities.BigliettiVidimati;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
