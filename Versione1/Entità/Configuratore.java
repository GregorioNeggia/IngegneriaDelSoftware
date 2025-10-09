package Versione1.Entità;
import java.util.*;

public class Configuratore {
    
    private String username;
    private String password;
    private List<Luogo> luoghiInteresse;
    private List<Volontario> volontari;

    public Configuratore(String username, String password, List<Luogo> luoghi, List<Volontario> volontari) {
        this.username = username;
        this.password = password;
        this.luoghiInteresse = luoghi;
        this.volontari = volontari;
    }

    public void aggiungiLuogo(Luogo luogo) {
        luoghiInteresse.add(luogo);
    }

    
    public String toString() {
        return "Configuratore{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", luoghiInteresse=" + luoghiInteresse + '\'' +
                ", volontari=" + volontari + '\'' + 
                '}';
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public List<Luogo> getLuoghiInteresse() {
        return luoghiInteresse;
    }





}
