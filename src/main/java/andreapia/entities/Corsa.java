package andreapia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "corsa")
public class Corsa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "id_mezzo", nullable = false)
    private UUID id_mezzo;

    @ManyToOne
    @JoinColumn (name="id_tratta")
    private Tratta id_tratta;

    @Column(name = "inizio_corsa", nullable = false)
    private LocalDate inizio_corsa;

    @Column(name = "fine_corsa", nullable = false)
    private LocalDate fine_corsa;



public Corsa (){

}

    public Corsa(UUID id, UUID id_mezzo, Tratta id_tratta, LocalDate inizio_corsa, LocalDate fine_corsa) {
        this.id = id;
        this.id_mezzo = id_mezzo;
        this.id_tratta = id_tratta;
        this.inizio_corsa = inizio_corsa;
        this.fine_corsa = fine_corsa;
    }

    public UUID getId() {
        return id;
    }

    public UUID getId_mezzo() {
        return id_mezzo;
    }

    public Tratta getId_tratta() {
        return id_tratta;
    }

    public LocalDate getInizio_corsa() {
        return inizio_corsa;
    }

    public LocalDate getFine_corsa() {
        return fine_corsa;
    }

    public void setId_mezzo(UUID id_mezzo) {
        this.id_mezzo = id_mezzo;
    }

    public void setId_tratta(Tratta id_tratta) {
        this.id_tratta = id_tratta;
    }

    public void setInizio_corsa(LocalDate inizio_corsa) {
        this.inizio_corsa = inizio_corsa;
    }

    public void setFine_corsa(LocalDate fine_corsa) {
        this.fine_corsa = fine_corsa;
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
