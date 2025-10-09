package Versione1.Entità;

public class Luogo {

    private String nome;

    private Volontario volontarioAssegnato;

    public Luogo(String nome, Volontario volontarioAssegnato) {
        this.nome = nome;
        this.volontarioAssegnato = volontarioAssegnato;
    }

    @Override
    public String toString() {
        return "Luogo{" +
                "nome='" + nome + '\'' +
                ", volontarioAssegnato=" + volontarioAssegnato +
                '}';
    }
    
}
