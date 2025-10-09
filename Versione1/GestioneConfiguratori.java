package Versione1;
import java.util.*;

public class GestioneConfiguratori {
   

    private static final String USERNAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "admin123";

    private List<Configuratore> configuratori;
    
    public GestioneConfiguratori(List<Configuratore> configuratori) {
        this.configuratori = configuratori;
    }

    public Configuratore faiPrimoAccesso(){

        boolean usernameValido = false;

        do{
            String username = Utilità.chiediStringaNonVuota("Inserisci username");
            String password = Utilità.chiediStringaNonVuota("Inserisci password");

            boolean usernameEsistente = false;
            for(Configuratore c : configuratori){
                if(c.getUsername().equals(username)){
                    usernameEsistente = true;
                    break;
                }
            }

            if(usernameEsistente){
                System.out.println("Errore: Username già esistente. Riprova.");
            } else {
                
                Configuratore nuovoConfiguratore = new Configuratore(username, password);
                configuratori.add(nuovoConfiguratore);
                Utilità.scriviConfiguratori("configuratori.txt", configuratori);
                return nuovoConfiguratore;
            }

        } while (!usernameValido);

        return null; 
    }

    public Configuratore login (){
        
        String username = Utilità.chiediStringaNonVuota("Inserisci username");
        String password = Utilità.chiediStringaNonVuota("Inserisci password");

        if(username.equals(USERNAME_ADMIN) && password.equals(PASSWORD_ADMIN)){
            
            return faiPrimoAccesso();
        }
        else{
            for(Configuratore c : configuratori){
                if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                    System.out.println("Login effettuato con successo.");
                    return c;
                }
            } 
            System.out.println("Errore: Credenziali non valide. Riprova.");
            return null;
        }
    }


    public List<Configuratore> getConfiguratori() {
        return configuratori;
    }



    








    
}
