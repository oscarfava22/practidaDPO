package reserva.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CartaView extends JFrame{

    public static final String PAGAR = "PAGAR";
    public static final String DEMANAR = "DEMANAR";
    private final JButton jbPagar;
    private final JButton jbDemanar;
    private final JLabel jlPreu;
    private ArrayList<JButton> jButtonsAfegirUnitat;
    private ArrayList<JButton> jButtonsTreureUnitat;
    private ArrayList<JButton> jButtonsAfegir;
    private ArrayList<JLabel> jLabelsUnits;
    private ArrayList<JButton> jButtonsAfegirCistella;
    private ArrayList<JButton> jButtonsTreureCistella;
    private ArrayList<JButton> jButtonsEliminarCistella;
    private ArrayList<JLabel> jLabelsUnitsCistella;

    public CartaView (int numEntrants, int numPrimers, int numBegudes, int numPostres, int numAfegits, int unitats) {

        setSize(800,600);
        setTitle("Carta");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //North

        jButtonsAfegir = new ArrayList<>();
        jButtonsAfegirUnitat = new ArrayList<>();
        jButtonsTreureUnitat = new ArrayList<>();
        jLabelsUnits = new ArrayList<>();
        jButtonsAfegirCistella = new ArrayList<>();
        jButtonsTreureCistella = new ArrayList<>();
        jButtonsEliminarCistella = new ArrayList<>();
        jLabelsUnitsCistella = new ArrayList<>();

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

            JButton jbAfegirUnitats = new JButton("+");
            JButton jbTreureUnitats = new JButton("-");
            JButton jbAfegir = new JButton("Esborrar");
            JLabel jlUnitats = new JLabel(String.valueOf(unitats));

            jButtonsAfegirCistella.add(jbAfegirUnitats);
            jButtonsTreureCistella.add(jbTreureUnitats);
            jButtonsEliminarCistella.add(jbAfegir);
            jLabelsUnitsCistella.add(jlUnitats);

            jpCesta.add(new ItemCartaView("Nom afegit", jbAfegirUnitats, jbTreureUnitats, jbAfegir, jlUnitats));
        }

        try {
            image = ImageIO.read(new File("data/ic_shopping_basket_black_24dp_2x.png"));
            Icon icon = new ImageIcon(image);
            jtpNorth.addTab("Cesta", icon, jpCesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel jpComanda = new JPanel();
        jtpNorth.addTab("Estat comanda", jpComanda);

        getContentPane().add(jtpNorth, BorderLayout.CENTER);

        //Center

        //Taula Entrants

        JPanel jpEntrants2 = new JPanel(new GridLayout(numEntrants,1));

        for(int i = 0; i < numEntrants; i++) {

            JButton jbAfegirUnitats = new JButton("+");
            JButton jbTreureUnitats = new JButton("-");
            JButton jbAfegir = new JButton("Afegir");
            JLabel jlUnitats = new JLabel(String.valueOf(unitats));

            jButtonsAfegirUnitat.add(jbAfegirUnitats);
            jButtonsTreureUnitat.add(jbTreureUnitats);
            jButtonsAfegir.add(jbAfegir);
            jLabelsUnits.add(jlUnitats);

            jpEntrants2.add(new ItemCartaView("Nom entrant", jbAfegirUnitats, jbTreureUnitats, jbAfegir, jlUnitats));
        }

        JScrollPane jpEntrants = new JScrollPane(jpEntrants2);
        jtpCenter.addTab("Entrants", jpEntrants);


        //Taula Primers

        JPanel jpPrimers2 = new JPanel(new GridLayout(numPrimers,1));

        for(int i = 0; i < numPrimers; i++) {

            JButton jbAfegirUnitats = new JButton("+");
            JButton jbTreureUnitats = new JButton("-");
            JButton jbAfegir = new JButton("Afegir");
            JLabel jlUnitats = new JLabel(String.valueOf(unitats));

            jButtonsAfegirUnitat.add(jbAfegirUnitats);
            jButtonsTreureUnitat.add(jbTreureUnitats);
            jButtonsAfegir.add(jbAfegir);
            jLabelsUnits.add(jlUnitats);

            jpPrimers2.add(new ItemCartaView("Nom primers", jbAfegirUnitats, jbTreureUnitats, jbAfegir, jlUnitats));
        }

        JScrollPane jpPrimers = new JScrollPane(jpPrimers2);
        jtpCenter.addTab("Principals", jpPrimers);

        //Taula Begudes

        JPanel jpBegudes2 = new JPanel(new GridLayout(numBegudes,1));

        for(int i = 0; i < numBegudes; i++) {

            JButton jbAfegirUnitats = new JButton("+");
            JButton jbTreureUnitats = new JButton("-");
            JButton jbAfegir = new JButton("Afegir");
            JLabel jlUnitats = new JLabel(String.valueOf(unitats));

            jButtonsAfegirUnitat.add(jbAfegirUnitats);
            jButtonsTreureUnitat.add(jbTreureUnitats);
            jButtonsAfegir.add(jbAfegir);
            jLabelsUnits.add(jlUnitats);

            jpBegudes2.add(new ItemCartaView("Nom beguda", jbAfegirUnitats, jbTreureUnitats, jbAfegir, jlUnitats));
        }

        JScrollPane jpBegudes = new JScrollPane(jpBegudes2);
        jtpCenter.addTab("Begudes", jpBegudes);

        //Taula Postres

        JPanel jpPostres2 = new JPanel(new GridLayout(numPostres,1));

        for(int i = 0; i < numPostres; i++) {

            JButton jbAfegirUnitats = new JButton("+");
            JButton jbTreureUnitats = new JButton("-");
            JButton jbAfegir = new JButton("Afegir");
            JLabel jlUnitats = new JLabel(String.valueOf(unitats));

            jButtonsAfegirUnitat.add(jbAfegirUnitats);
            jButtonsTreureUnitat.add(jbTreureUnitats);
            jButtonsAfegir.add(jbAfegir);
            jLabelsUnits.add(jlUnitats);

            jpPostres2.add(new ItemCartaView("Nom postre", jbAfegirUnitats, jbTreureUnitats, jbAfegir, jlUnitats));
        }

        JScrollPane jpPostres = new JScrollPane(jpPostres2);
        jtpCenter.addTab("Postres", jpPostres);

        //South
        JPanel jpSouth = new JPanel(new BorderLayout());

        jbPagar = new JButton("Pagar");
        jbDemanar = new JButton("Demanar");
        jlPreu = new JLabel("0.00€");

        JPanel jpEast = new JPanel(new FlowLayout());
        jpEast.add(jbPagar);

        JPanel jpEast2 = new JPanel(new FlowLayout());
        jpEast2.add(jbDemanar);

        JPanel jpEast3 = new JPanel(new FlowLayout());
        jpEast3.add(jlPreu);

        jpSouth.add(jpEast, BorderLayout.LINE_END);
        jpSouth.add(jpEast3, BorderLayout.CENTER);
        jpSouth.add(jpEast2, BorderLayout.LINE_START);

        getContentPane().add(jpSouth, BorderLayout.PAGE_END);

        setVisible(false);
    }

    /**
     * Enregistrar els controladors dels botons fixes, es a dir, aquells que corresponen a plats de la carta i no els de la cistella, que dependran de l'usuari.
     * @param actionListener
     */
    public void registerControllers (ActionListener actionListener) {

        jbPagar.addActionListener(actionListener);
        jbPagar.setActionCommand(PAGAR);
        jbDemanar.addActionListener(actionListener);
        jbDemanar.setActionCommand(DEMANAR);

        for (int i = 0; i < jButtonsAfegirUnitat.size(); i++) { //Per tots els plats

            jButtonsAfegirUnitat.get(i).addActionListener(actionListener);
            jButtonsAfegirUnitat.get(i).setActionCommand("AFEGIR_UNITAT_" + i);

            jButtonsTreureUnitat.get(i).addActionListener(actionListener);
            jButtonsTreureUnitat.get(i).setActionCommand("TREURE_UNITAT_" + i);

            jButtonsAfegir.get(i).addActionListener(actionListener);
            jButtonsAfegir.get(i).setActionCommand("AFEGIR_" + i);
        }

    }
}
