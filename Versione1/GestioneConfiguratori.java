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

    public String[] faiPrimoAccesso(){

        boolean usernameValido = false;

        System.out.println(BENVENUTO_PRIMO_ACCESSO);

        String[] credenziali = new String[2];

        do{
            credenziali[0] = Utilità.chiediStringaNonVuota(RICHIESTA_USER);
            

            boolean usernameEsistente = false;
            for(Configuratore c : configuratori){
                if(c.getUsername().equals(credenziali[0])){
                    usernameEsistente = true;
                    break;
                }
            }

            if(usernameEsistente){
                System.out.println("Errore: Username già esistente. Riprova.");

            } else {
                credenziali[1] = Utilità.chiediStringaNonVuota(RICHIESTA_PASSWORD);
                return credenziali;
            }

        } while (!usernameValido);

        return null; 
    }

    

    public Configuratore login (){


        String username = Utilità.chiediStringaNonVuota(RICHIESTA_USER);
        String password = Utilità.chiediStringaNonVuota(RICHIESTA_PASSWORD);

        if(username.equals(configuratori.get(0).getUsername()) && password.equals(configuratori.get(0).getPassword())){
            
            String[] credenziali = faiPrimoAccesso();
            Configuratore nuovoConfiguratore = setupConfiguratore(credenziali[0], credenziali[1]);
            configuratori.add(nuovoConfiguratore);
            Utilità.scriviJSon("configuratori.json", configuratori);
            System.out.println("Account creato con successo. Login effettuato.");
            return nuovoConfiguratore;
            
        }
        else{
            for(Configuratore c : configuratori){
                if(c.getUsername().equals(username) && c.getPassword().equals(password)){
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

    public Configuratore setupConfiguratore(String username, String password){

        List<Luogo> luoghi = new ArrayList<>();

        do {
            String nomeLuogo = Utilità.chiediStringaNonVuota("Inserisci il nome del luogo di interesse (o 'fine' per terminare): ");
            if(nomeLuogo.equalsIgnoreCase("fine")){
                break;
            }
            String nomeVolontario = Utilità.chiediStringaNonVuota("Inserisci il nome del volontario assegnato al luogo: ");
            String cognomeVolontario = Utilità.chiediStringaNonVuota("Inserisci il cognome del volontario assegnato al luogo: ");
            Volontario volontario = new Volontario(nomeVolontario, cognomeVolontario);
            
            Luogo nuovoLuogo = new Luogo(nomeLuogo, volontario);
            luoghi.add(nuovoLuogo);
            System.out.println("Luogo aggiunto con successo: " + nuovoLuogo.toString());
        } while (true);

        return new Configuratore(username, password, luoghi);
    }

    public void aggiungiLuogo(Configuratore c, Luogo luogo){
        c.aggiungiLuogo(luogo);
    }

    public void modificaConfiguratore(Configuratore c){

    }

    

    public void stampaInfo(Configuratore c){
        System.out.println(c.toString());
    }

    


    








    
}
