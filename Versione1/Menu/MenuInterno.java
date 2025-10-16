package Versione1.Menu;

import Versione1.Entità.*;
import Versione1.Gestori.GestioneConfiguratori;
import Versione1.Gestori.GestioneDateEscluse;
import Versione1.Gestori.GestioneVisite;
import Versione1.Utilità;
import Versione1.Gestori.GestioneLuoghi;
import Versione1.Gestori.GestioneVolontari;

import java.util.*;



public class MenuInterno {

    private Configuratore conf;
    private GestioneConfiguratori gestioneConfiguratori;
    private PrimoAvvioData avvio;
    private GestioneVisite gestioneVisite;

    
    public MenuInterno(Configuratore conf, GestioneConfiguratori gestore, PrimoAvvioData avvio) {
       this.conf = conf;
       this.gestioneConfiguratori = gestore;
       this.gestioneVisite = gestioneVisite;
       this.avvio = avvio;
    }

    public void mostraMenuInterno() {
            int scelta;
            
            System.out.println("\n=== BENVENUTO " + conf.getUsername().toUpperCase() + " ===");
            System.out.println(avvio.toString());

            do {
                System.out.println("\n=== MENU CONFIGURATORE ===");
                System.out.println("1. Setup configuratore");
                System.out.println("2. Setup visite");
                System.out.println("3. Escludi date per il terzo mese");
                System.out.println("0. Logout");

                scelta = Utilità.leggiIntero( "Inserisci la tua scelta: ");

                switch(scelta) {
                    case 1:
                        gestioneConfiguratori.modificaConfiguratore(conf);
                        break;
                    case 2:
                        setupVisite();
                        break;
                    case 3:
                        System.out.println("caso 3");
                        GestioneDateEscluse gestioneDateEscluse=new GestioneDateEscluse();
                        gestioneDateEscluse.menu();
                        break;
                    case 0:
                        System.out.println("Logout effettuato. Arrivederci " + conf.getUsername() + "!");
                        break;
                    default:
                        System.out.println("Scelta non valida! Riprova.");
                }
            } while(scelta != 0);

    }

    private void setupVisite() {
        System.out.println("\n=== SETUP INIZIALE VISITE ===");
        System.out.println("Configurazione dell'insieme di luoghi da destinare a visite guidate");

        // Inizializza i gestori
        GestioneLuoghi gestioneLuoghi = new GestioneLuoghi();
        GestioneVolontari gestioneVolontari = new GestioneVolontari();
        GestioneVisite gestioneVisite = new GestioneVisite(gestioneLuoghi, gestioneVolontari);

        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- MENU SETUP VISITE ---");
            System.out.println("1. Gestisci luoghi");
            System.out.println("2. Gestisci volontari");
            System.out.println("3. Configura visite (associa luoghi, tipi di visita e volontari)");
            System.out.println("4. Visualizza configurazione completa");
            System.out.println("5. Torna al menu principale");
            System.out.print("Scegli: ");

            String scelta = Utilità.chiediStringa();

            switch (scelta) {
                case "1":
                    gestioneLuoghi.menu();
                    break;
                case "2":
                    gestioneVolontari.menu();
                    break;
                case "3":
                    gestioneVisite.menu();
                    break;
                case "4":
//                    gestioneVisite.visualizzaConfigurazioneCompleta();
                    break;
                case "5":
                    esci = true;
                    System.out.println("Setup visite completato!");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }


}
    
    