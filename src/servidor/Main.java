package servidor;

import servidor.controller.*;
import servidor.model.*;
import servidor.model.Database.BBDDManager;
import servidor.network.MainServer;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;

public class Main {

    public static final String BBDD = "Restaurant";
    public static final String USERNAME = "Testo";
    public static final String PASSWORD = "machino";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainViewModel mainViewModel = new MainViewModel();
                LoginModel loginModel = new LoginModel();
                PlatosManager platosManager = new PlatosManager();
                ReservasManager reservasManager = new ReservasManager();
                PedidosManager pedidosManager = new PedidosManager();
                MesasManager mesasManager = new MesasManager();

                //TODO FALTA USER Y PASSWORD
                BBDDManager.setUsername(USERNAME);
                BBDDManager.setPassword(PASSWORD);
                BBDDManager.getInstance(BBDD);
                //TODO PORFAVOR
                //TODO WindowAdapter for my penis y cerrar la bbdd

                MainView mainView = new MainView();
                mainView.initMainView(mainViewModel, loginModel, platosManager.getPlatos(), mesasManager.getMesas(),
                                      reservasManager.getReservas());

                SelectorViewListener selectorViewListener = new SelectorViewListener(mainView, loginModel);
                ActionListener menuBarViewListener = new MenuBarViewListener(mainView);

                MouseInputListener gestionMesasViewListener = new GestionMesasViewListener(mainView);
                MouseInputListener gestionCartaViewListener = new GestionCartaViewListener(
                                                                  mainView.getGestionCartaView().getPlatosOptionsView(),
                                                                  mainView.getGestionCartaView().getPlatosView(),
                                                                  platosManager);
                MouseInputListener gestionPedidosViewListener = new GestionPedidosViewListener(mainView);
                MouseInputListener gestionTop5ViewListener = new GestionTop5ViewListener(mainView);

                MouseInputListener settingsDialogViewListener = new SettingsDialogViewListener(mainView);

                MesasViewListener mesasViewListener = new MesasViewListener(mainView, mesasManager);
                MesasOptionsViewListener mesasOptionsViewListener = new MesasOptionsViewListener(mainView, mesasManager, mesasViewListener);

                PedidosListListener pedidosListListener = new PedidosListListener(mainView, pedidosManager);

                mainView.registerControllers(selectorViewListener,
                                             menuBarViewListener,
                                             gestionMesasViewListener,
                                             gestionCartaViewListener,
                                             gestionPedidosViewListener,
                                             gestionTop5ViewListener,
                                             settingsDialogViewListener,

                                             mesasOptionsViewListener,
                                             mesasViewListener, pedidosListListener);

                MainServer mainServer = new MainServer(mainView, platosManager, reservasManager);
                mainServer.initServers();
            }
        });
    }
}
