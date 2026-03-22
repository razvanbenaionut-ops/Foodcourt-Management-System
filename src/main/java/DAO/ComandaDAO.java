package DAO;
import clase.PreparatComanda;
import clase.Restaurant;
import clase.Comanda;
import java.sql.*;

public class ComandaDAO {
    public void salveazaComanda(Comanda comanda, Restaurant restaurant) {
        String sqlComanda = "INSERT INTO public.comanda (total, data, restaurant_id) VALUES (?, ?, (SELECT id FROM public.restaurant WHERE name = ?)) RETURNING id";
        String sqlItem = "INSERT INTO public.comanda_preparat (comanda_id, preparat_id, cantitate) VALUES (?, (SELECT id FROM public.preparat WHERE name = ? AND restaurant_id = (SELECT id FROM public.restaurant WHERE name = ?)), ?)";
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            int comandaId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(sqlComanda)) {
                pstmt.setDouble(1, comanda.total());
                pstmt.setDate(2, java.sql.Date.valueOf(comanda.getData()));
                pstmt.setString(3, restaurant.getNume());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) comandaId = rs.getInt(1);
            }
            try (PreparedStatement pstmtItem = conn.prepareStatement(sqlItem)) {
                for (PreparatComanda pc : comanda.getPreparate()) {
                    pstmtItem.setInt(1, comandaId);
                    pstmtItem.setString(2, pc.getPreparat().getNume());
                    pstmtItem.setString(3, restaurant.getNume());
                    pstmtItem.setInt(4, pc.getCantitate());
                    pstmtItem.executeUpdate();
                }
            }
            conn.commit();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
