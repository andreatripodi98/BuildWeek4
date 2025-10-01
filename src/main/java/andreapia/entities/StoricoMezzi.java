package andreapia.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class StoricoMezzi {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn (name="id_mezzo")
    private Mezzi mezzo;

    @Column(name = "data_inizio_manutenzione")
    private LocalDate dataInizioManutenzione;
    @Column(name = "data_fine_manutenzione")
    private LocalDate dataFineManutenzione;
    private String causaManutenzione;

    public StoricoMezzi() {
    }

    public StoricoMezzi(Mezzi mezzo, LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, String causaManutenzione) {
        this.mezzo = mezzo;
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
        this.causaManutenzione = causaManutenzione;
    }

    public UUID getId() {
        return id;
    }

    public Mezzi getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzi mezzo) {
        this.mezzo = mezzo;
    }

    public LocalDate getDataInizioManutenzione() {
        return dataInizioManutenzione;
    }

    public void setDataInizioManutenzione(LocalDate dataInizioManutenzione) {
        this.dataInizioManutenzione = dataInizioManutenzione;
    }

    public LocalDate getDataFineManutenzione() {
        return dataFineManutenzione;
    }

    public void setDataFineManutenzione(LocalDate dataFineManutenzione) {
        this.dataFineManutenzione = dataFineManutenzione;
    }

    public String getCausaManutenzione() {
        return causaManutenzione;
    }

    public void setCausaManutenzione(String causaManutenzione) {
        this.causaManutenzione = causaManutenzione;
    }

    @Override
    public String toString() {
        return "StoricoMezzi{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", dataInizioManutenzione=" + dataInizioManutenzione +
                ", dataFineManutenzione=" + dataFineManutenzione +
                ", causaManutenzione='" + causaManutenzione + '\'' +
                '}';
    }
}
