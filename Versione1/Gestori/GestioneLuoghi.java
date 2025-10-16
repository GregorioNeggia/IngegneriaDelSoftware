package Versione1.Gestori;

import java.util.*;
import Versione1.Entità.*;
import Versione1.Utilità;

public class GestioneLuoghi {
    private List<Luogo> luoghi;
    private String nomeFile = "luoghi.json";
    private Scanner scanner = new Scanner(System.in);

    public GestioneLuoghi() {
        this.luoghi = Utilità.leggiJsonInLista(nomeFile, Luogo.class);
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
        String nome = Utilità.chiediStringaNonVuota("Nome del luogo: ");

        if (trovaLuogoPerNome(nome) != null) {
            System.out.println("❌ Luogo già esistente!");
            return;
        }

        String descrizione = Utilità.chiediStringaNonVuota("Descrizione: ");
        String collocazione = Utilità.chiediStringaNonVuota("Collocazione geografica: ");

        Luogo nuovoLuogo = new Luogo(nome, descrizione, collocazione);
        luoghi.add(nuovoLuogo);

        System.out.println("✅ Luogo aggiunto!");
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
            System.out.println((i + 1) + ". 📍 " + luogo.getNome());
            System.out.println("   📝 " + luogo.getDescrizione());
            System.out.println("   📌 " + luogo.getCollocazioneGeografica());
        }
    }

    private void modificaLuogo() {
        if (luoghi.isEmpty()) {
            System.out.println("Nessun luogo da modificare.");
            return;
        }

        visualizzaLuoghi();
        int scelta = Utilità.leggiIntero("Scegli il luogo da modificare: ") - 1;

        if (scelta >= 0 && scelta < luoghi.size()) {
            Luogo luogo = luoghi.get(scelta);
            System.out.println("Modifica di: " + luogo.getNome());

            String nuovaDescrizione = Utilità.chiediStringaNonVuota("Nuova descrizione (attuale: " + luogo.getDescrizione() + "): ");
            String nuovaCollocazione = Utilità.chiediStringaNonVuota("Nuova collocazione (attuale: " + luogo.getCollocazioneGeografica() + "): ");

            luogo.setDescrizione(nuovaDescrizione);
            luogo.setCollocazioneGeografica(nuovaCollocazione);

            System.out.println("✅ Luogo modificato!");
            salva();
        }
    }

    private void eliminaLuogo() {
        if (luoghi.isEmpty()) {
            System.out.println("Nessun luogo da eliminare.");
            return;
        }

        visualizzaLuoghi();
        int scelta = Utilità.leggiIntero("Scegli il luogo da eliminare: ") - 1;

        if (scelta >= 0 && scelta < luoghi.size()) {
            Luogo luogo = luoghi.get(scelta);
            String conferma = Utilità.chiediStringaNonVuota("Confermi eliminazione di '" + luogo.getNome() + "'? (si/no): ");

            if (conferma.equalsIgnoreCase("si")) {
                luoghi.remove(scelta);
                System.out.println("✅ Luogo eliminato!");
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

    public void aggiungiVisitaALuogo(String nomeLuogo, Visita visita) {
        Luogo luogo = trovaLuogoPerNome(nomeLuogo);
        if (luogo != null) {
            luogo.getTipiVisita().add(visita);
            salva();
        }
    }

    private void salva() {
        Utilità.scriviListaInJson(nomeFile, luoghi);
    }
}

/*package Versione1.Gestori;

import Versione1.Entità.Luogo;
import Versione1.Utilità;
import java.util.*;


public class GestioneLuoghi {



    public Luogo creaLuogo(){
        String nome = Utilità.chiediStringaNonVuota("INSERISCI IL NOME DEL LUOGO: \n");
        String descrizione = Utilità.chiediStringaNonVuota("INSERISCI LA DESCRIZIONE: \n");
        String posizione = Utilità.chiediStringaNonVuota("INSERISCI POSIZIONE: \n");



        return new Luogo(nome, descrizione, posizione, null);
    }





}*/
