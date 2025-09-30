package andreapia.entities;

import andreapia.enums.TipoAbbonamento;
import andreapia.enums.TipoRivenditore;
import andreapia.enums.TipoTicket;
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

    protected Abbonamenti() {}

    public Abbonamenti(TipoRivenditore tipoRivenditore, Rivenditore idRivenditore, Distributore idDistributore, LocalDate dataEmissione, LocalDate dataScadenza, TipoTicket tipoTicket, UUID tipoTicketId, Utente utente, TipoAbbonamento tipoAbbonamento) {
        super(tipoRivenditore, idRivenditore, idDistributore, dataEmissione, dataScadenza, tipoTicket, tipoTicketId, utente);
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public TipoAbbonamento getTipoAbbonamento() { return tipoAbbonamento; }
    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) { this.tipoAbbonamento = tipoAbbonamento; }
}
