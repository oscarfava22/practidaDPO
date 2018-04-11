package servidor.view;

import servidor.controller.PlatosOptionsViewListener;
import servidor.controller.PlatosViewListener;
import servidor.model.MainViewModel;
import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.LinkedList;

public class GestionCartaView extends JPanel {

    private JScrollPane jspCarta;
    private JPanel jpCarta;
    private PlatosView platosView;
    private PlatosOptionsView platosOptionsView;

    public GestionCartaView() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));

        jpCarta = new JPanel(new BorderLayout());
        jspCarta = new JScrollPane();
        jspCarta.getViewport().setView(jpCarta);
        jspCarta.setBorder(BorderFactory.createEmptyBorder());
        add(jspCarta, BorderLayout.CENTER);
    }

    public void initView(MainViewModel mainViewModel, LinkedList<Plato> platos) {

        platosView = new PlatosView();
        platosView.initPlatos(platos);
        platosOptionsView = new PlatosOptionsView();
        jpCarta.add(platosView, BorderLayout.NORTH);
        //add(platosView, BorderLayout.CENTER);
        add(platosOptionsView, BorderLayout.LINE_START);
    }

    public void registerControllers(MouseInputListener gestionCartaViewListener, PlatosViewListener platosViewListener,
                                    PlatosOptionsViewListener platosOptionsViewListener) {

        platosView.registerControllers(platosViewListener);
        platosOptionsView.registerControllers(platosOptionsViewListener);
    }

    public void setLabelsBackground(String id, boolean state) {
        platosView.setLabelsBackground(id, state);
    }

    public void setOptionsText(Plato plato) {
        platosOptionsView.setOptionsText(plato);
    }

    public boolean getPOVEditState() {
        return platosOptionsView.getEditState();
    }

    public boolean getPOVAddState() { return platosOptionsView.getAddState(); }

    public void setEditState(boolean state) {
        platosOptionsView.setEditState(state);
    }

    public void setAddProductState(boolean state) {
        platosOptionsView.setAddProductState(state);
    }

    public String getIdText() {
        return platosOptionsView.getIdText();
    }

    public String getTitleText() {
        return platosOptionsView.getTitleText();
    }

    public String getPriceText() {
        return platosOptionsView.getPriceText();
    }

    public String getUnitsText() {
        return platosOptionsView.getUnitsText();
    }

    public String getTypeText() {
        return platosOptionsView.getTypeText();
    }

    public void refreshPlatos(LinkedList<Plato> platos, PlatosViewListener platosViewListener) {
        platosView.refreshPlatos(platos, platosViewListener);
    }

    public void resetTextFields() {
        platosOptionsView.resetTextFields();
    }
}
