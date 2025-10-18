package Versione1.Entità;

import java.util.ArrayList;
import java.util.List;

public class Volontario {
    private String nickname;                        // Nickname univoco del volontario
    private List<String> tipiVisitaAssociati;      // Lista dei titoli dei tipi di visita per cui è abilitato

    public Volontario() {
        this.tipiVisitaAssociati = new ArrayList<>();
    }

    public Volontario(String nickname) {
        this.nickname = nickname;
        this.tipiVisitaAssociati = new ArrayList<>();
    }

    // Getter e Setter
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public List<String> getTipiVisitaAssociati() { return tipiVisitaAssociati; }
    public void setTipiVisitaAssociati(List<String> tipiVisitaAssociati) { this.tipiVisitaAssociati = tipiVisitaAssociati; }

    // Metodi di utilità
    public void aggiungiTipoVisita(String titoloTipoVisita) {
        if (!tipiVisitaAssociati.contains(titoloTipoVisita)) {
            tipiVisitaAssociati.add(titoloTipoVisita);
        }
    }

    public void rimuoviTipoVisita(String titoloTipoVisita) {
        tipiVisitaAssociati.remove(titoloTipoVisita);
    }

    @Override
    public String toString() {
        return "Volontario: " + nickname + " (Abilitato per " + tipiVisitaAssociati.size() + " tipi di visita)";
    }
}


/*public class Volontario {

    private String user;
    

    public Volontario(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Volontario{" +
                ", user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

}*/
