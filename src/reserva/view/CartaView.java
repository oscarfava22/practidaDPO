package reserva.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CartaView extends JFrame{

    private final JButton jbPagar;
    private ArrayList<JButton> jButtonsAfegirUnitat;
    private ArrayList<JButton> jButtonsTreureUnitat;
    private ArrayList<JButton> jButtonsAfegir;
    private ArrayList<JLabel> jLabelsUnits;

    public CartaView (int numEntrants, int numPrimers, int numBegudes, int numPostres, int numAfegits) {

        setSize(800,600);
        setTitle("Carta");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //North

        JTabbedPane jtpNorth = new JTabbedPane();

        JTabbedPane jtpCenter = new JTabbedPane();

        Image image = null;
        try {
            image = ImageIO.read(new File("data/logo24x24black.png"));
            Icon icon = new ImageIcon(image);
            jtpNorth.addTab("Carta", icon, jtpCenter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel jpCesta = new JPanel(new GridLayout(numAfegits, 1));
        for(int i = 0; i < numAfegits; i++) {
            jpCesta.add(new ItemCartaView("Nom afegit", 10, "Esborrar"));
        }

        try {
            image = ImageIO.read(new File("data/ic_shopping_basket_black_24dp_2x.png"));
            Icon icon = new ImageIcon(image);
            jtpNorth.addTab("Cesta", icon, jpCesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().add(jtpNorth, BorderLayout.CENTER);

        //Center

        //Taula Entrants

        JPanel jpEntrants2 = new JPanel(new GridLayout(numEntrants,1));

        for(int i = 0; i < numEntrants; i++) {
            jpEntrants2.add(new ItemCartaView("Nom entrant", 10, "Afegir"));
        }

        JScrollPane jpEntrants = new JScrollPane(jpEntrants2);
        jtpCenter.addTab("Entrants", jpEntrants);


        //Taula Primers

        JPanel jpPrimers2 = new JPanel(new GridLayout(numPrimers,1));

        for(int i = 0; i < numPrimers; i++) {
            jpPrimers2.add(new ItemCartaView("Nom primer", 5, "Afegir"));
        }

        JScrollPane jpPrimers = new JScrollPane(jpPrimers2);
        jtpCenter.addTab("Principals", jpPrimers);

        //Taula Begudes

        JPanel jpBegudes2 = new JPanel(new GridLayout(numBegudes,1));

        for(int i = 0; i < numBegudes; i++) {
            jpBegudes2.add(new ItemCartaView("Nom beguda", 10, "Afegir"));
        }

        JScrollPane jpBegudes = new JScrollPane(jpBegudes2);
        jtpCenter.addTab("Begudes", jpBegudes);

        //Taula Postres

        JPanel jpPostres2 = new JPanel(new GridLayout(numPostres,1));

        for(int i = 0; i < numPostres; i++) {
            jpPostres2.add(new ItemCartaView("Nom postre", 10, "Afegir"));
        }

        JScrollPane jpPostres = new JScrollPane(jpPostres2);
        jtpCenter.addTab("Postres", jpPostres);

        //South
        JPanel jpSouth = new JPanel(new BorderLayout());

        jbPagar = new JButton("Pagar");


        JPanel jpEast = new JPanel(new FlowLayout());
        jpEast.add(jbPagar);

        jpSouth.add(jpEast, BorderLayout.LINE_END);

        getContentPane().add(jpSouth, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public void registerControllers (ActionListener actionListener) {

    }
}
