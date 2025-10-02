package andreapia.dao;

import andreapia.entities.Abbonamenti;
import andreapia.entities.Tessera;
import andreapia.entities.Utente;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
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
        if (tesseraFound == null) {
            throw new NotFoundException(id);
        }
        System.out.println("tessera trovata: " + tesseraFound.getIdTessera() + " " + tesseraFound.getUtente());
        return tesseraFound;
    }

    public Tessera findById(Utente id) {
        Tessera tesseraFound = em.find(Tessera.class, id);
        if (tesseraFound == null) {
            System.out.println("Errore nessuna tessera trovata!");
        }
        System.out.println("tessera trovata: " + tesseraFound.getIdTessera() + " " + tesseraFound.getUtente());
        return tesseraFound;
    }

    public void rinnovaTessera(String id, LocalDate nuovaScadenza) {
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();

            Tessera tessera = em.find(Tessera.class, UUID.fromString(id));
            if (tessera == null) {
                throw new NotFoundException(id);
            }

            tessera.setDataScadenza(nuovaScadenza);

            t.commit();

            System.out.println("La tessera " + tessera.getIdTessera() +
                    " è stata rinnovata con scadenza: " + tessera.getDataScadenza());
        } catch (Exception e) {
            System.out.println("Errore nel rinnovo tessera: " + e.getMessage());
        }
    }

    public void collegaAbbonamento(String idTessera, String idAbbonamento) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            Tessera tessera = em.find(Tessera.class, UUID.fromString(idTessera));
            if (tessera == null) throw new NotFoundException("Tessera non trovata: " + idTessera);

            Abbonamenti abbonamento = em.find(Abbonamenti.class, UUID.fromString(idAbbonamento));
            if (abbonamento == null) throw new NotFoundException("Abbonamento non trovato: " + idAbbonamento);

            tessera.setAbbonamento(abbonamento);
            abbonamento.setTessera(tessera);

            t.commit();
            System.out.println("Abbonamento " + abbonamento.getId() + " collegato a tessera " + tessera.getIdTessera());
        } catch (Exception e) {
            System.out.println("Errore nel collegamento dell'abbonamento: " + e.getMessage());
        }
    }

    public void scollegaAbbonamento(String idTessera) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            Tessera tessera = em.find(Tessera.class, UUID.fromString(idTessera));
            if (tessera == null) throw new NotFoundException("Tessera non trovata: " + idTessera);

            Abbonamenti abbonamento = tessera.getAbbonamento();
            if (abbonamento != null) {
                tessera.setAbbonamento(null);
                abbonamento.setTessera(null);
                System.out.println("Abbonamento " + abbonamento.getId() + " scollegato dalla tessera " + idTessera);
            } else {
                System.out.println("Nessun abbonamento collegato alla tessera " + idTessera);
            }

            t.commit();
        } catch (Exception e) {
            System.out.println("Errore nello scollegamento dell'abbonamento: " + e.getMessage());
        }
    }

    public boolean isAbbonamentoValido(String idTessera, LocalDate allaData) {
        Tessera tessera = em.find(Tessera.class, UUID.fromString(idTessera));
        if (tessera == null) throw new NotFoundException("Tessera non trovata: " + idTessera);

        Abbonamenti abbonamento = tessera.getAbbonamento();
        if (abbonamento == null || abbonamento.getDataScadenza() == null) return false;

        return !allaData.isAfter(abbonamento.getDataScadenza());
    }
}



