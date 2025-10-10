package Versione1;

import Versione1.Entità.*;
import java.util.*;
import java.util.Scanner;


public class MenuInterno {

    private Configuratore conf;
    private GestioneConfiguratori gestioneConfiguratori;
    
    public MenuInterno(Configuratore conf, GestioneConfiguratori gestore) {
       this.conf = conf;
       this.gestioneConfiguratori = gestore;
    }

    public void mostraMenuInterno() {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        
        System.out.println("\n=== BENVENUTO " + conf.getUsername().toUpperCase() + " ===");
        
        do {
            System.out.println("\n=== MENU CONFIGURATORE ===");
            System.out.println("1. Setup configuratore");
            System.out.println("2. Stampa informazioni complete");
            System.out.println("3. Stampa luoghi e volontari");
            System.out.println("0. Logout");
            System.out.print("Inserisci la tua scelta: ");
            
            scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline
            
            switch(scelta) {
                case 1:
                    gestioneConfiguratori.modificaConfiguratore(conf);
                    break;
                case 2:
                    gestioneConfiguratori.stampaInfo(conf);
                    break;
                case 3:
                    
                    break;
                case 0:
                    System.out.println("Logout effettuato. Arrivederci " + conf.getUsername() + "!");
                    break;
                default:
                    System.out.println("Scelta non valida! Riprova.");
            }
        } while(scelta != 0);
    }

}
    
    