package servidor.view;

import servidor.controller.PlatosViewListener;
import servidor.model.Plato;

import javax.swing.*;

import java.util.LinkedList;

public class PlatosView extends JPanel {

    private LinkedList<PlatoView> platosView;

    public PlatosView() {
        platosView = new LinkedList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addPlato() { }

    public void addPlatos() { }

    public void initPlatos(LinkedList<Plato> platos) {

        for(Plato pl : platos) {
            platosView.add(new PlatoView(pl));
            add(platosView.getLast());
        }
    }

    public void refreshPlatos(LinkedList<Plato> platos, PlatosViewListener platosViewListener) {

        platosView.clear();
        removeAll();
        updateUI();
        initPlatos(platos);
        registerControllers(platosViewListener);
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
}