package Versione1;

import java.util.*;

import java.io.File;
import java.io.*;

public class Utilità {

    
    
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
    

    public static void scriviConfiguratori(String nomeFile, List<Configuratore> configuratori){
        File file = new File(nomeFile);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

            for(Configuratore c : configuratori){
                String line = c.getUsername() + "," + c.getPassword();
                bw.write(line);
                bw.newLine();
            }

        }
        catch (IOException e){
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public static List<Configuratore> leggiConfiguratori(String nomeFileConfiguratori){

        List<Configuratore> configuratori = new ArrayList<>();

        File file = new File(nomeFileConfiguratori);

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split(",");
                if(parts.length == 2){
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    Configuratore configuratore = new Configuratore(username, password);
                    configuratori.add(configuratore);
                }
            }

        }
        catch (IOException e){
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return configuratori;
    }
}
