package andreapia.entities;

import andreapia.enums.TipoRivenditore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Venditore {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tipo_rivenditore")
    private TipoRivenditore tipoRivenditore;

    public TipoRivenditore getTipoRivenditore() {
        return tipoRivenditore;
    }

    public void setTipoRivenditore(TipoRivenditore tipoRivenditore) {
        this.tipoRivenditore = tipoRivenditore;
    }

    public Venditore(TipoRivenditore tipoRivenditore) {
        this.tipoRivenditore = tipoRivenditore;
    }

    public Venditore() {
    }

    @Override
    public String toString() {
        return "Venditore{" +
                "id=" + id +
                ", tipoRivenditore=" + tipoRivenditore +
                '}';
    }

    public UUID getId() {
        return id;
    }

}
