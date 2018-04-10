package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller for when the init jlabel is clicked
 */
public class InitController extends MouseAdapter{

    /**
     * The whole view where the label is
     */
    private Entry view;

    /**
     * Creates a new controller from view
     * @param view the view
     */
    public InitController(Entry view){
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        view.switchPanel(Constants.RESERVE);
    }

}
