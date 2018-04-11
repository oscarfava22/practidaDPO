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

    private String[] productsTypes = { "Entrante", "Plato Principal", "Postre", "Bebidas" };

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

                                for(int i = 0; i < productsTypes.length; i++) {
                                    if(productsTypes[i].equals(mainView.getTypeText())) {

                                        platosManager.updatePlato(new Plato(
                                                        Long.parseLong(mainView.getIdText()),
                                                        String.valueOf(i),
                                                        mainView.getTitleText(),
                                                        Float.parseFloat(mainView.getPriceText()),
                                                        Integer.parseInt(mainView.getUnitsText())));
                                    }
                                }

                                mainView.setOptionsEditState(false);
                                mainView.refreshPlatos(platosManager.getPlatos(), platosViewListener);

                            } else if(option == JOptionPane.NO_OPTION) {
                                mainView.setOptionsEditState(true);
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

                            platosManager.removePlato(Long.parseLong(mainView.getIdText()));

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
                            System.out.println(mainView.getTypeText());
                            if (option2 == JOptionPane.YES_OPTION) {

                                for(int i = 0; i < productsTypes.length; i++) {
                                    if(productsTypes[i].equals(mainView.getTypeText())) {
                                        //Se crea un nuevo plato y se le asigna un nuevo Id
                                        System.out.println(String.valueOf(i));
                                        platosManager.getPlatos().add(new Plato(SerialGenerator.getProductId(),
                                                String.valueOf(i),
                                                mainView.getTitleText(),
                                                Float.parseFloat(mainView.getPriceText()),
                                                Integer.parseInt(mainView.getUnitsText())));
                                    }
                                }

                                mainView.setOptionsEditState(false);
                                mainView.setAddProductState(false);
                                mainView.refreshPlatos(platosManager.getPlatos(), platosViewListener);

                            } else if(option2 == JOptionPane.NO_OPTION) {
                                mainView.setOptionsEditState(true);
                            }
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
                try {
                    Float.parseFloat(mainView.getPriceText());
                    if(!mainView.getUnitsText().equals("")) {
                        try {
                            Integer.parseInt(mainView.getUnitsText());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(mainView, "Incorrect Info - Incorrect Units Format",
                                    "New Product Error", JOptionPane.ERROR_MESSAGE);
                            error_type = 4;
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainView, "Incorrect Info - Empty Units",
                                                     "New Product Error", JOptionPane.ERROR_MESSAGE);
                        error_type = 1;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(mainView, "Incorrect Info - Incorrect Price Format",
                                                 "New Product Error", JOptionPane.ERROR_MESSAGE);
                    error_type = 5;
                }

            } else {
                JOptionPane.showMessageDialog(mainView, "Incorrect Info - Empty Price",
                                             "New Product Error", JOptionPane.ERROR_MESSAGE);
                error_type = 2;
            }
        } else {
            JOptionPane.showMessageDialog(mainView, "Incorrect Info - Empty Title", "New Product Error",
                    JOptionPane.ERROR_MESSAGE);
            error_type = 3;
        }

        return error_type;
    }
}
