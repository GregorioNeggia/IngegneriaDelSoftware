package Versione1;

import Versione1.Entità.*;
import java.util.*;

public class Main {

     private static final String DEFAULT_FILE_CONFIGURATORI = "configuratori.json";
    

    public static void main(String[] args) {
        List<Configuratore> configuratori = Utilità.leggiConfiguratori(DEFAULT_FILE_CONFIGURATORI);
        GestioneConfiguratori gestione = new GestioneConfiguratori(configuratori);
        Utilità.stampaConfiguratori(configuratori);
        Configuratore configuratore = gestione.login();

        


        
    }
}

 
