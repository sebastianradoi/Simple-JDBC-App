package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private Connection con;
    private PreparedStatement statement1;
    private PreparedStatement statement2;

    public UserDao(Connection con) {
        try{
            this.con = con;
            this.statement1 = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            this.statement2 = con.prepareStatement("INSERT INTO users VALUES (NULL, ?, ?)");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Optional<User> findUser(String username) throws SQLException {
        statement1.setString(1,username);
        User user = null;
        try (ResultSet rs = statement1.executeQuery()){
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }
        return Optional.ofNullable(user);
    }

    public void adaugaUser(User user) throws SQLException {
        statement2.setString(1,user.getUsername());
        statement2.setString(2,user.getPassword());
        statement2.executeUpdate();
    }
}
