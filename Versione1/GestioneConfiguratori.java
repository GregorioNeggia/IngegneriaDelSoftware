package Versione1;
import java.util.*;

import Versione1.Entità.*;

public class GestioneConfiguratori {
   

    private static final String USERNAME_ADMIN = "admin";
    private static final String PASSWORD_ADMIN = "admin123";
    private static final String RICHIESTA_USER = "Inserisci username: \n";
    private static final String RICHIESTA_PASSWORD = "Inserisci password: \n";
    private static final String BENVENUTO_PRIMO_ACCESSO = "Benvenuto! Effettua il primo accesso creando un nuovo account configuratore.";

    private List<Configuratore> configuratori;
    
    public GestioneConfiguratori(List<Configuratore> configuratori) {
        this.configuratori = configuratori;
    }

    public Configuratore faiPrimoAccesso(){

        boolean usernameValido = false;

        System.out.println(BENVENUTO_PRIMO_ACCESSO);

        do{
            String username = Utilità.chiediStringaNonVuota(RICHIESTA_USER);
            String password = Utilità.chiediStringaNonVuota(RICHIESTA_PASSWORD);

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
                
                Configuratore nuovoConfiguratore = new Configuratore(username, password, null, null);
                configuratori.add(nuovoConfiguratore);
                Utilità.scriviConfiguratori("configuratori.json", configuratori);
                return nuovoConfiguratore;
            }

        } while (!usernameValido);

        return null; 
    }

    

    public Configuratore login (){

        String username = Utilità.chiediStringaNonVuota(RICHIESTA_USER);
        String password = Utilità.chiediStringaNonVuota(RICHIESTA_PASSWORD);

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

    public void setupConfiguratore(Configuratore configuratore) {
        do {
            String nomeLuogo = Utilità.chiediStringaNonVuota("Inserisci il nome del luogo di interesse (o 'fine' per terminare): ");
            String nomeVolontario = Utilità.chiediStringaNonVuota("Inserisci il nome del volontario assegnato al luogo: ");
            String cognomeVolontario = Utilità.chiediStringaNonVuota("Inserisci il cognome del volontario assegnato al luogo: ");
            Volontario volontario = new Volontario(nomeVolontario, cognomeVolontario);
            if (nomeLuogo.equalsIgnoreCase("fine")) {
                break;
            }
            Luogo nuovoLuogo = new Luogo(nomeLuogo, volontario);
            configuratore.aggiungiLuogo(nuovoLuogo);
        } while (true);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Configuratore c : configuratori) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }

    public void setupVolontari(Configuratore configuratore){

    }



    








    
}
