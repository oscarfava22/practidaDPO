package reserva;

import reserva.controller.AutenticacioListener;
import reserva.controller.ReservaListener;
import reserva.reserva.model.ReservaManager;
import reserva.view.AutenticacioView;
import reserva.view.CartaView;

public class Main {
    public static void main(String[] args) {
        CartaView secondView = new CartaView(5, 10, 4, 3, 5, 5);
        AutenticacioView firstView = new AutenticacioView();
        ReservaManager model = new ReservaManager();
        ReservaListener controller = new ReservaListener(model, secondView);
        AutenticacioListener controller2 = new AutenticacioListener(firstView, controller);
        secondView.registerControllers(controller);
        firstView.registerControllers(controller2);
    }
}
