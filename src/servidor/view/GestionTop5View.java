package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class GestionTop5View extends JPanel {

    private final Top5View view;

    /**
     *
     */
    public GestionTop5View() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
        view = new Top5View();
        add(view,BorderLayout.CENTER);
    }

    /**
     *
     * @param error
     */
    public void showError(String error){
        JOptionPane.showMessageDialog(this,error,"Top5Errror",JOptionPane.ERROR_MESSAGE);
    }

    /**
     *
     * @return
     */
    public Top5View getView() {
        return view;
    }
}
