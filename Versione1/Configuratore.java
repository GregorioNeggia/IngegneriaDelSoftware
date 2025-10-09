package Versione1;
import java.util.*;

public class Configuratore {
    
    private String username;
    private String password;

    public Configuratore(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    public String toString() {
        return "Configuratore{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }



}
