package servidor.controller;

import servidor.view.CustomLabel;
import servidor.view.PlatosView;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 *
 */
public class PlatosProcesadosListener implements MouseInputListener {
    private PlatosView platosView;

    /**
     *
     * @param platosView
     */
    public PlatosProcesadosListener(PlatosView platosView) {
        this.platosView = platosView;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(CustomLabel.class)) {
            CustomLabel cl = (CustomLabel) e.getSource();

            platosView.setSelectedPlatoState(cl.getId(), true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
