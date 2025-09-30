package andreapia.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rivenditore")
@PrimaryKeyJoinColumn(name = "id")
public class Rivenditore extends Venditore {

    protected Rivenditore() {}

}
