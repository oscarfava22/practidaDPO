package Entry.Controller;

import Entry.View.Entry;
import Entry.View.Panels.ReservePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReserveController implements ActionListener{
    private Entry view;

    public ReserveController(Entry view){
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ReservePanel.NOW:
                //TODO llamar al metodo:
                //TODO validateData(view.numPersonas,new Date())
                break;
            case ReservePanel.LATER:
                //TODO IR al siguiente panel
                view.switchPanel(Entry.DATE_PICKER);
                break;
        }
    }
}
