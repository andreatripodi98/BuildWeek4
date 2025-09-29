package andreapia.entities;

import andreapia.enums.StatoDistributore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "distributore")
public class Distributore {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoDistributore stato;

    protected Distributore() {}

    public Distributore(StatoDistributore stato) {
        this.stato = stato;
    }

    public UUID getId() { return id; }
    public StatoDistributore getStato() { return stato; }
    public void setStato(StatoDistributore stato) { this.stato = stato; }
}
