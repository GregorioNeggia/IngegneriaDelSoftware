package Versione1;
import java.util.*;

public class Configuratore {
    
    private String username;
    private String password;
    private AmbitoTerritoriale ambitoTerritoriale;
    private int maxPartecipanti;
    private List <Luogo> luoghiDisponibili = new ArrayList<>();

    public Configuratore(String username, String password) {
        this.username = username;
        this.password = password;
    }

    



    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    


}
