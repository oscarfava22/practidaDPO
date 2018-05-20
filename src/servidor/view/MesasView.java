package servidor.view;

import servidor.Main;
import servidor.controller.MesasViewListener;
import servidor.model.Database.BBDDManager;
import servidor.model.Mesa;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MesasView extends JPanel{

    /**
     * Atributos de la clase
     */
    private LinkedList<MesaView> mesasView;
    private JPanel jpMain;

    /**
     * Constructor de la clase sin parámetros
     */
    public MesasView() {
        mesasView = new LinkedList<>();
        jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());

        add(jpMain, BorderLayout.PAGE_START);
    }

    /**
     * Función para inicializar las mesas mediante conexión con la base de datos y obteniendo las mesas
     * De esta forma, con cada interacción de la pantalla que debamos cargar las mesas actuales, llamaremos a esta función
     * y obtendremos las mesas de la base de datos. Así nos protegemos de que haya cambios en la base de datos mientras
     * el programa está en ejecución.
     */
    public void initMesas() {
        //Connect with bbdd to get mesas every time we refresh the view
        //Llamar al getInstance
        BBDDManager bbddManager = BBDDManager.getInstance(Main.BBDD);
        // Del objeto getInstance hacer un connect
        bbddManager.connect();

        String query = "SELECT * FROM Mesa";
        ResultSet resultSet = bbddManager.readQuery(query);

        try {
            LinkedList<Mesa> mesaLinkedList = leerMesasFromResultSet(resultSet);
            //mesasView = new LinkedList<MesaView>();
            mesasView.clear();
            for (Mesa ml : mesaLinkedList) {
                mesasView.add(new MesaView(ml));
                jpMain.add(mesasView.getLast());
                setLabelsBackground2(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Del objeto getInstance, desconectar
        bbddManager.disconnect();
    }

    /**
     * Función que devuelve las mesas que hemos obtenido con la base de datos y que se encuentran en el resultSet
     * @param resultSet Result Set de la query para obtener las mesas
     * @return LinkedList de las Mesas obtenidas
     * @throws SQLException
     */
    public LinkedList<Mesa> leerMesasFromResultSet(ResultSet resultSet) throws SQLException {
        LinkedList<Mesa> mesas = new LinkedList<Mesa>();

        while (resultSet.next()){
            int idMesa = resultSet.getInt("id_mesa");
            int numComensales = resultSet.getInt("num_comensales");

            Mesa mesa = new Mesa(idMesa, numComensales);
            System.out.println(mesa.toString());

            mesas.add(mesa);
        }

        return mesas;
    }

    /**
     * Función para refrescar las mesas
     * @param mesasViewListener
     */
    public void refreshMesas(MesasViewListener mesasViewListener) {
        mesasView.clear();
        jpMain.removeAll();
        updateUI();
        initMesas();
        jpMain.updateUI();
        registerControllers(mesasViewListener);
        setLabelsBackground2(false);

    }

    /**
     * Función para vincular los controladores a cada una de las mesas
     * @param mesasViewListener
     */
    public void registerControllers(MesasViewListener mesasViewListener) {

        for (MesaView mv : mesasView) {
            mv.registerControllers(mesasViewListener);
        }
    }

    /**
     * Función que cambia el color de fondo según una id y un state
     * @param id
     * @param state
     */
    public void setLabelsBackground(String id, boolean state) {

        for(MesaView mv : mesasView) {
            if (mv.getJlIdMesa().getText().toString().equals(id)) {
                mv.setLabelsBackground(state);
            } else {
                mv.setLabelsBackground(false);
            }
        }
    }

    /**
     * Función que cambia el color de fondo según un state
     * @param state
     */
    public void setLabelsBackground2(boolean state) {
        for(MesaView mv : mesasView) {
            mv.setLabelsBackground(state);
        }
    }

    /**
     * Getters i Setters
     */
    public LinkedList<MesaView> getMesasView() {
        return mesasView;
    }
    public void setMesasView(LinkedList<MesaView> mesasView) {
        this.mesasView = mesasView;
    }
    public JPanel getJpMain() {
        return jpMain;
    }
    public void setJpMain(JPanel jpMain) {
        this.jpMain = jpMain;
    }

}
