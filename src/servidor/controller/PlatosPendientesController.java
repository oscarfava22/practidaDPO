package servidor.controller;

import servidor.view.CustomLabel;
import servidor.view.MainView;
import servidor.view.PlatoView;
import servidor.view.PlatosView;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class PlatosPendientesController implements MouseInputListener {

    private PlatosView platosView;

    public PlatosPendientesController(PlatosView platosView) {
        this.platosView = platosView;
    }

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