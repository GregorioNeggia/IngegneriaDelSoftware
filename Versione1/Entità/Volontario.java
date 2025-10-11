package Versione1.Entità;

public class Volontario {

    private String user;
    

    public Volontario(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Volontario{" +
                ", user='" + user + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

}
