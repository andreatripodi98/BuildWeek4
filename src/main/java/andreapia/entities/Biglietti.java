package andreapia.entities;

import andreapia.enums.StatoBiglietto;
import andreapia.enums.TipoRivenditore;
import andreapia.enums.TipoTicket;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
@PrimaryKeyJoinColumn(name = "id")
public class Biglietti extends Ticket {

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoBiglietto stato;

    protected Biglietti() {}

    public Biglietti(TipoRivenditore tipoRivenditore,
                     UUID idVenditore,
                     LocalDate dataEmissione,
                     LocalDate dataScadenza,
                     TipoTicket tipoTicket,
                     UUID tipoTicketId,
                     Utente utente,
                     StatoBiglietto stato) {
        super(tipoRivenditore, idVenditore, dataEmissione, dataScadenza, tipoTicket, tipoTicketId, utente);
        this.stato = stato;
    }

    public StatoBiglietto getStato() { return stato; }
    public void setStato(StatoBiglietto stato) { this.stato = stato; }
}

