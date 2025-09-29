package andreapia.entities;

import andreapia.enums.TipoUtente;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "utenti")

public class Utente {
    @Id
    @GeneratedValue
    @Column(name = "id_utente")
    private UUID idUtente;
    @Enumerated(EnumType.STRING)
    private TipoUtente tipoUtente;

    @OneToOne
    @JoinColumn
    private Tessera tessera;


    public Utente( TipoUtente tipoUtente, Tessera tessera) {
       
        this.tipoUtente = tipoUtente;
        this.tessera = tessera;
    }

    public UUID getIdUtente() {
        return idUtente;
    }


    public TipoUtente getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(TipoUtente tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "idUtente=" + idUtente +
                ", tipoUtente=" + tipoUtente +
                '}';
    }
}