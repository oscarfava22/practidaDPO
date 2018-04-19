package servidor.controller;

import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.SerialGenerator;
import servidor.view.CustomLabel;
import servidor.view.GestionCartaView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class GestionCartaViewListener implements MouseInputListener {

    private GestionCartaView gestionCartaView;
    private PlatosManager platosManager;

    public GestionCartaViewListener(GestionCartaView gestionCartaView, PlatosManager platosManager) {
        this.gestionCartaView = gestionCartaView;
        this.platosManager = platosManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();

            switch(jb.getText()) {
                case "Edit":
                    if (jb.isEnabled()) {
                        gestionCartaView.getPlatosOptionsView().setEditState(true);
                    }
                    break;

                case "Cancel":
                    if(jb.isEnabled()) {
                        if (gestionCartaView.getPlatosOptionsView().getEditState() || gestionCartaView.getPlatosOptionsView().getAddState()) {
                            if(gestionCartaView.getPlatosOptionsView().getEditState()) {
                                gestionCartaView.getPlatosOptionsView().setEditState(false);
                            } else {
                                gestionCartaView.getPlatosOptionsView().setAddProductState(false);
                            }
                        }
                    }
                    break;

                case "Update":
                    if (jb.isEnabled()) {
                        if (verifyProductForm()) {

                            int option = JOptionPane.showConfirmDialog(null,
                                    "Update Selected Product?",
                                    "Update Product Info", JOptionPane.YES_NO_OPTION);

                            if (option == JOptionPane.YES_OPTION) {

                                platosManager.updatePlato(
                                        Long.parseLong(gestionCartaView.getPlatosOptionsView().getIdText()),
                                        gestionCartaView.getPlatosOptionsView().getType(),
                                        gestionCartaView.getPlatosOptionsView().getTitleText(),
                                        Float.parseFloat(gestionCartaView.getPlatosOptionsView().getPriceText()),
                                        Integer.parseInt(gestionCartaView.getPlatosOptionsView().getUnitsText()));

                                gestionCartaView.getPlatosView().updatePlato(
                                        gestionCartaView.getPlatosOptionsView().getIdText(),
                                        gestionCartaView.getPlatosOptionsView().getType(),
                                        gestionCartaView.getPlatosOptionsView().getTitleText(),
                                        gestionCartaView.getPlatosOptionsView().getPriceText(),
                                        gestionCartaView.getPlatosOptionsView().getUnitsText());

                                gestionCartaView.getPlatosOptionsView().setEditState(false);

                            } else if(option == JOptionPane.NO_OPTION) {
                                gestionCartaView.getPlatosOptionsView().setEditState(true);
                            }
                        }
                    }
                    break;

                case "Delete":
                    if(jb.isEnabled()) {

                        int option = JOptionPane.showConfirmDialog(null,
                                "Delete Selected Product?",
                                "Edit Product Info", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {

                            platosManager.removePlato(Long.parseLong(gestionCartaView.getPlatosOptionsView().getIdText()));
                            gestionCartaView.getPlatosView().deletePlato(gestionCartaView.getPlatosOptionsView().getIdText());

                            gestionCartaView.getPlatosOptionsView().resetTextFields();
                            gestionCartaView.getPlatosOptionsView().setEditState(false);
                            gestionCartaView.getPlatosOptionsView().setAddProductState(false);

                        } else if(option == JOptionPane.NO_OPTION) {
                            gestionCartaView.getPlatosOptionsView().setEditState(true);
                        }
                    }

                    break;

                case "Create":

                    if (jb.isEnabled()) {
                        if (verifyProductForm()) {

                            int option2 = JOptionPane.showConfirmDialog(null,
                                    "Create New Product?",
                                    "New Product Info", JOptionPane.YES_NO_OPTION);

                            if (option2 == JOptionPane.YES_OPTION) {
                                //Se crea un nuevo plato y se le asigna un nuevo Id
                                platosManager.addPlato(new Plato(
                                        SerialGenerator.getProductId(),
                                        gestionCartaView.getPlatosOptionsView().getType(),
                                        gestionCartaView.getPlatosOptionsView().getTitleText(),
                                        Float.parseFloat(gestionCartaView.getPlatosOptionsView().getPriceText()),
                                        Integer.parseInt(gestionCartaView.getPlatosOptionsView().getUnitsText())));

                                gestionCartaView.getPlatosOptionsView().setEditState(false);
                                gestionCartaView.getPlatosOptionsView().setAddProductState(false);
                                gestionCartaView.getPlatosView().addPlato(platosManager.getPlatos().getLast(), this);

                            } else if(option2 == JOptionPane.NO_OPTION) {
                                gestionCartaView.getPlatosOptionsView().setEditState(true);
                            }
                        }
                    }

                    break;

                case"Add Product":
                    if(jb.isEnabled()) {
                        gestionCartaView.getPlatosOptionsView().setAddProductState(true);
                        gestionCartaView.getPlatosView().setSelectedPlatosState(false);
                    }
                    break;
            }

        }

        if (e.getSource().getClass().equals(CustomLabel.class)) {
            CustomLabel cl = (CustomLabel) e.getSource();

            if (gestionCartaView.getPlatosOptionsView().getEditState() || gestionCartaView.getPlatosOptionsView().getAddState()){

                if (gestionCartaView.getPlatosOptionsView().getEditState()) {

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Editing?", "Edit Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        gestionCartaView.getPlatosOptionsView().setEditState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        gestionCartaView.getPlatosOptionsView().setEditState(true);
                    }

                }else if (gestionCartaView.getPlatosOptionsView().getAddState()){

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Creating New Product?", "New Product Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        gestionCartaView.getPlatosOptionsView().setAddProductState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        gestionCartaView.getPlatosOptionsView().setAddProductState(true);
                    }
                }

            } else {

                gestionCartaView.setSelectedPlatoState(cl.getId(), true);
                //Default State
                gestionCartaView.getPlatosOptionsView().setEditState(false);

                for(int i = 0; i < platosManager.getPlatos().size(); i++) {
                    if(platosManager.getPlatos().get(i).getId() == Long.parseLong(cl.getId())) {
                        gestionCartaView.getPlatosOptionsView().setTextFields(platosManager.getPlato(i));
                        break;
                    }
                }
            }
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

    public boolean verifyProductForm() {

        int error_type = 0;

        if (!gestionCartaView.getPlatosOptionsView().getTitleText().equals("")) {
            if(!gestionCartaView.getPlatosOptionsView().getPriceText().equals("")){
                try {
                    Float.parseFloat(gestionCartaView.getPlatosOptionsView().getPriceText());
                    if(!gestionCartaView.getPlatosOptionsView().getUnitsText().equals("")) {
                        try {
                            Integer.parseInt(gestionCartaView.getPlatosOptionsView().getUnitsText());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(gestionCartaView.getPlatosOptionsView(), "Incorrect Info - Incorrect Units Format",
                                    "New Product Error", JOptionPane.ERROR_MESSAGE);
                            error_type = 4;
                        }
                    } else {
                        JOptionPane.showMessageDialog(gestionCartaView.getPlatosOptionsView(), "Incorrect Info - Empty Units",
                                "New Product Error", JOptionPane.ERROR_MESSAGE);
                        error_type = 1;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(gestionCartaView.getPlatosOptionsView(), "Incorrect Info - Incorrect Price Format",
                            "New Product Error", JOptionPane.ERROR_MESSAGE);
                    error_type = 5;
                }

            } else {
                JOptionPane.showMessageDialog(gestionCartaView.getPlatosOptionsView(), "Incorrect Info - Empty Price",
                        "New Product Error", JOptionPane.ERROR_MESSAGE);
                error_type = 2;
            }
        } else {
            JOptionPane.showMessageDialog(gestionCartaView.getPlatosOptionsView(), "Incorrect Info - Empty Title", "New Product Error",
                    JOptionPane.ERROR_MESSAGE);
            error_type = 3;
        }

        return error_type == 0;
    }
}
