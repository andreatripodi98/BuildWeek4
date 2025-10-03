package andreapia.dao;

import andreapia.entities.*;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.UUID;

public class TicketDAO {
    private EntityManager em;

    public TicketDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva ticket
    public void saveTicket(Ticket ticket) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(ticket);
            t.commit();
            System.out.println("Ticket " + ticket.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //METODO PER RICERCA ABBONAMENTO TRAMITE ID
    public Abbonamenti findAbbonamentiById(String id) {
        Abbonamenti ticketFound = em.find(Abbonamenti.class, UUID.fromString(id));
        if (ticketFound == null) {
            throw new NotFoundException(id);
        }
        System.out.println("Ticket trovato: " + ticketFound.getId());
        return ticketFound;
    }
    //METODO PER NUMERO BIGLIETTI EMESSEI PER PERIODO DI TEMPO

    public Long bigliettiEmessiPerPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT (b) FROM Ticket b WHERE b.dataEmissione BETWEEN :dataInizio AND :dataFine", Long.class);
            query.setParameter("dataInizio", dataInizio);
            query.setParameter("dataFine", dataFine);
            System.out.println("sono stati trovati " + query.getSingleResult() + " biglietti emessi tra " + dataInizio + " e il " + dataFine);
            return query.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Long bigliettiVidimatiPerPeriodo(Mezzi mezzo, LocalDate dataInizio, LocalDate dataFine) {
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT (b) FROM BigliettiVidimati b WHERE b.id_mezzo = :mezzo AND b.data_vidimazione BETWEEN :dataInizio AND :dataFine", Long.class);
            query.setParameter("dataInizio", dataInizio);
            query.setParameter("dataFine", dataFine);
            query.setParameter("mezzo", mezzo);
            System.out.println("sono stati trovati " + query.getSingleResult() + " biglietti vidimati emessi tra " + dataInizio + " e il " + dataFine);
            return query.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    //METODO PER NUMERI BIGLIETTI EMESSI PER VENDITORE

    public Long bigliettiEmessiVenditore(Venditore rivenditore) {
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT (b) FROM Ticket b WHERE b.idVenditore = :idVenditore", Long.class);
            query.setParameter("idVenditore", rivenditore);
            System.out.println("il rivenditore " + rivenditore.getId() + " ha emesso " + query.getSingleResult() + " biglietti");
            return query.getSingleResult();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;

        }
    }

    public void setStatoBiglietto(Ticket id, boolean stato) {
        EntityTransaction t = em.getTransaction();
        t.begin();
//        TypedQuery<Biglietti> query = em.createQuery("SELECT COUNT (b) FROM Ticket b WHERE b.idVenditore = :idVenditore", Long.class);
        if (id instanceof Biglietti) {
            Biglietti biglietto = (Biglietti) id;
            ((Biglietti) id).setStato(true);
            em.merge(id);
        } else {
            System.out.println("Non è possibile impostare il nuovo stato");
        }
        t.commit();
    }

    public boolean getStatoBiglietto(Ticket id) {
        boolean statoBiglietto = false;
        if (id instanceof Biglietti) {
            Biglietti biglietti = (Biglietti) id;
            statoBiglietto = biglietti.isStato();
        } else {
            System.out.println("Non è possibile impostare il nuovo stato");
        }
        return statoBiglietto;
    }

    public LocalDate getDataScadenza(Ticket id) {
        LocalDate dataScadenza = null;
        if (id instanceof Abbonamenti) {
            Abbonamenti abbonamento = (Abbonamenti) id;
            dataScadenza = abbonamento.getDataScadenza();
        } else {
            System.out.println("Non è possibile impostare il nuovo stato");
        }
        return dataScadenza;
    }

    public void rinnovaAbbonamento(Ticket id) {
        EntityTransaction t = em.getTransaction();

        t.begin();
        if (id instanceof Abbonamenti) {
            Abbonamenti abbonamento = (Abbonamenti) id;
            ((Abbonamenti) id).setDataScadenza(LocalDate.now().plusYears(1));
        } else {
            System.out.println("Non è possibile rinnovare l'abbonamento");
        }
        t.commit();
    }
}

