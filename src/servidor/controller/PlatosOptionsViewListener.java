package servidor.controller;

import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.SerialGenerator;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class PlatosOptionsViewListener implements MouseInputListener {

    private MainView mainView;
    private PlatosManager platosManager;
    private PlatosViewListener platosViewListener;

    public PlatosOptionsViewListener(MainView mainView, PlatosManager platosManager, PlatosViewListener platosViewListener) {
        this.mainView = mainView;
        this.platosManager = platosManager;
        this.platosViewListener = platosViewListener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();

            switch(jb.getText()) {
                case "Edit":

                    if (jb.isEnabled()) {
                        System.out.println(jb.getText());
                        mainView.setOptionsEditState(true);
                    }

                    break;

                case "Cancel":

                    if(jb.isEnabled()) {
                        if (mainView.getGCVEditState() || mainView.getGCVAddState()) {
                            if(mainView.getGCVEditState()) {
                                mainView.setOptionsEditState(false);
                            } else {
                                mainView.setAddProductState(false);
                            }
                        }
                    }

                    break;

                case "Update":

                    if (jb.isEnabled()) {
                        if (verifyProductForm() == 0) {

                            int option = JOptionPane.showConfirmDialog(null,
                                    "Update Selected Product?",
                                    "Update Product Info", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {

                                platosManager.getPlatos().get(Integer.parseInt(mainView.getIdText()) - 1).setTitle(mainView.getTitleText());
                                platosManager.getPlatos().get(Integer.parseInt(mainView.getIdText()) - 1).setPrice(mainView.getPriceText());
                                platosManager.getPlatos().get(Integer.parseInt(mainView.getIdText()) - 1).setUnits(mainView.getUnitsText());

                                mainView.setOptionsEditState(false);
                                mainView.refreshPlatos(platosManager.getPlatos(), platosViewListener);

                            } else if(option == JOptionPane.NO_OPTION) {
                                mainView.setOptionsEditState(true);
                            }

                        } else {
                            JOptionPane.showMessageDialog(mainView, "Incorrect Info",
                                    "Update Product Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    break;

                case "Delete":

                    if(jb.isEnabled()) {

                        int option = JOptionPane.showConfirmDialog(null,
                                "Delete Selected Product?",
                                "Edit Product Info", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {

                            for(int i = 0; i < platosManager.getPlatos().size(); i++) {
                                if(platosManager.getPlatos().get(i).getId().equals(mainView.getIdText())) {
                                    platosManager.getPlatos().remove(i);
                                }
                            }

                            mainView.resetTextFields();
                            mainView.setOptionsEditState(false);
                            mainView.setAddProductState(false);
                            mainView.refreshPlatos(platosManager.getPlatos(), platosViewListener);

                        } else if(option == JOptionPane.NO_OPTION) {
                            mainView.setOptionsEditState(true);
                        }
                    }

                    break;

                case "Create":

                    if (jb.isEnabled()) {
                        if (verifyProductForm() == 0) {

                            int option2 = JOptionPane.showConfirmDialog(null,
                                    "Create New Product?",
                                    "New Product Info", JOptionPane.YES_NO_OPTION);

                            if (option2 == JOptionPane.YES_OPTION) {
                                //Se crea un nuevo plato y se le asigna un nuevo Id
                                platosManager.getPlatos().add(new Plato(SerialGenerator.getId().toString(),
                                        mainView.getTitleText(),
                                        mainView.getPriceText(),
                                        mainView.getUnitsText()));

                                mainView.setOptionsEditState(false);
                                mainView.setAddProductState(false);
                                mainView.refreshPlatos(platosManager.getPlatos(), platosViewListener);

                            } else if(option2 == JOptionPane.NO_OPTION) {
                                mainView.setOptionsEditState(true);
                            }

                        } else {
                            JOptionPane.showMessageDialog(mainView, "Incorrect Info", "New Product Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    break;

                case"Add Product":

                    if(jb.isEnabled()) {
                        mainView.setAddProductState(true);
                        mainView.setLabelsBackground("1",false);
                    }

                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }

    public int verifyProductForm() {
        int error_type = 0;

        if (!mainView.getTitleText().equals("")) {
            if(!mainView.getPriceText().equals("")){
                if(mainView.getUnitsText().equals("")){ error_type = 1; }
            } else { error_type = 2; }
        } else { error_type = 3; }

        return error_type;
    }
}
