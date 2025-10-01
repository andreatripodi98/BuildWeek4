package andreapia.dao;

import andreapia.entities.Mezzi;
import andreapia.entities.StoricoMezzi;
import andreapia.enums.StatoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.UUID;

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

    // Metodo findById
    public Mezzi findById(UUID id) {
        return em.find(Mezzi.class, id);
    }

    // Metodo setStatoMezzo passiamo l'id del mezzo e l'enum StatoMezzo
    public void setStatoMezzo(UUID mezzoId, StatoMezzo nuovoStato) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi mezzo = em.find(Mezzi.class, mezzoId);
            if (mezzo != null) {
                mezzo.setStato(nuovoStato);
                t.commit();
                System.out.println("Stato del mezzo " + mezzoId + " aggiornato a: " + nuovoStato);
            } else {
                System.out.println("Mezzo " + mezzoId + " non trovato!");
                t.rollback();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    // Metodo periodoManutenzione passiamo l'id del mezzo, la data inizio, la data fine, e la causa della manutenzione
    public void periodoManutenzione(UUID mezzoId, LocalDate inizio, LocalDate fine, String causa) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi mezzo = em.find(Mezzi.class, mezzoId);
            if (mezzo != null) {
                // Creazione record per lo storico mezzi
                StoricoMezzi storico = new StoricoMezzi(mezzo, inizio, fine, causa);
                em.persist(storico);
                // Assegnare lo stato in manutenzione
                mezzo.setStato(StatoMezzo.IN_MANUTENZIONE);
                t.commit();
                System.out.println("Periodo di manutenzione registrato per il mezzo " + mezzoId + " (" + inizio + " - " + fine + ") e stato aggiornato a IN_MANUTENZIONE.");
            } else {
                System.out.println("Mezzo con ID " + mezzoId + " non trovato.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Metodo contaBigliettiVidimati passiamo l'id del mezzo.
    // Facciamo la query dove facciamo la conta dei bigliettividimati dove l'id_mezzo (tabella BigliettiVidimati)
    // è uguale all'id del mezzo che abbiamo passato.
    public long contaBigliettiVidimati(UUID mezzoId) {
        try {
            // JPQL per contare i biglietti vidimati associati a un mezzo specifico
            Long conteggio = em.createQuery(
                            "SELECT COUNT(bv) FROM BigliettiVidimati bv WHERE bv.mezzo.id = :idMezzo", Long.class)
                    .setParameter("idMezzo", mezzoId)
                    .getSingleResult();
            System.out.println("Biglietti vidimati trovati per Mezzo " + mezzoId + ": " + conteggio);
            return conteggio;
        } catch (NoResultException e) {
            // Se non ci sono biglietti vidimati ritorniamo 0.
            return 0;
        } catch (Exception e) {
            System.out.println("Errore nel conteggio dei biglietti vidimati: " + e.getMessage());
            return -1;
        }
    }

    public long contaCorsePercorseDaMezzo(UUID mezzoId) {
        try {
            // Query per contare le corse di un mezzo tra le tabelle corsa e mezzo
            Long conteggio = em.createQuery(
                            "SELECT COUNT(c) FROM Corsa c WHERE c.mezzo.id = :idMezzo", Long.class)
                    .setParameter("idMezzo", mezzoId)
                    .getSingleResult();
            System.out.println("Numero totale di corse percorse dal Mezzo " + mezzoId + ": " + conteggio);
            return conteggio;
        } catch (NoResultException e) {
            // Se non ci sono corse per quel mezzo restituisce 0
            return 0;
        } catch (Exception e) {
            System.out.println("Errore nel conteggio delle corse: " + e.getMessage());
            return -1;
        }
    }

}
