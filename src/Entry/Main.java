package Entry;

import Entry.Controller.DatePickerController;
import Entry.Controller.InitController;
import Entry.Controller.LandingController;
import Entry.Controller.ReserveController;
import Entry.View.Entry;

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
                    entry.addListeners(init,reserve,picker,landing);

                    entry.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
