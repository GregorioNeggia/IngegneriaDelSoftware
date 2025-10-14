package Versione1.Gestori;
import java.io.ObjectInputFilter.Config;
import java.util.*;

import Versione1.Utilità;
import Versione1.Entità.*;

import static Versione1.Utilità.scanner;


public class GestioneConfiguratori {

//  ATTRIBUTI
    private static final String RICHIESTA_USER = "Inserisci username: \n";
    private static final String RICHIESTA_PASSWORD = "Inserisci password: \n";
    private static final String BENVENUTO_PRIMO_ACCESSO = "Benvenuto! Effettua il primo accesso creando un nuovo account configuratore.";

    private List<Configuratore> configuratori;

//  COTRUTTORE
    public GestioneConfiguratori(List<Configuratore> configuratori) {

        this.configuratori = configuratori;
    }

//  METODI

//    getter e setter
    public List<Configuratore> getConfiguratori() {
        return configuratori;
    }


//    primo accesso
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



//    LOGIN
    public Configuratore login (){

        String username = Utilità.chiediStringaNonVuota(RICHIESTA_USER);
        String password = Utilità.chiediStringaNonVuota(RICHIESTA_PASSWORD);

        if(username.equals(configuratori.get(0).getUsername()) && password.equals(configuratori.get(0).getPassword())){
            
            String[] credenziali = faiPrimoAccesso();
            Configuratore nuovoConfiguratore = new Configuratore(credenziali[0], credenziali[1],null, null);

            configuratori.add(nuovoConfiguratore);
            Utilità.scriviJSonConfiguratori("configuratori.json", configuratori);
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





    //DA MODIFICARE PER IMPLEMENTARE LA CREAZIONE DI LUOGHI E VOLONTARI
    public Configuratore setupConfiguratore(String username, String password){
        boolean fine = false;
        List<Luogo> luoghi = new ArrayList<>();
        List<Volontario> volontari = new ArrayList<>();
        try{
            while(!fine){}




        }
        catch(Exception e){
            System.out.println("Errore durante la creazione del configuratore: " + e.getMessage());
        }

        return new Configuratore(username, password, luoghi, volontari);
    }

    

    public void modificaConfiguratore(Configuratore c){

    }

    

    public void stampaInfo(Configuratore c){
        System.out.println(c.toString());
    }

    


    








    
}
