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

    @OneToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    public Abbonamenti() {
    }

    public Abbonamenti(Venditore idVenditore, LocalDate dataEmissione, Utente utente, TipoAbbonamento tipoAbbonamento, LocalDate dataScadenza, Tessera tessera) {
        super(idVenditore, dataEmissione, utente);
        this.tipoAbbonamento = tipoAbbonamento;
        this.dataScadenza = dataScadenza;
        this.tessera = tessera;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamenti{" +
                "id=" + id +
                ", tipoAbbonamento=" + tipoAbbonamento +
                ", dataScadenza=" + dataScadenza +
                ", tessera=" + tessera +
                "} " + super.toString();
    }
}
