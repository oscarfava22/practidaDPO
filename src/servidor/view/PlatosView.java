package servidor.view;

import servidor.controller.PlatosViewListener;
import servidor.model.Plato;

import javax.swing.*;

import java.awt.*;
import java.util.LinkedList;

public class PlatosView extends JPanel {

    private LinkedList<PlatoView> platosView;
    private JPanel jpMain;

    private JPanel jpPlatos;
    private JScrollPane jspPlatos;

    public PlatosView() {
        platosView = new LinkedList<>();
        jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        //add(new JLabel("Carta") ,BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);

        /*jpPlatos = new JPanel();
        jpPlatos.setLayout(new BoxLayout(jpPlatos, BoxLayout.Y_AXIS));

        jspPlatos = new JScrollPane();
        jspPlatos.getViewport().setView(jpPlatos);

        jpMain = new JPanel(new BorderLayout());

        jpMain.add(jspPlatos, BorderLayout.CENTER);
        add(jpMain, BorderLayout.CENTER);*/
    }

    public void addPlato() { }

    public void addPlatos() { }

    public void initPlatos(LinkedList<Plato> platos) {

        for(Plato pl : platos) {
            platosView.add(new PlatoView(pl));
            jpMain.add(platosView.getLast());
            setLabelsBackground2(false);
        }
        //jspPlatos.getViewport().setView(jpPlatos);
        //jspPlatos.getViewport().setView(jpPlatos);
        //add(jpMain, BorderLayout.CENTER);
    }

    public void refreshPlatos(LinkedList<Plato> platos, PlatosViewListener platosViewListener) {

        platosView.clear();
        jpMain.removeAll();
        updateUI();
        initPlatos(platos);
        registerControllers(platosViewListener);
        setLabelsBackground2(false);

    }

    public void registerControllers(PlatosViewListener platosViewListener) {

        for (PlatoView pv : platosView) {
            pv.registerControllers(platosViewListener);
        }
    }

    public void setLabelsBackground(String id, boolean state) {

        for(PlatoView pv : platosView) {
            if (pv.getJlId().equals(id)) {
                pv.setLabelsBackground(state);
            } else {
                pv.setLabelsBackground(false);
            }
        }
    }

    public void setLabelsBackground2(boolean state) {
        for(PlatoView pv : platosView) {
            pv.setLabelsBackground(state);
        }
    }

}