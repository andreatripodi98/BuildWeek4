package andreapia.entities;

import andreapia.enums.TipoAbbonamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "abbonamenti")
@PrimaryKeyJoinColumn(name = "id")
public class Abbonamenti extends Ticket {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_abbonamento", nullable = false)
    private TipoAbbonamento tipoAbbonamento;
    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    public Abbonamenti() {
    }

    public Abbonamenti(Venditore idVenditore, LocalDate dataEmissione, LocalDate dataScadenza, Utente utente, TipoAbbonamento tipoAbbonamento) {
        super(idVenditore, dataEmissione, dataScadenza, utente);
        this.tipoAbbonamento = tipoAbbonamento;
        this.dataScadenza = dataScadenza;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public LocalDate getDataDiScadenza() {
        return dataScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataScadenza = dataDiScadenza;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Abbonamenti{" +
                "tipoAbbonamento=" + tipoAbbonamento +
                ", dataDiScadenza=" + dataScadenza +
                "} " + super.toString();
    }
}
