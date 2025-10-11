package Versione1;

import Versione1.Entità.*;
import Versione1.Gestori.GestioneConfiguratori;

import java.util.*;



public class MenuInterno {

    private Configuratore conf;
    private GestioneConfiguratori gestioneConfiguratori;
    
    public MenuInterno(Configuratore conf, GestioneConfiguratori gestore) {
       this.conf = conf;
       this.gestioneConfiguratori = gestore;
    }

    public void mostraMenuInterno() {
        try (Scanner scanner = new Scanner(System.in)) {
            int scelta;
            
            System.out.println("\n=== BENVENUTO " + conf.getUsername().toUpperCase() + " ===");
            
            do {
                System.out.println("\n=== MENU CONFIGURATORE ===");
                System.out.println("1. Setup configuratore");
                System.out.println("2. Stampa informazioni complete");
                System.out.println("0. Logout");
                
                scelta = Utilità.leggiIntero(scanner, "Inserisci la tua scelta: ");
                scanner.nextLine();

                switch(scelta) {
                    case 1:
                        gestioneConfiguratori.modificaConfiguratore(conf);
                        break;
                    case 2:
                        gestioneConfiguratori.stampaInfo(conf);
                        break;
                    case 0:
                        System.out.println("Logout effettuato. Arrivederci " + conf.getUsername() + "!");
                        break;
                    default:
                        System.out.println("Scelta non valida! Riprova.");
                }
            } while(scelta != 0);
        }
        catch (InputMismatchException e) {
            System.out.println("Errore di input. Per favore, inserisci un numero valido.");
        }
    }

}
    
    