package org.example;
import clase.CosCumparaturi;
import clase.PreparatComanda;
import clase.Restaurant;
import clase.Comanda;
import clase.HappyHour;

import java.awt.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class Interfata extends JFrame{
    List<Restaurant> restaurante;
    CosCumparaturi cosComun=new CosCumparaturi();
    List<Comanda> istoric=new ArrayList<>();
    LocalTime oraCurenta=LocalTime.now();
    public Interfata(List<Restaurant> restaurante) {
        this.restaurante=restaurante;
        this.cosComun=new CosCumparaturi();
        setTitle("Foodcourt");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        for(Restaurant r : restaurante) {
            JButton b=new JButton(r.getNume());
            b.addActionListener(e->{
                if(!r.esteDeschis(oraCurenta))
                {
                    JOptionPane.showMessageDialog(this,"Restaurant "+r.getNume()+" nu este deschis");
                }
                else {
                    JFrame f=new JFrame("Meniu " + r.getNume());
                    f.setSize(400, 400);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Comanda comandaNoua=new Comanda(LocalDate.now(),LocalTime.now());
                    this.cosComun.adaugaComanda(comandaNoua);
                    f.add(new Meniu(r.getPreparate(),comandaNoua,cosComun));
                    f.setVisible(true);
                }
            });
            add(b);
        }
        JButton cos=new JButton("Vezi Cos");
        cos.addActionListener(e -> {
            String textFinal="Cosul tau:\n";
            double totalFoodcourt=0;
            int nrComanda=1;
            for(Comanda c:cosComun.getComenzi())
            {
                if(c.getPreparate().isEmpty()==false)
                {
                    textFinal+="Comanda nr. "+nrComanda +"\n";
                    nrComanda++;
                    String numeRestaurant=c.getPreparate().get(0).getPreparat().getRestaurant().getNume();
                    textFinal+=numeRestaurant+":\n";
                    double totalRestaurant=0;
                    for (PreparatComanda pc : c.getPreparate()) {
                        double pretPreparat=pc.getPreparat().getPret();
                        double subtotal=pretPreparat*pc.getCantitate();
                        totalRestaurant+=subtotal;
                        double totalFinal=totalRestaurant;
                        if(c.esteHappyHour()) {
                            int procent=c.getPreparate().get(0).getPreparat().getRestaurant().getHappyHour().procent;
                            double reducere=totalRestaurant*procent/100.0;
                            totalFinal=totalRestaurant-reducere;
                            JOptionPane.showMessageDialog(this,"Comanda este Happy Hour! Reducere de "+procent+" %: "
                                    + totalRestaurant +" RON -> "+totalFinal+" RON");
                        }
                        totalFoodcourt+=totalFinal;
                        textFinal+=pc.getPreparat().getNume() +
                                " (" + pc.getCantitate() + " x " + pretPreparat + " RON) = " +
                                totalFinal + " RON\n";;
                    }

                }
                textFinal+="\n";
            }
            textFinal+="Total Foodcourt: "+totalFoodcourt+" RON";
            JOptionPane.showMessageDialog(this,textFinal);
        });
        add(cos);
        JButton raport=new JButton("Raport vânzări");
        raport.addActionListener(e -> {
            String text="Raport Vanzare\n";
            double totalFoodcourt=0;
            for (Restaurant r : restaurante) {
                double totalRestaurant=0;
                for (Comanda c : istoric) {
                    for (PreparatComanda pc : c.getPreparate()) {
                        if (pc.getPreparat().getRestaurant()==r) {
                            totalRestaurant+=pc.getPretNou();
                        }
                    }
                }
                text+=r.getNume() + ": " + totalRestaurant + " RON\n";
                totalFoodcourt+=totalRestaurant;
            }
            text+="\nTotal Foodcourt: " + totalFoodcourt + " RON";
            JOptionPane.showMessageDialog(this,text);
        });
        add(raport);
        JButton plateste=new JButton("Plateste");
        plateste.addActionListener(e->{
            JOptionPane.showMessageDialog(this,"Comanda platita");
            istoric.addAll(cosComun.getComenzi());
            cosComun.getComenzi().clear();
        });
        add(plateste);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
