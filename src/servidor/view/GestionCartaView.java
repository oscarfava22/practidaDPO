package servidor.view;

import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionCartaView extends JPanel {

    private JLabel jlTitle;

    public GestionCartaView() {

        setLayout(new BorderLayout());

        jlTitle = new JLabel();
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jlTitle, BorderLayout.NORTH);
    }

    public void initView(MainViewModel mainViewModel) {
        jlTitle.setText(mainViewModel.getGestionCarta());
    }

    public void registerControllers(MouseInputListener gestionCartaViewListener) {

        jlTitle.addMouseListener(gestionCartaViewListener);
    }
}
