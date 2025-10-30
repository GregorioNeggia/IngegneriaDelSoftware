package Versione1.Gestori;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Versione1.Entità.Periodo;
import Versione1.Utilita;

public class GestioneDateEscluse {
    private ArrayList<LocalDate> dateEscluse;
    private String nomeFile = "Versione1/Database/date_escluse.json";

    public GestioneDateEscluse() {
        this.dateEscluse = (ArrayList<LocalDate>) Utilita.leggiJsonInLista(nomeFile, LocalDate.class);
    }

    public void aggiungiDateEscluse() {
        LocalDate dataOggi = LocalDate.now();

        Periodo periodoI = Periodo.calcolaPeriodoI(dataOggi);
        Periodo periodoIPiuTre = periodoI.aggiungiMesi(3);

        System.out.println("Periodo i: " + periodoI);
        System.out.println("Aggiungendo date escluse per il periodo i+3: " + periodoIPiuTre);
        System.out.println("Periodo valido: dal " + periodoIPiuTre.getInizio() + " al " + periodoIPiuTre.getFine());

        while (true) {
            String input = Utilita.chiediStringa("Inserisci data da escludere (YYYY-MM-DD) o 'fine' per terminare: ");

            if (input.equals("fine")) break;

            try {
                LocalDate nuovaData = LocalDate.parse(input);

                if (periodoIPiuTre.contiene(nuovaData)) {
                    if (!dateEscluse.contains(nuovaData)) {
                        dateEscluse.add(nuovaData);
                        System.out.println("Data aggiunta: " + nuovaData);
                    } else {
                        System.out.println("Data già presente nel file!");
                    }
                } else {
                    System.out.println("La data non appartiene al periodo i+3 (" + periodoIPiuTre + ")");
                }
            } catch (Exception e) {
                System.out.println("Formato data non valido!");
            }
        }

        Utilita.scriviListaInJson(nomeFile, dateEscluse);
    }

    public void visualizzaDate() {
        System.out.println("\n--- Date Escluse ---");
        if (dateEscluse.isEmpty()) {
            System.out.println("Nessuna data esclusa presente.");
        } else {
            for (LocalDate data : dateEscluse) {
                System.out.println("- " + data);
            }
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- Gestione Date Escluse ---");
            System.out.println("1. Escludi date");
            System.out.println("2. Visualizza date escluse");
            System.out.println("3. Esci");

            String scelta = Utilita.chiediStringa("Scegli: ");


            switch (scelta) {
                case "1":
                    aggiungiDateEscluse();
                    break;
                case "2":
                    visualizzaDate();
                    break;
                case "3":
                    esci = true;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }
}

/*package Versione1.Gestori;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Versione1.Utilità;

public class GestioneDateEscluse {
    private ArrayList<LocalDate> dateEscluse;
    private String nomeFile = "date_escluse.json";

    public GestioneDateEscluse() {
        this.dateEscluse = (ArrayList<LocalDate>) Utilità.leggiJsonInLista(nomeFile, LocalDate.class);
    }

    // Calcola il periodo i in base alla data
    private int[] calcolaPeriodoI(LocalDate data) {
        int mese, anno;
        if (data.getDayOfMonth() >= 16) {
            mese = data.getMonthValue();
            anno = data.getYear();
        } else {
            if (data.getMonthValue() == 1) {
                mese = 12;
                anno = data.getYear() - 1;
            } else {
                mese = data.getMonthValue() - 1;
                anno = data.getYear();
            }
        }
        return new int[]{mese, anno};
    }

    // Calcola il periodo i+3
    private int[] calcolaPeriodoIPiuTre(int meseI, int annoI) {
        int nuovoMese = ((meseI - 1 + 3) % 12) + 1;
        int nuovoAnno = annoI + ((meseI - 1 + 3) / 12);
        return new int[]{nuovoMese, nuovoAnno};
    }

    public static boolean isDataNelPeriodo(LocalDate data, int mese, int anno) {
        // Inizio: 16 del mese corrente
        LocalDate inizio = LocalDate.of(anno, mese, 16);
        // Calcola il mese e anno del periodo di fine (mese successivo)
        int meseFine, annoFine;
        if (mese == 12) {
            meseFine = 1;
            annoFine = anno + 1;
        } else {
            meseFine = mese + 1;
            annoFine = anno;
        }
        // Fine: 15 del mese dopo
        LocalDate fine = LocalDate.of(annoFine, meseFine, 15);

        // Compreso tra inizio e fine, estremi inclusi
        return (!data.isBefore(inizio)) && (!data.isAfter(fine));
    }

    public void aggiungiDateEscluse() {
        Scanner scanner = new Scanner(System.in);
        LocalDate dataOggi = LocalDate.now();

        int[] periodoI = calcolaPeriodoI(dataOggi);
        int[] periodoIPiuTre = calcolaPeriodoIPiuTre(periodoI[0], periodoI[1]);

        System.out.println("Periodo i: " + periodoI[0] + "/" + periodoI[1]);
        System.out.println("Aggiungendo date escluse per il periodo i+3: " + periodoIPiuTre[0] + "/" + periodoIPiuTre[1]);

        while (true) {
            System.out.print("Inserisci data da escludere (YYYY-MM-DD) o 'fine' per terminare: ");
            String input = scanner.nextLine();

            if (input.equals("fine")) break;

            try {
                LocalDate nuovaData = LocalDate.parse(input);

                // Verifica se la data appartiene al periodo i+3
                if (isDataNelPeriodo(nuovaData, periodoIPiuTre[0], periodoIPiuTre[1])) {

                    if (!dateEscluse.contains(nuovaData)) {
                        dateEscluse.add(nuovaData);
                        System.out.println("Data aggiunta: " + nuovaData);
                    } else {
                        System.out.println("Data già presente nel file!");
                    }
                } else {
                    System.out.println("La data non appartiene al periodo i+3 (" +
                            periodoIPiuTre[0] + "/" + periodoIPiuTre[1] + ")");
                }
            } catch (Exception e) {
                System.out.println("Formato data non valido!");
            }
        }

        Utilità.scriviListaInJson(nomeFile, dateEscluse);
    }

    public void visualizzaDate() {
        System.out.println("\n--- Date Escluse ---");
        if (dateEscluse.isEmpty()) {
            System.out.println("Nessuna data esclusa presente.");
        } else {
            for (LocalDate data : dateEscluse) {
                System.out.println("- " + data);
            }
        }
    }

    public void menu() {
        System.out.println("è entrato");
        Scanner scanner = new Scanner(System.in);
        boolean esci = false;

        while (!esci) {
            System.out.println("\n--- Gestione Date Escluse ---");
            System.out.println("1. escludi date");
            System.out.println("2. Visualizza date escluse");
            System.out.println("3. Esci");
            System.out.print("Scegli: ");

            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1":
                    aggiungiDateEscluse();
                    break;
                case "2":
                    visualizzaDate();
                    break;
                case "3":
                    esci = true;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }
        }
    }
}
*/