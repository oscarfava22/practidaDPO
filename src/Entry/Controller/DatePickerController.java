package Entry.Controller;

import Entry.Constants.Constants;
import Entry.Model.Exception.NotEnoughTableException;
import Entry.Model.Network.Client;
import Entry.View.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

/**
 * Controller for when the next button in the date picker is clicked
 */
public class DatePickerController implements ActionListener {

    /**
     * Relation to the whole view
     */
    private Entry view;

    /**
     * Relation to the client
     */
    private Client client;

    /**
     * Creates a new controller from view and client
     * @param view the view
     * @param client the client
     */
    public DatePickerController(Entry view,Client client) {
        this.view = view;
        this.client = client;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Creates the reservation and handles the errors
        try {
            String password = client.getPassword(view.getNumOfPeople(),
                    Constants.DATE_FORMAT.parse(view.getSelectedDate()),view.getReserveName());
            view.setLandingName();
            view.switchPanel(Constants.LANDING);
        } catch (NotEnoughTableException e1) {
            view.showErrorMessage(e1.getMessage());
            view.switchPanel(Constants.INIT);
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
            view.dispose();
        }
    }
}
