package view;

import model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionPedidosView extends JPanel {

    private JLabel jlTitle;

    public GestionPedidosView() {

        setLayout(new BorderLayout());

        jlTitle = new JLabel("Pedidos");
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jlTitle, BorderLayout.NORTH);

    }

    public void initView(MainViewModel mainViewModel) {
        jlTitle.setText(mainViewModel.getGestionPedidos());
    }

    public void registerControllers(MouseInputListener gestionPedidosViewListener) {

        jlTitle.addMouseListener(gestionPedidosViewListener);
    }
}
