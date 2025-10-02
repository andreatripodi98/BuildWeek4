package andreapia.dao;

import andreapia.entities.Mezzi;
import andreapia.entities.Tratta;
import andreapia.enums.TipoMezzo;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva tratta
    public void saveTratta(Tratta tratta) {

        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(tratta);
            t.commit();
            System.out.println("Tratta " + tratta.getId_tratta() + " creata");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Tratta findById(UUID id_tratta) {
        Tratta found = em.find(Tratta.class, id_tratta);
        if (found == null) {
            throw new NotFoundException(id_tratta.toString());
        }
        return found;
    }

    public void assegnaTratta(Mezzi id, String zonaDiPartenza, String capolinea, double tempoPrevistoPercorrenza) {
        try {
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi mezzo = em.find(Mezzi.class, id);
            if (mezzo != null) {
                Tratta tratta = new Tratta(zonaDiPartenza, capolinea, tempoPrevistoPercorrenza, id);
                em.persist(tratta);
                t.commit();
                System.out.println("tratta assegnata al mezzo: " + id);
            } else System.out.println("errore");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    public List<Tratta> listaDiTratte(TipoMezzo tipoMezzo) {
        String listaTrovati = "SELECT t FROM Tratta t WHERE t.mezzi.tipoMezzo = :tipoMezzo";

        TypedQuery<Tratta> query = em.createQuery(listaTrovati, Tratta.class);

        query.setParameter("tipoMezzo", tipoMezzo);

        return query.getResultList();

    }
}
