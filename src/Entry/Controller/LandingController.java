package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingController implements ActionListener{
    private Entry view;

    public LandingController(Entry view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.switchPanel(Constants.INIT);
    }
}
