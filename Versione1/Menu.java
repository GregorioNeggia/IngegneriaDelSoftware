
package Versione1;
import java.util.*;
import Versione1.Entità.*;

public class Menu {
    
    public void mostraMenu() {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Stampa configuratori");
            System.out.println("0. Esci");
            System.out.print("Inserisci la tua scelta: ");
            
            scelta = scanner.nextInt();
            
            switch(scelta) {
                case 1:
                    GestioneConfiguratori gestione = new GestioneConfiguratori(Utilità.leggiJSon("configuratori.json"));
                    Configuratore configuratore = gestione.login();
                    if (configuratore != null) {
                        System.out.println("Login effettuato: " + configuratore.getUsername());
                        MenuInterno menuInterno = new MenuInterno(configuratore, gestione);
                        menuInterno.mostraMenuInterno();
                    }

                    break;
                case 2:
                    List<Configuratore> configuratori = Utilità.leggiJSon("configuratori.json");
                    System.out.println("\n=== CONFIGURATORI REGISTRATI ===");
                    Utilità.stampaConfiguratori(configuratori);
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        } while(scelta != 0);
        
        scanner.close();
    }
}





