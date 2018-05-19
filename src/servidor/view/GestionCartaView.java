package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.LinkedList;

/**
 *
 */
public class GestionCartaView extends JPanel {

    private PlatosView platosView;
    private PlatosOptionsView platosOptionsView;

    /**
     *
     */
    public GestionCartaView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));
        platosView = new PlatosView();
        platosOptionsView = new PlatosOptionsView();
    }

    /**
     *
     * @param platos
     */
    public void initView(LinkedList<Plato> platos) {
        platosView.initPlatosView(platos);
        add(platosOptionsView, BorderLayout.LINE_START);
        add(platosView, BorderLayout.CENTER);
    }

    /**
     *
     * @param gestionCartaViewListener
     */
    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        platosView.registerControllers(gestionCartaViewListener);
        platosOptionsView.registerControllers(gestionCartaViewListener);
    }

    /**
     *
     * @return
     */
    public PlatosOptionsView getPlatosOptionsView() {
        return platosOptionsView;
    }

    /**
     *
     * @return
     */
    public PlatosView getPlatosView() {
        return platosView;
    }
}
