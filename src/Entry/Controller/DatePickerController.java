package Entry.Controller;

import Entry.View.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DatePickerController implements ActionListener{
    private Entry view;

    public DatePickerController(Entry view) {
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO readDate
        view.switchPanel(Entry.LANDING);
    }
}
