package Entry;

import Entry.Controller.*;
import Entry.View.Entry;
import Entry.View.Panels.DatePickerPanel.DatePickerPanel;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                File file = new File("./data/img/logo.png");
                try {
                    ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
                    Entry entry = new Entry(imageIcon);
                    MouseListener init = new InitController(entry);
                    ActionListener reserve = new ReserveController(entry);
                    ActionListener picker = new DatePickerController(entry);
                    ActionListener landing = new LandingController(entry);
                    DateController dateController = new DateController(entry);
                    dateController.updateView(false);
                    entry.addListeners(init,reserve,picker,landing,dateController);

                    entry.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
