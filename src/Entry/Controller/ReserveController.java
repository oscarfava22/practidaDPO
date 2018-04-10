package Entry.Controller;

import Entry.Constants.Constants;
import Entry.Model.Exception.NotEnoughTableException;
import Entry.Model.Network.Client;
import Entry.View.Entry;
import Entry.View.Panels.ReservePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class ReserveController implements ActionListener{
    private Entry view;
    private Client client;

    public ReserveController(Entry view,Client client){
        this.view = view;
        this.client = client;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int state = view.reserveNotEmpty();
        if(state ==0){
            switch (e.getActionCommand()){
                case Constants.NOW:
                    try {
                        String password = client.getPassword(view.getNumOfPeople(),new Date(),view.getReserveName());
                        view.setPassword(password);
                        view.setLandingName();
                        view.switchPanel(Constants.LANDING);
                    } catch (NotEnoughTableException e1) {
                        view.showErrorMessage("No hay espacio para hacer la reserva ahora");
                        view.switchPanel(Constants.INIT);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        view.dispose();
                    }
                    break;
                case Constants.LATER:
                    view.switchPanel(Constants.DATE_PICKER);
                    break;
            }
            view.clear();
        }
        else if(state == 1){
            view.showErrorMessage("Error, el numero de personas no es un numero");
        }
        else{
            view.showErrorMessage("Error, el nombre de la reserva, o el numero de comensales esta vacio");
        }

    }
}
