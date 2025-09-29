package andreapia.entities;

import andreapia.enums.Capienza;
import andreapia.enums.StatoMezzo;
import andreapia.enums.TipoMezzo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "mezzi")
public class Mezzi {
    //ATTRIBUTI
    @Id
    @GeneratedValue
    private UUID id;
    private StatoMezzo stato;
    private LocalDate data_inizio_manutenzione;
    private LocalDate data_fine_manutenzione;
    private Capienza capienza;
    private TipoMezzo tipo_mezzo;
    @OneToMany(mappedBy = "mezzi")
    private List<Tratta> tratte = new ArrayList<>();
    private int numero_tratte_effettuate;
    @OneToMany (mappedBy = "mezzi")
    private List<BigliettiVidimati> biglietti_vidimati= new ArrayList<>();
    @OneToOne(mappedBy = "id_mezzo")
    private Corsa corsa;
    //COSTRUTTORI
    public Mezzi (){} //COSTRUTTORE VUOTO

    public Mezzi( StatoMezzo stato, LocalDate data_inizio_manutenzione, LocalDate data_fine_manutenzione, Capienza capienza, TipoMezzo tipo_mezzo, List<Tratta> id_tratta, int numero_tratte_effettuate, List<BigliettiVidimati> biglietti_vidimati) {
        this.stato = stato;
        this.data_inizio_manutenzione = data_inizio_manutenzione;
        this.data_fine_manutenzione = data_fine_manutenzione;
        this.capienza = capienza;
        this.tipo_mezzo = tipo_mezzo;
        this.tratte = id_tratta;
        this.numero_tratte_effettuate = numero_tratte_effettuate;
        this.biglietti_vidimati = biglietti_vidimati;
    }


    //METODI
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

    public TipoMezzo getTipo_mezzo() {
        return tipo_mezzo;
    }

    public void setTipo_mezzo(TipoMezzo tipo_mezzo) {
        this.tipo_mezzo = tipo_mezzo;
    }

    public List<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte(List<Tratta> tratte) {
        this.tratte = tratte;
    }

    public int getNumero_tratte_effettuate() {
        return numero_tratte_effettuate;
    }

    public void setNumero_tratte_effettuate(int numero_tratte_effettuate) {
        this.numero_tratte_effettuate = numero_tratte_effettuate;
    }

    public List<BigliettiVidimati> getBiglietti_vidimati() {
        return biglietti_vidimati;
    }

    public void setBiglietti_vidimati(List<BigliettiVidimati> biglietti_vidimati) {
        this.biglietti_vidimati = biglietti_vidimati;
    }

    @Override
    public String toString() {
        return "Mezzi{" +
                "id=" + id +
                ", stato=" + stato +
                ", data_inizio_manutenzione=" + data_inizio_manutenzione +
                ", data_fine_manutenzione=" + data_fine_manutenzione +
                ", capienza=" + capienza +
                ", tipo_mezzo=" + tipo_mezzo +
                ", tratte=" + tratte +
                ", numero_tratte_effettuate=" + numero_tratte_effettuate +
                ", biglietti_vidimati=" + biglietti_vidimati +
                ", corsa=" + corsa +
                '}';
    }
}
