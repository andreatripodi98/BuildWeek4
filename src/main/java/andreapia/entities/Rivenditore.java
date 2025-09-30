package andreapia.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rivenditore")
public class Rivenditore {

    @Id
    @GeneratedValue
    private UUID id_rivenditore;

    protected Rivenditore() {}

    public UUID getId() { return id_rivenditore; }
}
