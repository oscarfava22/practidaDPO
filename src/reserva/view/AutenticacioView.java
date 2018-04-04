package reserva.view;

import javax.swing.*;
import java.awt.*;

public class AutenticacioView extends JFrame {
    private JTextField jtfName;
    private JTextField jtfPassword;
    private JButton jbAccess;

    public AutenticacioView(){

        setSize(350,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Autenticació");

        //NORTH
        final JPanel jpNorth = new JPanel();
        final JLabel jlTitle = new JLabel("NOM RESTAURANT");
        jpNorth.add(jlTitle);

        //CENTER
        final JPanel jcbCenter = new JPanel();
        jcbCenter.setLayout(new BoxLayout(jcbCenter, BoxLayout.Y_AXIS));
        jtfName = new JTextField("Nom");
        jtfPassword = new JTextField("Contrassenya");
        final JPanel jpLeftCenter = new JPanel();
        final JLabel jlNomUser = new JLabel("Introdueix nom d'usuari: ");
        jpLeftCenter.add(jlNomUser);
        jpLeftCenter.add(jtfName);
        jcbCenter.add(jpLeftCenter);
        jcbCenter.add(jtfPassword);

        //SOUTH
        JPanel jpSouth = new JPanel();
        jpSouth.setLayout(new BorderLayout());
        jpSouth.setBorder(BorderFactory.createEtchedBorder());
        jbAccess = new JButton("Accedir");
        jpSouth.add(jbAccess);

        //add all to the final panel
        getContentPane().add(jcbCenter, BorderLayout.CENTER);
        getContentPane().add(jpNorth, BorderLayout.PAGE_START);
        getContentPane().add(jpSouth, BorderLayout.PAGE_END);
        setVisible(true);
    }
}
