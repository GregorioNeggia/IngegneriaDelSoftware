package Versione1.Entità;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Visita {
    private String titolo;                          // Titolo della visita
    private String descrizione;                     // Descrizione dettagliata
    private String puntoIncontro;                   // Punto di incontro
    private LocalDate dataInizio;                   // Data inizio periodo
    private LocalDate dataFine;                     // Data fine periodo
    private List<String> giorniSettimanali;         // Giorni della settimana (SABATO, DOMENICA, ecc.)
    private LocalTime oraInizio;                    // Ora di inizio visita
    private int durata;                             // Durata in minuti
    private boolean bigliettoNecessario;            // Se serve biglietto d'ingresso
    private int partecipantiMin;                    // Numero minimo partecipanti
    private int partecipantiMax;                    // Numero massimo partecipanti
    private List<String> volontariAssociati;        // Lista nickname volontari

    public Visita() {
        this.giorniSettimanali = new ArrayList<>();
        this.volontariAssociati = new ArrayList<>();
    }

    public Visita(String titolo, String descrizione, String puntoIncontro,
                  LocalDate dataInizio, LocalDate dataFine, List<String> giorniSettimanali,
                  LocalTime oraInizio, int durata, boolean bigliettoNecessario,
                  int partecipantiMin, int partecipantiMax) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.puntoIncontro = puntoIncontro;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.giorniSettimanali = giorniSettimanali;
        this.oraInizio = oraInizio;
        this.durata = durata;
        this.bigliettoNecessario = bigliettoNecessario;
        this.partecipantiMin = partecipantiMin;
        this.partecipantiMax = partecipantiMax;
        this.volontariAssociati = new ArrayList<>();
    }

    // Getter e Setter
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getPuntoIncontro() { return puntoIncontro; }
    public void setPuntoIncontro(String puntoIncontro) { this.puntoIncontro = puntoIncontro; }

    public LocalDate getDataInizio() { return dataInizio; }
    public void setDataInizio(LocalDate dataInizio) { this.dataInizio = dataInizio; }

    public LocalDate getDataFine() { return dataFine; }
    public void setDataFine(LocalDate dataFine) { this.dataFine = dataFine; }

    public List<String> getGiorniSettimanali() { return giorniSettimanali; }
    public void setGiorniSettimanali(List<String> giorniSettimanali) { this.giorniSettimanali = giorniSettimanali; }

    public LocalTime getOraInizio() { return oraInizio; }
    public void setOraInizio(LocalTime oraInizio) { this.oraInizio = oraInizio; }

    public int getDurata() { return durata; }
    public void setDurata(int durata) { this.durata = durata; }

    public boolean isBigliettoNecessario() { return bigliettoNecessario; }
    public void setBigliettoNecessario(boolean bigliettoNecessario) { this.bigliettoNecessario = bigliettoNecessario; }

    public int getPartecipantiMin() { return partecipantiMin; }
    public void setPartecipantiMin(int partecipantiMin) { this.partecipantiMin = partecipantiMin; }

    public int getPartecipantiMax() { return partecipantiMax; }
    public void setPartecipantiMax(int partecipantiMax) { this.partecipantiMax = partecipantiMax; }

    public List<String> getVolontariAssociati() { return volontariAssociati; }
    public void setVolontariAssociati(List<String> volontariAssociati) { this.volontariAssociati = volontariAssociati; }

    @Override
    public String toString() {
        return "Visita: " + titolo + " (" + dataInizio + " - " + dataFine + ")";
    }
}

/*package Versione1.Entità;
import java.util.*;

public class Visita {
    
    private String titolo;
    private String descrizione;
    private String puntoIncontro;
    private Data dataVisita;
    private Data dataInizio;
    private Data dataFine;
    private boolean biglietto;
    private String orario;
    private String durata;
    private String[] giorniDisponibili;
    private int numeroMinimo;
    private int numeroMassimo;
    private Volontario volontario;



    public Visita(String titolo, String descrizione, String puntoIncontro, Data dataVisita, Data dataInizio, Data dataFine, boolean biglietto, String orario, String durata, String[] giorniDisponibili, int numeroMinimo, int numeroMassimo, Volontario volontario) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.puntoIncontro = puntoIncontro;
        this.dataVisita = dataVisita;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.biglietto = biglietto;
        this.orario = orario;
        this.durata = durata;
        this.giorniDisponibili = giorniDisponibili;
        this.numeroMinimo = numeroMinimo;
        this.numeroMassimo = numeroMassimo;
        this.volontario = volontario;
    }

    @Override
    public String toString() {
        return "Visita { " +
                "titolo = " + titolo + '\n' +
                ", descrizione = " + descrizione + '\n' +
                ", puntoIncontro = " + puntoIncontro + '\n' +
                ", dataVisita = " + dataVisita + '\n' +
                ", periodo = " + " dal " + dataInizio + " al " + dataFine + '\n' +
                ", biglietto = " + biglietto + '\n' +
                ", orario = " + orario + '\n' +
                ", durata = " + durata + '\n' +
                ", giorniDisponibili = " + Arrays.toString(giorniDisponibili) + '\n' +
                ", numeroMinimo = " + numeroMinimo + '\n' +
                ", numeroMassimo = " + numeroMassimo + '\n' +
                ", volontario = " + volontario + '\n' +
                " }";
    }

}
*/