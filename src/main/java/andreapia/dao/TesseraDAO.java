package andreapia.dao;

import andreapia.entities.Tessera;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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

    public Tessera findById(String id) {
        Tessera tesseraFound = em.find(Tessera.class, UUID.fromString(id));
        System.out.println("tessera trovata: " + tesseraFound.getIdTessera() + " " + tesseraFound.getUtente());
        if (tesseraFound == null)
            throw new NotFoundException(id);
        return tesseraFound;
    }
}
