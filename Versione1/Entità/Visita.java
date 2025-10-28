package Versione1.Entità;

import java.time.LocalDate;

public class Visita {
    private String titoloTipoVisita;        // Riferimento al TipoVisita (per recuperare le info)
    private String nomeLuogo;                // Riferimento al Luogo
    private LocalDate dataVisita;            // Data specifica di questa istanza
    private String volontarioAssegnato;      // Volontario specifico per questa data
    private StatoVisita stato;               // Stato corrente
    private int numeroIscritti;              // Numero corrente di iscritti

    public Visita() {
        this.stato = StatoVisita.PROPOSTA;
        this.numeroIscritti = 0;
    }

    public Visita(String titoloTipoVisita, String nomeLuogo, LocalDate dataVisita, String volontarioAssegnato) {
        this.titoloTipoVisita = titoloTipoVisita;
        this.nomeLuogo = nomeLuogo;
        this.dataVisita = dataVisita;
        this.volontarioAssegnato = volontarioAssegnato;
        this.stato = StatoVisita.PROPOSTA;
        this.numeroIscritti = 0;
    }

    // Getter e Setter
    public String getTitoloTipoVisita() { return titoloTipoVisita; }
    public void setTitoloTipoVisita(String titoloTipoVisita) { this.titoloTipoVisita = titoloTipoVisita; }

    public String getNomeLuogo() { return nomeLuogo; }
    public void setNomeLuogo(String nomeLuogo) { this.nomeLuogo = nomeLuogo; }

    public LocalDate getDataVisita() { return dataVisita; }
    public void setDataVisita(LocalDate dataVisita) { this.dataVisita = dataVisita; }

    public String getVolontarioAssegnato() { return volontarioAssegnato; }
    public void setVolontarioAssegnato(String volontarioAssegnato) { this.volontarioAssegnato = volontarioAssegnato; }

    public StatoVisita getStato() { return stato; }
    public void setStato(StatoVisita stato) { this.stato = stato; }

    public int getNumeroIscritti() { return numeroIscritti; }
    public void setNumeroIscritti(int numeroIscritti) { this.numeroIscritti = numeroIscritti; }

    @Override
    public String toString() {
        return titoloTipoVisita + " presso " + nomeLuogo + " il " + dataVisita + " [" + stato + "]";
    }
}
