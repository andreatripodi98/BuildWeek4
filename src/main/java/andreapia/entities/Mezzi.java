package andreapia.entities;

import andreapia.enums.Capienza;
import andreapia.enums.StatoMezzo;
import andreapia.enums.TipoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mezzi")

public class Mezzi {
    @Id
    @GeneratedValue
    private UUID id_mezzi;

    @Enumerated(EnumType.STRING)
    private StatoMezzo stato;

    @Enumerated(EnumType.STRING)
    private Capienza capienza;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mezzo")
    private TipoMezzo tipoMezzo;

    @Column(name = "conto_biglietti_vidimati")
    private Long contoBigliettiVidimati;

    @OneToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;

    public Mezzi() {
    }

    public Mezzi(StatoMezzo stato, Capienza capienza, TipoMezzo tipoMezzo, Long contoBigliettiVidimati, Tratta tratta) {
        this.stato = stato;
        this.capienza = capienza;
        this.tipoMezzo = tipoMezzo;
        this.contoBigliettiVidimati = contoBigliettiVidimati;
        this.tratta = tratta;
    }


    public UUID getId() {
        return id_mezzi;
    }

    public StatoMezzo getStato() {
        return stato;
    }

    public void setStato(StatoMezzo stato) {
        this.stato = stato;
    }

    public Capienza getCapienza() {
        return capienza;
    }

    public void setCapienza(Capienza capienza) {
        this.capienza = capienza;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public Long getContoBigliettiVidimati() {
        return contoBigliettiVidimati;
    }

    public void setContoBigliettiVidimati(Long contoBigliettiVidimati) {
        this.contoBigliettiVidimati = contoBigliettiVidimati;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    // Aggiungi un toString() per debugging (opzionale)
    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id_mezzi +
                ", stato=" + stato +
                ", capienza=" + capienza +
                ", tipoMezzo=" + tipoMezzo +
                ", contoBigliettiVidimati=" + contoBigliettiVidimati +
                ", tratta=" + (tratta != null ? tratta.getId_tratta() : "null") +
                '}';
    }
}
