package servidor.model;

import javax.swing.*;
import java.awt.*;

public class MainViewModel {

    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final static Dimension defaultSize = new Dimension(1000, 618);

    private final static String titleBarTitle = "Server";
    private final static ImageIcon titleBarLogo = new ImageIcon("data/logo24x24black.png");
    private final static ImageIcon mainLogo = new ImageIcon("data/cena512gray.png");

    private final static String gestionMesas = "Mesas";
    private final static String gestionCarta = "Carta";
    private final static String gestionPedidos = "Pedidos";
    private final static String top5 = "Top 5";

    private final static Dimension loginDialogSize = new Dimension(316, 512);
    private final static String loginDialogTitle = "Log In";

    private final static Dimension settingsDialogSize = new Dimension(512, 316);
    private final static String settingsDialogTitle = "Settings";

    public MainViewModel() {

    }


    public Dimension getScreenSize() {
        return screenSize;
    }

    public Dimension getDefaultSize() {
        return defaultSize;
    }



    public String getTitleBarTitle() {
        return titleBarTitle;
    }

    public ImageIcon getTitleBarLogo() {
        return titleBarLogo;
    }

    public ImageIcon getMainLogo() {
        return mainLogo;
    }

    public String getGestionMesas() {
        return gestionMesas;
    }

    public String getGestionCarta() {
        return gestionCarta;
    }

    public String getGestionPedidos() {
        return gestionPedidos;
    }

    public String getTop5() {
        return top5;
    }

    public Dimension getLoginDialogSize() {
        return loginDialogSize;
    }

    public String getLoginDialogTitle() {
        return loginDialogTitle;
    }

    public Dimension getSettingsDialogSize() {
        return settingsDialogSize;
    }

    public String getSettingsDialogTitle() {
        return settingsDialogTitle;
    }
}
