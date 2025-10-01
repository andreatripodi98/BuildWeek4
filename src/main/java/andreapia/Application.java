package andreapia;

import andreapia.dao.*;
import andreapia.entities.Distributore;
import andreapia.entities.Rivenditore;
import andreapia.entities.Utente;
import andreapia.entities.Venditore;
import andreapia.enums.StatoDistributore;
import andreapia.enums.TipoRivenditore;
import andreapia.enums.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
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
        Rivenditore rivenditore2 = new Rivenditore();
        Distributore distributore1 = new Distributore(StatoDistributore.ATTIVO);
        Distributore distributore2 = new Distributore(StatoDistributore.FUORI_SERVIZIO);
        venditoreDAO.saveVenditore(rivenditore1);
        venditoreDAO.saveVenditore(rivenditore2);
        venditoreDAO.saveVenditore(distributore1);
        venditoreDAO.saveVenditore(distributore2);


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
                    while (continua) {
                        System.out.println("scegli utente: ");
                        System.out.println("premi 1 per amministratore");
                        System.out.println("premi 2 per utente");
                        int scelta3 = scanner.nextInt();
                        if (scelta3 == 1) {
                            System.out.println("Quale amministratore sei?");
                            int conteggio = 1;
                            List<Utente> listaAmministratori = utenteDAO.findByTipoUtente(TipoUtente.AMMINISTRATORE);
                            for (Utente utente : listaAmministratori) {
                                System.out.println(conteggio + ": " + utente);
                                conteggio++;
                            }
                            int scelta4 = scanner.nextInt();
                            Utente amministratoreScelto = listaAmministratori.get(scelta4 - 1);
                            System.out.println("Admin selezionato: " + amministratoreScelto);
                            System.out.println("inserisci la password");
                            scanner.nextLine();
                            String password = scanner.nextLine();
                            if (password.equals("admin123")) {
                                System.out.println("controlla i biglietti venduti");
                            }
                        } else if (scelta3 == 2) {
                            int conteggio1 = 1;
                            System.out.println("Quale utente sei?");
                            List<Utente> listaUtenti = utenteDAO.findByTipoUtente(TipoUtente.UTENTE);
                            for (Utente utente : listaUtenti) {
                                System.out.println(conteggio1 + ": " + utente);
                                conteggio1++;
                            }
                            int scelta5 = scanner.nextInt();
                            Utente utenteScelto = listaUtenti.get(scelta5 - 1);
                            System.out.println("Utente selezionato: " + utenteScelto);
                            System.out.println("Cosa vuoi fare?!");
                            System.out.println("premi 1 per acquistare un biglietto");
                            System.out.println("premi 2 per acquistare un abbonamento");
                            System.out.println("premi 3 per acquistare una tessera");
                            System.out.println("premi 4 per scegleire la tratta");
                            int scelta6 = scanner.nextInt();
                            switch (scelta6) {
                                case 1:
                                    System.out.println("Da dove vuoi acquistare il biglietto?");
                                    System.out.println("Premi 1 per distributore automatico");
                                    System.out.println("Premi 2 per rivenditore autorizzato");
                                    int scelta7 = scanner.nextInt();
                                    if (scelta7 == 1) {
                                        List<Venditore> listaDistributori = venditoreDAO.findByTipoVenditore(TipoRivenditore.DISTRIBUTORE);
                                        int conteggio2 = 1;
                                        for (Venditore venditore : listaDistributori) {
                                            System.out.println(conteggio2 + ": " + listaDistributori);
                                            conteggio2++;
                                        }
                                        int scelta8 = scanner.nextInt();
                                        Venditore distributoreScelto = listaDistributori.get(scelta8 - 1);
                                        System.out.println("Distributore selezionato: " + distributoreScelto);


                                    } else if (scelta7 == 2) {
                                        List<Venditore> listaRiuenditori = venditoreDAO.findByTipoVenditore(TipoRivenditore.RIVENDITORE);
                                        int conteggio3 = 1;
                                        for (Venditore venditore : listaRiuenditori) {
                                            System.out.println(conteggio3 + ": " + venditore);
                                            conteggio3++;
                                        }
                                        break;
                                        //case 2:
                                        // break;
                                        // case 3:
                                        //  break;
                                        // case 4:
                                        //  break;
                                    }

                            }
                            break;
                        }
                    }
//           else if () {
//
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













