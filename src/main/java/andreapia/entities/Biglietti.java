package andreapia.entities;

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
    private boolean stato;

    protected Biglietti() {}

    public Biglietti(TipoRivenditore tipoRivenditore,
                     UUID idVenditore,
                     LocalDate dataEmissione,
                     LocalDate dataScadenza,
                     TipoTicket tipoTicket,
                     UUID tipoTicketId,
                     Utente utente,
                     boolean stato) {
        super(tipoRivenditore, idVenditore, dataEmissione, dataScadenza, tipoTicket, tipoTicketId, utente);
        this.stato = stato;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}

