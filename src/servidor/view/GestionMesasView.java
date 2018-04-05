package servidor.view;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GestionMesasView extends JPanel {

    public static final int AUX_MAX_RESERVAS = 40;
    public static final int AUX_NUMBER_TABLES = 5;

    private int nMesas;

    private JScrollPane jspListaMesas;
        private JPanel jpListaMesas;
            private JButton[] jbMesas;

    private JPanel jpRight;
        private JLabel jlMesaNumero;
        private JPanel jpReservas;
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

        //PANEL SCROLLABLE A LA IZQUIERDA
        jspListaMesas = new JScrollPane();
        jpListaMesas = new JPanel(new GridLayout());
        //TODO RELLENAR LA LISTA SEGÚN EL NÚMERO DE MESAS QUE TENGAMOS EN EL .JSON
        // (fuera del constructor)
        jspListaMesas.add(jpListaMesas);
        //Size
        jspListaMesas.setMinimumSize(new Dimension(200, 0));
        jspListaMesas.setPreferredSize(new Dimension(200, 0));

        add(jspListaMesas, BorderLayout.LINE_START);


        //PANEL CENTRAL DE INFORMACION DE LAS RESERVAS DE UNA MESA
        jpRight = new JPanel(new BorderLayout());
        jpRight.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        jpRight.setMaximumSize(new Dimension(200, 0));
        jpRight.setPreferredSize(new Dimension(200, 0));

        //Titulo
        jlMesaNumero = new JLabel("Mesa X");
        jlMesaNumero.setHorizontalAlignment(SwingConstants.CENTER);
        jpRight.add(jlMesaNumero, BorderLayout.PAGE_START);

        //Panel Central
        jpReservas = new JPanel(new BorderLayout());
        jspReservasMesa = new JScrollPane();

        jpListaReservasMesa = new JPanel(new GridLayout(AUX_MAX_RESERVAS, 3));
        jpListaReservasMesa.setBorder(BorderFactory.createTitledBorder("Reservas"));
        Font titleFont = new Font(getFont().getName(), Font.BOLD, getFont().getSize());
        JLabel jlNombreReserva = new JLabel("Nombre de la Reserva");
        jlNombreReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlNombreReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlNombreReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlNombreReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlNombreReserva);
        JLabel jlHoraReserva = new JLabel("Hora (hh:mm)");
        jlHoraReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlHoraReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlHoraReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlHoraReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlHoraReserva);
        JLabel jlFechaReserva = new JLabel("Fecha (dd/mm/aaaa)");
        jlFechaReserva.setBorder(BorderFactory.createMatteBorder(2,2,4,2, Color.BLACK));
        jlFechaReserva.setHorizontalAlignment(SwingConstants.CENTER);
        jlFechaReserva.setVerticalAlignment(SwingConstants.NORTH);
        jlFechaReserva.setFont(titleFont);
        jpListaReservasMesa.add(jlFechaReserva);

        jlNombresReserva = new JLabel[AUX_MAX_RESERVAS - 1];
        jlHorasReserva = new JLabel[AUX_MAX_RESERVAS - 1];
        jlFechasReserva = new JLabel[AUX_MAX_RESERVAS - 1];
        for (int i = 0; i < AUX_MAX_RESERVAS - 1; i++){
            jlNombresReserva[i] = new JLabel("EJEMPLO NOMBRE");
            jlNombresReserva[i].setBorder(BorderFactory.createMatteBorder(0,2,2,0, Color.BLACK));
            jlNombresReserva[i].setHorizontalAlignment(SwingConstants.CENTER);
            jpListaReservasMesa.add(jlNombresReserva[i]);
            jlHorasReserva[i] = new JLabel("EJEMPLO HORA");
            jlHorasReserva[i].setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.BLACK));
            jlHorasReserva[i].setHorizontalAlignment(SwingConstants.CENTER);
            jpListaReservasMesa.add(jlHorasReserva[i]);
            jlFechasReserva[i] = new JLabel("EJEMPLO FECHA");
            jlFechasReserva[i].setBorder(BorderFactory.createMatteBorder(0,0,2,2, Color.BLACK));
            jlFechasReserva[i].setHorizontalAlignment(SwingConstants.CENTER);
            jpListaReservasMesa.add(jlFechasReserva[i]);
        }

        jspReservasMesa = new JScrollPane(jpListaReservasMesa);
        jpReservas.add(jspReservasMesa, BorderLayout.CENTER);
        //jpRight.add(jspListaMesas, BorderLayout.CENTER);
        jpRight.add(jpReservas, BorderLayout.CENTER);


        //Botones
        jpBotones = new JPanel(new BorderLayout());
        jbEliminarMesa = new JButton("Eliminar mesa");
        jbAnadirMesa = new JButton("Añadir mesa");
        jpBotones.add(jbEliminarMesa, BorderLayout.LINE_START);
        jpBotones.add(jbAnadirMesa, BorderLayout.LINE_END);
        jpRight.add(jpBotones, BorderLayout.PAGE_END);

        add(jpRight, BorderLayout.CENTER);

    }


    public int getNumberOfTables() throws FileNotFoundException {
        JsonParser parser = new JsonParser();

        //LLEGIR JSON I ESTRUCTURARLO A LES NOSTRES DADES
        //llegirem l'arxiu "laSallers.json" ubicat a la carpeta "src" del projecte
        FileReader file = new FileReader("data/json/config.json");
        Object obj = parser.parse(file);
        //Creem un jsonObject que ho englobarà TOT
        JsonObject dades = (JsonObject) obj;
        
        //TODO get Json file and get the number of tables that the restaurant disposes
        return AUX_NUMBER_TABLES;
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
    public void registerControllers(MouseInputListener gestionMesasViewListener) {

        //jlTitle.addMouseListener(gestionMesasViewListener);

    }
}
