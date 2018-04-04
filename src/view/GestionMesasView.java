package view;

import model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionMesasView extends JPanel {

    private JLabel jlTitle;

    public GestionMesasView() {

        setLayout(new BorderLayout());

        jlTitle = new JLabel();
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);




        add(jlTitle, BorderLayout.NORTH);
    }

    public void initView(MainViewModel mainViewModel) {
        jlTitle.setText(mainViewModel.getGestionMesas());
    }

    public void registerControllers(MouseInputListener gestionMesasViewListener) {

        jlTitle.addMouseListener(gestionMesasViewListener);

    }
}
