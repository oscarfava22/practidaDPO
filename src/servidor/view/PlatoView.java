package servidor.view;

import servidor.controller.PlatosViewListener;
import servidor.model.Plato;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlatoView extends JPanel {

    private Border compounBorder =  BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(Color.DARK_GRAY),
                                    BorderFactory.createEmptyBorder(10,10,10,10));

    private CustomLabel jlId;
    private CustomLabel jlTitle;
    private CustomLabel jlPrice;
    private CustomLabel jlUnits;

    public PlatoView() {

        setLayout(new GridLayout(1,5));

        jlId = new CustomLabel();
        jlId.setBorder(compounBorder);
        jlId.setOpaque(true);
        jlId.setBackground(Color.GRAY);

        jlTitle = new CustomLabel();
        jlTitle.setBorder(compounBorder);
        jlTitle.setOpaque(true);
        jlTitle.setBackground(Color.LIGHT_GRAY);

        jlPrice = new CustomLabel();
        jlPrice.setBorder(compounBorder);
        jlPrice.setOpaque(true);
        jlPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        jlPrice.setBackground(Color.LIGHT_GRAY);

        jlUnits = new CustomLabel();
        jlUnits.setBorder(compounBorder);
        jlUnits.setOpaque(true);
        jlUnits.setHorizontalAlignment(SwingConstants.RIGHT);
        jlUnits.setBackground(Color.LIGHT_GRAY);

        add(jlId);
        add(jlTitle);
        add(jlPrice);
        add(jlUnits);

        //setBorder(new LineBorder(Color.RED, 2));
    }


    public PlatoView(Plato plato) {
        this();
        setJlLabels(String.valueOf(plato.getId()), String.valueOf(plato.getId()), plato.getTitle(), String.valueOf(plato.getPrice()), String.valueOf(plato.getUnits()));
    }
    
    private void setJlLabels(String index, String id, String title, String price, String units) {
        setJlId(index, id);
        setJlTitle(index, title);
        setJlPrice(index, price);
        setJlUnits(index, units);
    }

    public void initPlatoView() {

    }

    public void registerControllers(PlatosViewListener platosViewListener) {

        jlId.addMouseListener(platosViewListener);
        jlId.addMouseMotionListener(platosViewListener);

        jlTitle.addMouseListener(platosViewListener);
        jlTitle.addMouseMotionListener(platosViewListener);

        jlPrice.addMouseListener(platosViewListener);
        jlPrice.addMouseMotionListener(platosViewListener);

        jlUnits.addMouseListener(platosViewListener);
        jlUnits.addMouseMotionListener(platosViewListener);
    }


    public String getJlId() {
        return jlId.getText();
    }

    public void setJlId(String index, String id) {
        jlId.setId(index);
        jlId.setText(id);
    }

    public String getJlTitle() {
        return jlTitle.getText();
    }

    public void setJlTitle(String index, String title) {
        jlTitle.setId(index);
        jlTitle.setText(title);
    }

    public String getJlPrice() {
        return jlPrice.getText();
    }

    public void setJlPrice(String index, String price) {
        jlPrice.setId(index);
        jlPrice.setText(price);
    }

    public String getJlUnits() {
        return jlUnits.getText();
    }

    public void setJlUnits(String index, String units) {
        jlUnits.setId(index);
        jlUnits.setText(units);
    }

    public void setLabelsBackground(boolean state) {

        if (state) {
            //setBorder(new LineBorder(Color.ORANGE, 2));
            jlId.setBackground(Color.ORANGE);
            jlTitle.setBackground(Color.ORANGE);
            jlPrice.setBackground(Color.ORANGE);
            jlUnits.setBackground(Color.ORANGE);

        } else {
            //setBorder(new LineBorder(Color.DARK_GRAY, 2));

            jlId.setBackground(Color.GRAY);
            jlTitle.setBackground(Color.LIGHT_GRAY);
            jlPrice.setBackground(Color.LIGHT_GRAY);
            jlUnits.setBackground(Color.LIGHT_GRAY);
        }
        if(jlUnits.getText().equals("0")) {
            jlUnits.setForeground(Color.RED);
        }
    }
}
