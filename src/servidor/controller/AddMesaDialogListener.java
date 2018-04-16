package servidor.controller;

import servidor.view.AddMesaDialogView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMesaDialogListener implements ActionListener{

    private AddMesaDialogView view;

    public AddMesaDialogListener(AddMesaDialogView view){
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case AddMesaDialogView.POSITIVE_TAG:
                if (view.isNumComensalesCorrect()){
                    //TODO: Connect with bbdd to create the specified table. BBDD must generate an id for this table
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
