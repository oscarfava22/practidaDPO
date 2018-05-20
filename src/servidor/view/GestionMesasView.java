package servidor.view;

import servidor.controller.MesasOptionsViewListener;
import servidor.controller.MesasViewListener;
import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GestionMesasView extends JPanel {

    //Borde
    private Border compounBorder =  BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY),
            BorderFactory.createEmptyBorder(10,10,10,10));

    /**
     * Constantes de la clase
     */
    public static final String RESERVAS_TAG = "Reservas";
    public static final String NOMBRE_RESERVA_TAG = "Nombre de la Reserva";
    public static final String HORA_TAG = "Hora (hh:mm)";
    public static final String FECHA_TAG = "Fecha (dd/mm/aaaa)";
    public static final String ELIMINAR_MESA_TAG = "Eliminar mesa";
    public static final String AÑADIR_MESA_TAG = "Añadir mesa";
    public static final String LISTADO_MESAS_TAG = "Listado de mesas";
    public static final String NINGUNA_MESA_SELECCIONADA_TAG = "Ninguna mesa seleccionada";
    public static final String MESA_SELECCIONADA = "Mesa seleccionada: ";

    /**
     * Atributos de la clase (Estructura de la vista)
     */
    private JPanel jpLeft;
        private JScrollPane jspListaMesas;
            private JPanel jpMesas;
            private MesasView mesasView;
    private JPanel jpRight;
        private JPanel jpReservas;
            private JPanel jpMesaSelected;
                private CustomLabel jlIdSelected;
                private JLabel jlIdMesaSelected;
            private ReservasView reservasView;
        private JPanel jpBotones;
            private JButton jbEliminarMesa;
            private JButton jbAnadirMesa;

    /**
     * Constructor de la vista de la gestión de las mesas
     */
    public GestionMesasView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.PINK, 10));

        //PANEL SCROLLABLE A LA IZQUIERDA
        jpMesas = new JPanel(new BorderLayout());
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

        jpMesaSelected = new JPanel(new BorderLayout());
        jlIdSelected = new CustomLabel();
        jlIdSelected.setBorder(compounBorder);
        jlIdSelected.setOpaque(true);
        jlIdSelected.setText(NINGUNA_MESA_SELECCIONADA_TAG);

        jpMesaSelected.add(jlIdSelected, BorderLayout.LINE_START);
        jpReservas.add(jpMesaSelected, BorderLayout.PAGE_START);

        reservasView = new ReservasView();

        JPanel aux = new JPanel(new BorderLayout());
        aux.add(reservasView, BorderLayout.CENTER);
        jpReservas.add(aux, BorderLayout.NORTH);

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

    /**
     * Getters & Setters
     */
    public JLabel getJlIdMesaSelected() {
        return jlIdSelected;
    }
    public void setJlIdMesaSelected(JLabel jlIdMesaSelected) {
        this.jlIdMesaSelected = jlIdMesaSelected;
    }

    /**
     * Función para inicializzar la vista
     * @param reservas
     */
    public void initView(ArrayList<InfoResultSetReserva> reservas) {
        mesasView = new MesasView();
        mesasView.initMesas();

        jpMesas.add(mesasView, BorderLayout.CENTER);
        jpMesas.updateUI();

        reservasView = new ReservasView();
        reservasView.initReservas(reservas);

        ponColorIdSelected();

        jpReservas.add(reservasView, BorderLayout.CENTER);
    }

    /**
     * Procedimiento que pinta del colo ROJO o NARANJA la JLabel de la id de la mesa seleccionada dependiendo de si
     *  hay alguna seleccionada o no
     */
    public void ponColorIdSelected() {
        if (jlIdSelected.getText().toString().contains(NINGUNA_MESA_SELECCIONADA_TAG)){
            jlIdSelected.setBackground(Color.RED);
        }else{
            jlIdSelected.setBackground(Color.orange);
        }
    }

    /**
     * Función para registrar los controladores a la vista, tanto el de añadir y eliminar mesa como el de
     *  clicar en las mesas
     * @param optionsListener
     * @param mesasViewListener
     */
    public void registerControllers(MesasOptionsViewListener optionsListener, MesasViewListener mesasViewListener) {
        mesasView.registerControllers(mesasViewListener);
        jbEliminarMesa.addActionListener(optionsListener);
        jbAnadirMesa.addActionListener(optionsListener);
    }

    /**
     * Dada una id (id), poner la mesa del color seleccionado o no segun el estado (state)
     * @param id
     * @param state
     */
    public void setLabelsBackground(String id, boolean state) {
        mesasView.setLabelsBackground(id, state);
    }

    /**
     * A los botones de añadir y eliminar, poner sus respectivos ActionCommands
     */
    public void setActionCommands(){
        jbAnadirMesa.setActionCommand(AÑADIR_MESA_TAG);
        jbEliminarMesa.setActionCommand(ELIMINAR_MESA_TAG);
    }

    public void refreshMesas(MesasViewListener mesasViewListener) {
        mesasView.refreshMesas(mesasViewListener);
        ponColorIdSelected();
    }

    public JPanel getJpMesas() {
        return jpMesas;
    }

    public MesasView getMesasView() {
        return mesasView;
    }

    public CustomLabel getJlIdSelected() {
        return jlIdSelected;
    }

    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        reservasView.refreshReservas(reservas);
        ponColorIdSelected();
    }

    public void setIdMesaSeleccionada(String idMesaSeleccionada){
        //jlIdMesaSelected.setText(MESA_SELECCIONADA + idMesaSeleccionada);
        jlIdSelected.setText(MESA_SELECCIONADA + idMesaSeleccionada);
    }
}
