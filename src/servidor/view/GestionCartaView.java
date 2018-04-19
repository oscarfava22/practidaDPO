package servidor.view;

import servidor.model.MainViewModel;
import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.LinkedList;

public class GestionCartaView extends JPanel {

    private PlatosView platosView;
    private PlatosOptionsView platosOptionsView;

    public GestionCartaView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));
        platosView = new PlatosView();
        platosOptionsView = new PlatosOptionsView();
    }

    public void initView(MainViewModel mainViewModel, LinkedList<Plato> platos) {
        platosView.initPlatosView(platos);
        add(platosOptionsView, BorderLayout.LINE_START);
        add(platosView, BorderLayout.CENTER);
    }

    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        platosView.registerControllers(gestionCartaViewListener);
        platosOptionsView.registerControllers(gestionCartaViewListener);
    }

    public PlatosOptionsView getPlatosOptionsView() {
        return platosOptionsView;
    }

    public PlatosView getPlatosView() {
        return platosView;
    }
}
