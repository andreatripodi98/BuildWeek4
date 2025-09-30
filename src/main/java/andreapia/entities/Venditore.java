package andreapia.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Venditore {
    @Id
    @GeneratedValue
    private UUID id;

    public Venditore() {
    }

    public UUID getId() {
        return id;
    }
}
