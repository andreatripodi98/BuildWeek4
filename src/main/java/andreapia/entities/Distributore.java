package andreapia.entities;

import andreapia.enums.StatoDistributore;
import andreapia.enums.TipoRivenditore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "distributore")
@PrimaryKeyJoinColumn(name = "id")
public class Distributore extends Venditore {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "stato", nullable = false)
    private StatoDistributore stato;

    public Distributore() {
    }

    public Distributore(TipoRivenditore tipoRivenditore, StatoDistributore stato) {
        super(tipoRivenditore);
        this.stato = stato;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public StatoDistributore getStato() {

        return stato;
    }

    @Override
    public String toString() {
        return "Distributore: " +
                " id= " + id +
                ", stato= " + stato;
    }

    public void setStato(StatoDistributore stato) {

        this.stato = stato;
    }
}
