package DAO;
import clase.Restaurant;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import clase.Restaurant;

public class RestarantDAO {
    public List<Restaurant> getAll() {
        List<Restaurant> restaurante = new ArrayList<>();
        String sql = "SELECT * FROM public.restaurant";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String[] ore = rs.getString("program").split("-");
                LocalTime start = LocalTime.parse(ore[0].trim());
                LocalTime stop = LocalTime.parse(ore[1].trim());
                Restaurant r = new Restaurant(
                        rs.getString("name"),
                        rs.getString("specific"),
                        start,
                        stop,
                        null
                );
                restaurante.add(r);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return restaurante;
    }
}
