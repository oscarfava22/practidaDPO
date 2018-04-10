package Entry.Controller;

import Entry.Constants.Constants;
import Entry.Model.Exception.NotEnoughTableException;
import Entry.Model.Network.Client;
import Entry.View.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class DatePickerController implements ActionListener{
    private Entry view;
    private Client client;

    public DatePickerController(Entry view,Client client) {
        this.view = view;
        this.client = client;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String password = client.getPassword(view.getNumOfPeople(),
                    Constants.DATE_FORMAT.parse(view.getSelectedDate()),view.getReserveName());
            view.setLandingName();
            view.switchPanel(Constants.LANDING);
        } catch (NotEnoughTableException e1) {
            view.showErrorMessage("No hay espacio para hacer la reserva ahora");
            view.switchPanel(Constants.INIT);
        } catch (IOException | ParseException e1) {
            e1.printStackTrace();
            view.dispose();
        }
    }
}
