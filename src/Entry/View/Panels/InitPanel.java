package Entry.View.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for the init screen
 */
public class InitPanel extends JPanel{

    /**
     * Panel for the logo
     * @param ii the icon that makes the logo
     */
    public InitPanel(ImageIcon ii){
        setLayout(new BorderLayout());
        JLabel label = new JLabel();
        label.setIcon(ii);

        add(label);
    }

}
