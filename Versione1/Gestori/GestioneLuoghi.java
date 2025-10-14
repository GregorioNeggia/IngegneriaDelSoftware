package Versione1.Gestori;

import Versione1.Entità.Luogo;
import Versione1.Utilità;
import java.util.*;


public class GestioneLuoghi {



    public Luogo creaLuogo(){
        String nome = Utilità.chiediStringaNonVuota("INSERISCI IL NOME DEL LUOGO: \n");
        String descrizione = Utilità.chiediStringaNonVuota("INSERISCI LA DESCRIZIONE: \n");
        String posizione = Utilità.chiediStringaNonVuota("INSERISCI POSIZIONE: \n");



        return new Luogo(nome, descrizione, posizione, null);
    }





}
