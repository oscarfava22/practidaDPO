package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InitController extends MouseAdapter{

    private Entry view;

    public InitController(Entry view){
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        view.switchPanel(Constants.RESERVE);
    }

}
