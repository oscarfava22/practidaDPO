package servidor.controller;

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
     * Cuando se clique un botón miraremos si es el botón de añadir una mesa o bien el de eliminarla
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GestionMesasView.AÑADIR_MESA_TAG:
                addDialog = new AddMesaDialogView(mainView);
                addDialog.initDialog();

                AddMesaDialogListener addListener = new AddMesaDialogListener(addDialog, mainView, mesasViewListener);
                addDialog.registerControllers(addListener);

                mainView.refreshMesas(mesasViewListener);
                break;

            case GestionMesasView.ELIMINAR_MESA_TAG:
                int idMesaSeleccionada = obtenerIdMesaSeleccionada(mainView);
                if (idMesaSeleccionada != -1){
                    int delete = JOptionPane.showConfirmDialog(null,
                            "Eliminar la mesa seleccionada?",
                            "Eliminar mesa", JOptionPane.YES_NO_OPTION);

                    if (delete == JOptionPane.YES_OPTION){

                        //Conectar con la bbd si en el dialog ha clicado en "ELIMINAR"
                        //Llamar al getInstance
                        BBDDManager bbddManager = BBDDManager.getInstance("Restaurant");
                        // Del objeto getInstance hacer un connect
                        bbddManager.connect();

                        //Querie --> eliminar la taula amb id de taula idMesaSeleccionada
                        String idMesaSeleccionadaString = Integer.toString(idMesaSeleccionada);
                        String queryMesa = "DELETE FROM Mesa WHERE id_mesa = " + idMesaSeleccionadaString + ";";
                        String queryReservas = "DELETE FROM Reserva WHERE id_mesa = " + idMesaSeleccionadaString + ";";

                        bbddManager.modificationQuery(queryReservas);
                        bbddManager.modificationQuery(queryMesa);

                        // Del objeto getInstance, desconectar
                        bbddManager.disconnect();

                        mainView.refreshMesas(mesasViewListener);

                    } else if(delete == JOptionPane.NO_OPTION) {
                        System.out.println("No eliminar mesa");
                    }
                }else {
                    System.out.println("Mesa no seleccionada");
                }


                break;
        }
    }


    private int obtenerIdMesaSeleccionada(MainView mainView) {
        String idMesaSelected = mainView.getGestionMesasView().getJlIdMesaSelected().getText().toString();

/*        LinkedList<MesaView> mesas = mainView.getGestionMesasView().getMesasView().getMesasView();

        for (int i = 0; i < mesas.size(); i++){
            if (mesas.get(i).getSelected()){
                idMesaSelected = Integer.parseInt(mesas.get(i).getJlIdMesa().getText().toString());
            }
        }
*/
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

    public void initDeleteDialog(){
        deleteDialog.setVisible(true);
        deleteDialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
