package Versione1;
import java.util.*;

public class GestioneConfiguratori {
    private static final String FILENAME = "configuratori.txt";

    private static final String USERNAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "admin123";

    private List<Configuratore> configuratori;
    
    public GestioneConfiguratori() {
        configuratori = new ArrayList<>();
    }

    public void faiPrimoAccesso(){
        boolean conferma = false;

        do{
            String username = Utilità.chiediStringaNonVuota("Inserisci username");
            String password = Utilità.chiediStringaNonVuota("Inserisci password");
        for(Configuratore c : configuratori){


            if(c.getUsername().equals(username)){
                System.out.println("Errore: Username già esistente. Riprova.");
                break;
            }
            else{
                conferma = true;
            }
            }

        }while (conferma == true);
    }

    public boolean login(String username, String password){
        boolean log = false;

        if(username.equals(USERNAME_ADMIN) && password.equals(PASSWORD_ADMIN)){
            log = true;
            faiPrimoAccesso();
        }
        else{
            for(Configuratore c : configuratori){
                if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                    log = true;
                    break;
                }
            }
        }
        return log;
    }






    public List<Configuratore> getConfiguratori() {
        return configuratori;
    }



    








    
}
