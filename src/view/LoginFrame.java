package view;

import model.User;
import service.MainService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginFrame{
    private JButton loginButton;
    private JButton singUpButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel mainPanel;

    JFrame frame;

    public LoginFrame(){
        frame = new JFrame("LoginFrame");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        loginButton.addActionListener(ev -> login());
        singUpButton.addActionListener(ev -> inregistare());
    }

    private void login(){
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());
        Optional<User> optionalUser = MainService.getInstance().login(username,password);
        if(optionalUser.isPresent()){
            new MainFrame();
            frame.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Username sau parola gresite!");
        }
    }

    private void inregistare(){
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());

        if(MainService.getInstance().inregistrare(new User(username,password))){
            JOptionPane.showMessageDialog(null, "Userul a fost inregistrat");
        }else{
            JOptionPane.showMessageDialog(null, "Userul nu a fost inregistrat");
        }
    }


}
