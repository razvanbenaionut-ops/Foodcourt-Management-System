package clase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Comanda implements Reducibil {
    List<PreparatComanda> preparate=new ArrayList<>();
    LocalDate data;
    LocalTime ora;

    public Comanda(LocalDate data, LocalTime ora) { this.data=data; this.ora=ora; }
    public LocalDate getData() { return data; }
    public LocalTime getOra() { return ora; }

    public void adaugaPreparat(Preparat p, int cantitate) throws PreparatIndisponibilException {
        if (p==null) { System.out.println("Preparatul nu exista"); return; }
        if (cantitate<=0) { System.out.println("Cantitatea trebuie sa fie mai mare de 0"); return; }
        if (p.getRestaurant()==null) { System.out.println("Preparatul nu are restaurant"); return; }
        if (!p.isDisponibil()) throw new PreparatIndisponibilException(p.getNume());
        if (!p.getRestaurant().esteDeschis(ora)) { System.out.println("Restaurant " + p.getRestaurant().getNume() + " nu este deschis"); return; }
        for (PreparatComanda pc : preparate) {
            if (pc.getPreparat().equals(p)) {
                pc.adaugaCantitate(cantitate);
                System.out.println("Adaugat: " + cantitate + " x " + p.getNume() + "Total: " + pc.getCantitate());
                return;
            }
        }
        preparate.add(new PreparatComanda(cantitate, p));
        System.out.println("Adaugat: " + cantitate + " x " + p.getNume());
    }

    public double total() {
        double sum=0;
        for (PreparatComanda pc : preparate) sum+=pc.getPretNou();
        return sum;
    }

    @Override
    public void reduce(int procent) {
        if (procent<=0 || procent>=50) { System.out.println("Reducere invalida!"); return; }
        System.out.println("Aplicare Happy Hour " + procent + "% la comanda de la " + ora);
        for (PreparatComanda pc : preparate) {
            double Pretvechi=pc.getPretNou();
            pc.aplicaReducere(procent);
            System.out.println(pc.getPreparat().getNume() + ": " + Pretvechi + " → " + pc.getPretNou());
        }
    }

    public void afiisare() {
        for (PreparatComanda pc : preparate) System.out.println(pc);
        System.out.println("Total comanda: " + total() + " RON");
    }
    public List<PreparatComanda> getPreparate() {
        return preparate;
    }
    public boolean esteHappyHour() {
        if (preparate.isEmpty()) return false;

        Restaurant r=preparate.get(0).getPreparat().getRestaurant();
        if (r.getHappyHour()==null) return false;

        return r.getHappyHour().activ(data, ora);

    }
    public void eliminaPreparat(Preparat p, int sters) {
        Iterator<PreparatComanda> it=preparate.iterator();
        while(it.hasNext()) {
            PreparatComanda pc=it.next();
            if(pc.getPreparat().equals(p)) {
                int cant=pc.getCantitate();
                if(cant>sters) {
                    pc.setCantitate(cant-sters);
                    System.out.println("Sterse: " + sters + " x " + p.getNume());
                } else {
                    it.remove();
                    System.out.println("Sterse: " + cant + " x " + p.getNume());
                }
                return;
            }
        }
    }

}
