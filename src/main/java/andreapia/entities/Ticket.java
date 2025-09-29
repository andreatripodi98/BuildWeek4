package andreapia.entities;

import andreapia.enums.TipoRivenditore;
import andreapia.enums.TipoTicket;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_venditore", nullable = false)
    private TipoRivenditore tipoRivenditore;

    // Punta a RIVENDITORE o DISTRIBUTORE a seconda di tipo_venditore
    @Column(name = "id_venditore", nullable = false)
    private UUID idVenditore;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ticket", nullable = false)
    private TipoTicket tipoTicket;

    // Punta a BIGLIETTO o ABBONAMENTI a seconda di tipo_ticket
    @Column(name = "tipo_ticket_id", nullable = false)
    private UUID tipoTicketId;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    protected Ticket() {}

    public Ticket(TipoRivenditore tipoRivenditore,
                  UUID idVenditore,
                  LocalDate dataEmissione,
                  LocalDate dataScadenza,
                  TipoTicket tipoTicket,
                  UUID tipoTicketId,
                  Utente utente) {
        this.tipoRivenditore = tipoRivenditore;
        this.idVenditore = idVenditore;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.tipoTicket = tipoTicket;
        this.tipoTicketId = tipoTicketId;
        this.utente = utente;
    }

    public UUID getId() { return id; }
    public TipoRivenditore getTipoRivenditore() { return tipoRivenditore; }
    public void setTipoRivenditore(TipoRivenditore tipoRivenditore) { this.tipoRivenditore = tipoRivenditore; }
    public UUID getIdVenditore() { return idVenditore; }
    public void setIdVenditore(UUID idVenditore) { this.idVenditore = idVenditore; }
    public LocalDate getDataEmissione() { return dataEmissione; }
    public void setDataEmissione(LocalDate dataEmissione) { this.dataEmissione = dataEmissione; }
    public LocalDate getDataScadenza() { return dataScadenza; }
    public void setDataScadenza(LocalDate dataScadenza) { this.dataScadenza = dataScadenza; }
    public TipoTicket getTipoTicket() { return tipoTicket; }
    public void setTipoTicket(TipoTicket tipoTicket) { this.tipoTicket = tipoTicket; }
    public UUID getTipoTicketId() { return tipoTicketId; }
    public void setTipoTicketId(UUID tipoTicketId) { this.tipoTicketId = tipoTicketId; }
    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }
}