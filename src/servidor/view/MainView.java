package servidor.view;

import servidor.controller.*;
import servidor.model.*;
import servidor.model.Database.BBDDManager;
import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainView extends JFrame implements WindowListener {

    private MenuBarView menuBarView;
    private SelectorView selectorView;

    private JPanel jpGestionView;

    private GestionMesasView gestionMesasView;
    private GestionCartaView gestionCartaView;
    private GestionPedidosView gestionPedidosView;
    private GestionTop5View gestionTop5View;

    private SettingsDialogView settingsDialogView;

    private StatusBarView statusBarView;

    public MainView() {

        menuBarView = new MenuBarView();
        selectorView = new SelectorView();

        jpGestionView = new JPanel(new BorderLayout());

        gestionMesasView = new GestionMesasView();
        gestionCartaView = new GestionCartaView();
        gestionPedidosView = new GestionPedidosView();
        gestionTop5View = new GestionTop5View();

        settingsDialogView = new SettingsDialogView();

        statusBarView = new StatusBarView();

        setJMenuBar(menuBarView);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initMainView(MainViewModel mainViewModel, LoginModel loginModel, LinkedList<Plato> platos, LinkedList<Mesa> mesas,
                         LinkedList<Pedido> pedidos) {

        selectorView.initView(mainViewModel);
        gestionMesasView.initView(new ArrayList<InfoResultSetReserva>());
        gestionCartaView.initView(mainViewModel, platos);
        gestionPedidosView.initView(mainViewModel, pedidos);
        statusBarView.initView(loginModel);
        settingsDialogView.initView(mainViewModel);

        jpGestionView.add(new JLabel(mainViewModel.getMainLogo()) , BorderLayout.CENTER);

        getContentPane().add(selectorView, BorderLayout.NORTH);
        getContentPane().add(jpGestionView, BorderLayout.CENTER);
        getContentPane().add(statusBarView, BorderLayout.SOUTH);

        setTitle(mainViewModel.getTitleBarTitle());
        setIconImage(mainViewModel.getTitleBarLogo().getImage());
        setSize(mainViewModel.getDefaultSize());
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void registerControllers(SelectorViewListener selectorViewListener, ActionListener menuBarViewListener,
                                    MouseInputListener gestionMesasViewListener,
                                    MouseInputListener gestionCartaViewListener,
                                    MouseInputListener gestionPedidosViewListener,
                                    MouseInputListener gestionTop5ViewListener,
                                    MouseInputListener settingsDialogViewListener,
                                    MesasOptionsViewListener mesasOptionsViewListener,
                                    MesasViewListener mesasViewListener,
                                    PedidosListListener pedidosListListener) {

        selectorView.registerControllers(selectorViewListener);
        menuBarView.registerControllers(menuBarViewListener);
        gestionMesasView.registerControllers(mesasOptionsViewListener, mesasViewListener);
        gestionCartaView.registerControllers(gestionCartaViewListener);
        gestionPedidosView.registerControllers(gestionPedidosViewListener, pedidosListListener);
        gestionTop5View.registerControllers(gestionTop5ViewListener);
        settingsDialogView.registerControllers(settingsDialogViewListener);
    }

    public void setSVSelectedButton(String button) {
        selectorView.setSelectedButton(button);
    }

    public void setSVFocusedButton(String button, boolean state) {
        selectorView.setFocusedButton(button, state);
    }

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
                jpGestionView.removeAll();
                jpGestionView.add(gestionTop5View, BorderLayout.CENTER);
                jpGestionView.updateUI();
                break;
        }
    }


    public void showSettingsDialog() {
        settingsDialogView.setVisible(true);
    }

    public void setConnectedState(boolean state) {
        statusBarView.setConnectedState(state);
    }

    public void setEntryServerStatus(boolean state) {
        statusBarView.setEntryServerStatus(state);
    }

    public void refreshMesas(MesasViewListener mesasViewListener){
        gestionMesasView.refreshMesas(mesasViewListener);
    }

    public void setConnectedDevices(Integer count) {
        statusBarView.setConnectedDevices(count);
        statusBarView.updateUI();
    }

    public JTable getJtPedidos() {
        return gestionPedidosView.getJtPedidos();
    }

    public int getSelectedRow() {
        return gestionPedidosView.getSelectedRow();
    }

    public GestionCartaView getGestionCartaView() {
        return gestionCartaView;
    }

    public GestionMesasView getGestionMesasView(){
        return gestionMesasView;
    }

    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        gestionMesasView.refreshReservas(reservas);
    }
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
