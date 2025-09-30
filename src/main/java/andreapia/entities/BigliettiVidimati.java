package andreapia.entities;

import andreapia.enums.TipoRivenditore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietti_vidimati")
public class BigliettiVidimati {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn (name = "id_biglietti", nullable = false)
    private Biglietti id_biglietto;

    @ManyToOne
    @JoinColumn(name = "id_mezzi")
    private Mezzi id_mezzo;

    @Column(name = "data_vidimazione", nullable = false)
    private LocalDate data_vidimazione;

    public BigliettiVidimati(){
    }

    public BigliettiVidimati(Biglietti id_biglietto, Mezzi id_mezzo, LocalDate data_vidimazione) {
        this.id_biglietto = id_biglietto;
        this.id_mezzo = id_mezzo;
        this.data_vidimazione = data_vidimazione;
    }

    public UUID getId() {
        return id;
    }

    public Biglietti getId_biglietto() {
        return id_biglietto;
    }

    public void setId_biglietto(Biglietti id_biglietto) {
        this.id_biglietto = id_biglietto;
    }

    public Mezzi getId_mezzo() {
        return id_mezzo;
    }

    public void setId_mezzo(Mezzi id_mezzo) {
        this.id_mezzo = id_mezzo;
    }

    public LocalDate getData_vidimazione() {
        return data_vidimazione;
    }

    public void setData_vidimazione(LocalDate data_vidimazione) {
        this.data_vidimazione = data_vidimazione;
    }
}
