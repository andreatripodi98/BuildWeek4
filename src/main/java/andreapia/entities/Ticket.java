package andreapia.entities;

import andreapia.enums.TipoRivenditore;
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

    @ManyToOne
    @JoinColumn (name="id_rivenditore")
    private Venditore idVenditore;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utente utente;

    protected Ticket() {}

    public Ticket(Venditore idVenditore, LocalDate dataEmissione, LocalDate dataScadenza, Utente utente) {
        this.idVenditore = idVenditore;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.utente = utente;
    }

    public UUID getId() {
        return id;
    }

    public Venditore getIdVenditore() {
        return idVenditore;
    }

    public void setIdVenditore(Venditore idVenditore) {
        this.idVenditore = idVenditore;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", idVenditore=" + idVenditore +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", utente=" + utente +
                '}';
    }
}