package Entry.View.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Panel for the init screen
 */
public class InitPanel extends JPanel{

    /**
     * Label to click
     */
    private final JLabel label;

    /**
     * Panel for the logo
     * @param ii the icon that makes the logo
     */
    public InitPanel(ImageIcon ii){
        setLayout(new BorderLayout());
        label = new JLabel();
        label.setIcon(ii);

        add(label);
    }

    /**
     * Adds controller to label
     * @param mouseListener controller of the label
     */
    public void relateControllers(MouseListener mouseListener){
        label.addMouseListener(mouseListener);
    }

}
