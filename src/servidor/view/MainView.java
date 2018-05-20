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
 *
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
     *
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
     *
     * @param platos
     * @param mesas
     * @param pedidos
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
     *
     * @param selectorViewListener
     * @param gestionCartaViewListener
     * @param gestionTop5ViewListener
     * @param mesasOptionsViewListener
     * @param mesasViewListener
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
     *
     * @param button
     */
    public void setSVSelectedButton(String button) {
        selectorView.setSelectedButton(button);
    }

    /**
     *
     * @param button
     * @param state
     */
    public void setSVFocusedButton(String button, boolean state) {
        selectorView.setFocusedButton(button, state);
    }

    /**
     *
     * @param option
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
     *
     * @param state
     */
    public void setEntryServerStatus(Integer state) {
        statusBarView.setEntryServerStatus(state);
    }

    /**
     *
     * @param mesasViewListener
     */
    public void refreshMesas(MesasViewListener mesasViewListener){
        gestionMesasView.refreshMesas(mesasViewListener);
    }

    /**
     *
     * @param count
     */
    public void setConnectedDevices(Integer count) {
        statusBarView.setConnectedDevices(count);
        statusBarView.updateUI();
    }

    /**
     *
     * @return
     */
    public int getSelectedRow() {
        return gestionPedidosView.getSelectedRow();
    }

    /**
     *
     * @return
     */
    public GestionCartaView getGestionCartaView() {
        return gestionCartaView;
    }

    /**
     *
     * @return
     */
    public GestionMesasView getGestionMesasView(){
        return gestionMesasView;
    }

    /**
     *
     * @return
     */
    public GestionTop5View getGestionTop5View(){
        return gestionTop5View;
    }

    /**
     *
     * @param reservas
     */
    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        gestionMesasView.refreshReservas(reservas);
    }

    /**
     *
     * @return
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
