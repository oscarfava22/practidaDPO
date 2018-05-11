package servidor.controller;

import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.SerialGenerator;
import servidor.network.MainServer;
import servidor.network.ReservaServer;
import servidor.view.CustomLabel;
import servidor.view.PlatosOptionsView;
import servidor.view.PlatosView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class GestionCartaViewListener implements MouseInputListener {

    /**
     * Atributos de la clase
     */
    private PlatosOptionsView platosOptionsView;
    private PlatosView platosView;
    private PlatosManager platosManager;
    private ReservaServer reservaServer;

    /**
     * Construcotr de la clase
     * @param platosOptionsView
     * @param platosView
     * @param platosManager
     */
    public GestionCartaViewListener(PlatosOptionsView platosOptionsView, PlatosView platosView, PlatosManager platosManager) {
        this.platosOptionsView = platosOptionsView;
        this.platosView = platosView;
        this.platosManager = platosManager;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();

            switch(jb.getText()) {
                case "Edit":
                    if (jb.isEnabled()) {
                        platosOptionsView.setEditState(true);
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
                                                          Long.parseLong(platosOptionsView.getIdText()),
                                                          platosOptionsView.getType(),
                                                          platosOptionsView.getTitleText(),
                                                          Float.parseFloat(platosOptionsView.getPriceText()),
                                                          Integer.parseInt(platosOptionsView.getUnitsText()));

                                platosView.updatePlato(
                                                       platosOptionsView.getIdText(),
                                                       platosOptionsView.getType(),
                                                       platosOptionsView.getTitleText(),
                                                       platosOptionsView.getPriceText(),
                                                       platosOptionsView.getUnitsText());

                                platosOptionsView.setEditState(false);
                                reservaServer.sendBroadcast();

                            } else if(option == JOptionPane.NO_OPTION) {
                                platosOptionsView.setEditState(true);
                            }
                        }
                    }
                    break;

                case"Add Product":
                    if(jb.isEnabled()) {
                        platosOptionsView.setAddProductState(true);
                        platosView.setSelectedPlatosState(false);
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
                                        platosOptionsView.getType(),
                                        platosOptionsView.getTitleText(),
                                        Float.parseFloat(platosOptionsView.getPriceText()),
                                        Integer.parseInt(platosOptionsView.getUnitsText())));

                                platosOptionsView.setEditState(false);
                                platosOptionsView.setAddProductState(false);
                                platosView.addPlato(platosManager.getPlatos().getLast(), this);
                                reservaServer.sendBroadcast();

                            } else if(option2 == JOptionPane.NO_OPTION) {
                                platosOptionsView.setEditState(true);
                            }
                        }
                    }

                    break;

                case "Cancel":
                    if(jb.isEnabled()) {
                        if (platosOptionsView.getEditState() || platosOptionsView.getAddState()) {
                            if(platosOptionsView.getEditState()) {
                                platosOptionsView.setEditState(false);
                            } else {
                                platosOptionsView.setAddProductState(false);
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
                            platosManager.removePlato(Long.parseLong(platosOptionsView.getIdText()));
                            platosView.deletePlato(platosOptionsView.getIdText());

                            platosOptionsView.resetTextFields();
                            platosOptionsView.setEditState(false);
                            platosOptionsView.setAddProductState(false);
                            reservaServer.sendBroadcast();

                        } else if(option == JOptionPane.NO_OPTION) {
                            platosOptionsView.setEditState(true);
                        }
                    }

                    break;
            }

        }

        if (e.getSource().getClass().equals(CustomLabel.class)) {
            CustomLabel cl = (CustomLabel) e.getSource();

            if (platosOptionsView.getEditState() || platosOptionsView.getAddState()){

                if (platosOptionsView.getEditState()) {

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Editing?", "Edit Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        platosOptionsView.setEditState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        platosOptionsView.setEditState(true);
                    }

                }else if (platosOptionsView.getAddState()){

                    int option = JOptionPane.showConfirmDialog(null,
                            "Exit Creating New Product?", "New Product Info", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        platosOptionsView.setAddProductState(false);
                    } else if (option == JOptionPane.NO_OPTION) {
                        platosOptionsView.setAddProductState(true);
                    }
                }

            } else {

                platosView.setSelectedPlatoState(cl.getId(), true);
                //Default State
                platosOptionsView.setEditState(false);
                platosOptionsView.setTextFields(platosManager.getPlato(Long.parseLong(cl.getId())));
            }
        }
    }

    public void registerServer(ReservaServer reservaServer) {
        this.reservaServer = reservaServer;
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

        if (!platosOptionsView.getTitleText().equals("")) {
            if(!platosOptionsView.getPriceText().equals("")){
                try {
                    Float.parseFloat(platosOptionsView.getPriceText());
                    if(!platosOptionsView.getUnitsText().equals("")) {
                        try {
                            Integer.parseInt(platosOptionsView.getUnitsText());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(platosOptionsView, 
                                    "Incorrect Info - Incorrect Units Format",
                                    "New Product Error", JOptionPane.ERROR_MESSAGE);
                            error_type = 4;
                        }
                    } else {
                        JOptionPane.showMessageDialog(platosOptionsView, 
                                "Incorrect Info - Empty Units",
                                "New Product Error", JOptionPane.ERROR_MESSAGE);
                        error_type = 1;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(platosOptionsView, 
                            "Incorrect Info - Incorrect Price Format",
                            "New Product Error", JOptionPane.ERROR_MESSAGE);
                    error_type = 5;
                }

            } else {
                JOptionPane.showMessageDialog(platosOptionsView, 
                        "Incorrect Info - Empty Price",
                        "New Product Error", JOptionPane.ERROR_MESSAGE);
                error_type = 2;
            }
        } else {
            JOptionPane.showMessageDialog(platosOptionsView, 
                    "Incorrect Info - Empty Title", "New Product Error",
                    JOptionPane.ERROR_MESSAGE);
            error_type = 3;
        }

        return error_type == 0;
    }
}
