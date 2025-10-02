package andreapia;

import andreapia.dao.*;
import andreapia.entities.*;
import andreapia.enums.StatoDistributore;
import andreapia.enums.TipoAbbonamento;
import andreapia.enums.TipoRivenditore;
import andreapia.enums.TipoUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
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
        Rivenditore rivenditore1 = new Rivenditore(TipoRivenditore.RIVENDITORE);
        Rivenditore rivenditore2 = new Rivenditore(TipoRivenditore.RIVENDITORE);
        Distributore distributore1 = new Distributore(TipoRivenditore.DISTRIBUTORE, StatoDistributore.ATTIVO);
        Distributore distributore2 = new Distributore(TipoRivenditore.DISTRIBUTORE, StatoDistributore.FUORI_SERVIZIO);
        venditoreDAO.saveVenditore(rivenditore1);
        venditoreDAO.saveVenditore(rivenditore2);
        venditoreDAO.saveVenditore(distributore1);
        venditoreDAO.saveVenditore(distributore2);


        boolean continua = true;

        //-----------------------------------INIZIO PROGRAMMA-------------------------------
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
                    break;
//-----------------------------------INSERIMENTO UTENTI-------------------------------
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
                        Tessera tessera1 = new Tessera(LocalDate.now(), LocalDate.now().plusYears(1), utente1);
                        tesseraDAO.saveTessera(tessera1);
                        System.out.println("utente creato " + utente1.getNomeUtente() + " " + utente1.getCognomeUtente());
                    } else {
                        System.out.println("scelta non valida");
                        continue;
                    }
                    break;
                case 2:
                    //-----------------------------------SCELTA UTENTE-------------------------------
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
                            //-----------------------------------OPZIONI UTENTE-------------------------------
                            System.out.println("Cosa vuoi fare?!");
                            System.out.println("premi 1 per acquistare un biglietto");
                            System.out.println("premi 2 per acquistare un abbonamento");
                            System.out.println("premi 3 per scegleire la tratta");
                            int scelta6 = scanner.nextInt();
                            switch (scelta6) {
                                case 1:
                                    //-----------------------------------ACQUISTA BIGLIETTO-------------------------------
                                    System.out.println("Da dove vuoi acquistare il biglietto?");
                                    System.out.println("Premi 1 per distributore automatico");
                                    System.out.println("Premi 2 per rivenditore autorizzato");
                                    int scelta7 = scanner.nextInt();
                                    //-----------------------------------DISTRIBUTORE-------------------------------
                                    if (scelta7 == 1) {
                                        List<Venditore> listaDistributori = venditoreDAO.findByTipoVenditore(TipoRivenditore.DISTRIBUTORE);
                                        int conteggio2 = 1;
                                        System.out.println("Seleziona il distributore");
                                        for (Venditore venditore : listaDistributori) {
                                            System.out.println(conteggio2 + ": " + venditore);
                                            conteggio2++;
                                        }
                                        int scelta8 = scanner.nextInt();
                                        Venditore distributoreScelto = listaDistributori.get(scelta8 - 1);
                                        System.out.println("Distributore selezionato: " + distributoreScelto);
                                        if (distributoreScelto instanceof Distributore) {
                                            Enum scelta9 = ((Distributore) distributoreScelto).getStato();
                                            if (scelta9 == StatoDistributore.FUORI_SERVIZIO) {
                                                System.out.println("Il distributore è fuori servizio! Non puoi acquistare il biglietto!");
                                                System.out.println("Seleziona un altro distributore");
                                            } else {
                                                Biglietti biglietto = new Biglietti(distributoreScelto, LocalDate.now(), utenteScelto, false);
                                                ticketDAO.saveTicket(biglietto);
                                                System.out.println("Biglietto acquistato: " + biglietto.getId());
                                            }
                                        }
                                    }
                                    //-----------------------------------VENDITORE-------------------------------
                                    else if (scelta7 == 2) {
                                        List<Venditore> listaRiuenditori = venditoreDAO.findByTipoVenditore(TipoRivenditore.RIVENDITORE);
                                        int conteggio3 = 1;
                                        System.out.println("Seleziona il venditore");
                                        for (Venditore venditore : listaRiuenditori) {
                                            System.out.println(conteggio3 + ": " + venditore);
                                            conteggio3++;
                                        }
                                        int scelta10 = scanner.nextInt();
                                        Venditore vendireScelto = listaRiuenditori.get(scelta10 - 1);
                                        System.out.println("Venditore selezionato: " + vendireScelto);
                                        Biglietti biglietto = new Biglietti(vendireScelto, LocalDate.now(), utenteScelto, false);
                                        ticketDAO.saveTicket(biglietto);
                                        System.out.println("Biglietto acquistato: " + biglietto.getId());
                                    } else {
                                        System.out.println("Scelta non valida scegli tra 1 e 2");
                                    }
                                    break;
                                case 2:
                                    //-----------------------------------ACQUISTA ABBONAMENTO-------------------------------
                                    System.out.println("Da dove vuoi acquistare l'abbonamento?");
                                    System.out.println("Premi 1 per distributore automatico");
                                    System.out.println("Premi 2 per rivenditore autorizzato");
                                    int scelta11 = scanner.nextInt();
                                    if (scelta11 == 1) {
                                        List<Venditore> listaDistributori = venditoreDAO.findByTipoVenditore(TipoRivenditore.DISTRIBUTORE);
                                        int conteggio = 1;
                                        System.out.println("Seleziona il distributore");
                                        for (Venditore venditore : listaDistributori) {
                                            System.out.println(conteggio + ": " + venditore);
                                            conteggio++;
                                        }
                                        int scelta12 = scanner.nextInt();
                                        Venditore distributoreScelto = listaDistributori.get(scelta12 - 1);
                                        System.out.println("Distributore selezionato: " + distributoreScelto);
                                        if (distributoreScelto instanceof Distributore) {
                                            Enum scelta13 = ((Distributore) distributoreScelto).getStato();
                                            if (scelta13 == StatoDistributore.FUORI_SERVIZIO) {
                                                System.out.println("Il distributore è fuori servizio! Non puoi acquistare il biglietto!");
                                                System.out.println("Seleziona un altro distributore");
                                            } else {
                                                System.out.println("Scegli il tipo di abbonamento");
                                                System.out.println("1 Abbonamento Mensile");
                                                System.out.println("2 Abbonamento Settimanale");
                                                int scelta14 = scanner.nextInt();
                                                if (scelta14 == 1) {
                                                    Abbonamenti abbonamento = new Abbonamenti(distributoreScelto, LocalDate.now(), utenteScelto, TipoAbbonamento.MENSILE, LocalDate.now().plusYears(1), utenteDAO.findTesseraByUtente(utenteScelto));
                                                    ticketDAO.saveTicket(abbonamento);
                                                    System.out.println("Abbonamento mensile acquistato: " + abbonamento.getId());
                                                } else if (scelta14 == 2) {
                                                    Abbonamenti abbonamento = new Abbonamenti(distributoreScelto, LocalDate.now(), utenteScelto, TipoAbbonamento.SETTIMANALE, LocalDate.now().plusYears(1), utenteDAO.findTesseraByUtente(utenteScelto));
                                                    ticketDAO.saveTicket(abbonamento);
                                                    System.out.println("Abbonamento settimanale acquistato: " + abbonamento.getId());
                                                } else {
                                                    System.out.println("Scelta non valida scegli tra 1 e 2");
                                                }
                                            }
                                        }

                                    } else if (scelta11 == 2) {
                                        List<Venditore> listaRiuenditori = venditoreDAO.findByTipoVenditore(TipoRivenditore.RIVENDITORE);
                                        int conteggio = 1;
                                        System.out.println("Seleziona il venditore");
                                        for (Venditore venditore : listaRiuenditori) {
                                            System.out.println(conteggio + ": " + venditore);
                                            conteggio++;
                                        }
                                        int scelta15 = scanner.nextInt();
                                        Venditore vendireScelto = listaRiuenditori.get(scelta15 - 1);
                                        System.out.println("Venditore selezionato: " + vendireScelto);
                                        System.out.println("Scegli il tipo di abbonamento");
                                        System.out.println("1 Abbonamento Mensile");
                                        System.out.println("2 Abbonamento Settimanale");
                                        int scelta14 = scanner.nextInt();
                                        if (scelta14 == 1) {
                                            Abbonamenti abbonamento = new Abbonamenti(vendireScelto, LocalDate.now(), utenteScelto, TipoAbbonamento.MENSILE, LocalDate.now().plusYears(1), utenteDAO.findTesseraByUtente(utenteScelto));
                                            ticketDAO.saveTicket(abbonamento);
                                            System.out.println("Abbonamento mensile acquistato: " + abbonamento.getId());
                                        } else if (scelta14 == 2) {
                                            Abbonamenti abbonamento = new Abbonamenti(vendireScelto, LocalDate.now(), utenteScelto, TipoAbbonamento.SETTIMANALE, LocalDate.now().plusYears(1), utenteDAO.findTesseraByUtente(utenteScelto));
                                            ticketDAO.saveTicket(abbonamento);
                                            System.out.println("Abbonamento settimanale acquistato: " + abbonamento.getId());
                                        } else {
                                            System.out.println("Scelta non valida scegli tra 1 e 2");
                                        }

                                    } else {
                                        System.out.println("Scelta non valida scegli tra 1 e 2");
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













