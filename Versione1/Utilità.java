package Versione1;

import java.util.*;
import com.google.gson.*;
import Versione1.Entità.*;
import Versione1.Gestori.GestoreVolontari;

import java.io.*;


public class Utilità {

    
    //METODI DI UTILITA GENERALE
    private static Scanner scanner = new Scanner(System.in);


    public static String chiediStringa(String messaggio) {
        System.out.print(messaggio + ": ");
        return scanner.nextLine();
    }
    
    public static String chiediStringa() {
        return chiediStringa("Inserisci una stringa");
    }
    
    
    public static String chiediStringaNonVuota(String messaggio) {
        String input;
        do {
            input = chiediStringa(messaggio);
            if (input.trim().isEmpty()) {
                System.out.println("Errore: La stringa non può essere vuota. Riprova.");
            }
        } while (input.trim().isEmpty());
        return input.trim();
    }

    public static int leggiIntero(Scanner scanner, String messaggio) {
        System.out.print(messaggio);
        while (!scanner.hasNextInt()) {
            System.out.print("Input non valido. Inserisci un numero intero:");
            scanner.next();
        }
        return scanner.nextInt();
//    while (true) {
//        try {
//            int numero = scanner.nextInt();
//            scanner.nextLine(); // pulisce il buffer
//            return numero;
//        } catch (InputMismatchException e) {
//            System.out.println("Input non valido! Inserisci un numero.");
//            scanner.nextLine(); // pulisce input errato
//        }
//        }
    }

    public static Data creaData(String messaggio){
        System.out.println(messaggio);
        int giorno = Utilità.leggiIntero(scanner, "Inserisci il giorno (1-31): ");
        int mese = Utilità.leggiIntero(scanner, "Inserisci il mese (1-12): ");
        int anno = Utilità.leggiIntero(scanner, "Inserisci l'anno (es. 2023): ");
        return new Data(giorno, mese, anno);
    }
    

    
    public void creaLuogo(Configuratore c){
    }



    public Visita CreaVisita(){

        try {
            String titolo = Utilità.chiediStringaNonVuota("Inserisci il nome della visita: ");
            String descrizione = Utilità.chiediStringaNonVuota("Inserisci la descrizione della visita: ");
            String puntoIncontro = Utilità.chiediStringaNonVuota("Inserisci il punto di incontro della visita: ");
            Data dataVisita = Utilità.creaData("Inserisci la data della visita: ");
            Data dataInizio = Utilità.creaData("Inserisci la data di inizio della visita: ");
            Data dataFine = Utilità.creaData("Inserisci la data di fine della visita: ");
            boolean biglietto = Utilità.chiediStringaNonVuota("La visita richiede un biglietto? (si/no): ").equalsIgnoreCase("si");
            String orario = Utilità.chiediStringaNonVuota("Inserisci l'orario della visita (es. 10:00): ");
            String durata = Utilità.chiediStringaNonVuota("Inserisci la durata della visita (es. 2 ore): ");
            String[] giorniDisponibili = Utilità.chiediStringaNonVuota("Inserisci i giorni disponibili separati da virgola (es. lunedì,mercoledì,venerdì): ").split(",");
            int numeroMinimo = Utilità.leggiIntero(scanner, "Inserisci il numero minimo di partecipanti: ");
            int numeroMassimo = Utilità.leggiIntero(scanner, "Inserisci il numero massimo di partecipanti: ");
            Volontario volontario = new GestoreVolontari(null).creaVolontario();

            return new Visita(titolo, descrizione, puntoIncontro, dataVisita, dataInizio, dataFine, biglietto, orario, durata, giorniDisponibili, numeroMinimo, numeroMassimo, volontario);
            
        } catch (Exception e) {
            System.out.println("Errore durante la creazione della visita: " + e.getMessage());
        }

        return null;
    }

    //METODI PER JSON

    public static void scriviJSonConfiguratori(String nomeFile, List<Configuratore> configuratori){
        File file = new File(nomeFile);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(configuratori);
            bw.write(json);

        }
        catch (IOException e){
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public static List<Configuratore> leggiJSonConfiguratori(String nomeFile){

        List<Configuratore> configuratori = new ArrayList<>();

        File file = new File(nomeFile);

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            Gson gson = new Gson();
            List<Configuratore> configuratoriDaFile = Arrays.asList(gson.fromJson(br, Configuratore[].class));
            configuratori.addAll(configuratoriDaFile);
        }
        catch (IOException e){
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return configuratori;
    }

    public static void stampaConfiguratori(List<Configuratore> configuratori){
        if(configuratori.isEmpty()){
            System.out.println("Nessun configuratore registrato.");
        }
        else{
            for(Configuratore c : configuratori){
                System.out.println("- Username: " + c.getUsername());
            }
        }
    }

    public static List<Volontario> leggiJSonVolontari(String nomeFile){

        List<Volontario> volontari = new ArrayList<>();

        File file = new File(nomeFile);

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            Gson gson = new Gson();
            List<Volontario> volontariDaFile = Arrays.asList(gson.fromJson(br, Volontario[].class));
            volontari.addAll(volontariDaFile);
        }
        catch (IOException e){
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return volontari;
    }

    public static void scriviJSonVolontari(String nomeFile, List<Volontario> volontari){
        File file = new File(nomeFile);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(volontari);
            bw.write(json);

        }
        catch (IOException e){
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    


    
}
