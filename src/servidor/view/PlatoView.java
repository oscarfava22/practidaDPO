package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class PlatoView extends JPanel {

    private Border compounBorder =  BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(Color.DARK_GRAY),
                                    BorderFactory.createEmptyBorder(10,10,10,10));

    private CustomLabel jlProductId;
    private CustomLabel jlTitle;
    private CustomLabel jlPrice;
    private CustomLabel jlUnits;

    public PlatoView() {
        setLayout(new GridLayout(1,5));
    }

    public PlatoView(Plato plato) {
        this();
        initPlatoView(plato);
    }

    public void initPlatoView(Plato plato) {
        initLabels(plato);
        add(jlProductId);
        add(jlTitle);
        add(jlPrice);
        add(jlUnits);
    }

    public void initLabels(Plato plato) {

        jlProductId = new CustomLabel(String.valueOf(plato.getId()));
        jlProductId.setBorder(compounBorder);
        jlProductId.setOpaque(true);
        jlProductId.setBackground(Color.GRAY);
        setJlProductId(String.valueOf(plato.getId()));

        jlTitle = new CustomLabel(String.valueOf(plato.getId()));
        jlTitle.setBorder(compounBorder);
        jlTitle.setOpaque(true);
        jlTitle.setBackground(Color.LIGHT_GRAY);
        setJlTitle(plato.getTitle());

        jlPrice = new CustomLabel(String.valueOf(plato.getId()));
        jlPrice.setBorder(compounBorder);
        jlPrice.setOpaque(true);
        jlPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        jlPrice.setBackground(Color.LIGHT_GRAY);
        setJlPrice(String.valueOf(plato.getPrice()));

        jlUnits = new CustomLabel(String.valueOf(plato.getId()));
        jlUnits.setBorder(compounBorder);
        jlUnits.setOpaque(true);
        jlUnits.setHorizontalAlignment(SwingConstants.RIGHT);
        jlUnits.setBackground(Color.LIGHT_GRAY);
        setJlUnits(String.valueOf(plato.getUnits()));
    }

    public void registerControllers(MouseInputListener gestionCartaViewListener) {

        jlProductId.addMouseListener(gestionCartaViewListener);
        jlProductId.addMouseMotionListener(gestionCartaViewListener);
        jlTitle.addMouseListener(gestionCartaViewListener);
        jlTitle.addMouseMotionListener(gestionCartaViewListener);
        jlPrice.addMouseListener(gestionCartaViewListener);
        jlPrice.addMouseMotionListener(gestionCartaViewListener);
        jlUnits.addMouseListener(gestionCartaViewListener);
        jlUnits.addMouseMotionListener(gestionCartaViewListener);
    }

    public void updatePlatoView(Plato plato) {
        setJlTitle(plato.getTitle());
        setJlPrice(String.valueOf(plato.getPrice()));
        setJlUnits(String.valueOf(plato.getUnits()));
    }

    public String getJlProductId() {
        return jlProductId.getText();
    }

    public void setJlProductId(String productId) {
        jlProductId.setText(productId);
    }

    public String getJlTitle() {
        return jlTitle.getText();
    }

    public void setJlTitle(String title) {
        jlTitle.setText(title);
    }

    public String getJlPrice() {
        return jlPrice.getText();
    }

    public void setJlPrice(String price) {
        jlPrice.setText(price);
    }

    public String getJlUnits() {
        return jlUnits.getText();
    }

    public void setJlUnits(String units) {
        jlUnits.setText(units);
    }

    public void setSelectedState(boolean state) {

        if (state) {
            jlProductId.setBackground(Color.ORANGE);
            jlTitle.setBackground(Color.ORANGE);
            jlPrice.setBackground(Color.ORANGE);
            jlUnits.setBackground(Color.ORANGE);

        } else {
            jlProductId.setBackground(Color.GRAY);
            jlTitle.setBackground(Color.LIGHT_GRAY);
            jlPrice.setBackground(Color.LIGHT_GRAY);
            jlUnits.setBackground(Color.LIGHT_GRAY);
        }

        if(jlUnits.getText().equals("0")) {
            jlUnits.setForeground(Color.RED);
        }
    }
}
