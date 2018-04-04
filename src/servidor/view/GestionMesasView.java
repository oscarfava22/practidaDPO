package servidor.view;

import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionMesasView extends JPanel {

    private int nMesas;
    private JLabel jlTitle;

    private JPanel jpGeneral;

    private JScrollPane jspListaMesas;
    private JLabel[] jlListaMesas;

    /**
     *
     */

    public GestionMesasView() {
        setLayout(new BorderLayout());

        jlTitle = new JLabel();
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);

        add(jlTitle, BorderLayout.NORTH);
    }

    public GestionMesasView(int nMesas) {
        this.nMesas = nMesas;

        jpGeneral = new JPanel();
        jpGeneral.setLayout(new BorderLayout());

        jspListaMesas = new JScrollPane();
        jspListaMesas.setLayout(new GridLayout(nMesas, 1));
        jspListaMesas.setPreferredSize(new Dimension(50, 350));

        jlListaMesas = new JLabel[nMesas];
        for (int i = 0; i < nMesas; i++){
            jlListaMesas[i] = new JLabel("MESA " + Integer.toString(i + 1));
            jlListaMesas[i].setPreferredSize(new Dimension(50, 20));
        }

    }


    /**
     * Crea la vista del apartado Gestionar Mesas
     * @param mainViewModel
     */
    public void initView(MainViewModel mainViewModel) {
        jlTitle.setText(mainViewModel.getGestionMesas());
    }


    /**
     * Registra los controladores para la vista
     * @param gestionMesasViewListener
     */
    public void registerControllers(MouseInputListener gestionMesasViewListener) {

        jlTitle.addMouseListener(gestionMesasViewListener);

    }
}
