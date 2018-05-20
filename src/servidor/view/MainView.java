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
 * Panel que representa la ventana principal del programa.
 * Tiene asociados todos los paneles que seran utilizados durante la ejecuccion de las diferentes opciones.
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
     * Constructor que inicializa todas las variables de la vista.
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
     * Permite inicializar la vista principal en cualquier momento.
     * @param platos lista de platos.
     * @param mesas lista de mesas.
     * @param pedidos lista de pedidos.
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
     * Permite registrar todos los controladores con los que trabajan los paneles internos de la vista.
     * @param selectorViewListener controlador del Selector View.
     * @param gestionCartaViewListener controlador de la gestion de la carta.
     * @param gestionTop5ViewListener controlador de la gestion del Top5.
     * @param mesasOptionsViewListener controlador de la gestion de las opciones de las mesas.
     * @param mesasViewListener controlador de de las mesas.
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
     * Permite establecer cual de los botones del Selector View ha sido apretado.
     * @param button el nombre del boton apretado.
     */
    public void setSVSelectedButton(String button) {
        selectorView.setSelectedButton(button);
    }

    /**
     * Permite establecer el estado de "Focus" del boton en el cual se ha detectado un mouse event.
     * @param button el nombre del boton.
     * @param state el estado del focus.
     */
    public void setSVFocusedButton(String button, boolean state) {
        selectorView.setFocusedButton(button, state);
    }

    /**
     * Permite activar la vista referente al boton apretado en el Selector View.
     * @param option opcion que determina que vista se activara.
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
     * Permite establecer el numero de clientes Entry conectados.
     * @param state el numero de clientes conectados.
     */
    public void setEntryServerStatus(Integer state) {
        statusBarView.setEntryServerStatus(state);
    }

    /**
     * Permite actualizar la vista de las mesas.
     * @param mesasViewListener controlador que registra en la vista de los platos.
     */
    public void refreshMesas(MesasViewListener mesasViewListener){
        gestionMesasView.refreshMesas(mesasViewListener);
    }

    /**
     * Permite establecer el numero de clientes de reserva conectados.
     * @param count el numero de clientes conectados.
     */
    public void setConnectedDevices(Integer count) {
        statusBarView.setConnectedDevices(count);
        statusBarView.updateUI();
    }

    /**
     * Permite obtener el indice de la fila seleccionada en el JTable de los pedidos.
     * @return el indice de la fila seleccionada.
     */
    public int getSelectedRow() {
        return gestionPedidosView.getSelectedRow();
    }

    /**
     * Permite obtener la vista de la gestion de la carta.
     * @return la vista de la gestion de la carta.
     */
    public GestionCartaView getGestionCartaView() {
        return gestionCartaView;
    }

    /**
     * Permite obtener la vista de la gestion de las mesas.
     * @return la vista de la gestion de las mesas.
     */
    public GestionMesasView getGestionMesasView(){
        return gestionMesasView;
    }

    /**
     * Permite obtener la vista de las gestion del Top5
     * @return la vista del Top5.
     */
    public GestionTop5View getGestionTop5View(){
        return gestionTop5View;
    }

    /**
     * Permite obtener la vista de la gestion de las reservas.
     * @param reservas la vista de la gestion de las reservas.
     */
    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        gestionMesasView.refreshReservas(reservas);
    }

    /**
     * Permite obtener la vista de la gestion de los pedidos.
     * @return vista de la gestion de los pedidos.
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
