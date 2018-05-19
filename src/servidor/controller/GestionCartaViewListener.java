package servidor.controller;

import servidor.model.Plato;
import servidor.model.PlatosManager;
import servidor.model.SerialGenerator;
import servidor.network.ReservaServer;
import servidor.view.CustomLabel;
import servidor.view.PlatosOptionsView;
import servidor.view.PlatosView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Controlador para gestionar la vista de la carta y la vista de las opciones de edicion para cada plato, mediante la
 * interraccion con el gestor de platos.
 *
 */
public class GestionCartaViewListener implements MouseInputListener {

    /**
     * Atributos de la clase
     */
    private PlatosOptionsView platosOptionsView;
    private PlatosView platosView;
    private PlatosManager platosManager;
    private ReservaServer reservaServer;

    /**
     * Construcotr de la que inicializa todas las variables.
     * @param platosOptionsView vista de las opciones de edición de un plato.
     * @param platosView vista de los platos de la carta.
     * @param platosManager gestor de platos.
     */
    public GestionCartaViewListener(PlatosOptionsView platosOptionsView, PlatosView platosView, PlatosManager platosManager) {
        this.platosOptionsView = platosOptionsView;
        this.platosView = platosView;
        this.platosManager = platosManager;
    }

    /**
     * Permite realizar diferentes acciones segun el tipo de clase detectada en el mouse event.
     * @param e el evento de "Mouse" detectado.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().getClass().equals(JButton.class)) {
            JButton jb = (JButton) e.getSource();

            switch(jb.getText()) {
                case "Edit":
                    if (jb.isEnabled()) {
                        //Se activa el estado de edicion del panel de opciones de la vista de la carta
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
                                //Se actualiza el plato modificado en el gestor de platos del sistema
                                platosManager.updatePlato(
                                                          Long.parseLong(platosOptionsView.getIdText()),
                                                          platosOptionsView.getType(),
                                                          platosOptionsView.getTitleText(),
                                                          Float.parseFloat(platosOptionsView.getPriceText()),
                                                          Integer.parseInt(platosOptionsView.getUnitsText()));

                                //Se actualiza la vista del plato modificado
                                platosView.updatePlato(
                                                       platosOptionsView.getIdText(),
                                                       platosOptionsView.getType(),
                                                       platosOptionsView.getTitleText(),
                                                       platosOptionsView.getPriceText(),
                                                       platosOptionsView.getUnitsText());

                                platosOptionsView.setEditState(false);

                                //Se realiza un broadcast a todos los clientes conectados para que actualicen sus cartas
                                reservaServer.sendBroadcast();

                            } else if(option == JOptionPane.NO_OPTION) {
                                platosOptionsView.setEditState(true);
                            }
                        }
                    }
                    break;

                case"Add Product":
                    if(jb.isEnabled()) {
                        //Se activa el modo de creacion de un plato permitiendo introducir los datos necesarios
                        platosOptionsView.setAddProductState(true);
                        //Se deseleccionan el plato previamente seleccionado en la vista
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

                                //Se reinicia al estado inicial de los botones
                                platosOptionsView.setEditState(false);
                                platosOptionsView.setAddProductState(false);

                                //Se añade el plato creado al gestor de platos del sistema
                                platosView.addPlato(platosManager.getPlatos().getLast(), this);

                                //Se realiza un broadcast a todos los clientes conectados para que actualicen sus cartas
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
                            //Se elimina el plato del modelo interno y de la vista de platos
                            platosManager.removePlato(Long.parseLong(platosOptionsView.getIdText()));
                            platosView.deletePlato(platosOptionsView.getIdText());

                            //Se reinician los campos del panel de opciones y se vuelve al estado inicial de los botones
                            platosOptionsView.resetTextFields();
                            platosOptionsView.setEditState(false);
                            platosOptionsView.setAddProductState(false);

                            //Se realiza un broadcast a todos los clientes conectados para que actualicen sus cartas
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

            //Condicion para solicitar al usuari si desea cancelar la edicion o creacion de un producto si apreta
            //en la lista de platos de la carta estando activado el modo de edicio o añadir nuevo plato
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

                //Se selecciona el plato detectado mediante el mouse event y se le cambia el color para diferenciarlo
                platosView.setSelectedPlatoState(cl.getId(), true);
                //Default State
                platosOptionsView.setEditState(false);
                //Se rellenan los campos de las opciones de cada plato para mostar informacion mas detallada
                platosOptionsView.setTextFields(platosManager.getPlato(Long.parseLong(cl.getId())));
            }
        }
    }

    /**
     * Permite registar el servidor Reserva para poder realizar Broadcast a todos los clientes conectados cuando se
     * realice una modificacion, creacion o eliminacion de un plato.
     * @param reservaServer servidor de Reserva que se recibe para registrar
     */
    public void registerServer(ReservaServer reservaServer) {
        this.reservaServer = reservaServer;
    }

    /**
     * Permite verificar la validez de los datos introducidos referentes a las caracteristicas de un plato.
     * @return el estado de la verificacion.
     */
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
