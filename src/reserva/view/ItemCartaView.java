package reserva.view;

import javax.swing.*;
import java.awt.*;

public class ItemCartaView extends JPanel {

    public ItemCartaView (String nom, JButton jbAfegirUnitats, JButton jbTreureUnitats, JButton jbAfegir, JLabel jlUnitats) {

        this.setLayout(new GridLayout(1, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel jlNom = new JLabel(nom);
        jlNom.setBorder((BorderFactory.createEmptyBorder(0, 10, 0, 10)));

        JPanel jpAfegirUnitats = new JPanel();
        jpAfegirUnitats.setLayout(new BoxLayout(jpAfegirUnitats, BoxLayout.Y_AXIS));
        jpAfegirUnitats.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        jpAfegirUnitats.add(jbAfegirUnitats);
        Container container = new Container();
        container.setMinimumSize(new Dimension(40,50));
        container.setMaximumSize(new Dimension(40, 50));
        jpAfegirUnitats.add(container);
        jpAfegirUnitats.add(jbTreureUnitats);

        JPanel jpAfegir = new JPanel();
        jpAfegir.setLayout(new BoxLayout(jpAfegir, BoxLayout.Y_AXIS));
        //jpAfegir.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        Container container2 = new Container();
        container2.setMinimumSize(new Dimension(40,50));
        container2.setMaximumSize(new Dimension(40, 50));
        jpAfegir.add(container2);
        jpAfegir.add(jbAfegir);
        Container container3 = new Container();
        container3.setMinimumSize(new Dimension(40,50));
        container3.setMaximumSize(new Dimension(40, 50));
        jpAfegir.add(container3);

        this.add(jlNom);
        this.add(jlUnitats);
        this.add(jpAfegirUnitats);
        this.add(jpAfegir);
    }
}
