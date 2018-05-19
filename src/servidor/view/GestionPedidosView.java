package servidor.view;

import servidor.controller.PlatosPendientesController;
import servidor.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Panel que gestiona la vista de los pedidos.
 * Tiene asociado el panel de Pedidos y dos paneles PlatosView necesarios para representar las listas de los platos
 * pendientes y procesados.
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
     * Constructor que inicializa todos los componentes necesarios que utiliza el panel.
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
     * Permite inicializar la vista de los pedidos en cualquier momento.
     * @param pedidos lista de pedidos que se reciben para inicializar la vista de los pedidos.
     */
    public void initView(LinkedList<Pedido> pedidos) {
        pedidosView.initView(pedidos);
    }

    /**
     * Permite establecer el estado del boton de servir.
     * @param state determina el estado del boton de servir.
     */
    public void setJbServeState(boolean state) {
        jbServe.setEnabled(state);
    }

    /**
     * Permite obtener el panel referente a la vista de los pedidos.
     * @return el panel de la vista de los pedidos.
     */
    public PedidosView getPedidosView() {
        return pedidosView;
    }

    /**
     * Permite inicializar la vista de los platos pendientes.
     * @param platos lista de platos que se recibe para inicializar la vista de los platos pendientes.
     */
    public void initPlatosPendientesView(LinkedList<Plato> platos) {
        platosPendientes.initPlatosView(platos);
        updateUI();
    }

    /**
     * Permite obtener el panel referente a los platos pendientes.
     * @return el panel de los platos pendientes.
     */
    public PlatosView getPlatosPendientes() {
        return platosPendientes;
    }

    /**
     * Permite registrar los componentes al controlador que se recibe como parametro.
     * @param controller controlador que se recibe para registrar a los componnetes.
     */
    public void registerPlatosPendientesController(PlatosPendientesController controller) {
        platosPendientes.registerControllers(controller);
        jbServe.removeMouseListener(controller);
        jbServe.addMouseListener(controller);
    }

    /**
     * Permite inicializar la vista de los platos procesados.
     * @param platos lista de platos procesados.
     */
    public void initPlatosProcesadosView(LinkedList<Plato> platos) {
        platosProcesados.initPlatosView(platos);
        updateUI();
    }

    /**
     * Permite obtener el JTable de los pedidos.
     * @return el JTable de los pedidos.
     */
    public JTable getJtPedidos() {
        return pedidosView.getJtPedidos();
    }

    /**
     * Permite obtener el indice de la fila seleccionada del JTable de pedidos.
     * @return el incide de la fila seleccionada.
     */
    public int getSelectedRow() {
        return pedidosView.getSelectedRow();
    }
}
