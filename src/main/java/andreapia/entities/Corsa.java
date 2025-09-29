package andreapia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "corse")
public class Corsa {

    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzi id_mezzo;
    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta id_tratta;
    private LocalDate inizio_corsa;
    private LocalDate fine_corsa;


    //COSTRUTTORI
    public Corsa (){}

    public Corsa(LocalDate inizio_corsa, LocalDate fine_corsa) {
        this.inizio_corsa = inizio_corsa;
        this.fine_corsa = fine_corsa;
    }


    //METODI
    public LocalDate getFine_corsa() {
        return fine_corsa;
    }

    public void setFine_corsa(LocalDate fine_corsa) {
        this.fine_corsa = fine_corsa;
    }

    public LocalDate getInizio_corsa() {
        return inizio_corsa;
    }

    public void setInizio_corsa(LocalDate inizio_corsa) {
        this.inizio_corsa = inizio_corsa;
    }

    public Tratta getId_tratta() {
        return id_tratta;
    }

    public Mezzi getId_mezzo() {
        return id_mezzo;
    }


    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Corsa{" +
                "id=" + id +
                ", id_mezzo=" + id_mezzo +
                ", id_tratta=" + id_tratta +
                ", inizio_corsa=" + inizio_corsa +
                ", fine_corsa=" + fine_corsa +
                '}';
    }
}
