package andreapia.dao;

import andreapia.entities.Mezzi;
import andreapia.entities.Tratta;
import andreapia.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.UUID;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo salva utente
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
        if(found == null) throw new NotFoundException(id_tratta.toString());
        return found;
    }

public void  assegnaTratta(Mezzi id, String zonaDiPartenza, String capolinea, double tempoPrevistoPercorrenza){
        try{
            EntityTransaction t = em.getTransaction();
            t.begin();
            Mezzi mezzo = em.find(Mezzi.class, id);
            if(mezzo!= null){
                 Tratta tratta = new Tratta(zonaDiPartenza,capolinea,tempoPrevistoPercorrenza,id);
                 em.persist(tratta);
                 t.commit();
                System.out.println("tratta assegnata al mezzo: " + id);
            }else System.out.println("errore");
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

}
}
