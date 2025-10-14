package Versione1.Gestori;

import Versione1.Entità.PrimoAvvioData;
import Versione1.Utilità;

public class GestioneApp {

    public PrimoAvvioData InizializzazioneApp(){
        System.out.println("Primo avvio entro");

        String luogo = Utilità.chiediStringaNonVuota("INSERISCI IL LUOGO DI INTERESSE: \n");
        int max = Utilità.leggiIntero("MASSIMO VISITATORI: \n");


        PrimoAvvioData dati = new PrimoAvvioData(max, luogo);

        Utilità.scriviJsonConfigurazione(dati);

        return dati;
    }
}
