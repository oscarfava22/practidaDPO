package servidor.view;

import servidor.controller.MesasOptionsViewListener;
import servidor.controller.MesasViewListener;
import servidor.model.MainViewModel;
import servidor.model.Mesa;

import javax.swing.*;
import java.awt.*;
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

    private JPanel jpLeft;
        private JScrollPane jspListaMesas;
            private JPanel jpMesas;
            private MesasView mesasView;
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
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 10));

        //PANEL SCROLLABLE A LA IZQUIERDA
        jpMesas = new JPanel();
        jspListaMesas = new JScrollPane();
        jspListaMesas.getViewport().setView(jpMesas);

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
    public void initView(MainViewModel mainViewModel, LinkedList<Mesa> mesas) {
        mesasView = new MesasView();
        mesasView.initMesas(mesas);
        jpMesas.add(mesasView, BorderLayout.CENTER);
    }

    public void registerControllers(MesasOptionsViewListener optionsListener, MesasViewListener mesasViewListener) {
        mesasView.registerControllers(mesasViewListener);
        jbEliminarMesa.addActionListener(optionsListener);
        jbAnadirMesa.addActionListener(optionsListener);
    }

    public void setLabelsBackground(String id, boolean state) {
        mesasView.setLabelsBackground(id, state);
    }

    public void refreshPlatos(LinkedList<Mesa> mesas, MesasViewListener mesasViewListener) {
        mesasView.refreshMesas(mesas, mesasViewListener);
    }

    public void setActionCommands(){
        jbAnadirMesa.setActionCommand(AÑADIR_MESA_TAG);
        jbEliminarMesa.setActionCommand(ELIMINAR_MESA_TAG);
    }

    public void refreshMesas(LinkedList<Mesa> mesas, MesasViewListener mesasViewListener) {
        mesasView.refreshMesas(mesas, mesasViewListener);
    }
}
