package clase;

public abstract class Preparat {
    String nume;
    double pret;
    boolean disponibil;
    Restaurant restaurant;

    public Preparat(String nume,double pret,boolean disponibil,Restaurant restaurant) {
        this.nume=nume;
        this.pret=pret;
        this.disponibil=disponibil;
        this.restaurant=restaurant;
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public boolean isDisponibil() { return disponibil; }
    public Restaurant getRestaurant() { return restaurant; }
    public abstract String toString();
}