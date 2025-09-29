package andreapia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity

public class Tessera {
@Id
@GeneratedValue
@Column(name = "id_tessera")
private UUID idTessera;
@Column(name = "data_emissione")
private LocalDate dataEmissione;
@Column(name = "data_scadenza")
private LocalDate dataScadenza;


    public Tessera( LocalDate dataEmissione, LocalDate dataScadenza) {

        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
    }

    public UUID getIdTessera() {
        return idTessera;
    }



    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }


    @Override
    public String toString() {
        return "Tessera{" +
                "idTessera=" + idTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
