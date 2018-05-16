package servidor.controller;

import servidor.Main;
import servidor.model.Database.BBDDManager;
import servidor.model.MesasManager;
import servidor.view.AddMesaDialogView;
import servidor.view.GestionMesasView;
import servidor.view.MainView;
import servidor.view.MesaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MesasOptionsViewListener implements ActionListener{

    private MesasViewListener mesasViewListener;
    private MainView mainView;
    private MesasManager mesasManager;
    private AddMesaDialogView addDialog;
    private JDialog deleteDialog;

    public MesasOptionsViewListener(MainView mainView, MesasManager mesasManager, MesasViewListener mesasViewListener){
        this.mainView = mainView;
        this.mesasManager = mesasManager;
        this.mesasViewListener = mesasViewListener;
    }

    /**
     * Cuando se clique un botón miraremos si es el botón de añadir una mesa o bien el de eliminarla.
     *       - Botón de Añadir Mesa: Abrimos un Dialog para preguntar de cuántos comensales será la mesa y refrescamos
     *          la lista de las mesas para que aparezca la nueva.
     *       - Botón de Eliminar Mesa: Abrimos un Dialog para preguntar si se está seguro de eliminar la mesa que
     *          estaba seleccionada, si no hay ninguna seleccionada, no se abre el Dialog
     * @param e: Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GestionMesasView.AÑADIR_MESA_TAG:
                //Abrimos Dialog
                addDialog = new AddMesaDialogView(mainView);
                addDialog.initDialog();

                //Registramos el controlador del Dialog
                AddMesaDialogListener addListener = new AddMesaDialogListener(addDialog, mainView, mesasViewListener);
                addDialog.registerControllers(addListener);

                //Refresh del panel de las mesas para que aparezca la nueva si
                mainView.refreshMesas(mesasViewListener);
                break;

            case GestionMesasView.ELIMINAR_MESA_TAG:
                //Obtenemos el id de la mesa seleccionada
                int idMesaSeleccionada = obtenerIdMesaSeleccionada(mainView);

                if (idMesaSeleccionada != -1){
                    //Abrimos Dialog para confirmar la eliminación de la mesa seleccionada
                    int delete = JOptionPane.showConfirmDialog(null,
                            "Eliminar la mesa seleccionada?",
                            "Eliminar mesa", JOptionPane.YES_NO_OPTION);

                    if (delete == JOptionPane.YES_OPTION){
                        //Conectar con la bbd si en el dialog ha clicado en "ELIMINAR"
                        //Llamar al getInstance
                        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
                        // Del objeto getInstance hacer un connect
                        bbddManager.connect();

                        //Querie --> eliminar la taula amb id de taula idMesaSeleccionada
                        String idMesaSeleccionadaString = Integer.toString(idMesaSeleccionada);
                        String queryMesa = "DELETE FROM Mesa WHERE id_mesa = " + idMesaSeleccionadaString + ";";
                        String queryReservas = "DELETE FROM Reserva WHERE id_mesa = " + idMesaSeleccionadaString + ";";

                        //Llancem les queries a la BBDD
                        bbddManager.modificationQuery(queryReservas);
                        bbddManager.modificationQuery(queryMesa);

                        // Del objeto getInstance, desconectar
                        bbddManager.disconnect();

                        mainView.refreshMesas(mesasViewListener);
                        mainView.getGestionMesasView().getJlIdSelected().setText(GestionMesasView.NINGUNA_MESA_SELECCIONADA_TAG);
                        mainView.getGestionMesasView().ponColorIdSelected();

                    } else if(delete == JOptionPane.NO_OPTION) {
                        System.out.println("No eliminar mesa");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,
                            "Ninguna mesa seleccionada", "Error!", JOptionPane.ERROR_MESSAGE);

                }
                break;
        }
    }


    /**
     * Obtener el id de la mesa seleccionada en el panel izquierdo
     * @param mainView
     * @return
     */
    private int obtenerIdMesaSeleccionada(MainView mainView) {
        String idMesaSelected = mainView.getGestionMesasView().getJlIdMesaSelected().getText().toString();

        int id;
        if (idMesaSelected.contains(GestionMesasView.NINGUNA_MESA_SELECCIONADA_TAG)){
            id = -1;
        }else{
            int index = idMesaSelected.indexOf(":");
            String substring = idMesaSelected.substring(idMesaSelected.lastIndexOf(":") + 2);
            id = Integer.parseInt(substring);
        }
        return id;
    }
}
