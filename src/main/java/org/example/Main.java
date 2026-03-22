package org.example;
import DAO.PreparatDAO;
import clase.Preparat;
import DAO.RestarantDAO;
import clase.Restaurant;

import java.util.List;
public class Main {
    public static void main(String[] args) {
        RestarantDAO restDAO = new RestarantDAO();
        PreparatDAO prepDAO = new PreparatDAO();

        List<Restaurant> restaurante = restDAO.getAll();
        for(Restaurant r : restaurante) {
            List<Preparat> lista = prepDAO.getByRestaurant(r);
            for(Preparat p : lista) r.adaugaPreparat(p);
        }

        new Interfata(restaurante);
    }
}