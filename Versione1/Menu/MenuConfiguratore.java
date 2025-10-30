package Versione1.Menu;

import Versione1.Entità.*;
import Versione1.Gestori.*;
import Versione1.Utilita;


public class MenuConfiguratore {

    private Configuratore conf;
    private GestioneConfiguratori gestioneConfiguratori;
    private PrimoAvvioData avvio;
    private GestioneTipoVisita gestioneTipoVisita;



    public MenuConfiguratore(Configuratore conf, GestioneConfiguratori gestore, PrimoAvvioData avvio) {
       this.conf = conf;
       this.gestioneConfiguratori = gestore;
       this.gestioneTipoVisita = gestioneTipoVisita;
       this.avvio = avvio;
    }

    public void mostraMenuConfiguratore() {
            int scelta;
            
            System.out.println("\n=== BENVENUTO " + conf.getUsername().toUpperCase() + " ===");
            System.out.println(avvio.toString());


            do {
                System.out.println("\n=== MENU CONFIGURATORE ===");
                System.out.println("1. modificare il numero massimo di iscritti per evento");
                System.out.println("2. gestisci visite luoghi volontari");
                System.out.println("3. Escludi date per il terzo mese da oggi");
                System.out.println("0. Logout");

                scelta = Utilita.leggiIntero( "Inserisci la tua scelta: ");

                switch(scelta) {
                    case 1:
                        gestioneConfiguratori.modificaNumMaxIscrizioni(conf);
                        break;
                    case 2:
                        gestioneTipoVisiteLuoghi();
                        break;
                    case 3:
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

    private void gestioneTipoVisiteLuoghi() {
        System.out.println("\n=== SETUP INIZIALE VISITE ===");
        System.out.println("Configurazione dell'insieme di luoghi da destinare a visite guidate");

        // Inizializza i gestori
        GestioneLuoghi gestioneLuoghi = new GestioneLuoghi();
        GestioneVolontari gestioneVolontari = new GestioneVolontari();
        GestioneTipoVisita gestioneTipoVisita = new GestioneTipoVisita(gestioneLuoghi, gestioneVolontari);
        GestioneVisite gestioneVisite = new GestioneVisite(gestioneLuoghi, gestioneTipoVisita);


        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- MENU SETUP VISITE ---");
            System.out.println("1. Gestisci luoghi");
            System.out.println("2. Gestisci volontari");
            System.out.println("3. Configura tipo visite (associa luoghi, tipi di visita e volontari)");
            System.out.println("4. Visualizza/gestisci visite calendarizzate");
            System.out.println("5. Torna al menu principale");
            System.out.print("Scegli: ");

            String scelta = Utilita.chiediStringaNonVuota("");

            switch (scelta) {
                case "1":
                    gestioneLuoghi.menu();
                    break;
                case "2":
                    gestioneVolontari.menu();
                    break;
                case "3":
                    gestioneTipoVisita.menu();
                    break;
                case "4":
                    gestioneVisite.menu();
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
    
    