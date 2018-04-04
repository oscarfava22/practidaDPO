package Entry;

import Entry.View.Entry;

import javax.imageio.ImageIO;
import javax.swing.*;

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

                    entry.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
