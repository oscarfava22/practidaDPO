package servidor.controller;

import servidor.model.PlatosManager;
import servidor.view.CustomLabel;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class PlatosViewListener implements MouseInputListener {

    private MainView mainView;
    private PlatosManager platosManager;

    public PlatosViewListener(MainView mainView, PlatosManager platosManager) {
        this.mainView = mainView;
        this.platosManager = platosManager;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();
            System.out.println(jb.getText());
        }

        if (e.getSource().getClass().equals(CustomLabel.class)) {

            CustomLabel cl = (CustomLabel) e.getSource();
            System.out.println(cl.getId() + "%" + cl.getText());

            if (mainView.getGCVEditState() || mainView.getGCVAddState()){

                if (mainView.getGCVEditState()) {

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Editing?", "Edit Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainView.setOptionsEditState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        mainView.setOptionsEditState(true);
                    }

                }else if (mainView.getGCVAddState()){

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Creating New Product?", "New Product Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        mainView.setAddProductState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        mainView.setAddProductState(true);
                    }
                }

            } else {

                mainView.setLabelsBackground(cl.getId(), true);
                //Default State
                mainView.setOptionsEditState(false);
                for(int i = 0; i < platosManager.getPlatos().size(); i++) {
                    if(platosManager.getPlatos().get(i).getId().equals(cl.getId())) {
                        mainView.setOptionsText(platosManager.getPlato(i));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) { }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) { }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) { }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) { }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) { }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) { }

}
