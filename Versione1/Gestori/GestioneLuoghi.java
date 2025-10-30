package Versione1.Gestori;

import java.util.*;
import Versione1.Entità.*;
import Versione1.Utilita;

public class GestioneLuoghi {
    private List<Luogo> luoghi;
    private String nomeFile = "Versione1/Database/luoghi.json";
    private Scanner scanner = new Scanner(System.in);

    public GestioneLuoghi() {
        this.luoghi = Utilita.leggiJsonInLista(nomeFile, Luogo.class);
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE LUOGHI ===");
            System.out.println("1. Aggiungi luogo");
            System.out.println("2. Visualizza luoghi");
            System.out.println("3. Modifica luogo");
            System.out.println("4. Elimina luogo");
            System.out.println("5. Esci");
            System.out.print("Scegli: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    aggiungiLuogo();
                    break;
                case "2":
                    visualizzaLuoghi();
                    break;
                case "3":
                    modificaLuogo();
                    break;
                case "4":
                    eliminaLuogo();
                    break;
                case "5":
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    private void aggiungiLuogo() {
        System.out.println("\n--- AGGIUNGI NUOVO LUOGO ---");
        String nome = Utilita.chiediStringaNonVuota("Nome del luogo: ");

        if (trovaLuogoPerNome(nome) != null) {
            System.out.println("❌ Luogo già esistente!");
            return;
        }

        String descrizione = Utilita.chiediStringaNonVuota("Descrizione: ");
        String collocazione = Utilita.chiediStringaNonVuota("Collocazione geografica: ");

        Luogo nuovoLuogo = new Luogo(nome, descrizione, collocazione);
        luoghi.add(nuovoLuogo);

        System.out.println("Luogo aggiunto!");
        salva();
    }

    public void visualizzaLuoghi() {
        System.out.println("\n--- LUOGHI REGISTRATI ---");
        if (luoghi.isEmpty()) {
            System.out.println("Nessun luogo registrato.");
            return;
        }

        for (int i = 0; i < luoghi.size(); i++) {
            Luogo luogo = luoghi.get(i);
            System.out.println((i + 1) + "\n nome luogo: " + luogo.getNome());
            System.out.println("descrizione: " + luogo.getDescrizione());
            System.out.println("dove si trova: " + luogo.getCollocazioneGeografica());
        }
    }

    private void modificaLuogo() {
        if (luoghi.isEmpty()) {
            System.out.println("Nessun luogo da modificare.");
            return;
        }

        visualizzaLuoghi();
        int scelta = Utilita.leggiIntero("Scegli il luogo da modificare: ") - 1;

        if (scelta >= 0 && scelta < luoghi.size()) {
            Luogo luogo = luoghi.get(scelta);
            System.out.println("Modifica di: " + luogo.getNome());

            String nuovaDescrizione = Utilita.chiediStringaNonVuota("Nuova descrizione (attuale: " + luogo.getDescrizione() + "): ");
            String nuovaCollocazione = Utilita.chiediStringaNonVuota("Nuova collocazione (attuale: " + luogo.getCollocazioneGeografica() + "): ");

            luogo.setDescrizione(nuovaDescrizione);
            luogo.setCollocazioneGeografica(nuovaCollocazione);

            System.out.println("Luogo modificato!");
            salva();
        }
    }

    private void eliminaLuogo() {
        if (luoghi.isEmpty()) {
            System.out.println("Nessun luogo da eliminare.");
            return;
        }

        visualizzaLuoghi();
        int scelta = Utilita.leggiIntero("Scegli il luogo da eliminare: ") - 1;

        if (scelta >= 0 && scelta < luoghi.size()) {
            Luogo luogo = luoghi.get(scelta);
            String conferma = Utilita.chiediStringaNonVuota("Confermi eliminazione di '" + luogo.getNome() + "'? (si/no): ");

            if (conferma.equalsIgnoreCase("si")) {
                luoghi.remove(scelta);
                System.out.println("Luogo eliminato!");
                salva();
            }
        }
    }

    // Metodi pubblici per altre classi
    public List<Luogo> getLuoghi() {
        return new ArrayList<>(luoghi);
    }

    public Luogo trovaLuogoPerNome(String nome) {
        return luoghi.stream()
                .filter(l -> l.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public void aggiungiVisitaALuogo(String nomeLuogo, TipoVisita tipoVisita) {
        Luogo luogo = trovaLuogoPerNome(nomeLuogo);
        if (luogo != null) {
            luogo.getTipiVisita().add(tipoVisita);
            salva();
        }
    }

    private void salva() {
        Utilita.scriviListaInJson(nomeFile, luoghi);
    }
}




