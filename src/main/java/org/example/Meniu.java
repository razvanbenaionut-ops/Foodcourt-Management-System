package org.example;
import clase.CosCumparaturi;
import clase.Preparat;
import clase.PreparatIndisponibilException;
import clase.Comanda;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class Meniu extends JPanel{
    List<Preparat> preparate;
    Comanda comanda;
    CosCumparaturi cos;
    public Meniu(List<Preparat> preparate,Comanda comanda,CosCumparaturi cos) {
        this.preparate=preparate;
        this.comanda=comanda;
        this.cos=cos;
        BoxLayout exp=new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(exp);
        JLabel total=new JLabel("Total:0.00 RON");
        for(Preparat p : preparate) {
            JPanel panel=new JPanel(new FlowLayout());
            JButton plus=new JButton("+");
            JButton minus=new JButton("-");
            JLabel label=new JLabel(p.toString());
            plus.addActionListener(e -> {
                System.out.println("Preparat selectat " + p.getNume());
                try {
                    comanda.adaugaPreparat(p, 1);
                    if (comanda.getPreparate().isEmpty())
                        cos.adaugaComanda(comanda);
                    System.out.println("Preparat adaugat");
                    total.setText("Total:" + comanda.total() + " RON");
                } catch (PreparatIndisponibilException ex) {
                    JOptionPane.showMessageDialog(this, p.getNume() + " nu este disponibil");
                }
            });
            minus.addActionListener(e -> {
                comanda.eliminaPreparat(p,1);
                total.setText("Total:" + comanda.total() + " RON");
            });
            panel.add(label);
            panel.add(plus);
            panel.add(minus);
            add(panel);
        }
        add(total);
    }
}

