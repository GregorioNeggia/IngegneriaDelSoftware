package Versione1.Gestori;

import java.util.*;
import Versione1.Entità.*;
import Versione1.Utilita;

public class GestioneVolontari {
    private List<Volontario> volontari;
    private String nomeFile = "Versione1/Database/volontari.json";
    private Scanner scanner = new Scanner(System.in);

    public GestioneVolontari() {
        this.volontari = Utilita.leggiJsonInLista(nomeFile, Volontario.class);
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE VOLONTARI ===");
            System.out.println("1. Aggiungi volontario");
            System.out.println("2. Visualizza volontari");
            System.out.println("3. Modifica volontario");
            System.out.println("4. Elimina volontario");
            System.out.println("5. Visualizza competenze volontari");
            System.out.println("6. Esci");
            System.out.print("Scegli: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    aggiungiVolontario();
                    break;
                case "2":
                    visualizzaVolontari();
                    break;
                case "3":
                    modificaVolontario();
                    break;
                case "4":
                    eliminaVolontario();
                    break;
                case "5":
                    visualizzaCompetenze();
                    break;
                case "6":
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    private void aggiungiVolontario() {
        System.out.println("\n--- AGGIUNGI VOLONTARIO ---");
        String nickname = Utilita.chiediStringaNonVuota("Nickname del volontario: ");

        if (trovaVolontarioPerNickname(nickname) != null) {
            System.out.println("❌ Volontario già esistente!");
            return;
        }

        Volontario nuovoVolontario = new Volontario(nickname);
        volontari.add(nuovoVolontario);

        System.out.println("✅ Volontario aggiunto!");
        salva();
    }

    public void visualizzaVolontari() {
        System.out.println("\n--- VOLONTARI REGISTRATI ---");
        if (volontari.isEmpty()) {
            System.out.println("Nessun volontario registrato.");
            return;
        }

        for (int i = 0; i < volontari.size(); i++) {
            Volontario vol = volontari.get(i);
            System.out.println((i + 1) + ". 👤 " + vol.getNickname());
            System.out.println("   🎯 Competenze: " + vol.getTipiVisitaAssociati().size() + " tipi di visita");
        }
    }

    private void modificaVolontario() {
        if (volontari.isEmpty()) {
            System.out.println("Nessun volontario da modificare.");
            return;
        }

        visualizzaVolontari();
        int scelta = Utilita.leggiIntero("Scegli il volontario da modificare: ") - 1;

        if (scelta >= 0 && scelta < volontari.size()) {
            Volontario vol = volontari.get(scelta);
            String nuovoNickname = Utilita.chiediStringaNonVuota("Nuovo nickname (attuale: " + vol.getNickname() + "): ");

            if (!nuovoNickname.equals(vol.getNickname()) && trovaVolontarioPerNickname(nuovoNickname) != null) {
                System.out.println("❌ Nickname già esistente!");
                return;
            }

            vol.setNickname(nuovoNickname);
            System.out.println("✅ Volontario modificato!");
            salva();
        }
    }

    private void eliminaVolontario() {
        if (volontari.isEmpty()) {
            System.out.println("Nessun volontario da eliminare.");
            return;
        }

        visualizzaVolontari();
        int scelta = Utilita.leggiIntero("Scegli il volontario da eliminare: ") - 1;

        if (scelta >= 0 && scelta < volontari.size()) {
            Volontario vol = volontari.get(scelta);
            String conferma = Utilita.chiediStringaNonVuota("Confermi eliminazione di '" + vol.getNickname() + "'? (si/no): ");

            if (conferma.equalsIgnoreCase("si")) {
                volontari.remove(scelta);
                System.out.println("✅ Volontario eliminato!");
                salva();
            }
        }
    }

    private void visualizzaCompetenze() {
        System.out.println("\n--- COMPETENZE VOLONTARI ---");
        for (Volontario vol : volontari) {
            System.out.println("\n👤 " + vol.getNickname() + ":");
            if (vol.getTipiVisitaAssociati().isEmpty()) {
                System.out.println("   ⚠️ Nessuna competenza registrata");
            } else {
                for (String competenza : vol.getTipiVisitaAssociati()) {
                    System.out.println("   ✅ " + competenza);
                }
            }
        }
    }

    // Metodi pubblici per altre classi
    public List<Volontario> getVolontari() {
        return new ArrayList<>(volontari);
    }

    public Volontario trovaVolontarioPerNickname(String nickname) {
        return volontari.stream()
                .filter(v -> v.getNickname().equalsIgnoreCase(nickname))
                .findFirst()
                .orElse(null);
    }

    public void aggiungiCompetenzaAVolontario(String nickname, String titoloVisita) {
        Volontario vol = trovaVolontarioPerNickname(nickname);
        if (vol != null) {
            vol.aggiungiTipoVisita(titoloVisita);
            salva();
        }
    }

    public List<String> getNicknameVolontari() {
        return volontari.stream()
                .map(Volontario::getNickname)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private void salva() {
        Utilita.scriviListaInJson(nomeFile, volontari);
    }
}

/*package Versione1.Gestori;
import Versione1.Entità.*;
import java.util.*; 
import Versione1.Utilità;

public class GestioneVolontari {
    private String pathJson = "volontari.json";

    private List<Volontario> volontari;

    public GestioneVolontari(List<Volontario> volontari) {
        this.volontari = volontari != null ? volontari : new ArrayList<Volontario>();
    }

    public Volontario creaVolontario() {
        boolean fine = false;

        try {
            volontari = Utilità.leggiJSonVolontari();
            while (!fine) {
                String user = Utilità.chiediStringaNonVuota("Inserisci l'username del volontario");
                for (Volontario v : volontari) {

                    if (v.getUser().equals(user)) {
                        System.out.println("Errore: Username già esistente. Riprova.");
                        break;
                    } else {
                        Volontario nuovoVolontario = new Volontario(user);
                        volontari.add(nuovoVolontario);
                        Utilità.scriviJSonVolontari(pathJson, volontari);
                        System.out.println("Volontario aggiunto con successo.");
                        Utilità.scriviJSonVolontari(pathJson, volontari);
                        fine = true;
                        return nuovoVolontario;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Errore durante la lettura dei volontari: " + e.getMessage());
            return null;
        }
        return null;

    }


    public Volontario cercaVolontario() {

        List<Volontario> volontari = Utilità.leggiJSonVolontari();
        try {
            String nome = Utilità.chiediStringaNonVuota("Inserisci l'nome del volontario: \n");
            for (Volontario v : volontari) {
                if (v.getUser().equals(nome)) {
                    return v;
                } else {
                    System.out.println("VOLONTARIO " + nome + " NON TROVATO");
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura dei volontario: " + e.getMessage());
        }

        return null;
    }










    
}
*/