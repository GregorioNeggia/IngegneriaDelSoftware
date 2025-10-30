package Versione1.Gestori;
import java.util.*;

import Versione1.Utilita;
import Versione1.Entità.*;


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
            credenziali[0] = Utilita.chiediStringaNonVuota(RICHIESTA_USER);

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
                credenziali[1] = Utilita.chiediStringaNonVuota(RICHIESTA_PASSWORD);
                return credenziali;
            }

        } while (!usernameValido);

        return null; 
    }



//    LOGIN
    public Configuratore login (){

        String username = Utilita.chiediStringaNonVuota(RICHIESTA_USER);
        String password = Utilita.chiediStringaNonVuota(RICHIESTA_PASSWORD);

        if(username.equals(configuratori.get(0).getUsername()) && password.equals(configuratori.get(0).getPassword())){
            
            String[] credenziali = faiPrimoAccesso();
            Configuratore nuovoConfiguratore = new Configuratore(credenziali[0], credenziali[1],null, null);

            configuratori.add(nuovoConfiguratore);
            Utilita.scriviListaInJson("Versione1/Database/configuratori.json", configuratori);
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


    public void modificaNumMaxIscrizioni(Configuratore c){
        System.out.println("Modifica tantum- num iscritti");

        String nomeFile = "Versione1/Database/PrimoAvvio.json";

        // Carica i dati esistenti dal file JSON
        List<PrimoAvvioData> listaDati = Utilita.leggiJsonInLista(nomeFile, PrimoAvvioData.class);
        PrimoAvvioData dati;
        dati = listaDati.get(0);

        System.out.println("Num max iscritti impostato a:" + dati.getNumMaxIscrizioni());
        int numMaxIscrizioni = Utilita.leggiIntero("inserisci il nuovo massimo di iscritti: \n");
        dati.setNumMaxIscrizioni(numMaxIscrizioni);
        Utilita.scriviListaInJson(nomeFile, listaDati);

        System.out.println("cap massimo di iscritti modificato a: "+ dati.getNumMaxIscrizioni());

    }

    

}
