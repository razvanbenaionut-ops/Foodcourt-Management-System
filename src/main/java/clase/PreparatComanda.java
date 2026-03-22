package clase;

public class PreparatComanda {
    int cantitate;
    Preparat preparat;
    double pretNou;

    public PreparatComanda(int cantitate,Preparat preparat) {
        this.cantitate=cantitate;
        this.preparat=preparat;
        this.pretNou=cantitate*preparat.getPret();
    }

    public void adaugaCantitate(int cant) {
        this.cantitate+=cant;
        this.pretNou=this.cantitate*preparat.getPret();
    }

    public void aplicaReducere(int procent) {
        pretNou=cantitate*preparat.getPret()*(1-procent/100.0);
    }

    public double getPretNou() { return pretNou; }
    public int getCantitate() { return cantitate; }
    public Preparat getPreparat() { return preparat; }

    public String toString() {
        if (pretNou<cantitate*preparat.getPret()) {
            return cantitate + " x " + preparat.getNume() + " " + preparat.getRestaurant().getNume() +
                    " = " + pretNou + " RON (reducere aplicata)";
        } else {
            return cantitate + " x " + preparat.getNume() + " " + preparat.getRestaurant().getNume() +
                    " = " + pretNou + " RON";
        }
    }

    public void setCantitate(int cantitate) {
        this.cantitate=cantitate;
    }
}
