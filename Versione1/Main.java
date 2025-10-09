package Versione1;

public class Main {

     private static final String DEFAULT_FILE_CONFIGURATORI = "configuratori.txt";
    

    public static void main(String[] args) {
        GestioneConfiguratori gestione = new GestioneConfiguratori(Utilità.leggiConfiguratori(DEFAULT_FILE_CONFIGURATORI));
        Configuratore configuratore = gestione.login();

        System.out.println(configuratore.toString());




        
        
        
    }
}
