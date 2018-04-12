package Entry.View.Panels.ReservePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Class thas has a textfield and a label for it
 */
class TextFieldPanel extends JPanel{

    /**
     * Textfield in which  the information resides
     */
    private JTextField jTextField;

    /**
     * Creates a new TextField panel
     * @param title the title of the panel
     * @param perferedWidth the width of the JTextField
     */
    TextFieldPanel(String title, int perferedWidth){
        this.add(new JLabel(title));
        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(perferedWidth,(int)jTextField.getPreferredSize().getHeight()));

        this.add(jTextField);
    }

    /**
     * Returns the contents of the textfield
     * @return the contents of the textfield
     */
    String getFieldText(){
        return jTextField.getText();
    }

    /**
     * Sets the textfield data
     * @param fieldText the new textfield data
     */
    void setFieldText(String fieldText) {
        this.jTextField.setText(fieldText);
    }
}
