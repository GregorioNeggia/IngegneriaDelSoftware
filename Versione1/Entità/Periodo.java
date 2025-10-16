package Versione1.Entità;

import java.time.LocalDate;

public class Periodo {
    private final int mese;
    private final int anno;

    public Periodo(int mese, int anno) {
        this.mese = mese;
        this.anno = anno;
    }

    // Costruttore che calcola il periodo i da una data
    public static Periodo calcolaPeriodoI(LocalDate data) {
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
        return new Periodo(mese, anno);
    }

    // Calcola il periodo i+3
    public Periodo aggiungiMesi(int numeroMesi) {
        int nuovoMese = ((this.mese - 1 + numeroMesi) % 12) + 1;
        int nuovoAnno = this.anno + ((this.mese - 1 + numeroMesi) / 12);
        return new Periodo(nuovoMese, nuovoAnno);
    }

    // Restituisce l'inizio del periodo (16 del mese)
    public LocalDate getInizio() {
        return LocalDate.of(anno, mese, 16);
    }

    // Restituisce la fine del periodo (15 del mese successivo)
    public LocalDate getFine() {
        Periodo periodoSuccessivo = this.aggiungiMesi(1);
        return LocalDate.of(periodoSuccessivo.anno, periodoSuccessivo.mese, 15);
    }

    // Verifica se una data appartiene a questo periodo
    public boolean contiene(LocalDate data) {
        LocalDate inizio = getInizio();
        LocalDate fine = getFine();
        return (!data.isBefore(inizio)) && (!data.isAfter(fine));
    }

    @Override
    public String toString() {
        return mese + "/" + anno;
    }

    // Getter
    public int getMese() {
        return mese;
    }

    public int getAnno() {
        return anno;
    }
}
