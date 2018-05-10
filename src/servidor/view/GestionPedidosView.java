package servidor.view;

import servidor.controller.PedidosListListener;
import servidor.controller.PlatosPendientesController;
import servidor.controller.PlatosProcesadosListener;
import servidor.model.*;

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

    private JButton jbServe;
    private JPanel jpPlatosPendientes;


    public GestionPedidosView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 10));

        pedidosView = new PedidosView();
        pedidosView.setPreferredSize(new Dimension(800,200));

        platosPendientes = new PlatosView();
        platosProcesados = new PlatosView();

        //platosPendientes.initPlatosView(pl.getPlatos());
        platosPendientes.setBorder(BorderFactory.createTitledBorder("Platos Pendientes"));

        jbServe = new JButton("Servir");
        jbServe.setFocusPainted(false);
        jbServe.setEnabled(false);
        //jbServe.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //jbServe.setBorder();
        jpPlatosPendientes = new JPanel(new BorderLayout());

        jpPlatosPendientes.add(platosPendientes, BorderLayout.CENTER);
        jpPlatosPendientes.add(jbServe, BorderLayout.SOUTH);

        //platosProcesados.initPlatosView(pl.getPlatos());
        platosProcesados.setBorder(BorderFactory.createTitledBorder("Platos Procesados"));

        jpPedidos = new JPanel(new GridLayout(1,2));

        jpPedidos.add(jpPlatosPendientes);
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

    public void initView(MainViewModel mainViewModel, LinkedList<Pedido> pedidos) {
        pedidosView.initView(pedidos);
    }

    public void setJbServeState(boolean state) {
        jbServe.setEnabled(state);
    }

    public PedidosView getPedidosView() {
        return pedidosView;
    }

    public void initPlatosPendientesView(LinkedList<Plato> platos) {
        platosPendientes.initPlatosView(platos);
        //jpPlatosPendientes.add(platosPendientes, BorderLayout.CENTER);
        //jpPedidos = new JPanel(new GridLayout(1,2));

        //jpPedidos.add(jpPlatosPendientes);
        //jpPedidos.add(platosProcesados);
        updateUI();
    }


    public PlatosView getPlatosPendientes() {
        return platosPendientes;
    }

    public PlatosView getPlatosProcesados() {
        return platosProcesados;
    }

    public void registerPlatosPendientesController(PlatosPendientesController controller) {

        platosPendientes.registerControllers(controller);

        jbServe.removeMouseListener(controller);
        jbServe.addMouseListener(controller);

    }

    public void registerPlatosProcesadosController(PlatosProcesadosListener controller) {
        platosProcesados.registerControllers(controller);
    }

    public void initPlatosProcesadosView(LinkedList<Plato> platos) {
        platosProcesados.initPlatosView(platos);
        updateUI();
    }

    public void registerControllers(MouseInputListener gestionPedidosViewListener, PedidosListListener pedidosListListener) {

        registerPedidosListController(pedidosListListener);
    }


    public void registerPedidosListController(PedidosListListener pedidosListListener) {
        //pedidosView.registerControllers(pedidosListListener);
    }

    public JTable getJtPedidos() {
        return pedidosView.getJtPedidos();
    }

    public int getSelectedRow() {
        return pedidosView.getSelectedRow();
    }
}
