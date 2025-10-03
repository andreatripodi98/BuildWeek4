package andreapia.dao;

import andreapia.entities.Mezzi;
import andreapia.entities.StoricoMezzi;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
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
    public StoricoMezzi findById(String id) {
        StoricoMezzi storico = em.find(StoricoMezzi.class, UUID.fromString(id));
        if (storico == null) {
            throw new NotFoundException("L'id inserito non è valido ");
        }
        System.out.println("Storico trovato ");
        return storico;
    }

    //METODO PER STORICO DETERMINATO PERIODO
    public List<StoricoMezzi> storicoMezziPerPeriodo(Mezzi mezzo, LocalDate dataInizio, LocalDate dataFine) {
        try {
            TypedQuery<StoricoMezzi> query = em.createQuery("SELECT s FROM StoricoMezzi s WHERE s.dataInizioManutenzione BETWEEN :dataInizio AND :dataFine", StoricoMezzi.class);
            query.setParameter("dataInizio", dataInizio);
            query.setParameter("dataFine", dataFine);
            List<StoricoMezzi> listaStorici = query.getResultList();
            System.out.println("sono stati trovati " + listaStorici.size() + " storico tra " + dataInizio + " e il " + dataFine);
            System.out.println(listaStorici);
            return listaStorici;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
