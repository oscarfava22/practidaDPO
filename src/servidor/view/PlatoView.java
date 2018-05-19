package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.*;

/**
 * Panel que representa la vista de un Plato.
 */
public class PlatoView extends JPanel {

    private Border compounBorder =  BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(Color.DARK_GRAY),
                                    BorderFactory.createEmptyBorder(10,10,10,10));

    private CustomLabel jlProductId;
    private CustomLabel jlTitle;
    private CustomLabel jlPrice;
    private CustomLabel jlUnits;

    /**
     * Constructor por defecto.
     */
    public PlatoView() {
        setLayout(new GridLayout(1,5));
    }

    /**
     * Constructor que recibe otro plato.
     * @param plato
     */
    public PlatoView(Plato plato) {
        this();
        initPlatoView(plato);
    }

    /**
     * Permite inicializar un plato mediante otro plato.
     * @param plato
     */
    public void initPlatoView(Plato plato) {
        initLabels(plato);
        add(jlProductId);
        add(jlTitle);
        add(jlPrice);
        add(jlUnits);
    }

    /**
     * Método utilizado para inicializar el estado de las etiquetas.
     * @param plato
     */
    public void initLabels(Plato plato) {

        jlProductId = new CustomLabel(String.valueOf(plato.getId()));
        jlProductId.setBorder(compounBorder);
        jlProductId.setOpaque(true);
        jlProductId.setForeground(Color.ORANGE);
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

    /**
     * Permite registrar los componentes al controlador recibido.
     * @param gestionCartaViewListener controlador que se recibe.
     */
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

    /**
     * Permite obtener el id del producto.
     * @return el id del producto.
     */
    public String getJlProductId() {
        return jlProductId.getText();
    }

    /**
     * Permite asignar un id al producto.
     * @param productId para asignar al producto.
     */
    public void setJlProductId(String productId) {
        jlProductId.setText(productId);
    }

    /**
     * Permite asignar el nombre de un producto
     * @param title nombre para asignar al producto.
     */
    public void setJlTitle(String title) {
        jlTitle.setText(title);
    }

    /**
     * Permite asignar un precio al producto.
     * @param price precio para asignar al producto.
     */
    public void setJlPrice(String price) {
        jlPrice.setText(price);
    }

    /**
     * Permite asignar el numero de unidades de un producto.
     * @param units numero de unidades del producto.
     */
    public void setJlUnits(String units) {
        jlUnits.setText(units);
    }

    /**
     * Permite cambiar la apariencia de un producto cuando se hace click sobre el,
     * diferenciandolo de los no seleccionados.
     * @param state
     */
    public void setSelectedState(boolean state) {

        if (state) {
            jlProductId.setBackground(Color.ORANGE);
            jlProductId.setForeground(Color.BLACK);
            jlTitle.setBackground(Color.ORANGE);
            jlPrice.setBackground(Color.ORANGE);
            jlUnits.setBackground(Color.ORANGE);

        } else {
            jlProductId.setBackground(Color.GRAY);
            jlProductId.setForeground(Color.ORANGE);
            jlTitle.setBackground(Color.LIGHT_GRAY);
            jlPrice.setBackground(Color.LIGHT_GRAY);
            jlUnits.setBackground(Color.LIGHT_GRAY);
        }

        if(jlUnits.getText().equals("0")) {
            jlUnits.setForeground(Color.RED);
        }
    }
}
