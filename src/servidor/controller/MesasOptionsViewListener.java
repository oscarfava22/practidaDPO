package servidor.controller;

import servidor.model.MesasManager;
import servidor.view.AddMesaDialogView;
import servidor.view.GestionMesasView;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MesasOptionsViewListener implements ActionListener {

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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GestionMesasView.AÑADIR_MESA_TAG:
                addDialog = new AddMesaDialogView(mainView);
                addDialog.initDialog();

                AddMesaDialogListener addListener = new AddMesaDialogListener(addDialog);
                addDialog.registerControllers(addListener);

                //while (addDialog.isVisible()){}
                //TODO: Aparecer Dialog para añadir mesa
                    //TODO: Conectar con la bbd si en el dialog ha clicado a "AÑADIR"
                    //TODO: Crear la mesa en la bbdd
                mainView.refreshMesas(mesasManager.getMesas(), mesasViewListener);
                break;

            case GestionMesasView.ELIMINAR_MESA_TAG:
                int delete = JOptionPane.showConfirmDialog(null,
                        "ELiminar la mesa seleccionada?",
                        "Eliminar mesa", JOptionPane.YES_NO_OPTION);

                if (delete == JOptionPane.YES_OPTION){
                    System.out.println("Eliminar mesa");
                    //TODO: Conectar con la bbd si en el dialog ha clicado en "ELIMINAR"
                    //TODO: Eliminar la mesa y todas sus reservas de la bbdd
                    mainView.refreshMesas(mesasManager.getMesas(), mesasViewListener);
                } else if(delete == JOptionPane.NO_OPTION) {
                    System.out.println("No eliminar mesa");
                    mainView.setOptionsEditState(true);
                }

                break;
        }
    }

    public void initDeleteDialog(){
        deleteDialog.setVisible(true);
        deleteDialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
