package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.util.LinkedList;

/**
 * Panel que contiene todos los platos que le sean añadidos.
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
     * Permite inicializar la vista de platos.
     * @param platos lista de platos que se recibe para mostar.
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
     * Método para registrar el controlador recibido a los componentes utilizados.
     * @param gestionCartaViewListener el controlador que se recibe para registrar.
     */
    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        for (PlatoView pv : platosView) {
            pv.registerControllers(gestionCartaViewListener);
        }
    }

    /**
     * Permite registar los diferentes componentes al controlador recibido.
     * @param plato el plato recibido,
     * @param gestionCartaViewListener el controlador que se regitrara al plato recibido.
     */
    public synchronized void addPlato(Plato plato, MouseInputListener gestionCartaViewListener) {
        platosView.add(new PlatoView(plato));
        platosView.getLast().registerControllers(gestionCartaViewListener);
        jpPlatos.add(platosView.getLast());
        setSelectedPlatosState(false);
        jpPlatos.updateUI();
    }

    /**
     * Permite actualizar todos los atributos de un plato.
     * @param id del plato.
     * @param type del plato.
     * @param title del plato.
     * @param price del plato.
     * @param units del plato.
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
     * Permite eliniar un plato del panel de plato mediante su id.
     * @param productId el id del plato a eliminar.
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
     * Permite seleccionar un plato cambiando su apariencia, mediante su id.
     * @param id del plato a seleccionar.
     * @param state el estado de la seleccion.
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
     * Permite establecer el estado de los platos.
     * @param state el estado para asignar.
     */
    public synchronized void setSelectedPlatosState(boolean state) {
        for(PlatoView pv : platosView) {
            pv.setSelectedState(state);
        }
    }

}