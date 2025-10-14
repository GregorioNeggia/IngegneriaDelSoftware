package Versione1.Menu;

import Versione1.Entità.*;
import Versione1.Gestori.GestioneConfiguratori;
import Versione1.Gestori.GestioneVisite;
import Versione1.Utilità;

import java.util.*;



public class MenuInterno {

    private Configuratore conf;
    private GestioneConfiguratori gestioneConfiguratori;
    private PrimoAvvioData avvio;
    private GestioneVisite gestioneVisite;
    
    public MenuInterno(Configuratore conf, GestioneConfiguratori gestore, GestioneVisite gestioneVisite, PrimoAvvioData avvio) {
       this.conf = conf;
       this.gestioneConfiguratori = gestore;
       this.gestioneVisite = gestioneVisite;
       this.avvio = avvio;
    }

    public void mostraMenuInterno() {
        try (Scanner scanner = new Scanner(System.in)) {
            int scelta;
            
            System.out.println("\n=== BENVENUTO " + conf.getUsername().toUpperCase() + " ===");
            System.out.println(avvio.toString());
            
            do {
                System.out.println("\n=== MENU CONFIGURATORE ===");
                System.out.println("1. Setup configuratore");
                System.out.println("2. Setup visite");
                System.out.println("0. Logout");
                
                scelta = Utilità.leggiIntero( "Inserisci la tua scelta: ");
                scanner.nextLine();

                switch(scelta) {
                    case 1:
                        gestioneConfiguratori.modificaConfiguratore(conf);
                        break;
                    case 2:
                        gestioneVisite.stampaVisite();
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
    
    