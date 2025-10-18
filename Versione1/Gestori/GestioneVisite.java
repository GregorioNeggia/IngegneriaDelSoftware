package Versione1.Gestori;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import Versione1.Entità.*;
import Versione1.Utilità;

public class GestioneVisite {
    private GestioneLuoghi gestioneLuoghi;
    private GestioneVolontari gestioneVolontari;
    private Scanner scanner = new Scanner(System.in);

    public GestioneVisite(GestioneLuoghi gestioneLuoghi, GestioneVolontari gestioneVolontari) {
        this.gestioneLuoghi = gestioneLuoghi;
        this.gestioneVolontari = gestioneVolontari;
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE VISITE ===");
            System.out.println("1. Crea nuova visita");
            System.out.println("2. Visualizza tutte le visite");
            System.out.println("3. Modifica visita");
            System.out.println("4. Elimina visita");
            System.out.println("5. Associa volontario a visita");
            System.out.println("6. Visualizza configurazione completa");
            System.out.println("7. Esci");
            System.out.print("Scegli: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    creaVisita();
                    break;
                case "2":
                    visualizzaTutteLeVisite();
                    break;
                case "3":
                    modificaVisita();
                    break;
                case "4":
                    eliminaVisita();
                    break;
                case "5":
                    associaVolontarioAVisita();
                    break;
                case "6":
                    visualizzaConfigurazioneCompleta();
                    break;
                case "7":
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    private void creaVisita() {
        if (gestioneLuoghi.getLuoghi().isEmpty()) {
            System.out.println("❌ Devi prima creare almeno un luogo!");
            return;
        }
        if (gestioneVolontari.getVolontari().isEmpty()) {
            System.out.println("❌ Devi prima registrare almeno un volontario!");
            return;
        }

        System.out.println("\n--- CREA NUOVA VISITA ---");

        // Scegli luogo
        gestioneLuoghi.visualizzaLuoghi();
        int sceltaLuogo = Utilità.leggiIntero("Scegli il luogo per la visita: ") - 1;
        List<Luogo> luoghi = gestioneLuoghi.getLuoghi();

        if (sceltaLuogo < 0 || sceltaLuogo >= luoghi.size()) {
            System.out.println("❌ Scelta non valida!");
            return;
        }

        Luogo luogoScelto = luoghi.get(sceltaLuogo);

        // Crea la visita
        Visita nuovaVisita = costruisciVisita();
        if (nuovaVisita != null) {
            // Associa volontari
            associaVolontari(nuovaVisita);

            // Aggiungi al luogo
            gestioneLuoghi.aggiungiVisitaALuogo(luogoScelto.getNome(), nuovaVisita);

            System.out.println("✅ Visita creata e associata a: " + luogoScelto.getNome());
        }
    }

    private Visita costruisciVisita() {
        try {
            String titolo = Utilità.chiediStringaNonVuota("Titolo della visita: ");
            String descrizione = Utilità.chiediStringaNonVuota("Descrizione: ");
            String puntoIncontro = Utilità.chiediStringaNonVuota("Punto di incontro: ");

            // Date
            String dataInizioStr = Utilità.chiediStringaNonVuota("Data inizio periodo (YYYY-MM-DD): ");
            String dataFineStr = Utilità.chiediStringaNonVuota("Data fine periodo (YYYY-MM-DD): ");
            LocalDate dataInizio = LocalDate.parse(dataInizioStr);
            LocalDate dataFine = LocalDate.parse(dataFineStr);

            if (dataFine.isBefore(dataInizio)) {
                System.out.println("❌ Data fine deve essere >= data inizio!");
                return null;
            }

            // Giorni
            List<String> giorni = inserisciGiorni();

            // Orario
            String oraStr = Utilità.chiediStringaNonVuota("Ora inizio (HH:MM): ");
            LocalTime ora = LocalTime.parse(oraStr);

            int durata = Utilità.leggiIntero("Durata in minuti: ");

            boolean biglietto = Utilità.chiediStringaNonVuota("Biglietto necessario? (si/no): ").equalsIgnoreCase("si");

            int minPart = Utilità.leggiIntero("Numero minimo partecipanti: ");
            int maxPart = Utilità.leggiIntero("Numero massimo partecipanti: ");

            if (maxPart < minPart) {
                System.out.println("❌ Massimo deve essere >= minimo!");
                return null;
            }

            return new Visita(titolo, descrizione, puntoIncontro, dataInizio, dataFine,
                    giorni, ora, durata, biglietto, minPart, maxPart);

        } catch (Exception e) {
            System.out.println("❌ Errore: " + e.getMessage());
            return null;
        }
    }

    private List<String> inserisciGiorni() {
        List<String> giorniValidi = Arrays.asList("LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA");
        List<String> giorni = new ArrayList<>();

        System.out.println("Giorni disponibili: " + giorniValidi);
        System.out.println("Inserisci i giorni uno per volta, 'fine' per terminare:");

        while (true) {
            String giorno = Utilità.chiediStringaNonVuota("Giorno: ").toUpperCase();
            if (giorno.equals("FINE")) break;

            if (giorniValidi.contains(giorno) && !giorni.contains(giorno)) {
                giorni.add(giorno);
                System.out.println("✅ Aggiunto: " + giorno);
            } else if (giorni.contains(giorno)) {
                System.out.println("⚠️ Già inserito!");
            } else {
                System.out.println("❌ Giorno non valido!");
            }
        }

        if (giorni.isEmpty()) {
            giorni.add("SABATO");
            System.out.println("🔄 Aggiunto SABATO come default");
        }

        return giorni;
    }

    private void associaVolontari(Visita visita) {
        System.out.println("\n--- ASSOCIA VOLONTARI ---");
        gestioneVolontari.visualizzaVolontari();

        List<String> volontariScelti = new ArrayList<>();
        System.out.println("Inserisci numeri separati da virgola (es. 1,3):");
        String input = Utilità.chiediStringaNonVuota("Volontari: ");

        String[] numeri = input.split(",");
        for (String numero : numeri) {
            try {
                int index = Integer.parseInt(numero.trim()) - 1;
                List<Volontario> volontari = gestioneVolontari.getVolontari();

                if (index >= 0 && index < volontari.size()) {
                    String nickname = volontari.get(index).getNickname();
                    if (!volontariScelti.contains(nickname)) {
                        volontariScelti.add(nickname);
                        gestioneVolontari.aggiungiCompetenzaAVolontario(nickname, visita.getTitolo());
                        System.out.println("✅ Associato: " + nickname);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Numero non valido: " + numero);
            }
        }

        if (volontariScelti.isEmpty()) {
            // Associa il primo disponibile
            List<String> nicknames = gestioneVolontari.getNicknameVolontari();
            if (!nicknames.isEmpty()) {
                volontariScelti.add(nicknames.get(0));
                gestioneVolontari.aggiungiCompetenzaAVolontario(nicknames.get(0), visita.getTitolo());
                System.out.println("🔄 Associato automaticamente: " + nicknames.get(0));
            }
        }

        visita.setVolontariAssociati(volontariScelti);
    }

    private void visualizzaTutteLeVisite() {
        System.out.println("\n=== TUTTE LE VISITE ===");
        boolean trovatoVisite = false;

        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
            if (!luogo.getTipiVisita().isEmpty()) {
                System.out.println("\n📍 " + luogo.getNome() + ":");
                for (Visita visita : luogo.getTipiVisita()) {
                    System.out.println("  🎯 " + visita.getTitolo());
                    System.out.println("     📅 " + visita.getDataInizio() + " - " + visita.getDataFine());
                    System.out.println("     👥 " + visita.getVolontariAssociati());
                }
                trovatoVisite = true;
            }
        }

        if (!trovatoVisite) {
            System.out.println("❌ Nessuna visita configurata.");
        }
    }

    private void modificaVisita() {
        // Implementazione per modificare visite esistenti
        System.out.println("🚧 Funzionalità in sviluppo");
    }

    private void eliminaVisita() {
        // Implementazione per eliminare visite
        System.out.println("🚧 Funzionalità in sviluppo");
    }

    private void associaVolontarioAVisita() {
        // Implementazione per modificare associazioni
        System.out.println("🚧 Funzionalità in sviluppo");
    }

    private void visualizzaConfigurazioneCompleta() {
        System.out.println("\n=== CONFIGURAZIONE COMPLETA ===");

        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
            System.out.println("\n📍 LUOGO: " + luogo.getNome());
            System.out.println("   📝 " + luogo.getDescrizione());
            System.out.println("   📌 " + luogo.getCollocazioneGeografica());

            if (luogo.getTipiVisita().isEmpty()) {
                System.out.println("   ⚠️ Nessuna visita configurata");
            } else {
                for (Visita visita : luogo.getTipiVisita()) {
                    System.out.println("\n   🎯 " + visita.getTitolo());
                    System.out.println("      📝 " + visita.getDescrizione());
                    System.out.println("      📍 Incontro: " + visita.getPuntoIncontro());
                    System.out.println("      📅 Periodo: " + visita.getDataInizio() + " - " + visita.getDataFine());
                    System.out.println("      📆 Giorni: " + visita.getGiorniSettimanali());
                    System.out.println("      🕐 Orario: " + visita.getOraInizio() + " (" + visita.getDurata() + " min)");
                    System.out.println("      👥 Partecipanti: " + visita.getPartecipantiMin() + "-" + visita.getPartecipantiMax());
                    System.out.println("      🎫 Biglietto: " + (visita.isBigliettoNecessario() ? "Sì" : "No"));
                    System.out.println("      👨‍💼 Volontari: " + visita.getVolontariAssociati());
                }
            }
        }

        // Statistiche
        int totVisite = gestioneLuoghi.getLuoghi().stream().mapToInt(l -> l.getTipiVisita().size()).sum();
        System.out.println("\n📊 STATISTICHE:");
        System.out.println("   📍 Luoghi: " + gestioneLuoghi.getLuoghi().size());
        System.out.println("   🎯 Visite: " + totVisite);
        System.out.println("   👥 Volontari: " + gestioneVolontari.getVolontari().size());
    }
}

/*package Versione1.Gestori;

import Versione1.Entità.*;
import Versione1.Utilità;
import java.util.*;


public class GestioneVisite {


    public Visita creaVisita() {
        String titolo = Utilità.chiediStringaNonVuota("Inserisci il titolo della visita: ");
        String descrizione = Utilità.chiediStringaNonVuota("Inserisci la descrizione della visita: ");
        String puntoIncontro = Utilità.chiediStringaNonVuota("Inserisci il punto di incontro: ");
        // Per le date, supponiamo che esista un metodo chiediData
        Data dataVisita = Utilità.creaData("Inserisci la data della visita (formato gg/mm/aaaa): ");
        Data dataInizio = Utilità.creaData("Inserisci la data di inizio (formato gg/mm/aaaa): ");
        Data dataFine = Utilità.creaData("Inserisci la data di fine (formato gg/mm/aaaa): ");
        boolean biglietto = Utilità.chiediStringaNonVuota("La visita richiede un biglietto? (si/no): ").equalsIgnoreCase("si");
        String orario = Utilità.chiediStringaNonVuota("Inserisci l'orario della visita (es. 10:00): ");
        String durata = Utilità.chiediStringaNonVuota("Inserisci la durata della visita (es. 2 ore): ");
        String[] giorniDisponibili = Utilità.chiediStringaNonVuota("Inserisci i giorni disponibili separati da virgola (es. lunedì,mercoledì,venerdì): ").split(",");
        int numeroMinimo = Integer.parseInt(Utilità.chiediStringaNonVuota("Inserisci il numero minimo di partecipanti: "));
        int numeroMassimo = Integer.parseInt(Utilità.chiediStringaNonVuota("Inserisci il numero massimo di partecipanti: "));

        String scelta = Utilità.chiediStringaNonVuota("VUOI INSERIRE UN VOLONTARIO NUOVO? (Y/N) : \n");
        GestioneVolontari gestVolontari = new GestioneVolontari(Utilità.leggiJSonVolontari());

        Volontario volontario = new Volontario(null);

        if (scelta.equals("Y")) {
            volontario = gestVolontari.creaVolontario();
        } else {
            volontario = gestVolontari.cercaVolontario();
        }


        return new Visita(titolo, descrizione, puntoIncontro, dataVisita, dataInizio, dataFine, biglietto, orario, durata, giorniDisponibili, numeroMinimo, numeroMassimo, volontario);
    }

    public void aggiungiVisita() {
        String nome = Utilità.chiediStringaNonVuota("INSERISCI NOME: \n");

        List<Luogo> luoghi = Utilità.leggiJsonInLista("luoghi.json", Luogo.class);
        for (Luogo luogo : luoghi) {
            if (nome.equals(luogo.getNome())) {
                System.out.println(luogo.getNome() + "Trovato, inserisci la visita\n");
                creaVisita();
                System.out.println(toString());
                break;
            }
        }

    }

    public void stampaVisite(){




    }

}
*/







