package andreapia.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "tratte")
public class Tratta {
    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;
    private String zona_di_partenza;
    private String capolinea;
    private double tempo_previsto_di_percorrenza;
    @OneToMany(mappedBy = "id_tratta")
    private List<Corsa> corse = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "tratte")
    private Mezzi mezzi;

    //COSTRUTTORI
    public Tratta (){}

    public Tratta( String zona_di_partenza, String capolinea, double tempo_previsto_di_percorrenza) {
        this.zona_di_partenza = zona_di_partenza;
        this.capolinea = capolinea;
        this.tempo_previsto_di_percorrenza = tempo_previsto_di_percorrenza;
    }

    //METODI

    public UUID getId() {
        return id;
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
                "id=" + id +
                ", zona_di_partenza='" + zona_di_partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempo_previsto_di_percorrenza=" + tempo_previsto_di_percorrenza +
                ", corse=" + corse +
                ", mezzi=" + mezzi +
                '}';
    }
}
