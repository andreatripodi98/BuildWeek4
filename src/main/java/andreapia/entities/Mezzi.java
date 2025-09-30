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
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatoMezzo stato;

    private LocalDate data_inizio_manutenzione;
    private LocalDate data_fine_manutenzione;

    @Enumerated(EnumType.STRING)
    private Capienza capienza;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_mezzo")
    private TipoMezzo tipoMezzo;

    @Column(name = "conto_biglietti_vidimati")
    private Long contoBigliettiVidimati;

    @OneToOne
    @JoinColumn(name = "id_tratta", referencedColumnName = "id")
    private Tratta tratta;


    public Mezzi() {
    }

    public Mezzi(StatoMezzo stato, LocalDate data_inizio_manutenzione, LocalDate data_fine_manutenzione, Capienza capienza, TipoMezzo tipoMezzo, Long contoBigliettiVidimati, Tratta tratta) {
        this.stato = stato;
        this.data_inizio_manutenzione = data_inizio_manutenzione;
        this.data_fine_manutenzione = data_fine_manutenzione;
        this.capienza = capienza;
        this.tipoMezzo = tipoMezzo;
        this.contoBigliettiVidimati = contoBigliettiVidimati;
        this.tratta = tratta;
    }


    public UUID getId() {
        return id;
    }

    public StatoMezzo getStato() {
        return stato;
    }

    public void setStato(StatoMezzo stato) {
        this.stato = stato;
    }

    public LocalDate getData_inizio_manutenzione() {
        return data_inizio_manutenzione;
    }

    public void setData_inizio_manutenzione(LocalDate data_inizio_manutenzione) {
        this.data_inizio_manutenzione = data_inizio_manutenzione;
    }

    public LocalDate getData_fine_manutenzione() {
        return data_fine_manutenzione;
    }

    public void setData_fine_manutenzione(LocalDate data_fine_manutenzione) {
        this.data_fine_manutenzione = data_fine_manutenzione;
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
                "id=" + id +
                ", stato=" + stato +
                ", data_inizio_manutenzione=" + data_inizio_manutenzione +
                ", data_fine_manutenzione=" + data_fine_manutenzione +
                ", capienza=" + capienza +
                ", tipoMezzo=" + tipoMezzo +
                ", contoBigliettiVidimati=" + contoBigliettiVidimati +
                ", tratta=" + (tratta != null ? tratta.getId() : "null") +
                '}';
    }
}