package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 * @project reservaClientProject
 */
public class Top5View extends JPanel{

    private int [] values =  new int[5];
    private  String[] labels = new String[5];
    private BarChartPanel bc;
    private Color[] colors = new Color[]{
            Color.red,
            Color.orange,
            Color.yellow,
            Color.green,
            Color.blue
    };
    private String title = "Top 5";
    //private JFrame frame;

    /**
     * Crea la grafica inicial i establece los parametros de configuracion.
     */
    public Top5View () {

        //frame = new JFrame("Bar Chart Example");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400, 250);

        this.setLayout(new BorderLayout());

        //frame.add(bc);
        //frame.setVisible(true);
    }

    /**
     * Actualiza la grafica siempre i con los nuevos valores de values i labels
     */
    public void modificaBC(){
        bc = new BarChartPanel(values, labels, colors, title);
        this.removeAll();
        this.add(bc,BorderLayout.CENTER);
        this.updateUI();
    }

    /**
     * Establecer la cadena de nombres
     * @param labels nombre del valor de contador
     */
    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    /**
     * Establecer la cadena de valores
     * @param values cadena para saber el valor de contador
     */
    public void setValues(int[] values) {
        this.values = values;
    }
}
