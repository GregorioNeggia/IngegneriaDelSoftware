package Versione1.Gestori;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import Versione1.Entità.*;
import Versione1.Utilita;

public class GestioneTipoVisita {
    private GestioneLuoghi gestioneLuoghi;
    private GestioneVolontari gestioneVolontari;
    private Scanner scanner = new Scanner(System.in);

    public GestioneTipoVisita(GestioneLuoghi gestioneLuoghi, GestioneVolontari gestioneVolontari) {
        this.gestioneLuoghi = gestioneLuoghi;
        this.gestioneVolontari = gestioneVolontari;
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE TIPI DI VISITA ===");
            System.out.println("1. Crea nuovo tipo di visita");
            System.out.println("2. Visualizza tutti i tipi di visita");
            System.out.println("3. Modifica tipo di visita");
            System.out.println("4. Elimina tipo di visita");
            System.out.println("5. Associa volontario a tipo di visita");
            System.out.println("6. Visualizza configurazione completa");
            System.out.println("7. Esci");
            System.out.print("Scegli: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    creaTipoVisita();
                    break;
                case "2":
                    visualizzaTuttiITipiDiVisita();
                    break;
                case "3":
                    modificaTipoVisita();
                    break;
                case "4":
                    eliminaTipoVisita();
                    break;
                case "5":
                    associaVolontarioATipoVisita();
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

    private void creaTipoVisita() {
        if (gestioneLuoghi.getLuoghi().isEmpty()) {
            System.out.println("Devi prima creare almeno un luogo!");
            return;
        }
        if (gestioneVolontari.getVolontari().isEmpty()) {
            System.out.println("Devi prima registrare almeno un volontario!");
            return;
        }

        System.out.println("\n--- CREA NUOVO TIPO DI VISITA ---");

        // Scegli luogo
        gestioneLuoghi.visualizzaLuoghi();
        int sceltaLuogo = Utilita.leggiIntero("Scegli il luogo per questo tipo di visita: ") - 1;
        List<Luogo> luoghi = gestioneLuoghi.getLuoghi();

        if (sceltaLuogo < 0 || sceltaLuogo >= luoghi.size()) {
            System.out.println("Scelta non valida!");
            return;
        }

        Luogo luogoScelto = luoghi.get(sceltaLuogo);

        // Crea il tipo di visita
        TipoVisita nuovaTipoVisita = costruisciTipoVisita();
        if (nuovaTipoVisita != null) {
            // Associa volontari
            associaVolontari(nuovaTipoVisita);

            // Aggiungi al luogo
            gestioneLuoghi.aggiungiVisitaALuogo(luogoScelto.getNome(), nuovaTipoVisita);

            System.out.println("[OK] Tipo di visita creato e associato a: " + luogoScelto.getNome());
        }
    }

    private TipoVisita costruisciTipoVisita() {
        try {
            String titolo = Utilita.chiediStringaNonVuota("Titolo del tipo di visita: ");
            String descrizione = Utilita.chiediStringaNonVuota("Descrizione: ");
            String puntoIncontro = Utilita.chiediStringaNonVuota("Punto di incontro: ");

            // Date
            LocalDate dataInizio = Utilita.leggiData("Data inizio Periodo");
            LocalDate dataFine = Utilita.leggiData("Data fine Periodo");

            if (dataFine.isBefore(dataInizio)) {
                System.out.println("[ERRORE] Data fine deve essere >= data inizio!");
                return null;
            }

            // Giorni
            List<String> giorni = inserisciGiorni();

            // Orario
            LocalTime ora = Utilita.leggiOrario("Inserisci ora di inizio");

            int durata = Utilita.leggiIntero("Durata in minuti: ");

            boolean biglietto = Utilita.chiediStringaNonVuota("Biglietto necessario? (si/no): ").equalsIgnoreCase("si");

            int minPart = Utilita.leggiIntero("Numero minimo partecipanti: ");
            int maxPart = Utilita.leggiIntero("Numero massimo partecipanti: ");

            if (maxPart < minPart) {
                System.out.println("[ERRORE] Massimo deve essere >= minimo!");
                return null;
            }

            return new TipoVisita(titolo, descrizione, puntoIncontro, dataInizio, dataFine,
                    giorni, ora, durata, biglietto, minPart, maxPart);

        } catch (Exception e) {
            System.out.println("[ERRORE] " + e.getMessage());
            return null;
        }
    }

    private List<String> inserisciGiorni() {
        List<String> giorniValidi = Arrays.asList("LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA");
        List<String> giorni = new ArrayList<>();

        System.out.println("Giorni disponibili: " + giorniValidi);
        System.out.println("Inserisci i giorni uno per volta, 'fine' per terminare:");

        while (true) {
            String giorno = Utilita.chiediStringaNonVuota("Giorno: ").toUpperCase();
            if (giorno.equals("FINE")) break;

            if (giorniValidi.contains(giorno) && !giorni.contains(giorno)) {
                giorni.add(giorno);
                System.out.println("[OK] Aggiunto: " + giorno);
            } else if (giorni.contains(giorno)) {
                System.out.println("[AVVISO] Già inserito!");
            } else {
                System.out.println("[ERRORE] Giorno non valido!");
            }
        }

        if (giorni.isEmpty()) {
            giorni.add("SABATO");
            System.out.println("[OK] Aggiunto SABATO come default");
        }

        return giorni;
    }

    private void associaVolontari(TipoVisita tipoVisita) {
        System.out.println("\n--- ASSOCIA VOLONTARI AL TIPO DI VISITA ---");
        gestioneVolontari.visualizzaVolontari();

        List<String> volontariScelti = new ArrayList<>();
        System.out.println("Inserisci numeri separati da virgola (es. 1,3):");
        String input = Utilita.chiediStringaNonVuota("Volontari: ");

        String[] numeri = input.split(",");
        for (String numero : numeri) {
            try {
                int index = Integer.parseInt(numero.trim()) - 1;
                List<Volontario> volontari = gestioneVolontari.getVolontari();

                if (index >= 0 && index < volontari.size()) {
                    String nickname = volontari.get(index).getNickname();
                    if (!volontariScelti.contains(nickname)) {
                        volontariScelti.add(nickname);
                        gestioneVolontari.aggiungiCompetenzaAVolontario(nickname, tipoVisita.getTitolo());
                        System.out.println("[OK] Associato: " + nickname);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("[ERRORE] Numero non valido: " + numero);
            }
        }

        if (volontariScelti.isEmpty()) {
            // Associa il primo disponibile
            List<String> nicknames = gestioneVolontari.getNicknameVolontari();
            if (!nicknames.isEmpty()) {
                volontariScelti.add(nicknames.get(0));
                gestioneVolontari.aggiungiCompetenzaAVolontario(nicknames.get(0), tipoVisita.getTitolo());
                System.out.println("[OK] Associato automaticamente: " + nicknames.get(0));
            }
        }

        tipoVisita.setVolontariAssociati(volontariScelti);
    }

    private void visualizzaTuttiITipiDiVisita() {
        System.out.println("\n=== TUTTI I TIPI DI VISITA ===");
        boolean trovatoTipi = false;

        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
            if (!luogo.getTipiVisita().isEmpty()) {
                System.out.println("\n[LUOGO] " + luogo.getNome() + ":");
                for (TipoVisita tipoVisita : luogo.getTipiVisita()) {
                    System.out.println("  [TIPO] " + tipoVisita.getTitolo());
                    System.out.println("     [DATA] " + tipoVisita.getDataInizio() + " - " + tipoVisita.getDataFine());
                    System.out.println("     [VOLONTARI] " + tipoVisita.getVolontariAssociati());
                }
                trovatoTipi = true;
            }
        }

        if (!trovatoTipi) {
            System.out.println("[AVVISO] Nessun tipo di visita configurato.");
        }
    }

    private void modificaTipoVisita() {
        // Implementazione per modificare tipi di visita esistenti
        System.out.println("[INFO] Funzionalità in sviluppo");
    }

    private void eliminaTipoVisita() {
        // Implementazione per eliminare tipi di visita
        System.out.println("[INFO] Funzionalità in sviluppo");
    }

    private void associaVolontarioATipoVisita() {
        // Implementazione per modificare associazioni volontari/tipi di visita
        System.out.println("[INFO] Funzionalità in sviluppo");
    }

    private void visualizzaConfigurazioneCompleta() {
        System.out.println("\n=== CONFIGURAZIONE COMPLETA ===");

        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
            System.out.println("\n[LUOGO] " + luogo.getNome());
            System.out.println("   [DESC] " + luogo.getDescrizione());
            System.out.println("   [LOC] " + luogo.getCollocazioneGeografica());

            if (luogo.getTipiVisita().isEmpty()) {
                System.out.println("   [AVVISO] Nessun tipo di visita configurato");
            } else {
                for (TipoVisita tipoVisita : luogo.getTipiVisita()) {
                    System.out.println("\n   [TIPO VISITA] " + tipoVisita.getTitolo());
                    System.out.println("      [DESC] " + tipoVisita.getDescrizione());
                    System.out.println("      [INCONTRO] " + tipoVisita.getPuntoIncontro());
                    System.out.println("      [PERIODO] " + tipoVisita.getDataInizio() + " - " + tipoVisita.getDataFine());
                    System.out.println("      [GIORNI] " + tipoVisita.getGiorniSettimanali());
                    System.out.println("      [ORARIO] " + tipoVisita.getOraInizio() + " (" + tipoVisita.getDurata() + " min)");
                    System.out.println("      [PARTECIPANTI] " + tipoVisita.getPartecipantiMin() + "-" + tipoVisita.getPartecipantiMax());
                    System.out.println("      [BIGLIETTO] " + (tipoVisita.isBigliettoNecessario() ? "Si" : "No"));
                    System.out.println("      [VOLONTARI] " + tipoVisita.getVolontariAssociati());
                }
            }
        }

        // Statistiche
        int totTipiVisita = gestioneLuoghi.getLuoghi().stream().mapToInt(l -> l.getTipiVisita().size()).sum();
        System.out.println("\n=== STATISTICHE ===");
        System.out.println("   [LUOGHI] " + gestioneLuoghi.getLuoghi().size());
        System.out.println("   [TIPI VISITA] " + totTipiVisita);
        System.out.println("   [VOLONTARI] " + gestioneVolontari.getVolontari().size());
    }
}

//package Versione1.Gestori;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//import Versione1.Entità.*;
//import Versione1.Utilita;
//
//public class GestioneTipoVisita {
//    private GestioneLuoghi gestioneLuoghi;
//    private GestioneVolontari gestioneVolontari;
//    private Scanner scanner = new Scanner(System.in);
//
//    public GestioneTipoVisita(GestioneLuoghi gestioneLuoghi, GestioneVolontari gestioneVolontari) {
//        this.gestioneLuoghi = gestioneLuoghi;
//        this.gestioneVolontari = gestioneVolontari;
//    }
//
//    public void menu() {
//        boolean esci = false;
//
//        while (!esci) {
//            System.out.println("\n=== GESTIONE VISITE ===");
//            System.out.println("1. Crea nuova visita");
//            System.out.println("2. Visualizza tutte le visite");
//            System.out.println("3. Modifica visita");
//            System.out.println("4. Elimina visita");
//            System.out.println("5. Associa volontario a visita");
//            System.out.println("6. Visualizza configurazione completa");
//            System.out.println("7. Esci");
//            System.out.print("Scegli: ");
//
//            String scelta = scanner.nextLine();
//
//            switch (scelta) {
//                case "1":
//                    creaVisita();
//                    break;
//                case "2":
//                    visualizzaTutteLeVisite();
//                    break;
//                case "3":
//                    modificaVisita();
//                    break;
//                case "4":
//                    eliminaVisita();
//                    break;
//                case "5":
//                    associaVolontarioAVisita();
//                    break;
//                case "6":
//                    visualizzaConfigurazioneCompleta();
//                    break;
//                case "7":
//                    esci = true;
//                    break;
//                default:
//                    System.out.println("Scelta non valida!");
//            }
//        }
//    }
//
//    private void creaVisita() {
//        if (gestioneLuoghi.getLuoghi().isEmpty()) {
//            System.out.println("Devi prima creare almeno un luogo!");
//            return;
//        }
//        if (gestioneVolontari.getVolontari().isEmpty()) {
//            System.out.println("Devi prima registrare almeno un volontario!");
//            return;
//        }
//
//        System.out.println("\n--- CREA NUOVA VISITA ---");
//
//        // Scegli luogo
//        gestioneLuoghi.visualizzaLuoghi();
//        int sceltaLuogo = Utilita.leggiIntero("Scegli il luogo per la visita: ") - 1;
//        List<Luogo> luoghi = gestioneLuoghi.getLuoghi();
//
//        if (sceltaLuogo < 0 || sceltaLuogo >= luoghi.size()) {
//            System.out.println("Scelta non valida!");
//            return;
//        }
//
//        Luogo luogoScelto = luoghi.get(sceltaLuogo);
//
//        // Crea la visita
//        TipoVisita nuovaTipoVisita = costruisciVisita();
//        if (nuovaTipoVisita != null) {
//            // Associa volontari
//            associaVolontari(nuovaTipoVisita);
//
//            // Aggiungi al luogo
//            gestioneLuoghi.aggiungiVisitaALuogo(luogoScelto.getNome(), nuovaTipoVisita);
//
//            System.out.println("Visita creata e associata a: " + luogoScelto.getNome());
//        }
//    }
//
//    private TipoVisita costruisciVisita() {
//        try {
//            String titolo = Utilita.chiediStringaNonVuota("Titolo della visita: ");
//            String descrizione = Utilita.chiediStringaNonVuota("Descrizione: ");
//            String puntoIncontro = Utilita.chiediStringaNonVuota("Punto di incontro: ");
//
//            // Date
//            LocalDate dataInizio = Utilita.leggiData("Data inizio Periodo");
//            LocalDate dataFine = Utilita.leggiData("Data fine Periodo");
//
//            if (dataFine.isBefore(dataInizio)) {
//                System.out.println("Data fine deve essere >= data inizio!");
//                return null;
//            }
//
//            // Giorni
//            List<String> giorni = inserisciGiorni();
//
//            // Orario
//            LocalTime ora = Utilita.leggiOrario("Inserisci ora di inizio");
//
//            int durata = Utilita.leggiIntero("Durata in minuti: ");
//
//            boolean biglietto = Utilita.chiediStringaNonVuota("Biglietto necessario? (si/no): ").equalsIgnoreCase("si");
//
//            int minPart = Utilita.leggiIntero("Numero minimo partecipanti: ");
//            int maxPart = Utilita.leggiIntero("Numero massimo partecipanti: ");
//
//            if (maxPart < minPart) {
//                System.out.println("❌ Massimo deve essere >= minimo!");
//                return null;
//            }
//
//            return new TipoVisita(titolo, descrizione, puntoIncontro, dataInizio, dataFine,
//                    giorni, ora, durata, biglietto, minPart, maxPart);
//
//        } catch (Exception e) {
//            System.out.println("❌ Errore: " + e.getMessage());
//            return null;
//        }
//    }
//
//    private List<String> inserisciGiorni() {
//        List<String> giorniValidi = Arrays.asList("LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA");
//        List<String> giorni = new ArrayList<>();
//
//        System.out.println("Giorni disponibili: " + giorniValidi);
//        System.out.println("Inserisci i giorni uno per volta, 'fine' per terminare:");
//
//        while (true) {
//            String giorno = Utilita.chiediStringaNonVuota("Giorno: ").toUpperCase();
//            if (giorno.equals("FINE")) break;
//
//            if (giorniValidi.contains(giorno) && !giorni.contains(giorno)) {
//                giorni.add(giorno);
//                System.out.println("Aggiunto: " + giorno);
//            } else if (giorni.contains(giorno)) {
//                System.out.println("Già inserito!");
//            } else {
//                System.out.println("Giorno non valido!");
//            }
//        }
//
//        if (giorni.isEmpty()) {
//            giorni.add("SABATO");
//            System.out.println("Aggiunto SABATO come default");
//        }
//
//        return giorni;
//    }
//
//    private void associaVolontari(TipoVisita tipoVisita) {
//        System.out.println("\n--- ASSOCIA VOLONTARI ---");
//        gestioneVolontari.visualizzaVolontari();
//
//        List<String> volontariScelti = new ArrayList<>();
//        System.out.println("Inserisci numeri separati da virgola (es. 1,3):");
//        String input = Utilita.chiediStringaNonVuota("Volontari: ");
//
//        String[] numeri = input.split(",");
//        for (String numero : numeri) {
//            try {
//                int index = Integer.parseInt(numero.trim()) - 1;
//                List<Volontario> volontari = gestioneVolontari.getVolontari();
//
//                if (index >= 0 && index < volontari.size()) {
//                    String nickname = volontari.get(index).getNickname();
//                    if (!volontariScelti.contains(nickname)) {
//                        volontariScelti.add(nickname);
//                        gestioneVolontari.aggiungiCompetenzaAVolontario(nickname, tipoVisita.getTitolo());
//                        System.out.println("Associato: " + nickname);
//                    }
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Numero non valido: " + numero);
//            }
//        }
//
//        if (volontariScelti.isEmpty()) {
//            // Associa il primo disponibile
//            List<String> nicknames = gestioneVolontari.getNicknameVolontari();
//            if (!nicknames.isEmpty()) {
//                volontariScelti.add(nicknames.get(0));
//                gestioneVolontari.aggiungiCompetenzaAVolontario(nicknames.get(0), tipoVisita.getTitolo());
//                System.out.println("Associato automaticamente: " + nicknames.get(0));
//            }
//        }
//
//        tipoVisita.setVolontariAssociati(volontariScelti);
//    }
//
//    private void visualizzaTutteLeVisite() {
//        System.out.println("\n=== TUTTE LE VISITE ===");
//        boolean trovatoVisite = false;
//
//        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
//            if (!luogo.getTipiVisita().isEmpty()) {
//                System.out.println("\n📍 " + luogo.getNome() + ":");
//                for (TipoVisita tipoVisita : luogo.getTipiVisita()) {
//                    System.out.println("  🎯 " + tipoVisita.getTitolo());
//                    System.out.println("     📅 " + tipoVisita.getDataInizio() + " - " + tipoVisita.getDataFine());
//                    System.out.println("     👥 " + tipoVisita.getVolontariAssociati());
//                }
//                trovatoVisite = true;
//            }
//        }
//
//        if (!trovatoVisite) {
//            System.out.println("Nessuna visita configurata.");
//        }
//    }
//
//    private void modificaVisita() {
//        // Implementazione per modificare visite esistenti
//        System.out.println("Funzionalità in sviluppo");
//    }
//
//    private void eliminaVisita() {
//        // Implementazione per eliminare visite
//        System.out.println("Funzionalità in sviluppo");
//    }
//
//    private void associaVolontarioAVisita() {
//        // Implementazione per modificare associazioni
//        System.out.println("Funzionalità in sviluppo");
//    }
//
//    private void visualizzaConfigurazioneCompleta() {
//        System.out.println("\n=== CONFIGURAZIONE COMPLETA ===");
//
//        for (Luogo luogo : gestioneLuoghi.getLuoghi()) {
//            System.out.println("\n📍 LUOGO: " + luogo.getNome());
//            System.out.println("   📝 " + luogo.getDescrizione());
//            System.out.println("   📌 " + luogo.getCollocazioneGeografica());
//
//            if (luogo.getTipiVisita().isEmpty()) {
//                System.out.println("   ⚠️ Nessuna visita configurata");
//            } else {
//                for (TipoVisita tipoVisita : luogo.getTipiVisita()) {
//                    System.out.println("\n   🎯 " + tipoVisita.getTitolo());
//                    System.out.println("      📝 " + tipoVisita.getDescrizione());
//                    System.out.println("      📍 Incontro: " + tipoVisita.getPuntoIncontro());
//                    System.out.println("      📅 Periodo: " + tipoVisita.getDataInizio() + " - " + tipoVisita.getDataFine());
//                    System.out.println("      📆 Giorni: " + tipoVisita.getGiorniSettimanali());
//                    System.out.println("      🕐 Orario: " + tipoVisita.getOraInizio() + " (" + tipoVisita.getDurata() + " min)");
//                    System.out.println("      👥 Partecipanti: " + tipoVisita.getPartecipantiMin() + "-" + tipoVisita.getPartecipantiMax());
//                    System.out.println("      🎫 Biglietto: " + (tipoVisita.isBigliettoNecessario() ? "Sì" : "No"));
//                    System.out.println("      👨‍💼 Volontari: " + tipoVisita.getVolontariAssociati());
//                }
//            }
//        }
//
//        // Statistiche
//        int totVisite = gestioneLuoghi.getLuoghi().stream().mapToInt(l -> l.getTipiVisita().size()).sum();
//        System.out.println("\n📊 STATISTICHE:");
//        System.out.println("   📍 Luoghi: " + gestioneLuoghi.getLuoghi().size());
//        System.out.println("   🎯 Visite: " + totVisite);
//        System.out.println("   👥 Volontari: " + gestioneVolontari.getVolontari().size());
//    }
//}








