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

    @Column(name = "stato", nullable = false)
    private boolean stato;

    protected Biglietti() {}

    public Biglietti(boolean stato) {
        this.stato = stato;
    }

    public Biglietti(TipoRivenditore tipoRivenditore, Rivenditore idRivenditore, Distributore idDistributore, LocalDate dataEmissione, LocalDate dataScadenza, TipoTicket tipoTicket, UUID tipoTicketId, Utente utente, boolean stato) {
        super(tipoRivenditore, idRivenditore, idDistributore, dataEmissione, dataScadenza, tipoTicket, tipoTicketId, utente);
        this.stato = stato;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}

