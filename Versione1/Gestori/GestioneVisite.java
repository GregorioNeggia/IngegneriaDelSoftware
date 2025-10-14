package Versione1.Gestori;

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

        List<Luogo> luoghi = Utilità.leggiJsonInArray("luoghi.json", Luogo[].class);

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








