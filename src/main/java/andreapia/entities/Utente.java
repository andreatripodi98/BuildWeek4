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
    @Column(name = "nome_utente")
    private String nomeUtente;
    @Column(name = "cognome_utente")
    private String cognomeUtente;


    public Utente(TipoUtente tipoUtente, String nomeUtente, String cognomeUtente) {
        this.tipoUtente = tipoUtente;
        this.nomeUtente = nomeUtente;
        this.cognomeUtente = cognomeUtente;
    }

    public UUID getIdUtente() {
        return idUtente;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public TipoUtente getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(TipoUtente tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public String getCognomeUtente() {
        return cognomeUtente;
    }

    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }
}