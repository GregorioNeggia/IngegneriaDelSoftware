package Versione1.Entità;

public class PrimoAvvioData {
//    ATTRIBUTI
    public String ambitoTerritoriale;
    public int numMaxIscrizioni;

//    COSTRUTTORI
    public PrimoAvvioData() {
        this.ambitoTerritoriale = "";
        this.numMaxIscrizioni = 0;
    }

    public PrimoAvvioData(int numMaxIscrizioni, String ambitoTerritoriale) {
        this.ambitoTerritoriale = ambitoTerritoriale;
        this.numMaxIscrizioni = numMaxIscrizioni;

    }

//  getter e setter
    public String getAmbitoTerritoriale() {
        return ambitoTerritoriale;
    }
    
    public void setAmbitoTerritoriale(String ambitoTerritoriale) {
        this.ambitoTerritoriale = ambitoTerritoriale;
    }
    public int getNumMaxIscrizioni() {
        return numMaxIscrizioni;
    }
    public void setNumMaxIscrizioni(int numMaxIscrizioni) {
        this.numMaxIscrizioni = numMaxIscrizioni;
    }

    public String toString(){
        return "Ambito Territoriale: " + ambitoTerritoriale +
            "Max partecipante: " + numMaxIscrizioni + "}";
    }
}
