package andreapia;

import andreapia.dao.UtenteDAO;
import andreapia.entities.Utente;
import andreapia.enums.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw4pu");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);

        Utente utente1 = new Utente(TipoUtente.UTENTE, "Giacomo", "Pillitteri");

        utenteDAO.saveUser(utente1);

        utenteDAO.findById("6bbd654b-a2d8-4ed6-9b85-9bcb2f3a6385");
    }
}













