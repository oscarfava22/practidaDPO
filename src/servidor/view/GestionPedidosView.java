package servidor.view;

import servidor.controller.PlatosPendientesController;
import servidor.controller.PlatosProcesadosListener;
import servidor.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 *
 */
public class GestionPedidosView extends JPanel {

    private PedidosView pedidosView;

    private PlatosView platosPendientes;
    private PlatosView platosProcesados;

    private JPanel jpPedidos;
    private JSplitPane splitPane;

    private JButton jbServe;
    private JPanel jpPlatosPendientes;

    /**
     *
     */
    public GestionPedidosView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.CYAN, 10));

        pedidosView = new PedidosView();
        pedidosView.setPreferredSize(new Dimension(800,200));

        platosPendientes = new PlatosView();
        platosProcesados = new PlatosView();

        platosPendientes.setBorder(BorderFactory.createTitledBorder("Platos Pendientes"));

        jbServe = new JButton("Servir");
        jbServe.setFocusPainted(false);
        jbServe.setEnabled(false);

        jpPlatosPendientes = new JPanel(new BorderLayout());

        jpPlatosPendientes.add(platosPendientes, BorderLayout.CENTER);
        jpPlatosPendientes.add(jbServe, BorderLayout.SOUTH);

        platosProcesados.setBorder(BorderFactory.createTitledBorder("Platos Procesados"));

        jpPedidos = new JPanel(new GridLayout(1,2));

        jpPedidos.add(jpPlatosPendientes);
        jpPedidos.add(platosProcesados);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setPreferredSize(new Dimension(800,200));

        splitPane.add(pedidosView);
        splitPane.add(jpPedidos);
        splitPane.setDividerLocation(200);

        add(splitPane, BorderLayout.CENTER);
    }

    /**
     *
     * @param pedidos
     */
    public void initView(LinkedList<Pedido> pedidos) {
        pedidosView.initView(pedidos);
    }

    /**
     *
     * @param state
     */
    public void setJbServeState(boolean state) {
        jbServe.setEnabled(state);
    }

    /**
     *
     * @return
     */
    public PedidosView getPedidosView() {
        return pedidosView;
    }

    /**
     *
     * @param platos
     */
    public void initPlatosPendientesView(LinkedList<Plato> platos) {
        platosPendientes.initPlatosView(platos);
        updateUI();
    }

    /**
     *
     * @return
     */
    public PlatosView getPlatosPendientes() {
        return platosPendientes;
    }

    /**
     *
     * @return
     */
    public PlatosView getPlatosProcesados() {
        return platosProcesados;
    }

    /**
     *
     * @param controller
     */
    public void registerPlatosPendientesController(PlatosPendientesController controller) {
        platosPendientes.registerControllers(controller);
        jbServe.removeMouseListener(controller);
        jbServe.addMouseListener(controller);
    }

    /**
     *
     * @param controller
     */
    public void registerPlatosProcesadosController(PlatosProcesadosListener controller) {
        platosProcesados.registerControllers(controller);
    }

    /**
     *
     * @param platos
     */
    public void initPlatosProcesadosView(LinkedList<Plato> platos) {
        platosProcesados.initPlatosView(platos);
        updateUI();
    }

    /**
     *
     * @return
     */
    public JTable getJtPedidos() {
        return pedidosView.getJtPedidos();
    }

    /**
     *
     * @return
     */
    public int getSelectedRow() {
        return pedidosView.getSelectedRow();
    }
}
