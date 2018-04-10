package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller to finish the creation of the reservation
 */
public class LandingController implements ActionListener{
    /**
     * the whole view where the landing panel is
     */
    private Entry view;

    /**
     * Creates a new landing controller from view
     * @param view the view
     */
    public LandingController(Entry view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.switchPanel(Constants.INIT);
    }
}
