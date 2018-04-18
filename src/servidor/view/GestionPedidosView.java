package servidor.view;

import servidor.controller.PedidosListListener;
import servidor.model.MainViewModel;
import servidor.model.PlatosManager;
import servidor.model.Reserva;
import servidor.model.ReservasManager;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.LinkedList;

public class GestionPedidosView extends JPanel {

    private PedidosView pedidosView;

    private PlatosView platosPendientes;
    private PlatosView platosProcesados;

    private JPanel jpPedidos;

    private JSplitPane splitPane;


    public GestionPedidosView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 10));

        pedidosView = new PedidosView();
        pedidosView.setPreferredSize(new Dimension(800,200));

        platosPendientes = new PlatosView();
        platosProcesados = new PlatosView();

        PlatosManager pl = new PlatosManager();

        platosPendientes.initPlatos(pl.getPlatos());
        platosPendientes.setBorder(BorderFactory.createTitledBorder("Platos Pendientes"));
        platosProcesados.initPlatos(pl.getPlatos());
        platosProcesados.setBorder(BorderFactory.createTitledBorder("Platos Procesados"));

        jpPedidos = new JPanel(new GridLayout(1,2));

        jpPedidos.add(platosPendientes);
        jpPedidos.add(platosProcesados);



        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setPreferredSize(new Dimension(800,200));

        splitPane.add(pedidosView);
        splitPane.add(jpPedidos);
        splitPane.setDividerLocation(200);
        //add(pedidosView, BorderLayout.NORTH);
        //add(jpPedidos, BorderLayout.CENTER);

        add(splitPane, BorderLayout.CENTER);

    }

    public void initView(MainViewModel mainViewModel, LinkedList<Reserva> reservas) {

        pedidosView.initView(reservas);
    }

    public void registerControllers(MouseInputListener gestionPedidosViewListener, PedidosListListener pedidosListListener) {

        pedidosView.registerControllers(pedidosListListener);

    }

    public JTable getJtPedidos() {
        return pedidosView.getJtPedidos();
    }

    public int getSelectedRow() {
        return pedidosView.getSelectedRow();
    }
}
