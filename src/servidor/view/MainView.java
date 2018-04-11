package servidor.view;

import servidor.controller.PlatosOptionsViewListener;
import servidor.controller.PlatosViewListener;
import servidor.model.LoginModel;
import servidor.model.MainViewModel;
import servidor.model.Plato;
import servidor.model.PlatosManager;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainView extends JFrame {

    private MenuBarView menuBarView;
    private SelectorView selectorView;

    private JPanel jpGestionView;

    private GestionMesasView gestionMesasView;
    private GestionCartaView gestionCartaView;
    private GestionPedidosView gestionPedidosView;
    private GestionTop5View gestionTop5View;

    private LoginDialogView loginDialogView;
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

        loginDialogView = new LoginDialogView();
        settingsDialogView = new SettingsDialogView();

        statusBarView = new StatusBarView();

        setJMenuBar(menuBarView);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initView(MainViewModel mainViewModel, LoginModel loginModel, LinkedList<Plato> platos) {

        selectorView.initView(mainViewModel);
        gestionMesasView.initView(mainViewModel);
        gestionCartaView.initView(mainViewModel, platos);
        settingsDialogView.initView(mainViewModel);

        statusBarView.initView(loginModel);

        //jpGestionView.add(gestionMesasView, BorderLayout.CENTER);

        jpGestionView.add(new JLabel(mainViewModel.getMainLogo()) , BorderLayout.CENTER);

        getContentPane().add(selectorView, BorderLayout.NORTH);
        getContentPane().add(jpGestionView, BorderLayout.CENTER);
        getContentPane().add(statusBarView, BorderLayout.SOUTH);

        setTitle(mainViewModel.getTitleBarTitle());
        setIconImage(mainViewModel.getTitleBarLogo().getImage());
        setSize(mainViewModel.getDefaultSize());
        setLocationRelativeTo(null);
        setVisible(true);

        //loginDialogView.initView(mainViewModel);

    }

    public void registerControllers(ActionListener selectorViewListener, ActionListener menuBarViewListener,
                                    MouseInputListener gestionMesasViewListener,
                                    MouseInputListener gestionCartaViewListener,
                                    MouseInputListener gestionPedidosViewListener,
                                    MouseInputListener gestionTop5ViewListener,
                                    MouseInputListener loginDialogViewListener,
                                    MouseInputListener settingsDialogViewListener,
                                    PlatosViewListener platosViewListener,
                                    PlatosOptionsViewListener platosOptionsViewListener) {


        selectorView.registerControllers(selectorViewListener);
        menuBarView.registerControllers(menuBarViewListener);

        gestionMesasView.registerControllers(gestionMesasViewListener);
        gestionCartaView.registerControllers(gestionCartaViewListener, platosViewListener, platosOptionsViewListener);
        gestionPedidosView.registerControllers(gestionPedidosViewListener);
        gestionTop5View.registerControllers(gestionTop5ViewListener);

        loginDialogView.registerControllers(loginDialogViewListener);
        settingsDialogView.registerControllers(settingsDialogViewListener);
    }

    public void setSVSelectedButton(String button) {
        selectorView.setSelectedButton(button);
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

    public void showLoginDialog() {
        loginDialogView.setVisible(true);
    }

    public void showSettingsDialog() {
        settingsDialogView.setVisible(true);
    }

    public String getUsername() {
        return loginDialogView.getJtfUsernameText();
    }

    public String getPassword() {
        return loginDialogView.getJtfPasswordText();
    }

    public char[] getPassword2() {
        return loginDialogView.getJpfPassword();
    }

    public void setLoginDialogVisible(boolean state) {
        loginDialogView.setVisible(state);
    }

    public void setConnectedState(boolean state) {
        statusBarView.setConnectedState(state);
    }

    public void setLabelsBackground(String id, boolean state) {
        gestionCartaView.setLabelsBackground(id, state);
    }

    public void setOptionsText(Plato plato) {
        gestionCartaView.setOptionsText(plato);
    }

    public boolean getGCVEditState() {
        return gestionCartaView.getPOVEditState();
    }

    public boolean getGCVAddState() { return gestionCartaView.getPOVAddState(); }

    public void setOptionsEditState(boolean state) {
        gestionCartaView.setEditState(state);
    }

    public void setAddProductState(boolean state) {
        gestionCartaView.setAddProductState(state);
    }

    public String getIdText() {
        return gestionCartaView.getIdText();
    }

    public String getTitleText() {
        return gestionCartaView.getTitleText();
    }

    public String getPriceText() {
        return gestionCartaView.getPriceText();
    }

    public String getUnitsText() {
        return gestionCartaView.getUnitsText();
    }

    public  String getTypeText() {
        return gestionCartaView.getTypeText();
    }

    public void refreshPlatos(LinkedList<Plato> platos, PlatosViewListener platosViewListener) {
        gestionCartaView.refreshPlatos(platos, platosViewListener);
    }

    public void resetTextFields() {
        gestionCartaView.resetTextFields();
    }
}
