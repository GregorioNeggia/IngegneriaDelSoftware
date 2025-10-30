package Versione1;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.io.*;


public class Utilita {


    //    ATTRIBUTI
    public static final Scanner scanner = new Scanner(System.in);

    //    METODI DI UTILITA GENERALE
    public static String chiediStringa(String messaggio) {
        System.out.print(messaggio + " ");
        return scanner.nextLine();
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
        int valore = scanner.nextInt();
        scanner.nextLine();
        return valore;
    }

    //    LETTURA DATA
    public static LocalDate leggiData(String messaggio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String input = chiediStringaNonVuota(messaggio + " formato data: gg/mm/aaaa): ");
            try {
                LocalDate data = LocalDate.parse(input, formatter);
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("Formato non valido, riprova: esempio corretto 09/11/2025");
            }
        }
    }

    //    LETTURA ORARIO
    public static LocalTime leggiOrario(String messaggio) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        while (true) {
            String input = chiediStringaNonVuota(messaggio + " formato orario: hh:mm (es. 14:30): ");
            try {
                LocalTime orario = LocalTime.parse(input, formatter);
                return orario;
            } catch (DateTimeParseException e) {
                System.out.println("Formato orario non valido, riprova: esempio corretto 14:30");
            }
        }
    }


    //    LEGGI LISTA E SCRIVI LISTA E NON ARRAY
    public static <T> void scriviListaInJson(String nomeFile, List<T> listaElementi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeFile))) {
            Gson gson = buildGson();
            String json = gson.toJson(listaElementi);
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }

    public static <T> List<T> leggiJsonInLista(String nomeFile, Class<T> classeElemento) {
        List<T> listaElementi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            Gson gson = buildGson();
            Type tipoLista = TypeToken.getParameterized(ArrayList.class, classeElemento).getType();
            listaElementi = gson.fromJson(br, tipoLista);
            if (listaElementi == null) listaElementi = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return listaElementi;
    }


    public static Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, context) ->
                                LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .registerTypeAdapter(LocalTime.class,
                        (JsonSerializer<LocalTime>) (src, typeOfSrc, context) ->
                                new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME)))
                .registerTypeAdapter(LocalTime.class,
                        (JsonDeserializer<LocalTime>) (json, type, context) ->
                                LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME))
                .setPrettyPrinting()
                .create();
    }


}