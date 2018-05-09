package servidor.view;

import servidor.controller.GestionMesasViewListener;
import servidor.controller.MesasViewListener;
import servidor.model.Mesa;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MesasView extends JPanel{

    private LinkedList<MesaView> mesasView;
    private JPanel jpMain;

    public static int NUMERO_FILES = 0;

    public MesasView() {
        mesasView = new LinkedList<>();
        jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());

        add(jpMain, BorderLayout.PAGE_START);
    }

    public void initMesas(LinkedList<Mesa> mesas) {

        for (Mesa ml : mesas) {
            mesasView.add(new MesaView(ml));
            jpMain.add(mesasView.getLast());
            setLabelsBackground2(false);
        }
    }

    public void refreshMesas(LinkedList<Mesa> mesas, MesasViewListener mesasViewListener) {
        mesasView.clear();
        jpMain.removeAll();
        updateUI();
        initMesas(mesas);
        registerControllers(mesasViewListener);
        setLabelsBackground2(false);

    }

    public void registerControllers(MesasViewListener mesasViewListener) {

        for (MesaView mv : mesasView) {
            mv.registerControllers(mesasViewListener);
        }
    }

    public void setLabelsBackground(String id, boolean state) {

        for(MesaView mv : mesasView) {
            if (mv.getJlIdMesa().getText().toString().equals(id)) {
                mv.setLabelsBackground(state);
            } else {
                mv.setLabelsBackground(false);
            }
        }
    }

    public void setLabelsBackground2(boolean state) {
        for(MesaView mv : mesasView) {
            mv.setLabelsBackground(state);
        }
    }

    public LinkedList<MesaView> getMesasView() {
        return mesasView;
    }

    public void setMesasView(LinkedList<MesaView> mesasView) {
        this.mesasView = mesasView;
    }

    public JPanel getJpMain() {
        return jpMain;
    }

    public void setJpMain(JPanel jpMain) {
        this.jpMain = jpMain;
    }
}
