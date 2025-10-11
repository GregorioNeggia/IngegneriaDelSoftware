package Versione1.Entità;
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
