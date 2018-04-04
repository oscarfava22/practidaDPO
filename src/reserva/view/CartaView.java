package reserva.view;

import javax.swing.*;
import java.awt.*;

public class CartaView extends JFrame{

    public CartaView () {

        setSize(800,600);
        setTitle("Carta");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Center

        JTabbedPane jtpCenter = new JTabbedPane();

        JPanel jpEntrants = new JPanel();
        jtpCenter.addTab("Entrants", jpEntrants);

        //Taula Entrants

        JTable jtEntrants = new JTable();
        //jtEntrants.ad

        JPanel jpPrimers = new JPanel();
        jtpCenter.add("Principals", jpPrimers);

        JPanel jpBegudes = new JPanel();
        jtpCenter.add("Begudes", jpBegudes);

        JPanel jpPostres = new JPanel();
        jtpCenter.add("Postres", jpPostres);

        getContentPane().add(jtpCenter, BorderLayout.CENTER);

        //South
        JPanel jpSouth = new JPanel(new BorderLayout());

        JButton jbVeureEstat = new JButton("Veure estat");
        JButton jbPagar = new JButton("Pagar");

        JPanel jpWest = new JPanel(new FlowLayout());
        jpWest.add(jbVeureEstat);

        JPanel jpEast = new JPanel(new FlowLayout());
        jpEast.add(jbPagar);

        jpSouth.add(jpWest, BorderLayout.LINE_START);
        jpSouth.add(jpEast, BorderLayout.LINE_END);

        getContentPane().add(jpSouth, BorderLayout.PAGE_END);

        setVisible(true);
    }
}
