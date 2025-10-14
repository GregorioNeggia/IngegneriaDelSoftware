package Versione1;

import java.util.*;
import com.google.gson.*;
import Versione1.Entità.*;

import java.io.*;


public class Utilità {

    
    //METODI DI UTILITA GENERALE
    public static Scanner scanner = new Scanner(System.in);


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

    public static int leggiIntero(String messaggio) {
        System.out.print(messaggio);
        while (!scanner.hasNextInt()) {
            System.out.print("Input non valido. Inserisci un numero intero:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static Data creaData(String messaggio){
        System.out.println(messaggio);
        int giorno = Utilità.leggiIntero( "Inserisci il giorno (1-31): ");
        int mese = Utilità.leggiIntero("Inserisci il mese (1-12): ");
        int anno = Utilità.leggiIntero( "Inserisci l'anno (es. 2023): ");
        return new Data(giorno, mese, anno);
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

    public static List<Volontario> leggiJSonVolontari(){
        String nomeFile = "volontari.json";

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

    public static void scriviJsonConfigurazione(PrimoAvvioData dati){
        String nomeFile = "PrimoAvvio.json";
        File file = new File(nomeFile);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dati);
            bw.write(json);

        }
        catch (IOException e){
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
            }
        }





//      LETTURA E SCRITTURA GENERALE DA FILE JSON IN UN ARRAY
        public static <T> List<T> leggiJsonInArray(String nomeFile, Class<T[]> classeArray) {
            T[] arrayElementi = null;

            try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
                Gson gson = new Gson();
                arrayElementi = gson.fromJson(br, classeArray);
            } catch (IOException e) {
                System.out.println("Errore nella lettura del file: " + e.getMessage());
            }

            return List.of(arrayElementi);
        }

        public static <T> void scriviArrayInJson(String nomeFile, T[] arrayElementi) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(arrayElementi);
                bw.write(json);
            } catch (IOException e) {
                System.out.println("Errore nella scrittura del file: " + e.getMessage());
            }
        }


    
}
