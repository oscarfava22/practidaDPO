package reserva.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CartaView extends JFrame{

    public CartaView (int numEntrants, int numPrimers, int numBegudes, int numPostres) {

        setSize(800,600);
        setTitle("Carta");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //North

        JMenuBar jmbNorth = new JMenuBar();
        jmbNorth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JMenu jmNorth = new JMenu();
        try {
            Image image = ImageIO.read(new File("data/ic_shopping_basket_black_24dp_2x.png"));
            Icon icon = new ImageIcon(image);
            jmNorth.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //jmNorth.setIcon((UIManager.getIcon("OptionPane.errorIcon")));
        jmbNorth.add(jmNorth);

        JMenuItem jmi1 = new JMenuItem("Productos:");
        JMenuItem jmi2 = new JMenuItem("Pan con tomate - x2");
        jmNorth.add(jmi1);
        jmNorth.add(jmi2);

        getContentPane().add(jmbNorth, BorderLayout.PAGE_START);

        //Center

        JTabbedPane jtpCenter = new JTabbedPane();

        //Taula Entrants

        JPanel jpEntrants2 = new JPanel(new GridLayout(numEntrants,1));

        for(int i = 0; i < numEntrants; i++) {
            jpEntrants2.add(new ItemCartaView("Nom entrant", 10));
        }

        JScrollPane jpEntrants = new JScrollPane(jpEntrants2);
        jtpCenter.addTab("Entrants", jpEntrants);


        //Taula Primers

        JPanel jpPrimers2 = new JPanel(new GridLayout(numPrimers,1));

        for(int i = 0; i < numPrimers; i++) {
            jpPrimers2.add(new ItemCartaView("Nom primer", 5));
        }

        JScrollPane jpPrimers = new JScrollPane(jpPrimers2);
        jtpCenter.addTab("Principals", jpPrimers);

        //Taula Begudes

        JPanel jpBegudes2 = new JPanel(new GridLayout(numBegudes,1));

        for(int i = 0; i < numBegudes; i++) {
            jpBegudes2.add(new ItemCartaView("Nom beguda", 10));
        }

        JScrollPane jpBegudes = new JScrollPane(jpBegudes2);
        jtpCenter.addTab("Begudes", jpBegudes);

        //Taula Postres

        JPanel jpPostres2 = new JPanel(new GridLayout(numPostres,1));

        for(int i = 0; i < numPostres; i++) {
            jpPostres2.add(new ItemCartaView("Nom postre", 10));
        }

        JScrollPane jpPostres = new JScrollPane(jpPostres2);
        jtpCenter.addTab("Postres", jpPostres);

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
