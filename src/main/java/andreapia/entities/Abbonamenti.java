package andreapia.entities;

import andreapia.enums.TipoAbbonamento;
import andreapia.enums.TipoRivenditore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
@PrimaryKeyJoinColumn(name = "id")
public class Abbonamenti extends Ticket {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_abbonamento", nullable = false)
    private TipoAbbonamento tipoAbbonamento;
    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataDiScadenza;

    protected Abbonamenti() {}

    public Abbonamenti(Venditore idVenditore, LocalDate dataEmissione, LocalDate dataScadenza, Utente utente, TipoAbbonamento tipoAbbonamento, LocalDate dataDiScadenza) {
        super(idVenditore, dataEmissione, dataScadenza, utente);
        this.tipoAbbonamento = tipoAbbonamento;
        this.dataDiScadenza = dataDiScadenza;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    @Override
    public String toString() {
        return "Abbonamenti{" +
                "tipoAbbonamento=" + tipoAbbonamento +
                ", dataDiScadenza=" + dataDiScadenza +
                "} " + super.toString();
    }
}
