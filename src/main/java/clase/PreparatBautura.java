package clase;

public class PreparatBautura extends Preparat {
    int ml;
    public PreparatBautura(String nume, double pret, boolean disponibil, Restaurant restaurant, int ml) {
        super(nume,pret,disponibil,restaurant);
        this.ml=ml;
    }
    public String toString() {
        return nume+" - "+ml+" ml "+ " - "+pret+" RON";
    }
}
