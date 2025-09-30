package andreapia.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "rivenditore")
@PrimaryKeyJoinColumn(name = "id")
public class Rivenditore extends Venditore {
    @Id
    @GeneratedValue
    private UUID id;

    public Rivenditore() {
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "id=" + id +
                '}';
    }
}
