package service;

import dao.ProdusDao;
import dao.UserDao;
import model.Produs;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MainService {
    private Connection con;
    private String user = "root";
    private String pass = "";
    private String url = "jdbc:mysql://localhost/simplejdbc";

    private static MainService ourInstance = new MainService();

    public static MainService getInstance() {
        return ourInstance;
    }

    private MainService() {
        try {
            con = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean inregistrare(User user){
        UserDao userDao = new UserDao(con);

        boolean rez = false;

        try {
            Optional<User> optionalUser = userDao.findUser(user.getUsername());

            if(!optionalUser.isPresent()){
                userDao.adaugaUser(user);
                rez = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rez;
    }

    public Optional<User> login(String username, String password){
        UserDao userDao = new UserDao(con);
        try {
            Optional<User> optionalUser = userDao.findUser(username);
            if(optionalUser.isPresent()){
                if(optionalUser.get().getPassword().equals(password)){
                    return optionalUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void adaugaProdus(Produs p){
        ProdusDao produsDao = new ProdusDao(con);
        try {
            produsDao.adaugaProdus(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produs> getAllProducts(){
        ProdusDao produsDao = new ProdusDao(con);
        try {
            return produsDao.getAllProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void stergeProdus(int id){
        ProdusDao produsDao = new ProdusDao(con);
        try {
            produsDao.stergeProdus(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
