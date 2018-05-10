package servidor.view;

import servidor.controller.GestionMesasViewListener;
import servidor.controller.MesasViewListener;
import servidor.model.Database.BBDDManager;
import servidor.model.Mesa;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MesasView extends JPanel{

    private LinkedList<MesaView> mesasView;
    private JPanel jpMain;

    public static int NUMERO_FILES = 0;

    public MesasView() {
        mesasView = new LinkedList<>();
        jpMain = new JPanel();
        jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());

        add(jpMain, BorderLayout.PAGE_START);
    }

    public void initMesas(LinkedList<Mesa> mesas) {
        //TODO: Connect with bbdd to get mesas every time we refresh it
        //Llamar al getInstance
        BBDDManager bbddManager = BBDDManager.getInstance("Restaurant");
        // Del objeto getInstance hacer un connect
        bbddManager.connect();

        String query = "SELECT * FROM Mesa";
        ResultSet resultSet = bbddManager.readQuery(query);

        try {
            LinkedList<Mesa> mesaLinkedList = leerMesasFromResultSet(resultSet);
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

    public void refreshMesas(LinkedList<Mesa> mesas, MesasViewListener mesasViewListener) {
        mesasView.clear();
        jpMain.removeAll();
        updateUI();
        initMesas(mesas);
        registerControllers(mesasViewListener);
        setLabelsBackground2(false);

    }

    public void registerControllers(MesasViewListener mesasViewListener) {

        for (MesaView mv : mesasView) {
            mv.registerControllers(mesasViewListener);
        }
    }

    public void setLabelsBackground(String id, boolean state) {

        for(MesaView mv : mesasView) {
            if (mv.getJlIdMesa().getText().toString().equals(id)) {
                mv.setLabelsBackground(state);
            } else {
                mv.setLabelsBackground(false);
            }
        }
    }

    public void setLabelsBackground2(boolean state) {
        for(MesaView mv : mesasView) {
            mv.setLabelsBackground(state);
        }
    }

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
