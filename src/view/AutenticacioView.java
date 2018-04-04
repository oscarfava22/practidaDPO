package view;

import javax.swing.*;
import java.awt.*;

public class AutenticacioView extends JFrame{
    private JTextField jtfName;
    private JTextField jtfPassword;
    private JButton jbAccess;

    public AutenticacioView(){
        setSize(350,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autenticació");

        //NORTH
        final JLabel jlTitle = new JLabel("NOM RESTAURANT");
        //CENTER
        final JComboBox jcbCenter = new JComboBox();
        jtfName = new JTextField("Nom");
        jtfPassword = new JTextField("Contrassenya");
        jcbCenter.add(jtfName);
        jcbCenter.add(jtfPassword);

        //SOUTH
        JPanel jpSouth = new JPanel();
        jpSouth.setLayout(new BorderLayout());
        jpSouth.setBorder(BorderFactory.createEtchedBorder());
        jbAccess = new JButton("Accedir");
        jpSouth.add(jbAccess);

        //add all to the final panel
        getContentPane().add(jcbCenter, BorderLayout.CENTER);
        getContentPane().add(jlTitle, BorderLayout.PAGE_START);
        getContentPane().add(jpSouth, BorderLayout.PAGE_END);
        setVisible(true);
    }
}
