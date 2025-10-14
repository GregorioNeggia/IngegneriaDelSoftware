package Versione1.Entità;
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

    

