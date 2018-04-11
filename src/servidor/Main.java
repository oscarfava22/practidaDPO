package servidor;

import json.io.JsonIO;
import servidor.controller.*;
import servidor.model.*;
<<<<<<< Updated upstream
import servidor.network.Server;
=======
import servidor.network.Server2;
>>>>>>> Stashed changes
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainViewModel mainViewModel = new MainViewModel();
                LoginModel loginModel = new LoginModel();
                PlatosManager platosManager = new PlatosManager();
<<<<<<< Updated upstream
                MesasManager mesasManager = new MesasManager(Server.getJsonObjectFromConfigFile());
=======
                ReservasManager reservasManager = new ReservasManager();
                PedidosManager pedidosManager = new PedidosManager();
>>>>>>> Stashed changes

                MainView mainView = new MainView();
                mainView.initView(mainViewModel, loginModel, platosManager.getPlatos());

                ActionListener selectorViewListener = new SelectorViewListener(mainView, loginModel);
                ActionListener menuBarViewListener = new MenuBarViewListener(mainView);

                ActionListener gestionMesasViewListener = new GestionMesasViewListener(mainView, mesasManager);
                MouseInputListener gestionCartaViewListener = new GestionCartaViewListener(mainView);
                MouseInputListener gestionPedidosViewListener = new GestionPedidosViewListener(mainView);
                MouseInputListener gestionTop5ViewListener = new GestionTop5ViewListener(mainView);

                MouseInputListener loginDialogViewListener = new LoginDialogViewListener(mainView, loginModel);
                MouseInputListener settingsDialogViewListener = new SettingsDialogViewListener(mainView);

                PlatosViewListener platosViewListener = new PlatosViewListener(mainView, platosManager);
                PlatosOptionsViewListener platosOptionsViewListener = new PlatosOptionsViewListener(mainView, platosManager, platosViewListener);

                mainView.registerControllers(selectorViewListener,
                                             menuBarViewListener,
                                             gestionMesasViewListener,
                                             gestionCartaViewListener,
                                             gestionPedidosViewListener,
                                             gestionTop5ViewListener,
                                             loginDialogViewListener,
                                             settingsDialogViewListener,

                                             platosViewListener,
                                             platosOptionsViewListener);

<<<<<<< Updated upstream
                Server server = new Server(mesasManager);
=======
                Server2 server2 = new Server2(mainView, platosManager);
                server2.start();

>>>>>>> Stashed changes
            }
        });
    }
}
