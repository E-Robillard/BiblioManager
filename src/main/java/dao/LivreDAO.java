package dao;

import db.Database;
import models.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    public void insert(Livre livre) {
        String sql = "INSERT INTO livre (title, author, isbn, available) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livre.getTitle());
            stmt.setString(2, livre.getAuthor());
            stmt.setString(3, livre.getIsbn());
            stmt.setBoolean(4, livre.isAvailable());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Livre> findAll() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT id, title, author, isbn, available FROM livre";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livre livre = new Livre(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("available")
                );
                livres.add(livre);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return livres;
    }


}
