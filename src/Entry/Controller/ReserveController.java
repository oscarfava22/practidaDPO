package Entry.Controller;

import Entry.Constants.Constants;
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
        int state = view.reserveNotEmpty();
        if(state ==0){
            switch (e.getActionCommand()){
                case Constants.NOW:
                    //TODO llamar al metodo:
                    //TODO validateData(view.numPersonas,new Date())
                    view.setLandingName();
                    view.switchPanel(Constants.LANDING);
                    break;
                case Constants.LATER:
                    //TODO IR al siguiente panel
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
