package clase;

import java.util.*;
public class CosCumparaturi {
    List<Comanda> comenzi=new ArrayList<>();
    public CosCumparaturi() {}
    public void afisare() { for (Comanda c : comenzi) c.afiisare(); }
    public double total() { double s=0; for (Comanda c : comenzi) s+=c.total(); return s; }
    public void adaugaComanda(Comanda c) {
        comenzi.add(c);
        for(PreparatComanda pc : c.getPreparate()) {
            Restaurant r=pc.getPreparat().getRestaurant();
            if(r!=null) {
                r.adaugaComanda(c);
            }
        }
    }
    public List<Comanda> getComenzi(){
        return comenzi;
    }

}
