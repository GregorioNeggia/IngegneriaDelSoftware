
package Versione1.Menu;
import Versione1.Entità.*;
import Versione1.Gestori.GestioneApp;
import Versione1.Gestori.GestioneConfiguratori;
import Versione1.Utilita;

public class Menu {

    public void mostraMenu() {
        int scelta;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Login Configuratore");
            System.out.println("0. Esci");

            scelta = Utilita.leggiIntero( "Inserisci la tua scelta: ");

            switch(scelta) {
                case 1:
                    loginConfiguratore();
                    break;
                case 0:
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        } while(scelta != 0);

    }

    private static void loginConfiguratore() {
        GestioneConfiguratori gestioneConf = new GestioneConfiguratori(Utilita.leggiJsonInLista("Versione1/Database/configuratori.json", Configuratore.class));
        GestioneApp app =  new GestioneApp();
        Configuratore configuratore = gestioneConf.login();
        if (configuratore != null) {
            System.out.println("Login effettuato: " + configuratore.getUsername());
            PrimoAvvioData dati = app.InizializzazioneApp();
            MenuConfiguratore menuConfiguratore = new MenuConfiguratore(configuratore, gestioneConf, dati);
            menuConfiguratore.mostraMenuConfiguratore();
        }
    }
}





