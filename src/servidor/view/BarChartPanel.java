package servidor.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ramon on 07/05/2018.
 * @project reservaClientProject
 */
public class BarChartPanel extends JPanel {

    private int[] data;
    private String[] nomPlats;
    private Color[] colorsPlats;
    private String chartTitle;

    public BarChartPanel (int[] data, String[] nomPlats, Color[] colorsPlats, String chartTitle) {
        this.data = data;
        this.nomPlats = nomPlats;
        this.colorsPlats = colorsPlats;
        this.chartTitle = chartTitle;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        if (data != null && data.length != 0) {

            double minValue = 0;
            double maxValue = 0;
            for (int i = 0; i < data.length; i++) {
                if (minValue > data[i]) {
                    minValue = data[i];
                }
                if (maxValue < data[i]) {
                    maxValue = data[i];
                }
            }

            Dimension dimension = getSize();
            int barChartPanelWidth = dimension.width;
            int barChartPanelHeight = dimension.height;
            int barWidth = barChartPanelWidth / data.length - 40 / data.length;

            Font title = new Font("Arial", Font.BOLD, 20);
            FontMetrics titleMetrics = g.getFontMetrics(title);

            Font label = new Font("Arial", Font.PLAIN, 15);
            FontMetrics labelMetrics = g.getFontMetrics(label);

            int titleWidth = titleMetrics.stringWidth(chartTitle);
            int stringWidth = (barChartPanelWidth - titleWidth) / 2;
            g.setFont(title);
            g.drawString(chartTitle, stringWidth, titleMetrics.getAscent());

            g.setFont(label);
            double scale = (barChartPanelHeight - titleMetrics.getHeight() - labelMetrics.getHeight()) / (maxValue - minValue);
            int numbersYHeight;

            //Mostrar nombres de unitats per l'eix Y

            int numbersInterval = (int) ((maxValue - minValue) / 8);
            int maxWidthNumber = 0;
            for (int i = 7; i >= 0; i--) {

               // numbersYHeight = (int) (titleMetrics.getHeight() + (maxValue - data[i]) * scale);
                g.setColor(Color.black);
                g.drawString(String.valueOf(numbersInterval * (8 - i)), 5, (int) (titleMetrics.getHeight() + numbersInterval * i * scale));

                if (labelMetrics.stringWidth(String.valueOf(numbersInterval * (8 - i))) > maxWidthNumber) {
                    maxWidthNumber = labelMetrics.stringWidth(String.valueOf(numbersInterval * (8 - i)));
                }
            }

            g.setColor(Color.black);
            g.fillRect(10 + maxWidthNumber, 10, 1, barChartPanelHeight - labelMetrics.getHeight());

            for (int i = 0; i < data.length; i++) {

                int xCoord = i * barWidth + 40;
                int yCoord;
                int height = (int) (data[i] * scale);
                if (data[i] >= 0) {
                    yCoord = (int) ((titleMetrics.getHeight() + (maxValue - data[i]) * scale));
                } else {
                    yCoord = (int) (titleMetrics.getHeight() + (maxValue * scale));
                    height = -height;
                }

                numbersYHeight = (int) (titleMetrics.getHeight() + (maxValue - data[i]) * scale);

                g.setColor(colorsPlats[i]);
                g.fillRect(xCoord, yCoord, barWidth - 2, height);

                int labelWidth = labelMetrics.stringWidth(nomPlats[i]);
                stringWidth = i * barWidth + (barWidth - labelWidth) / 2 + 40;
                g.drawString(nomPlats[i], stringWidth, barChartPanelHeight - labelMetrics.getDescent());
            }

        }
    }
}
