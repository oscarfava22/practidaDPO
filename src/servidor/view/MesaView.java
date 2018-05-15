package servidor.view;

import servidor.controller.MesasViewListener;
import servidor.model.Mesa;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class MesaView extends JPanel {

    private Border compounBorder =  BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10,10,10,10));

    private CustomLabel jlIdMesa;
    private CustomLabel jlNumComensalsMax;
    private boolean selected;

    public MesaView(){
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        //setBackground(Color.GREEN);

        jlIdMesa = new CustomLabel();
        jlIdMesa.setBorder(compounBorder);
        jlIdMesa.setOpaque(true);
        //jlIdMesa.setBackground(Color.GRAY);

        jlNumComensalsMax = new CustomLabel();
        jlNumComensalsMax.setBorder(compounBorder);
        jlNumComensalsMax.setOpaque(true);
        //jlNumComensalsMax.setBackground(Color.GRAY);

        add(jlIdMesa);
        add(jlNumComensalsMax);
        selected = false;
    }

    public MesaView(Mesa mesa){
        this();
        setJlLabels("Id Mesa: " + Integer.toString(mesa.getId()), "Comensales: " + Integer.toString(mesa.getNumComensales()));
        jlIdMesa.setFont(new Font("Comic Sans", Font.BOLD, 14));
        jlIdMesa.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void setJlLabels(String idMesa, String numComensalsMax) {
        setJlIdMesa(idMesa);
        setJlNumComensalsMax(numComensalsMax);
    }

    public CustomLabel getJlIdMesa() {
        return jlIdMesa;
    }

    public void setJlIdMesa(String idMesa) {
        this.jlIdMesa.setText(idMesa);
    }

    public CustomLabel getJlNumComensalsMax() {
        return jlNumComensalsMax;
    }

    public void setJlNumComensalsMax(String numComensalsMax) {
        this.jlNumComensalsMax.setText(numComensalsMax);
    }

    public void setLabelsBackground(boolean state) {

        if (state) {
            //setBorder(new LineBorder(Color.ORANGE, 2));
            jlIdMesa.setBackground(Color.ORANGE);
            jlNumComensalsMax.setBackground(Color.ORANGE);
        } else {
            //setBorder(new LineBorder(Color.DARK_GRAY, 2));
            jlIdMesa.setBackground(Color.GRAY);
            jlNumComensalsMax.setBackground(Color.GRAY);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void registerControllers(MesasViewListener mesasViewListener) {
        this.addMouseListener(mesasViewListener);
        this.addMouseMotionListener(mesasViewListener);
    }

    public boolean getSelected() {
        return selected;
    }
}
