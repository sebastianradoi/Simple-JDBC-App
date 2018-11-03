package dao;

import model.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdusDao {
    private Connection con;
    private PreparedStatement statement1;
    private PreparedStatement statement2;
    private PreparedStatement statement3;

    public ProdusDao(Connection con) {

        try {
            this.con = con;
            statement1 = con.prepareStatement("INSERT INTO produse values (NULL, ?, ?)");
            statement2 = con.prepareStatement("SELECT * FROM produse");
            statement3 = con.prepareStatement("DELETE FROM produse WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adaugaProdus(Produs produs) throws SQLException {
        statement1.setString(1,produs.getNume());
        statement1.setDouble(2,produs.getPret());
        statement1.executeUpdate();
    }

    public List<Produs> getAllProducts() throws SQLException {
        List<Produs> produse = new ArrayList<>();
        ResultSet rs = statement2.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String nume = rs.getString("nume");
            double pret = rs.getDouble("pret");
            produse.add(new Produs(id,nume,pret));
        }
        return produse;
    }

    public void stergeProdus(int id) throws SQLException {
        statement3.setInt(1, id);
        statement3.executeUpdate();
    }

}
