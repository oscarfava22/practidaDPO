package Entry.Controller;

import Entry.Constants.Constants;
import Entry.Model.Exception.NotEnoughTableException;
import Entry.Model.Network.Client;
import Entry.View.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

/**
 * Controller for the reserve panel
 */
public class ReserveController implements ActionListener{

    /**
     * The view (whole) where the panel is
     */
    private Entry view;

    /**
     * The client of the server
     */
    private Client client;

    /**
     * Creates a new ReserveController
     * @param view the view of the controller
     * @param client the relation to the client
     */
    public ReserveController(Entry view,Client client){
        this.view = view;
        this.client = client;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtain the state of the textfields
        int state = view.reserveNotEmpty();
        if(state ==0){
            //Select which button was pressed
            switch (e.getActionCommand()){
                case Constants.NOW:
                    //Creates a reservation now and treat the exceptions
                    try {
                        String password = client.getPassword(view.getNumOfPeople(),new Date(),view.getReserveName());
                        view.setPassword(password);
                        view.setLandingName();
                        view.switchPanel(Constants.LANDING);
                        view.clear();
                    } catch (NotEnoughTableException e1) {
                        view.showErrorMessage(e1.getMessage());
                        view.switchPanel(Constants.INIT);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        view.dispose();
                    }
                    break;
                case Constants.LATER:
                    //switch to date picker
                    view.switchPanel(Constants.DATE_PICKER);
                    break;
            }
        }
        else if(state == 1){
            view.showErrorMessage("Error, el numero de personas no es un numero");
        }
        else{
            view.showErrorMessage("Error, el nombre de la reserva, o el numero de comensales esta vacio");
        }

    }
}
