package servidor.controller;

import servidor.model.Database.BBDDManager;
import servidor.model.Database.SerialMesasGenerator;
import servidor.view.AddMesaDialogView;
import servidor.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMesaDialogListener implements ActionListener{

    private AddMesaDialogView view;
    private MainView mainView;

    public AddMesaDialogListener(AddMesaDialogView view, MainView mainView){
        this.view = view;
        this.mainView = mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case AddMesaDialogView.POSITIVE_TAG:
                new AddMesaDialogView(null);
                if (view.isNumComensalesCorrect()){
                    System.out.println("Numero de comensales correcto");

                    int numComensales = view.getNumComensales();
                    int idSerial = SerialMesasGenerator.getSerial();

                    //Llamar al getInstance
                    BBDDManager bbddManager = BBDDManager.getInstance("Restaurant");
                    // Del objeto getInstance hacer un connect
                    bbddManager.connect();

                    String idSerialString = Integer.toString(idSerial);
                    String numComensalesString = Integer.toString(numComensales);

                    String query = "INSERT INTO Mesa (id_mesa, num_comensales) VALUES (" + idSerialString + ", " + numComensalesString + ");";
                    bbddManager.modificationQuery(query);



                    // Del objeto getInstance, desconectar
                    bbddManager.disconnect();

                    //TODO: actualizar mainview (AQUI O AL SALIR DEL DIALOG)
                    //mainView.getGestionMesasView().refreshMesas();

                    //Cerrar el dialog
                    view.disable();
                    view.setVisible(false);
                    view.dispose();
                }else{
                    System.out.println("Numero de comensales incorrecto");
                }
                break;
            case AddMesaDialogView.NEGATIVE_TAG:
                view.disable();
                view.setVisible(false);
                view.dispose();
                break;
        }
    }
}
