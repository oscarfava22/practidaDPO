package servidor.controller;

import servidor.Main;
import servidor.model.Database.BBDDManager;
import servidor.model.Database.SerialMesasGenerator;
import servidor.view.AddMesaDialogView;
import servidor.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMesaDialogListener implements ActionListener{

    /**
     * Atributos de la clase
     */
    private AddMesaDialogView view;
    private MainView mainView;
    private MesasViewListener listener;

    /**
     * Constructor de la clase con parámetros
     * @param view Dialog
     * @param mainView Vista principal
     * @param mesasViewListener Listener
     */
    public AddMesaDialogListener(AddMesaDialogView view, MainView mainView, MesasViewListener mesasViewListener){
        this.view = view;
        this.mainView = mainView;
        listener = mesasViewListener;
    }

    /**
     * El usuario clica en un botón, ya sea positivo o negativo:
     *      Positive button: Comprueba que el número de comensales sea correcto
     *          y posteriormente añade una mesa (con un id generado por
     *          SerialMesaGenerator) a la BBDD y cierra el dialog.
     *      Negative button: Cierra el dialog sin efectuar cambios en la BBDD.
     * @param e
     */
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
                    BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
                    // Del objeto getInstance hacer un connect
                    bbddManager.connect();

                    String idSerialString = Integer.toString(idSerial);
                    String numComensalesString = Integer.toString(numComensales);

                    String query = "INSERT INTO Mesa (id_mesa, num_comensales) VALUES (" + idSerialString + ", " + numComensalesString + ");";
                    bbddManager.modificationQuery(query);

                    // Del objeto getInstance, desconectar
                    bbddManager.disconnect();

                    //TODO: actualizar mainview (AQUI O AL SALIR DEL DIALOG)
                    mainView.getGestionMesasView().refreshMesas(listener);

                    //Cerrar el dialog
                    view.disable();
                    view.setVisible(false);
                    view.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Formato de comensales incorrecto", "Error!", JOptionPane.ERROR_MESSAGE);
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
