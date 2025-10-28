package Versione1.Gestori;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import Versione1.Entità.*;
import Versione1.Utilità;

public class GestioneVisite {
    private List<Visita> visite;                    // Visite correnti
    private List<Visita> archivioStorico;           // Visite effettuate
    private GestioneLuoghi gestioneLuoghi;          // Per accedere ai luoghi
    private GestioneTipoVisita gestioneTipoVisita;  // Per accedere ai tipi di visita
    private String nomeFileVisite = "visite.json";
    private String nomeFileArchivio = "archivio_storico.json";

    public GestioneVisite(GestioneLuoghi gestioneLuoghi, GestioneTipoVisita gestioneTipoVisita) {
        this.visite = Utilità.leggiJsonInLista(nomeFileVisite, Visita.class);
        this.archivioStorico = Utilità.leggiJsonInLista(nomeFileArchivio, Visita.class);
        this.gestioneLuoghi = gestioneLuoghi;
        this.gestioneTipoVisita = gestioneTipoVisita;
    }

    public void menu() {
        boolean esci = false;

        while (!esci) {
            System.out.println("\n=== GESTIONE VISITE CALENDARIZZATE ===");
            System.out.println("1. Visualizza visite per stato");
            System.out.println("2. Cambia stato visita");
            System.out.println("3. Visualizza archivio storico");
            System.out.println("4. Esci");
            System.out.print("Scegli: ");

            int scelta = Utilità.leggiIntero("");

            switch (scelta) {
                case 1:
                    visualizzaVisitePerStato();
                    break;
                case 2:
                    cambiaStatoVisita();
                    break;
                case 3:
                    visualizzaArchivioStorico();
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
        System.out.println("5. EFFETTUATA (archivio)");
        System.out.println("6. Tutte (escluso archivio)");

        int scelta = Utilità.leggiIntero("Scegli stato: ");
        StatoVisita filtro = null;
        boolean mostraArchivio = false;

        switch (scelta) {
            case 1: filtro = StatoVisita.PROPOSTA; break;
            case 2: filtro = StatoVisita.COMPLETA; break;
            case 3: filtro = StatoVisita.CONFERMATA; break;
            case 4: filtro = StatoVisita.CANCELLATA; break;
            case 5:
                mostraArchivio = true;
                break;
            case 6: filtro = null; break;
            default:
                System.out.println("Scelta non valida!");
                return;
        }

        if (mostraArchivio) {
            visualizzaArchivioStorico();
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

        int scelta = Utilità.leggiIntero("Scegli visita: ") - 1;
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
        System.out.println("5. EFFETTUATA (sposta in archivio)");

        int nuovoStato = Utilità.leggiIntero("Scegli: ");

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
                archivioStorico.add(visita);
                visite.remove(scelta);
                System.out.println("✅ Visita spostata nell'archivio storico");
                salvaArchivio();
                salvaVisite();
                return;
            default:
                System.out.println("Scelta non valida!");
                return;
        }

        System.out.println("✅ Stato aggiornato a: " + visita.getStato());
        salvaVisite();
    }

    private void visualizzaArchivioStorico() {
        System.out.println("\n=== ARCHIVIO STORICO - VISITE EFFETTUATE ===");

        if (archivioStorico.isEmpty()) {
            System.out.println("Nessuna visita nell'archivio.");
            return;
        }

        for (int i = 0; i < archivioStorico.size(); i++) {
            Visita v = archivioStorico.get(i);
            System.out.println((i + 1) + ". " + v);
            System.out.println("   Volontario: " + v.getVolontarioAssegnato());
            System.out.println("   Partecipanti: " + v.getNumeroIscritti());
        }
    }

    private void salvaVisite() {
        Utilità.scriviListaInJson(nomeFileVisite, visite);
    }

    private void salvaArchivio() {
        Utilità.scriviListaInJson(nomeFileArchivio, archivioStorico);
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
