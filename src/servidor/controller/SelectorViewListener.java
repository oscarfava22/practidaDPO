package servidor.controller;

import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class SelectorViewListener implements MouseInputListener {

    private MainView mainView;

    /**
     *
     * @param mainView
     */
    public SelectorViewListener (MainView mainView) {
        this.mainView = mainView;
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVSelectedButton(jb.getText());
            mainView.setGestionView(jb.getText());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVFocusedButton(jb.getText(), true);
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().getClass().equals(JButton.class)) {

            JButton jb = (JButton) e.getSource();

            mainView.setSVFocusedButton(jb.getText(), false);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
