package view;

import model.Produs;
import service.MainService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton addButton;
    private JTextField a0TextField;
    private JList list1;
    private JButton logOutButton;
    private JButton exitButton;
    JFrame frame;
    private DefaultListModel<Produs> model;

    public MainFrame() {
        initComponents();
        model = new DefaultListModel<>();
        list1.setModel(model);
        afisareProduse();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        logOutButton.addActionListener(e -> logout());
        exitButton.addActionListener(e -> System.exit(0));
        addButton.addActionListener(e -> adaugaProdus());
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    Produs produs = (Produs) list1.getSelectedValue();
                    MainService.getInstance().stergeProdus(produs.getId());
                    afisareProduse();
                }
            }
        });
    }

    private void initComponents(){
        frame = new JFrame("Application");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void afisareProduse(){
        List<Produs> produse = MainService.getInstance().getAllProducts();
        model.clear();
        produse.forEach(model::addElement);
    }

    private void logout(){
        new LoginFrame();
        frame.dispose();
    }

    private void adaugaProdus(){
        String nume = textField1.getText();
        double pret = Double.parseDouble(a0TextField.getText());
        MainService.getInstance().adaugaProdus(new Produs(nume,pret));
        afisareProduse();
        JOptionPane.showMessageDialog(null,"A fost adaugat produsul");
    }
}
