package Versione1.Entità;

import java.util.ArrayList;
import java.util.List;

public class Luogo {
    private String nome;                    // Nome del luogo (es. "Parco Grotta Cascata del Varone")
    private String descrizione;             // Descrizione del luogo
    private String collocazioneGeografica;  // Indirizzo o coordinate geografiche
    private List<Visita> tipiVisita;        // Tipi di visita associati a questo luogo

    public Luogo() {
        this.tipiVisita = new ArrayList<>();
    }

    public Luogo(String nome, String descrizione, String collocazioneGeografica) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.collocazioneGeografica = collocazioneGeografica;
        this.tipiVisita = new ArrayList<>();
    }

    // Getter e Setter
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getCollocazioneGeografica() { return collocazioneGeografica; }
    public void setCollocazioneGeografica(String collocazioneGeografica) { this.collocazioneGeografica = collocazioneGeografica; }

    public List<Visita> getTipiVisita() { return tipiVisita; }
    public void setTipiVisita(List<Visita> tipiVisita) { this.tipiVisita = tipiVisita; }

    @Override
    public String toString() {
        return "Luogo: " + nome + " - " + descrizione;
    }
}

/*package Versione1.Entità;
import java.util.*;

public class Luogo {

    private String nome;
    private String descrizione;
    private String posizione;
    private List<Visita> visite;

    public Luogo(String nome, String descrizione, String posizione, List<Visita> visite) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.posizione = posizione;
        this.visite = visite != null ? visite : new ArrayList<Visita>();
    }

    public String getNome() {
        return nome;
    }

    public void aggiungiVisita(Visita visita) {
        this.visite.add(visita);
    }



    @Override
    public String toString() {
        return "Luogo { " +
                "nome = " + nome + '\n' +
                ", descrizione = " + descrizione + '\n' +
                ", posizione = " + posizione + '\n' +
                ", visite = " + visite.toString() + '\n' +
                " }";
    }

}
*/
    

