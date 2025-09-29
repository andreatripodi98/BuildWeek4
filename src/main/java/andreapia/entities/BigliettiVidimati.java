package andreapia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "biglietti_vidimati")
public class BigliettiVidimati {
    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate data_vidimazione;
    @ManyToOne
    @JoinColumn (name = "id_mezzo")
    private Mezzi mezzi;


    //COSTRUTTORI
    public BigliettiVidimati(){}

    public BigliettiVidimati(UUID id, LocalDate data_vidimazione) {
        this.id = id;
        this.data_vidimazione = data_vidimazione;
    }

    //METODI

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getData_vidimazione() {
        return data_vidimazione;
    }

    public void setData_vidimazione(LocalDate data_vidimazione) {
        this.data_vidimazione = data_vidimazione;
    }

    @Override
    public String toString() {
        return "BigliettiVidimati{" +
                "id=" + id +
                ", data_vidimazione=" + data_vidimazione +
                '}';
    }
}
