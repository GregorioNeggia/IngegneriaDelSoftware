package Versione1.Gestori;
import Versione1.Entità.*;
import java.util.*; 
import Versione1.Utilità;

public class GestioneVolontari {
    private String pathJson = "volontari.json";

    private List<Volontario> volontari;

    public GestioneVolontari(List<Volontario> volontari) {
        this.volontari = volontari != null ? volontari : new ArrayList<Volontario>();
    }

    public Volontario creaVolontario() {
        boolean fine = false;

        try {
            volontari = Utilità.leggiJSonVolontari();
            while (!fine) {
                String user = Utilità.chiediStringaNonVuota("Inserisci l'username del volontario");
                for (Volontario v : volontari) {

                    if (v.getUser().equals(user)) {
                        System.out.println("Errore: Username già esistente. Riprova.");
                        break;
                    } else {
                        Volontario nuovoVolontario = new Volontario(user);
                        volontari.add(nuovoVolontario);
                        Utilità.scriviJSonVolontari(pathJson, volontari);
                        System.out.println("Volontario aggiunto con successo.");
                        Utilità.scriviJSonVolontari(pathJson, volontari);
                        fine = true;
                        return nuovoVolontario;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Errore durante la lettura dei volontari: " + e.getMessage());
            return null;
        }
        return null;

    }


    public Volontario cercaVolontario() {

        List<Volontario> volontari = Utilità.leggiJSonVolontari();
        try {
            String nome = Utilità.chiediStringaNonVuota("Inserisci l'nome del volontario: \n");
            for (Volontario v : volontari) {
                if (v.getUser().equals(nome)) {
                    return v;
                } else {
                    System.out.println("VOLONTARIO " + nome + " NON TROVATO");
                }
            }
        } catch (Exception e) {
            System.out.println("Errore durante la lettura dei volontario: " + e.getMessage());
        }

        return null;
    }










    
}
