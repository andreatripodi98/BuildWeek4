package andreapia.entities;

import andreapia.enums.StatoDistributore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "distributore")
@PrimaryKeyJoinColumn(name = "id")
public class Distributore extends Venditore {

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoDistributore stato;

    protected Distributore() {}

    public Distributore(StatoDistributore stato) {
        this.stato = stato;
    }

    public StatoDistributore getStato() { return stato; }
    public void setStato(StatoDistributore stato) { this.stato = stato; }
}
