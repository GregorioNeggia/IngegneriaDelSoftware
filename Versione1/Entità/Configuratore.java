package Versione1.Entità;
import java.util.*;

public class Configuratore {
    
    private String username;
    private String password;
    private List<Luogo> luoghi;
    private List<Volontario> volontari;

    public Configuratore(String username, String password, List<Luogo> luoghi, List<Volontario> volontari) {
        this.username = username;
        this.password = password;
        this.luoghi = luoghi != null ? luoghi : new ArrayList<Luogo>();
        this.volontari = volontari != null ? volontari : new ArrayList<Volontario>();
    }

    @Override
    public String toString() {
        return "Configuratore{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", luoghi=" + luoghi.toString() + '\'' +
                ", volontari=" + volontari.toString() + '\'' +
                '}';
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public List<Luogo> getLuoghi() {
        return luoghi;
    }

    public void aggiungiLuogo(Luogo luogo) {
        luoghi.add(luogo);
    }





}
