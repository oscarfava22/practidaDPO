package reserva.controller;

import reserva.reserva.model.ReservaManager;
import reserva.view.AutenticacioView;
import reserva.view.CartaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservaListener implements ActionListener{

    private ReservaManager model;
    private CartaView view;

    public ReservaListener (ReservaManager model, CartaView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case CartaView.PAGAR:
                System.out.println("PAGAR");
                break;

            case CartaView.DEMANAR:
                System.out.println("DEMANAR");
                break;

        }

        for (int i = 0; i < model.getNumPlats(); i++) {

            if (e.getActionCommand().equals("AFEGIR_UNITAT_" + i)) {

                System.out.println("AFEGIR UNITAT");
            } else if (e.getActionCommand().equals("TREURE_UNITAT_" + i)) {
                System.out.println("TREURE UNITAT");
            } else if (e.getActionCommand().equals("AFEGIR_" + i)) {
                System.out.println("AFEGIR");
            }
        }

    }

    public void setViewVisible() {
        view.setVisible(true);
    }
}
