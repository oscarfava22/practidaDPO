package servidor.view;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import servidor.model.MainViewModel;
import servidor.model.Mesa;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class GestionMesasView extends JPanel {

    public static final String RESERVAS_TAG = "Reservas";
    public static final String NOMBRE_RESERVA_TAG = "Nombre de la Reserva";
    public static final String HORA_TAG = "Hora (hh:mm)";
    public static final String FECHA_TAG = "Fecha (dd/mm/aaaa)";
    public static final String ELIMINAR_MESA_TAG = "Eliminar mesa";
    public static final String AÑADIR_MESA_TAG = "Añadir mesa";
    public static final String LISTADO_MESAS_TAG = "Listado de mesas";

    public static int NUMERO_MESAS;

    private int nMesas;

    private JPanel jpLeft;
        private JScrollPane jspListaMesas;
            private JPanel jpListaMesas;
                private JPanel[] jpMesas;
                    private JLabel[] jlIdMesas;
                    private JPanel[] jpSouthMesa;
                        private JLabel[] jlNumComensalesMesas;
                        private JButton[] jbMesas;
    private JPanel jpRight;
        private JPanel jpReservas;
            private JPanel jpMesaSelected;
                private JLabel jlIdMesaSelected;
            private JScrollPane jspReservasMesa;
                private JPanel jpListaReservasMesa;
                    private JLabel[] jlNombresReserva;
                    private JLabel[] jlHorasReserva;
                    private JLabel[] jlFechasReserva;
        private JPanel jpBotones;
            private JButton jbEliminarMesa;
            private JButton jbAnadirMesa;

    /**
     *
     */

    public GestionMesasView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.ORANGE, 10));

        //nMesas = NUMBER_TABLES;
        jpMesas = new JPanel[nMesas];
        jbMesas = new JButton[nMesas];


        //PANEL SCROLLABLE A LA IZQUIERDA
        jpListaMesas = new JPanel(new GridLayout(nMesas, 1));

        //TODO RELLENAR LA LISTA SEGÚN EL NÚMERO DE MESAS QUE TENGAMOS EN EL .JSON
        /*for (int i = 0; i < nMesas; i++){
            jpMesas[i] = new JPanel(new BorderLayout());
            jbMesas[i] = new JButton("Mesa " + Integer.toString(i + 1));
            jpMesas[i].add(jbMesas[i], BorderLayout.CENTER);
            jpMesas[i].setMinimumSize(new Dimension(0, 75));
            jpListaMesas.add(jpMesas[i]);
        }*/

        jspListaMesas = new JScrollPane(jpListaMesas);
        //Size
        jspListaMesas.setMinimumSize(new Dimension(200, 0));
        jspListaMesas.setPreferredSize(new Dimension(200, 0));
        jpLeft = new JPanel(new BorderLayout());
        jpLeft.add(jspListaMesas, BorderLayout.CENTER);
        jpLeft.setBorder(BorderFactory.createTitledBorder(LISTADO_MESAS_TAG));
        add(jpLeft, BorderLayout.LINE_START);


        //PANEL CENTRAL DE INFORMACION DE LAS RESERVAS DE UNA MESA
        jpRight = new JPanel(new BorderLayout());
        jpRight.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        jpRight.setMaximumSize(new Dimension(200, 0));
        jpRight.setPreferredSize(new Dimension(200, 0));


        //Panel Central
        jpReservas = new JPanel(new BorderLayout());
        jspReservasMesa = new JScrollPane();

        jpListaReservasMesa = new JPanel(new GridLayout(1, 3));
        jpListaReservasMesa.setBorder(BorderFactory.createTitledBorder(RESERVAS_TAG));
        Font titleFont = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
        JLabel jlNombreReserva = new JLabel(NOMBRE_RESERVA_TAG);
        jlNombreReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlNombreReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlNombreReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlNombreReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlNombreReserva);
        JLabel jlHoraReserva = new JLabel(HORA_TAG);
        jlHoraReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlHoraReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlHoraReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlHoraReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlHoraReserva);
        JLabel jlFechaReserva = new JLabel(FECHA_TAG);
        jlFechaReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlFechaReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlFechaReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlFechaReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlFechaReserva);

        jspReservasMesa = new JScrollPane(jpListaReservasMesa);
        jpReservas.add(jspReservasMesa, BorderLayout.CENTER);

        jpMesaSelected = new JPanel(new BorderLayout());
        jpMesaSelected.add(new JLabel("Mesa: "), BorderLayout.LINE_START);
        jlIdMesaSelected = new JLabel("Ninguna mesa seleccionada");
        jpMesaSelected.add(jlIdMesaSelected, BorderLayout.CENTER);
        jpReservas.add(jpMesaSelected, BorderLayout.PAGE_START);

        jpRight.add(jpReservas, BorderLayout.CENTER);


        //Botones
        jpBotones = new JPanel(new BorderLayout());
        jbEliminarMesa = new JButton(ELIMINAR_MESA_TAG);
        jbAnadirMesa = new JButton(AÑADIR_MESA_TAG);
        jpBotones.add(jbEliminarMesa, BorderLayout.LINE_START);
        jpBotones.add(jbAnadirMesa, BorderLayout.LINE_END);
        jpBotones.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        jpRight.add(jpBotones, BorderLayout.PAGE_END);

        add(jpRight, BorderLayout.CENTER);

    }

    public JLabel getJlIdMesaSelected() {
        return jlIdMesaSelected;
    }

    public void setJlIdMesaSelected(JLabel jlIdMesaSelected) {
        this.jlIdMesaSelected = jlIdMesaSelected;
    }

    /**
     * Crea la vista del apartado Gestionar Mesas
     * @param mainViewModel
     */
    public void initView(MainViewModel mainViewModel) {
        //jlTitle.setText(mainViewModel.getGestionMesas());
    }


    /**
     * Registra los controladores para la vista
     * @param gestionMesasViewListener
     */
    public void registerControllers(ActionListener gestionMesasViewListener) {
        setActionCommands();

        for (int i = 0; i < jbMesas.length; i++){
            jbMesas[i].addActionListener(gestionMesasViewListener);
        }
        jbAnadirMesa.addActionListener(gestionMesasViewListener);
        jbEliminarMesa.addActionListener(gestionMesasViewListener);
    }

    public void setActionCommands(){
        for (int i = 0; i < jbMesas.length; i++){
            jbMesas[i].setActionCommand(Integer.toString(i));
        }
        jbAnadirMesa.setActionCommand("Añadir mesa");
        jbEliminarMesa.setActionCommand("Eliminar mesa");
    }

    public void setMesasScrollablePanel(LinkedList<Mesa> mesas){
        nMesas = mesas.size();

        jpMesas = new JPanel[nMesas];
        jlIdMesas = new JLabel[nMesas];
        jpSouthMesa = new JPanel[nMesas];
        jlNumComensalesMesas = new JLabel[nMesas];
        jbMesas = new JButton[nMesas];

        jpListaMesas = new JPanel(new GridLayout(nMesas, 1));

        for (int i = 0; i < nMesas; i++){
            int idMesa = mesas.get(i).getId();
            int numComensales = mesas.get(i).getNumComensales();
            jpMesas[i] = new JPanel(new BorderLayout());
            jlIdMesas[i] = new JLabel("Id: " + idMesa);
            jlNumComensalesMesas[i] = new JLabel("Num. Comensales: " + Integer.toString(numComensales));
            jpSouthMesa[i] = new JPanel(new BorderLayout());
            jbMesas[i] = new JButton("Select");

            jlIdMesas[i].setHorizontalAlignment(SwingConstants.CENTER);
            //jlIdMesas[i].setVerticalAlignment(SwingConstants.CENTER);
            jlIdMesas[i].setFont(new Font("Arial", Font.PLAIN, 25));

            //jlNumComensalesMesas[i].setHorizontalAlignment(SwingConstants.RIGHT);
            //jlNumComensalesMesas[i].setVerticalAlignment(SwingConstants.SOUTH);

            jpMesas[i].add(jlIdMesas[i], BorderLayout.CENTER);

            jpSouthMesa[i].add(jbMesas[i], BorderLayout.LINE_END);
            jpSouthMesa[i].add(jlNumComensalesMesas[i], BorderLayout.LINE_START);
            //TODO
            jpMesas[i].add(jpSouthMesa[i], BorderLayout.PAGE_END);

            jpMesas[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            jpMesas[i].setMinimumSize(new Dimension(0, 75));

            jpListaMesas.add(jpMesas[i]);
        }

        jspListaMesas = new JScrollPane(jpListaMesas);
        //Size
        jspListaMesas.setMinimumSize(new Dimension(200, 0));
        jspListaMesas.setPreferredSize(new Dimension(200, 0));

        jpLeft = new JPanel(new BorderLayout());
        jpLeft.add(jspListaMesas, BorderLayout.CENTER);
        jpLeft.setBorder(BorderFactory.createTitledBorder(LISTADO_MESAS_TAG));
        add(jpLeft, BorderLayout.LINE_START);
    }

    public void mostraReservesTaula(){
        //TODO Connectar amb BBDD i fer la query que obtingui un array de Reserves.(a controller i aqui(view))
    }
}
