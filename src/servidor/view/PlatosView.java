package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.util.LinkedList;

/**
 *
 */
public class PlatosView extends JPanel {

    private LinkedList<PlatoView> platosView;
    private JPanel jpMain;
    private JPanel jpPlatos;
    private JScrollPane jspPlatos;

    /**
     *
     */
    public PlatosView() {

        setLayout(new BorderLayout());

        platosView = new LinkedList<>();

        jpPlatos = new JPanel();
        jpPlatos.setLayout(new BoxLayout(jpPlatos, BoxLayout.Y_AXIS));

        jspPlatos = new JScrollPane();
        jspPlatos.getViewport().setView(jpPlatos);
        jspPlatos.setBorder(BorderFactory.createEmptyBorder());

        jpMain = new JPanel(new BorderLayout());
        jpMain.add(jspPlatos, BorderLayout.CENTER);

        add(jpMain, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /**
     *
     * @param platos
     */
    public synchronized void initPlatosView(LinkedList<Plato> platos) {

        platosView.clear();
        jpPlatos.removeAll();

        for(Plato pl : platos) {
            platosView.add(new PlatoView(pl));
            jpPlatos.add(platosView.getLast());
            setSelectedPlatosState(false);
        }
        jpPlatos.updateUI();
    }

    /**
     *
     * @param gestionCartaViewListener
     */
    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        for (PlatoView pv : platosView) {
            pv.registerControllers(gestionCartaViewListener);
        }
    }

    /**
     *
     * @param plato
     * @param gestionCartaViewListener
     */
    public synchronized void addPlato(Plato plato, MouseInputListener gestionCartaViewListener) {
        platosView.add(new PlatoView(plato));
        platosView.getLast().registerControllers(gestionCartaViewListener);
        jpPlatos.add(platosView.getLast());
        setSelectedPlatosState(false);
        jpPlatos.updateUI();
    }

    /**
     *
     * @param id
     * @param type
     * @param title
     * @param price
     * @param units
     */
    public synchronized void updatePlato(String id, String type, String title, String price, String units) {
        for(PlatoView pv : platosView) {
            if(pv.getJlProductId().equals(id)) {
                pv.setJlTitle(title);
                pv.setJlPrice(price);
                pv.setJlUnits(units);
                jpPlatos.updateUI();
                break;
            }
        }
    }

    /**
     *
     * @param productId
     */
    public synchronized void deletePlato(String productId) {
        for(int i = 0; i < platosView.size(); i++) {
            if (platosView.get(i).getJlProductId().equals(productId)){
                platosView.remove(i);
                jpPlatos.remove(i);
                setSelectedPlatosState(false);
                jpPlatos.updateUI();
                break;
            }
        }
    }

    /**
     *
     * @param id
     * @param state
     */
    public synchronized void setSelectedPlatoState(String id, boolean state) {
        for(PlatoView pv : platosView) {
            if (pv.getJlProductId().equals(id)) {
                pv.setSelectedState(state);
            } else {
                pv.setSelectedState(false);
            }
        }
    }

    /**
     *
     * @param state
     */
    public synchronized void setSelectedPlatosState(boolean state) {
        for(PlatoView pv : platosView) {
            pv.setSelectedState(state);
        }
    }

}