package reserva.controller;

import reserva.view.AutenticacioView;
import reserva.view.CartaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutenticacioListener implements ActionListener{

    private AutenticacioView view;
    private ReservaListener controller;

    public AutenticacioListener (AutenticacioView view, ReservaListener controller) {

        this.view = view;
        this.controller = controller;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case AutenticacioView.ACCESS:

                controller.setViewVisible();
                view.setVisible(false);
                break;
        }
    }
}
