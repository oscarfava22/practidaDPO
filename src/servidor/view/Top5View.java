package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ramon on 02/05/2018.
 * @project reservaClientProject
 */
public class Top5View extends JFrame{

    public Top5View () {

        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        String title = "Top 5";
        int[] values = new int[]{11,25,32,120,5};
        String[] labels = new String[]{"Chorizo","Queso","Polla","Carne","Cebolla"};
        Color[] colors = new Color[]{
                Color.red,
                Color.orange,
                Color.yellow,
                Color.green,
                Color.blue
        };
        BarChartPanel bc = new BarChartPanel(values, labels, colors, title);

        frame.add(bc);
        frame.setVisible(true);
    }
}