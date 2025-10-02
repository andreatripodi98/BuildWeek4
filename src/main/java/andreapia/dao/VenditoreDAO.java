package andreapia.dao;

import andreapia.entities.Distributore;
import andreapia.entities.Venditore;
import andreapia.enums.StatoDistributore;
import andreapia.enums.TipoRivenditore;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class VenditoreDAO {
    private EntityManager em;

    public VenditoreDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva utente
    public void saveVenditore(Venditore venditore) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(venditore);
            t.commit();
            System.out.println("Venditore " + venditore.getId() + " creato");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo find by Id
    public Venditore findById(String id) {
        Venditore v = em.find(Venditore.class, UUID.fromString(id));
        if (v == null) {
            throw new NotFoundException(id);
        }
        System.out.println("Venditore num" + v.getId() + " trovato");
        return v;
    }

    //Metodo setState
    public void updateStatoDistributore(String id, String nuovoStatoDistributore) {
        EntityTransaction s = em.getTransaction();
        try {
            s.begin();

            Distributore d = em.find(Distributore.class, UUID.fromString(id));
            if (d == null) {
                throw new NotFoundException("Venditore non esistente");
            }
            if (nuovoStatoDistributore.equals("Attivo")) {
                d.setStato(StatoDistributore.ATTIVO);
            } else if (nuovoStatoDistributore.equals("Fuori Servizio")) {
                d.setStato(StatoDistributore.FUORI_SERVIZIO);
            }

        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Venditore> findByTipoVenditore(TipoRivenditore tipo) {
        String listaTrovati = "SELECT u FROM Venditore u WHERE u.tipoRivenditore = :tipo";

        TypedQuery<Venditore> query = em.createQuery(listaTrovati, Venditore.class);

        query.setParameter("tipo", tipo);

        return query.getResultList();
    }


    public List<Venditore> findAll() {
        TypedQuery<Venditore> query = em.createQuery("SELECT v FROM Venditore v ", Venditore.class);
        return query.getResultList();
    }

    public void getStatoDistributure(UUID idDistributore) {
        EntityTransaction s = em.getTransaction();
        try {
            s.begin();
            Distributore d = em.find(Distributore.class, idDistributore);
            if (d != null) {
                d.getStato();
            } else {
                System.out.println("Distributore non trovato.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
