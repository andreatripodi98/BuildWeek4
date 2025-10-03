package andreapia;

import andreapia.dao.*;
import andreapia.entities.*;
import andreapia.enums.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
       Mezzi mezzo1 = new Mezzi(StatoMezzo.IN_SERVIZIO, Capienza.AUTOBUS, TipoMezzo.AUTOBUS);
        mezziDAO.saveMezzo(mezzo1);
        Mezzi mezzo2 = new Mezzi(StatoMezzo.IN_SERVIZIO, Capienza.TRAM, TipoMezzo.TRAM);
        mezziDAO.saveMezzo(mezzo2);
//        Tratta tratta1 = new Tratta("Termini", "Cinecittà", 1, mezzo1);
//        trattaDAO.saveTratta(tratta1);
//        Tratta tratta2 = new Tratta("Termini", "San Pietro", 2, mezzo2);
//        Corsa corsa1 = new Corsa(tratta1, mezzo1, LocalTime.now(), LocalTime.now().plusHours(2));
//        corsaDAO.saveCorsa(corsa1);
//        trattaDAO.saveTratta(tratta2);
//        Utente utenteFromDb = utenteDAO.findById("357e4dc1-5070-4bf6-ae5c-b4ddc750a7ec");
//        Tessera tesseraFromDb = tesseraDAO.findById("6ed61ce2-b08a-4d23-bcbd-499bbb4ab04d");
//        Abbonamenti abbonamentoScaduto = new Abbonamenti(rivenditore1, LocalDate.now(), utenteFromDb, TipoAbbonamento.MENSILE, LocalDate.of(2025, 10, 1), tesseraFromDb);
//        ticketDAO.saveTicket(abbonamentoScaduto);
//        System.out.println(abbonamentoScaduto);


        boolean continua = true;

        //-----------------------------------INIZIO PROGRAMMA-------------------------------
        while (continua) {
            System.out.println("Cosa vuoi fare?");
            System.out.println("Premi 1 per creare un utente");
            System.out.println("Premi 2 per continuare");
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

                        //-----------------------------------SCELTA AMMINISTRATORE-------------------------------
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
                                System.out.println("1 controlla i biglietti venduti da un punto di emissione specifico");
                                System.out.println("2 controlla i biglietti venduti in un determinato periodo di tempo");
                                System.out.println("3 Seleziona mezzo");
                                int scelta18 = scanner.nextInt();
                                //-----------------------------------Controlla i biglietti/abbonamenti venduti da un punto di emissione specifico-------------------------------
                                if (scelta18 == 1) {
                                    System.out.println("scegli il punto di emissione");
                                    List<Venditore> listaVenditori = venditoreDAO.findAll();
                                    int conteggio3 = 1;
                                    for (Venditore venditore : listaVenditori) {
                                        System.out.println(conteggio3 + ": " + venditore);
                                        conteggio3++;
                                    }

                                    int scelta19 = scanner.nextInt();
                                    Venditore venditoreScelto = listaVenditori.get(scelta19 - 1);
                                    ticketDAO.bigliettiEmessiVenditore(venditoreScelto);

                                    //-----------------------------------Controlla i biglietti/abbonamenti venduti in un determinato periodo di tempo-------------------------------
                                } else if (scelta18 == 2) {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                    System.out.println("Inserisci data inizio (formato GG/MM/AAAA, es. 02/10/2025):");
                                    String dataInizioStringa = scanner.next();

                                    LocalDate dataInizio = LocalDate.parse(dataInizioStringa, formatter);

                                    System.out.println("Data di inizio salvata come LocalDate: " + dataInizio);

                                    System.out.println("Inserisci data fine (formato GG/MM/AAAA, es. 02/10/2025):");
                                    String dataFineStringa = scanner.next();

                                    LocalDate dataFine = LocalDate.parse(dataFineStringa, formatter);

                                    System.out.println("Data di inizio salvata come LocalDate: " + dataFine);

                                    ticketDAO.bigliettiEmessiPerPeriodo(dataInizio, dataFine);
                                    //-----------------------------------SCEGLI MEZZO-------------------------------
                                } else if (scelta18 == 3) {
                                    List<Mezzi> listaMezzi = mezziDAO.findAll();
                                    int conteggio5 = 1;
                                    System.out.println("Seleziona il mezzo");
                                    for (Mezzi mezzo : listaMezzi) {
                                        System.out.println(conteggio5 + ": " + mezzo);
                                        conteggio5++;
                                    }
                                    int scelta21 = scanner.nextInt();
                                    Mezzi mezzoScelto = listaMezzi.get(scelta21 - 1);
                                    System.out.println("Il mezzo selezionato è: " + mezzoScelto);
                                    System.out.println("Cosa vuoi fare?");
                                    System.out.println("1 Setta lo stato del mezzo(IN MANUTENZIONE)");
                                    System.out.println("2 Assegna mezzo a  tratta");
                                    System.out.println("3 Controlla i periodi di manutenzione");
                                    System.out.println("4 Controlla biglietti vidimati sul mezzo");
                                    System.out.println("5 Controlla biglietti vidimati sul mezzo per periodo di tempo");
                                    System.out.println("6 Controlla i numero totale delle corse percorse per mezzo ");
                                    System.out.println("7 tempo effettivo di percorrenza delle corse del singolo mezzo ");
                                    System.out.println("8 tempo medio effettivo di percorenza di una determinata tratta da parte di un mezzo specifico");
                                    int scelta22 = scanner.nextInt();
                                    switch (scelta22) {
                                        //-----------------------------------SET STATO MEZZO IN MANUTENZIONE-------------------------------
                                        case 1:
                                            LocalDate dataInizio = LocalDate.now();
                                            LocalDate dataFine = LocalDate.now().plusMonths(3);
                                            mezziDAO.periodoManutenzione(mezzoScelto.getId(), dataInizio, dataFine, "Problemi al motore");
                                            break;
                                        case 2:
                                            //---------------------------------ASSEGNA MEZZO A TRATTA--------------------------------
                                            System.out.println("Assegna zona di partenza");
                                            scanner.nextLine();
                                            String zonaDiPartenza = scanner.nextLine();
                                            System.out.println("Assegna capolinea");
                                            String capolinea = scanner.nextLine();
                                            trattaDAO.assegnaTratta(mezzoScelto.getId(), zonaDiPartenza, capolinea, 1.5);
                                            System.out.println("tratta assegnata al mezzo " + mezzoScelto.getId() + " partenza " + zonaDiPartenza + " capolinea " + capolinea);
                                            break;
                                        case 3:
                                            //-----------------------------------CONTROLLA PERIODI MANUTENZIONE-------------------------------

                                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                            System.out.println("Inserisci data inizio (formato GG/MM/AAAA, es. 02/10/2025):");
                                            String dataInizioStringa = scanner.next();

                                            LocalDate dataInizioManutenzione = LocalDate.parse(dataInizioStringa, formatter);

                                            System.out.println("Data di inizio salvata come LocalDate: " + dataInizioManutenzione);

                                            System.out.println("Inserisci data fine (formato GG/MM/AAAA, es. 02/10/2025):");
                                            String dataFineStringa = scanner.next();

                                            LocalDate dataFineManutenzione = LocalDate.parse(dataFineStringa, formatter);

                                            System.out.println("Data di fine salvata come LocalDate: " + dataFineManutenzione);
                                            storicoDAO.storicoMezziPerPeriodo(mezzoScelto, dataInizioManutenzione, dataFineManutenzione);
                                            break;
                                        case 4:
                                            mezziDAO.contaBigliettiVidimati(mezzoScelto);
                                            break;
                                        case 5:
                                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                            System.out.println("Inserisci data inizio (formato GG/MM/AAAA, es. 02/10/2025):");
                                            String dataInizioStringa1 = scanner.next();
                                            LocalDate dataVidimazione = LocalDate.parse(dataInizioStringa1, formatter1);
                                            System.out.println("Inserisci data fine (formato GG/MM/AAAA, es. 02/10/2025):");
                                            String dataFineStringa1 = scanner.next();
                                            LocalDate dataFineVidimazione = LocalDate.parse(dataFineStringa1, formatter1);
                                            ticketDAO.bigliettiVidimatiPerPeriodo(mezzoScelto, dataVidimazione, dataFineVidimazione);
                                            break;
                                        case 6:
                                            mezziDAO.contaCorsePercorseDaMezzo(mezzoScelto);
                                            break;
                                        case 7:

                                            break;
                                        case 8:

                                            break;
                                    }

                                }

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
                            System.out.println("premi 3 se hai gia un biglietto o un abbonamento");
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
                                    //-----------------------------------ACQUISTA ABBONAMENTO DISTRIBUTORE-------------------------------
                                    if (scelta11 == 1) {
                                        List<Venditore> listaDistributori = venditoreDAO.findByTipoVenditore(TipoRivenditore.DISTRIBUTORE);
                                        int conteggio8 = 1;
                                        System.out.println("Seleziona il distributore");
                                        for (Venditore venditore : listaDistributori) {
                                            System.out.println(conteggio8 + ": " + venditore);
                                            conteggio8++;
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
                                        //-----------------------------------ACQUISTA ABBONAMENTO RIVENDITORE-------------------------------
                                    } else if (scelta11 == 2) {
                                        List<Venditore> listaRiuenditori = venditoreDAO.findByTipoVenditore(TipoRivenditore.RIVENDITORE);
                                        int conteggio9 = 1;
                                        System.out.println("Seleziona il venditore");
                                        for (Venditore venditore : listaRiuenditori) {
                                            System.out.println(conteggio9 + ": " + venditore);
                                            conteggio9++;
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
                                    break;
                                case 3:
                                    break;


                            }
                            //-----------------------------------SCEGLI IL MEZZO-------------------------------

                            System.out.println("Scegli il mezzo");
                            System.out.println("1 Autobus");
                            System.out.println("2 Tram");
                            int scelta17 = scanner.nextInt();
                            Tratta trattaScelta = null;
                            if (scelta17 == 1) {
                                List<Tratta> listaTratte = trattaDAO.listaDiTratte(TipoMezzo.AUTOBUS);
                                int conteggio10 = 1;
                                //-----------------------------------SCEGLI TRATTA-------------------------------

                                System.out.println("Seleziona la tratta ");
                                for (Tratta tratta : listaTratte) {
                                    System.out.println(conteggio10 + ": " + tratta.getZona_di_partenza() + " " + tratta.getCapolinea());
                                    conteggio10++;
                                }
                                int scelta16 = scanner.nextInt();
                                trattaScelta = listaTratte.get(scelta16 - 1);
                                System.out.println("Tratta selezionata: " + trattaScelta.getZona_di_partenza() + " " + trattaScelta.getCapolinea());


                            } else if (scelta17 == 2) {
                                List<Tratta> listaTratte = trattaDAO.listaDiTratte(TipoMezzo.TRAM);
                                int conteggio11 = 1;
                                System.out.println("Seleziona la tratta ");
                                for (Tratta tratta : listaTratte) {
                                    System.out.println(conteggio11 + ": " + tratta.getZona_di_partenza() + " - " + tratta.getCapolinea());
                                    conteggio11++;
                                }
                                int scelta16 = scanner.nextInt();
                                trattaScelta = listaTratte.get(scelta16 - 1);
                                System.out.println("Tratta selezionata: " + trattaScelta.getZona_di_partenza() + " - " + trattaScelta.getCapolinea());
                            }
                            List<Ticket> listaTicket = utenteDAO.findTicketByUtente(utenteScelto);
                            if (!listaTicket.isEmpty()) {
                                if (listaTicket.stream().anyMatch(t -> t instanceof Abbonamenti)) {
                                    System.out.println("Hai un abbonamento: " + listaTicket.getFirst());
                                    Ticket abbonamentoSalvato = listaTicket.getFirst();
                                    LocalDate dataDiScadenza = ticketDAO.getDataScadenza(abbonamentoSalvato);
                                    System.out.println(dataDiScadenza);
                                    LocalDate dataOdierna = LocalDate.now();
                                    if (dataDiScadenza != null && dataDiScadenza.isBefore(dataOdierna)) {
                                        System.out.println("Abbonamento scaduto in data: " + dataDiScadenza);
                                        System.out.println("Rinnovo effettuato");
                                        ticketDAO.rinnovaAbbonamento(abbonamentoSalvato);
                                    } else {
                                        System.out.println("Hai l'abbonamento e puoi salire nel mezzo");
                                    }
                                } else if (listaTicket.stream().anyMatch(t -> t instanceof Biglietti)) {
                                    System.out.println("Hai un biglietto: " + listaTicket.getFirst());
                                    Ticket bigliettoSalvato = listaTicket.getFirst();

                                    if (ticketDAO.getStatoBiglietto(bigliettoSalvato) == true) {
                                        System.out.println("non puoi salire il biglietto è gia stato vidimato");

                                    } else {
                                        Mezzi mezzoScelto1 = trattaDAO.mezzi(trattaScelta);
                                        ticketDAO.setStatoBiglietto(bigliettoSalvato, true);
                                        bigliettiVidimatiDAO.saveBigliettiVidimati(bigliettoSalvato, LocalDate.now(), mezzoScelto1 );
                                        System.out.println("Biglietto validato e puoi salire nel mezzo");

                                    }
                                }
                            } else {
                                System.out.println("l'utente non ha biglietti");
                            }
                        }

                        break;

                    }
            }
        }
    }
}

















