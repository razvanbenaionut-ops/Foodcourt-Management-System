package clase;

public class PreparatMancare extends Preparat {
    int grame;
    public PreparatMancare(String nume, double pret, boolean disponibil, Restaurant restaurant, int grame) {
        super(nume,pret,disponibil,restaurant);
        this.grame=grame;
    }
    public String toString() {
        return nume+" - "+grame+" g "+ " - "+pret+" RON";
    }
}
