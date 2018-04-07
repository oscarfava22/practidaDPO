package servidor;

import servidor.controller.*;
import servidor.model.LoginModel;
import servidor.model.MainViewModel;
import servidor.network.Server;
import servidor.view.MainView;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Server data = new Server();
                /*TODO Hacer que las views y el modelo estén dentro del Server (que extiende de thread): HABLARLO EN CLASE*/
                MainViewModel mainViewModel = new MainViewModel();
                LoginModel loginModel = new LoginModel();

                MainView mainView = new MainView();
                mainView.initView(mainViewModel, loginModel);

                ActionListener selectorViewListener = new SelectorViewListener(mainView, loginModel);
                ActionListener menuBarViewListener = new MenuBarViewListener(mainView);

                MouseInputListener gestionMesasViewListener = new GestionMesasViewListener(mainView);
                MouseInputListener gestionCartaViewListener = new GestionCartaViewListener(mainView);
                MouseInputListener gestionPedidosViewListener = new GestionPedidosViewListener(mainView);
                MouseInputListener gestionTop5ViewListener = new GestionTop5ViewListener(mainView);

                MouseInputListener loginDialogViewListener = new LoginDialogViewListener(mainView, loginModel);
                MouseInputListener settingsDialogViewListener = new SettingsDialogViewListener(mainView);

                mainView.registerControllers(selectorViewListener,
                                             menuBarViewListener,
                                             gestionMesasViewListener,
                                             gestionCartaViewListener,
                                             gestionPedidosViewListener,
                                             gestionTop5ViewListener,
                                             loginDialogViewListener,
                                             settingsDialogViewListener);


            }
        });
    }
}
