package Versione1;

import java.util.*;
import com.google.gson.*;
import Versione1.Entità.*;

import java.io.File;
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
    



    //METODI PER I CONFIGURATORI

    public static void scriviConfiguratori(String nomeFile, List<Configuratore> configuratori){
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

    public static List<Configuratore> leggiConfiguratori(String nomeFileConfiguratori){

        List<Configuratore> configuratori = new ArrayList<>();

        File file = new File(nomeFileConfiguratori);

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
        for(Configuratore c : configuratori){
            System.out.println(c.toString());
        }
    }


    //METODI PER I LUOGHI DI INTERESSE
    public void aggiungiLuogo(Configuratore c, Luogo luogo){
        c.aggiungiLuogo(luogo);
    }
}
