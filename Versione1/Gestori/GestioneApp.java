package Versione1.Gestori;

import Versione1.Entità.PrimoAvvioData;
import Versione1.Utilità;

import java.util.ArrayList;
import java.util.List;

public class GestioneApp {


    public PrimoAvvioData InizializzazioneApp() {
        System.out.println("Primo avvio entro");

        String nomeFile = "PrimoAvvio.json";

        // Carica i dati esistenti dal file JSON
        List<PrimoAvvioData> listaDati = Utilità.leggiJsonInLista(nomeFile, PrimoAvvioData.class);

        PrimoAvvioData dati;

        // Se il file è vuoto o non esiste, crea un oggetto vuoto
        if (listaDati.isEmpty()) {
            dati = new PrimoAvvioData();
        } else {
            // Prende il primo (e presumibilmente unico) elemento
            dati = listaDati.get(0);
        }

        // Verifica se l'ambito territoriale è vuoto o nullo
        if (dati.getAmbitoTerritoriale() == null || dati.getAmbitoTerritoriale().trim().isEmpty()) {
            // Richiede i dati all'utente
            String ambitoTerritoriale = Utilità.chiediStringaNonVuota("INSERISCI IL LUOGO DI INTERESSE: \n");
            int numMaxIscrizioni = Utilità.leggiIntero("MASSIMO VISITATORI: \n");

            // Aggiorna l'oggetto con i nuovi valori
            dati.setAmbitoTerritoriale(ambitoTerritoriale);
            dati.setNumMaxIscrizioni(numMaxIscrizioni);

            // Salva i dati aggiornati nel file JSON
            List<PrimoAvvioData> listaDaSalvare = new ArrayList<>();
            listaDaSalvare.add(dati);
            Utilità.scriviListaInJson(nomeFile, listaDaSalvare);
        }

        return dati;
    }

}
