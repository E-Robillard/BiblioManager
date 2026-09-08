package dao;

import db.Database;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void insert(User user) {
        String sql = """
            INSERT INTO users
                (first_name, last_name, birth_date, email, ad_street, ad_street_num, ad_supplement, ad_city, ad_postal_code)
            VALUES
                (?,?,?,?,?,?,?,?,?)
        """;
        try (
            Connection connexion = Database.getConnection();
            PreparedStatement statement = connexion.prepareStatement(sql)
        ){
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            // Attention au format Date utilisé par SQL et Java
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getStreet());
            statement.setString(6, user.getStreetNumber());
            statement.setString(7, user.getAddressSupplement());
            statement.setString(8, user.getCity());
            statement.setInt(9, user.getPostalCode());

            statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();

        String sql = """
                SELECT * FROM users
        """;

        try (
            Connection connexion = Database.getConnection();
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
        ){
            while (rs.next()){

                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        // Il faut retransformer le format Date de SQL vers Java
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("ad_street"),
                        rs.getString("ad_street_num"),
                        rs.getString("ad_supplement"),
                        rs.getString("ad_city"),
                        rs.getInt("ad_postal_code")
                );

                users.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return users;
    }
}