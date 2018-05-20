package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.LinkedList;

/**
 * Panel que contiene la vista de los platos y la vista de las opciones de edicion para cada plato.
 */
public class GestionCartaView extends JPanel {

    private PlatosView platosView;
    private PlatosOptionsView platosOptionsView;

    /**
     * Constructor que inicializa el panel.
     */
    public GestionCartaView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));
        platosView = new PlatosView();
        platosOptionsView = new PlatosOptionsView();
    }

    /**
     * Método que permite incializar la vista del panel en cualquier momento.
     * @param platos lista de platos que se recibe para cargar en la vista de platos.
     */
    public void initView(LinkedList<Plato> platos) {
        platosView.initPlatosView(platos);
        add(platosOptionsView, BorderLayout.LINE_START);
        add(platosView, BorderLayout.CENTER);
    }

    /**
     * Permite registrar los paneles al controlador que se recibe como parametro.
     * @param gestionCartaViewListener controlador que se recibe para registar a los paneles asociados.
     */
    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        platosView.registerControllers(gestionCartaViewListener);
        platosOptionsView.registerControllers(gestionCartaViewListener);
    }

    /**
     * Permite obtener el panel referente a las opciones de edicion.
     * @return el panel de las opciones de edicion.
     */
    public PlatosOptionsView getPlatosOptionsView() {
        return platosOptionsView;
    }

    /**
     * Permite obtener el paner referente a los platos.
     * @return el panel de los platos.
     */
    public PlatosView getPlatosView() {
        return platosView;
    }
}
