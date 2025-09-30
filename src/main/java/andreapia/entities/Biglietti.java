package andreapia.entities;

import andreapia.enums.TipoRivenditore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
@PrimaryKeyJoinColumn(name = "id")
public class Biglietti extends Ticket {

    @Column(name = "stato", nullable = false)
    private boolean stato;

    protected Biglietti() {
    }

    public Biglietti(Venditore idVenditore, LocalDate dataEmissione, LocalDate dataScadenza, Utente utente, boolean stato) {
        super(idVenditore, dataEmissione, dataScadenza, utente);
        this.stato = stato;
    }

    public Biglietti(boolean stato) {
        this.stato = stato;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Biglietti{" +
                "stato=" + stato +
                "} " + super.toString();
    }
}

