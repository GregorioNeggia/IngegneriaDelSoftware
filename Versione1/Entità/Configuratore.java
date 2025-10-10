package Versione1.Entità;
import java.util.*;

public class Configuratore {
    
    private String username;
    private String password;
    private List<Luogo> luoghiInteresse;

    public Configuratore(String username, String password, List<Luogo> luoghi) {
        this.username = username;
        this.password = password;
        this.luoghiInteresse = luoghi != null ? luoghi : new ArrayList<Luogo>();
    }

    public void aggiungiLuogo(Luogo luogo) {
        luoghiInteresse.add(luogo);
    }

    
    public String toString() {
        return "Configuratore{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", luoghiInteresse=" + luoghiInteresse.toString() + '\'' +
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
