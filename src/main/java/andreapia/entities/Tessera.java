package andreapia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity

public class Tessera {
    @Id
    @GeneratedValue
    @Column(name = "id_tessera")
    private UUID idTessera;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;
    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToOne(mappedBy = "tessera")
    private Abbonamenti abbonamento;

    public Tessera() {

    }

    public Tessera(LocalDate dataEmissione, LocalDate dataScadenza, Utente utente) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
        this.utente = utente;
    }

    public UUID getIdTessera() {

        return idTessera;
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

    public Abbonamenti getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamenti abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "utente=" + utente +
                ", dataScadenza=" + dataScadenza +
                ", dataEmissione=" + dataEmissione +
                ", idTessera=" + idTessera +
                '}';
    }
}

