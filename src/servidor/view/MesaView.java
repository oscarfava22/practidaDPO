package servidor.view;

import servidor.controller.MesasViewListener;
import servidor.model.Mesa;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class MesaView extends JPanel {

    //Borde
    private Border compounBorder =  BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10,10,10,10));

    /**
     * Atributos de la clase
     */
    private CustomLabel jlIdMesa;
    private CustomLabel jlNumComensalsMax;
    private boolean selected;

    /**
     * Constructor sin parámetros
     */
    public MesaView(){
        setLayout(new GridLayout(2, 1));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        jlIdMesa = new CustomLabel();
        jlIdMesa.setBorder(compounBorder);
        jlIdMesa.setOpaque(true);

        jlNumComensalsMax = new CustomLabel();
        jlNumComensalsMax.setBorder(compounBorder);
        jlNumComensalsMax.setOpaque(true);

        add(jlIdMesa);
        add(jlNumComensalsMax);
        selected = false;
    }

    /**
     * Constructor con parámetros
     * @param mesa
     */
    public MesaView(Mesa mesa){
        this();
        setJlLabels("Id Mesa: " + Integer.toString(mesa.getId()), "Comensales: " + Integer.toString(mesa.getNumComensales()));
        jlIdMesa.setFont(new Font("Comic Sans", Font.BOLD, 14));
        jlIdMesa.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    /**
     * Getters & Setters
     */
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

    /**
     * Función para vincular los listeners
     * @param mesasViewListener
     */
    public void registerControllers(MesasViewListener mesasViewListener) {
        this.addMouseListener(mesasViewListener);
        this.addMouseMotionListener(mesasViewListener);
    }

}
