package clase;

import java.time.*;
import java.util.*;
public class Restaurant {
    String nume;
    String specific;
    LocalTime oraDeschidere;
    LocalTime oraInchidere;
    List<Preparat> preparate=new ArrayList<>();
    List<Comanda> comenzi=new ArrayList<>();
    HappyHour happyHour;

    public Restaurant(String nume,String specific,LocalTime deschidere,LocalTime inchidere,HappyHour hh) {
        this.nume=nume;
        this.specific=specific;
        this.oraDeschidere=deschidere;
        this.oraInchidere=inchidere;
        this.happyHour=hh;
    }
    public String getNume() { return nume; }

    public boolean esteDeschis(LocalTime ora) {
        if (ora==null) { System.out.println("Ora comenzii nu este valida"); return false; }
        return !ora.isBefore(oraDeschidere) && !ora.isAfter(oraInchidere);
    }

    public void aplicaHappyHour(Comanda c) { if (happyHour!=null && happyHour.activ(c.data,c.ora)) c.reduce(happyHour.procent); }

    public void adaugaPreparat(Preparat p) { preparate.add(p); }
    public void adaugaComanda(Comanda c) { comenzi.add(c); }
    public void afiseazaMeniu() { System.out.println("Meniu " + nume); for (Preparat p : preparate) System.out.println(p); }
    public List<Preparat> getPreparate() {
        return preparate;
    }
    public List<Comanda> getComenzi() {
        return comenzi;
    }
    public HappyHour getHappyHour() {
        return happyHour;
    }


}

