package Versione1.Entità;

import java.util.ArrayList;
import java.util.List;

public class Luogo {
    private String nome;                    // Nome del luogo (es. "Parco Grotta Cascata del Varone")
    private String descrizione;             // Descrizione del luogo
    private String collocazioneGeografica;  // Indirizzo o coordinate geografiche
    private List<TipoVisita> tipiTipoVisita;        // Tipi di visita associati a questo luogo

    public Luogo() {
        this.tipiTipoVisita = new ArrayList<>();
    }

    public Luogo(String nome, String descrizione, String collocazioneGeografica) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.collocazioneGeografica = collocazioneGeografica;
        this.tipiTipoVisita = new ArrayList<>();
    }

    // Getter e Setter
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getCollocazioneGeografica() { return collocazioneGeografica; }
    public void setCollocazioneGeografica(String collocazioneGeografica) { this.collocazioneGeografica = collocazioneGeografica; }

    public List<TipoVisita> getTipiVisita() { return tipiTipoVisita; }
    public void setTipiVisita(List<TipoVisita> tipiTipoVisita) { this.tipiTipoVisita = tipiTipoVisita; }

    @Override
    public String toString() {
        return "Luogo: " + nome + " - " + descrizione;
    }
}



