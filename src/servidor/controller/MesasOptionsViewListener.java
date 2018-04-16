package servidor.controller;

import servidor.model.MesasManager;
import servidor.view.AddMesaDialogView;
import servidor.view.GestionMesasView;
import servidor.view.MainView;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MesasOptionsViewListener implements ActionListener {

    private MainView mainView;
    private MesasManager mesasManager;
    private AddMesaDialogView addDialog;

    public MesasOptionsViewListener(MainView mainView, MesasManager mesasManager){
        this.mainView = mainView;
        this.mesasManager = mesasManager;
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
                break;
            case GestionMesasView.ELIMINAR_MESA_TAG:
                //TODO: Aparecer Dialog para confirmar que desea eliminar la mesa
                    //TODO: Conectar con la bbd si en el dialog ha clicado en "ELIMINAR"
                    //TODO: Eliminar la mesa y todas sus reservas de la bbdd
                break;
        }
    }
}
