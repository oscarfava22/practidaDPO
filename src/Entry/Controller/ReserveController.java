package Entry.Controller;

import Entry.View.Panels.ReservePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReserveController implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ReservePanel.NOW:
                //TODO llamar al metodo:
                //TODO validateData(view.numPersonas,new Date())
                break;
            case ReservePanel.LATER:
                //TODO IR al siguiente panel
                break;
        }
    }
}
