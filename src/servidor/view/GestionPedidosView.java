package servidor.view;

import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionPedidosView extends JPanel {

    private PedidosView pedidosView;

    public GestionPedidosView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 10));

        pedidosView = new PedidosView();
        add(pedidosView, BorderLayout.CENTER);
    }

    public void initView(MainViewModel mainViewModel) {

    }

    public void registerControllers(MouseInputListener gestionPedidosViewListener) {

    }
}
