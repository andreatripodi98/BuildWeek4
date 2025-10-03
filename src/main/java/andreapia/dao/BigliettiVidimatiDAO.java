package andreapia.dao;

import andreapia.entities.BigliettiVidimati;
import andreapia.entities.Mezzi;
import andreapia.entities.Ticket;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class BigliettiVidimatiDAO {
    private EntityManager em;

    public BigliettiVidimatiDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva biglietto vidimato
    public void saveBigliettiVidimati(Ticket biglietto, LocalDate dataVidimazione, Mezzi mezzo) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            BigliettiVidimati bigliettiVidimati1 = new BigliettiVidimati(biglietto, mezzo, dataVidimazione);
            em.persist(bigliettiVidimati1);
            t.commit();
            System.out.println("Biglietto " + biglietto.getId() + " vidimato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo biglietti vidimati by id
    public BigliettiVidimati findById(String id) {
        BigliettiVidimati vidimati = em.find(BigliettiVidimati.class, UUID.fromString(id));
        if (vidimati == null) {
            throw new NotFoundException(id);
        }
        System.out.println("Biglietto numero: " + vidimati.getId() + " trovato");
        return vidimati;
    }
}
