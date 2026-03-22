package DAO;
import clase.Preparat;
import clase.PreparatMancare;
import clase.Restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PreparatDAO {
    public List<Preparat> getByRestaurant(Restaurant restaurant) {
        List<Preparat> preparate = new ArrayList<>();
        String sql = "SELECT * FROM public.preparat WHERE restaurant_id = " +
                "(SELECT id FROM public.restaurant WHERE name = ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurant.getNume());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nume = rs.getString("name");
                double pret = rs.getDouble("pret");
                boolean disp = rs.getBoolean("disponibil");
                preparate.add(new PreparatMancare(nume, pret, disp, restaurant, 400));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return preparate;
    }
}
