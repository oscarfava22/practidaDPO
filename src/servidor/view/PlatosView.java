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

        setLayout(new BorderLayout());

        platosView = new LinkedList<>();

        /*jpPlatos = new JPanel();
        jpPlatos.setLayout(new BoxLayout(jpPlatos, BoxLayout.Y_AXIS));
        add(jpPlatos, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));*/


        jpPlatos = new JPanel();
        jpPlatos.setLayout(new BoxLayout(jpPlatos, BoxLayout.Y_AXIS));

        jspPlatos = new JScrollPane();
        jspPlatos.getViewport().setView(jpPlatos);
        jspPlatos.setBorder(BorderFactory.createEmptyBorder());

        jpMain = new JPanel(new BorderLayout());
        jpMain.add(jspPlatos, BorderLayout.CENTER);


        add(jpMain, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        /*jpPedidos.add(jtPedidos, BorderLayout.CENTER);

        jspPedidos = new JScrollPane();
        jspPedidos.getViewport().setView(jpPedidos);
        jspPedidos.setBorder(BorderFactory.createEmptyBorder());

        jpMain = new JPanel(new BorderLayout());

        jpMain.add(jspPedidos, BorderLayout.CENTER);

        add(jtPedidos.getTableHeader(), BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);*/
    }

    public void addPlato() { }

    public void addPlatos() { }

    public void initPlatos(LinkedList<Plato> platos) {

        for(Plato pl : platos) {
            platosView.add(new PlatoView(pl));
            jpPlatos.add(platosView.getLast());
            setLabelsBackground2(false);
        }
        //jspPlatos.getViewport().setView(jpPlatos);
        //jspPlatos.getViewport().setView(jpPlatos);
        //add(jpMain, BorderLayout.CENTER);
    }

    public void refreshPlatos(LinkedList<Plato> platos, PlatosViewListener platosViewListener) {

        platosView.clear();
        jpPlatos.removeAll();
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