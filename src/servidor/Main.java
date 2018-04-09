package servidor;

import json.io.JsonIO;
import servidor.controller.*;
import servidor.model.LoginModel;
import servidor.model.MainViewModel;
import servidor.model.Plato;
import servidor.model.PlatosManager;
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


                MainView mainView = new MainView();
                mainView.initView(mainViewModel, loginModel, platosManager.getPlatos());

                ActionListener selectorViewListener = new SelectorViewListener(mainView, loginModel);
                ActionListener menuBarViewListener = new MenuBarViewListener(mainView);

                MouseInputListener gestionMesasViewListener = new GestionMesasViewListener(mainView);
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


            }
        });
    }
}
