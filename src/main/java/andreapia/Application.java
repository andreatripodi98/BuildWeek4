package andreapia;

import andreapia.dao.*;
import andreapia.entities.Rivenditore;
import andreapia.entities.Utente;
import andreapia.enums.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw4pu");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        VenditoreDAO venditoreDAO = new VenditoreDAO(em);
        BigliettiVidimatiDAO bigliettiVidimatiDAO = new BigliettiVidimatiDAO(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        StoricoDAO storicoDAO = new StoricoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        CorsaDAO corsaDAO = new CorsaDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);

        Rivenditore rivenditore1 = new Rivenditore();
        venditoreDAO.saveVenditore(rivenditore1);
        boolean continua = true;

        //-----------------------------------TEST INSERIMENTO UTENTI-------------------------------
        while (continua) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("Premi 1 per creare un utente");
            System.out.println("Premi 2 per continuare");//OKKKKKKK
            System.out.println("Premi 0 per uscire");
            int scelta = scanner.nextInt();
            switch (scelta) {
                case 0:
                    continua = false;
                    System.out.println("programma terminato");

                case 1:
                    System.out.println("inserisci nome");
                    scanner.nextLine();
                    String nomeUtente = scanner.nextLine();
                    System.out.println("inserisci cognome");

                    String cognomeUtente = scanner.nextLine();
                    System.out.println("sei amministratore o utente normale?");
                    System.out.println("premi 1 per amministatore");
                    System.out.println("premi 2 per utente");
                    int scelta2 = scanner.nextInt();
                    if (scelta2 == 1) {

                        Utente utente1 = new Utente(TipoUtente.AMMINISTRATORE, nomeUtente, cognomeUtente);
                        utenteDAO.saveUser(utente1);

                        System.out.println("amministratore creato " + utente1.getNomeUtente() + " " + utente1.getCognomeUtente());

                    } else if (scelta2 == 2) {

                        Utente utente1 = new Utente(TipoUtente.UTENTE, nomeUtente, cognomeUtente);
                        utenteDAO.saveUser(utente1);
                        System.out.println("utente creato " + utente1.getNomeUtente() + " " + utente1.getCognomeUtente());
                    } else {
                        System.out.println("scelta non valida");
                        continue;
                    }
                    break;
                case 2:
                    break;


            }
        }
        while (continua) {
            System.out.println("scegli utente: ");
            System.out.println("premi 1 per amministratore");
            System.out.println("premi 2 per utente");
            int scelta3 = scanner.nextInt();
            if(scelta3 == 1){
                System.out.println("inserisci la password");
                scanner.nextLine();
                String password = scanner.nextLine();
                if (password.equals("admin123")){
                    System.out.println("controlla i biglietti venduti");
                }
            } else if () {

            }
        }


//        utenteDAO.saveUser(utente1);

//        Utente utenteFromDB = utenteDAO.findById("cbba4a94-d32c-4916-aa3b-11503e401d1b");
//
//
//        Rivenditore rivenditore1 = new Rivenditore();
//      venditoreDAO.saveVenditore(rivenditore1);
//
//
//        Abbonamenti abbonamenti1 = new Abbonamenti(rivenditore1, LocalDate.now(), LocalDate.now().plusYears(1), utente1, TipoAbbonamento.MENSILE);
//        System.out.println(abbonamenti1);
//       ticketDAO.saveTicket(abbonamenti1);
//        Abbonamenti abbonamentoFromDb = ticketDAO.findById("9eb31650-8f00-4fb6-91cf-ce3df8398cf3");
//        Tessera tessera1 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1), utenteFromDB, abbonamentoFromDb);
//        tesseraDAO.saveTessera(tessera1);
//
//
    }
}













