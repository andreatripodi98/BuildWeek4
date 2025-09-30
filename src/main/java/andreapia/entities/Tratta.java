package andreapia.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue
    private UUID id_tratta;

    @Column(name = "zona_di_partenza", nullable = false)
    private String zona_di_partenza;

    @Column(name = "capolinea", nullable = false)
    private String capolinea;

    @Column(name = "tempo_previsto_di_percorrenza", nullable = false)
    private double tempo_previsto_di_percorrenza;

    public Tratta() {

    }

    public Tratta(String zona_di_partenza, String capolinea, double tempo_previsto_di_percorrenza) {
        this.zona_di_partenza = zona_di_partenza;
        this.capolinea = capolinea;
        this.tempo_previsto_di_percorrenza = tempo_previsto_di_percorrenza;
    }

    public UUID getId_tratta() {
        return id_tratta;
    }

    public String getZona_di_partenza() {
        return zona_di_partenza;
    }

    public void setZona_di_partenza(String zona_di_partenza) {
        this.zona_di_partenza = zona_di_partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public double getTempo_previsto_di_percorrenza() {
        return tempo_previsto_di_percorrenza;
    }

    public void setTempo_previsto_di_percorrenza(double tempo_previsto_di_percorrenza) {
        this.tempo_previsto_di_percorrenza = tempo_previsto_di_percorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id_tratta=" + id_tratta +
                ", zona_di_partenza='" + zona_di_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_previsto_di_percorrenza=" + tempo_previsto_di_percorrenza +
                '}';
    }
}
