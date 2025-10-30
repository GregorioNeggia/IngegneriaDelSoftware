package Versione1.Gestori;

import java.util.*;
import java.util.stream.Collectors;
import Versione1.Entità.*;
import Versione1.Utilita;

public class GestioneVisite {
    private List<Visita> visite;           // Visite effettuate
    private GestioneLuoghi gestioneLuoghi;          // Per accedere ai luoghi
    private GestioneTipoVisita gestioneTipoVisita;  // Per accedere ai tipi di visita
    private String nomeFileVisite = "Versione1/Database/visite.json";

    public GestioneVisite(GestioneLuoghi gestioneLuoghi, GestioneTipoVisita gestioneTipoVisita) {
        this.visite = Utilita.leggiJsonInLista(nomeFileVisite, Visita.class);
        this.gestioneLuoghi = gestioneLuoghi;
        this.gestioneTipoVisita = gestioneTipoVisita;
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE VISITE CALENDARIZZATE ===");
            System.out.println("1. Visualizza visite per stato");
            System.out.println("2. Cambia stato visita");
            System.out.println("3. Visualizza visite");
            System.out.println("4. Esci");
            System.out.print("Scegli: ");

            int scelta = Utilita.leggiIntero("");

            switch (scelta) {
                case 1:
                    visualizzaVisitePerStato();
                    break;
                case 2:
                    cambiaStatoVisita();
                    break;
                case 3:
                    visualizzaVisite();
                    break;
                case 4:
                    esci = true;
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }

    private void visualizzaVisitePerStato() {
        System.out.println("\n--- FILTRA VISITE PER STATO ---");
        System.out.println("1. PROPOSTA");
        System.out.println("2. COMPLETA");
        System.out.println("3. CONFERMATA");
        System.out.println("4. CANCELLATA");
        System.out.println("5. EFFETTUATA ");
        System.out.println("6. Tutte");

        int scelta = Utilita.leggiIntero("Scegli stato: ");
        StatoVisita filtro = null;

        switch (scelta) {
            case 1: filtro = StatoVisita.PROPOSTA; break;
            case 2: filtro = StatoVisita.COMPLETA; break;
            case 3: filtro = StatoVisita.CONFERMATA; break;
            case 4: filtro = StatoVisita.CANCELLATA; break;
            case 5: filtro = StatoVisita.EFFETTUATA; break;
            case 6: filtro = null; break;
            default:
                System.out.println("Scelta non valida!");
                return;
        }

        final StatoVisita filtroFinale = filtro;

        List<Visita> visiteFiltrate = (filtroFinale == null) ? visite :
                visite.stream().filter(v -> v.getStato() == filtroFinale).collect(Collectors.toList());

        if (visiteFiltrate.isEmpty()) {
            System.out.println("Nessuna visita trovata.");
        } else {
            System.out.println("\n=== VISITE TROVATE ===");
            for (int i = 0; i < visiteFiltrate.size(); i++) {
                Visita v = visiteFiltrate.get(i);
                System.out.println((i + 1) + ". " + v);
                System.out.println("   Volontario: " + v.getVolontarioAssegnato());
                System.out.println("   Iscritti: " + v.getNumeroIscritti());
            }
        }
    }

    private void cambiaStatoVisita() {
        System.out.println("\n--- CAMBIA STATO VISITA ---");

        if (visite.isEmpty()) {
            System.out.println("Nessuna visita presente.");
            return;
        }

        // Mostra visite
        for (int i = 0; i < visite.size(); i++) {
            System.out.println((i + 1) + ". " + visite.get(i));
        }

        int scelta = Utilita.leggiIntero("Scegli visita: ") - 1;
        if (scelta < 0 || scelta >= visite.size()) {
            System.out.println("Scelta non valida!");
            return;
        }

        Visita visita = visite.get(scelta);

        System.out.println("\nNuovo stato:");
        System.out.println("1. PROPOSTA");
        System.out.println("2. COMPLETA");
        System.out.println("3. CONFERMATA");
        System.out.println("4. CANCELLATA");
        System.out.println("5. EFFETTUATA");

        int nuovoStato = Utilita.leggiIntero("Scegli: ");

        switch (nuovoStato) {
            case 1:
                visita.setStato(StatoVisita.PROPOSTA);
                break;
            case 2:
                visita.setStato(StatoVisita.COMPLETA);
                break;
            case 3:
                visita.setStato(StatoVisita.CONFERMATA);
                break;
            case 4:
                visita.setStato(StatoVisita.CANCELLATA);
                break;
            case 5:
                visita.setStato(StatoVisita.EFFETTUATA);
                return;
            default:
                System.out.println("Scelta non valida!");
                return;
        }

        System.out.println("✅ Stato aggiornato a: " + visita.getStato());
        salvaVisite();
    }

    private void visualizzaVisite() {
        System.out.println("\n=== VISITE TOTALI ===");

        for (int i = 0; i < visite.size(); i++) {
            Visita v = visite.get(i);
            System.out.println((i + 1) + ". " + v);
            System.out.println("   Volontario: " + v.getVolontarioAssegnato());
            System.out.println("   Partecipanti: " + v.getNumeroIscritti());
        }
    }

    private void salvaVisite() {
        Utilita.scriviListaInJson(nomeFileVisite, visite);
    }

    public List<Visita> getVisite() {
        return new ArrayList<>(visite);
    }

    public TipoVisita getTipoVisitaPerVisita(Visita visita) {
        Luogo luogo = gestioneLuoghi.trovaLuogoPerNome(visita.getNomeLuogo());
        if (luogo == null) return null;

        return luogo.getTipiVisita().stream()
                .filter(tv -> tv.getTitolo().equals(visita.getTitoloTipoVisita()))
                .findFirst()
                .orElse(null);
    }

    public Luogo getLuogoPerVisita(Visita visita) {
        return gestioneLuoghi.trovaLuogoPerNome(visita.getNomeLuogo());
    }

    public void visualizzaDettagliCompleti(Visita visita) {
        TipoVisita tipo = getTipoVisitaPerVisita(visita);
        Luogo luogo = getLuogoPerVisita(visita);

        if (tipo == null || luogo == null) {
            System.out.println("⚠️ Riferimenti non validi!");
            return;
        }

        System.out.println("=== DETTAGLI VISITA ===");
        System.out.println("Titolo: " + tipo.getTitolo());
        System.out.println("Luogo: " + luogo.getNome());
        System.out.println("Data: " + visita.getDataVisita());
        System.out.println("Ora: " + tipo.getOraInizio());
        System.out.println("Durata: " + tipo.getDurata() + " minuti");
        System.out.println("Punto incontro: " + tipo.getPuntoIncontro());
        System.out.println("Volontario: " + visita.getVolontarioAssegnato());
        System.out.println("Stato: " + visita.getStato());
        System.out.println("Iscritti: " + visita.getNumeroIscritti() + "/" + tipo.getPartecipantiMax());
    }

}
