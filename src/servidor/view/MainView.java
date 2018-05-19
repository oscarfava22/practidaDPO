package servidor.view;

import servidor.controller.*;
import servidor.model.*;
import servidor.model.Database.BBDDManager;
import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Ventana principal del programa que tiene asociados otros paneles que se iran mostrando en funcion de la opcion
 * seleccionada en el Selector View.
 */
public class MainView extends JFrame implements WindowListener {

    private SelectorView selectorView;
    private JPanel jpGestionView;
    private GestionMesasView gestionMesasView;
    private GestionCartaView gestionCartaView;
    private GestionPedidosView gestionPedidosView;
    private GestionTop5View gestionTop5View;
    private StatusBarView statusBarView;
    private GestionTop5ViewListener gestorTop5View;

    /**
     * Constructor que incializa todas las clases vinculadas con la vista principal.
     */
    public MainView() {

        selectorView = new SelectorView();
        jpGestionView = new JPanel(new BorderLayout());
        gestionMesasView = new GestionMesasView();
        gestionCartaView = new GestionCartaView();
        gestionPedidosView = new GestionPedidosView();
        gestionTop5View = new GestionTop5View();
        statusBarView = new StatusBarView();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Método que permite inicializar la vista principal en cualquier momento.
     * @param platos lista de platos que se recibe para mostar.
     * @param mesas lista de mesas que se recibe para mostart.
     * @param pedidos lista de pedidos que se recibe para mostrar.
     */
    public void initMainView(LinkedList<Plato> platos, LinkedList<Mesa> mesas, LinkedList<Pedido> pedidos) {

        selectorView.initView();
        gestionMesasView.initView(new ArrayList<InfoResultSetReserva>());
        gestionCartaView.initView(platos);
        gestionPedidosView.initView(pedidos);

        jpGestionView.add(new JLabel(Constants.mainLogo) , BorderLayout.CENTER);

        getContentPane().add(selectorView, BorderLayout.NORTH);
        getContentPane().add(jpGestionView, BorderLayout.CENTER);
        getContentPane().add(statusBarView, BorderLayout.SOUTH);

        setTitle(Constants.titleBarTitle);
        setIconImage(Constants.titleBarLogo.getImage());
        setSize(Constants.defaultSize);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Permite registrar los componente aqui presentes al controlador correspondiente de los recibidos.
     * @param selectorViewListener controlador del selector de la vista principal.
     * @param gestionCartaViewListener controlador de la gestion de la vista de la carta y las opciones.
     * @param gestionTop5ViewListener controlador de la gestion del Top5.
     * @param mesasOptionsViewListener controlador de la gestion de las opciones de las mesas.
     * @param mesasViewListener controlador de la vista de las mesas.
     */
    public void registerControllers(SelectorViewListener selectorViewListener,
                                    MouseInputListener gestionCartaViewListener,
                                    GestionTop5ViewListener gestionTop5ViewListener,
                                    MesasOptionsViewListener mesasOptionsViewListener,
                                    MesasViewListener mesasViewListener) {

        selectorView.registerControllers(selectorViewListener);
        gestionMesasView.registerControllers(mesasOptionsViewListener, mesasViewListener);
        gestionCartaView.registerControllers(gestionCartaViewListener);
        gestorTop5View = gestionTop5ViewListener;
    }

    /**
     * Selecciona el boton apretado en el Selector View segun el nombre del boton.
     * @param button
     */
    public void setSVSelectedButton(String button) {
        selectorView.setSelectedButton(button);
    }

    /**
     * Permite activar el estado de focalizado cuando se detecta un Mouse Entered/Exited encima de un boton medianre
     * el nombre del boton.
     * @param button nombre del boton.
     * @param state estado de focus.
     */
    public void setSVFocusedButton(String button, boolean state) {
        selectorView.setFocusedButton(button, state);
    }

    /**
     * Permite activar la vista relacionada con el boton apretado en el Selector View.
     * @param option opcion que se recibe que indica que tipo de vista activar.
     */
    public void setGestionView(String option) {

        switch (option) {
            case "Mesas":
                jpGestionView.removeAll();
                jpGestionView.add(gestionMesasView, BorderLayout.CENTER);
                jpGestionView.updateUI();

                break;
            case "Carta":
                jpGestionView.removeAll();
                jpGestionView.add(gestionCartaView, BorderLayout.CENTER);
                jpGestionView.updateUI();

                break;
            case "Pedidos":
                jpGestionView.removeAll();
                jpGestionView.add(gestionPedidosView, BorderLayout.CENTER);
                jpGestionView.updateUI();
                break;
            case "Top 5":
                gestorTop5View.calculaTop5();
                jpGestionView.removeAll();
                jpGestionView.add(gestionTop5View, BorderLayout.CENTER);
                jpGestionView.updateUI();
                break;
        }
    }

    /**
     * Permite establecer el estado de conexion de los servidores Entry conectados.
     * @param state el estado de los servidores dedicados a los clientes Entry.
     */
    public void setEntryServerStatus(Integer state) {
        statusBarView.setEntryServerStatus(state);
    }

    /**
     * Permite refrescar la vista de los platos
     * @param mesasViewListener controlador asociados a la vista de las mesas.
     */
    public void refreshMesas(MesasViewListener mesasViewListener){
        gestionMesasView.refreshMesas(mesasViewListener);
    }

    /**
     * Permite establecer el numero de servidores dedicados de clientes de Reserva conectados.
     * @param count
     */
    public void setConnectedDevices(Integer count) {
        statusBarView.setConnectedDevices(count);
        statusBarView.updateUI();
    }

    /**
     * Permite obtener el numero de la fila seleccionada en el JTable de los pedidos.
     * @return el indice de la fila seleccionada.
     */
    public int getSelectedRow() {
        return gestionPedidosView.getSelectedRow();
    }

    /**
     * Permite obtener el panel referente a la gestion de la carta.
     * @return el panel de la gestion de la carta.
     */
    public GestionCartaView getGestionCartaView() {
        return gestionCartaView;
    }

    /**
     * Permite obtener el panel referente a la gestion de las mesas.
     * @return el panel de la gestion de las mesas.
     */
    public GestionMesasView getGestionMesasView(){
        return gestionMesasView;
    }

    /**
     * Permite obtener el panel referente a la gestion del Top 5.
     * @return el panel de la gestion del Top 5.
     */
    public GestionTop5View getGestionTop5View(){
        return gestionTop5View;
    }

    /**
     * Permite refrescar la vista de las reservas.
     * @param reservas lista de reservas que se recibe para actualizar.
     */
    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        gestionMesasView.refreshReservas(reservas);
    }

    /**
     * Permite obtener el panel referente a la gestion de los pedidos.
     * @return el panel de la gestion de los pedidos.
     */
    public GestionPedidosView getGestionPedidosView() { return gestionPedidosView; }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        BBDDManager.recreateBBDD();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
