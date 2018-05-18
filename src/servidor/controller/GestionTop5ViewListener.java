package servidor.controller;

import servidor.Main;
import servidor.model.Database.BBDDManager;
import servidor.view.GestionTop5View;
import servidor.view.MainView;
import servidor.view.Top5View;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionTop5ViewListener {

    private MainView mainView;
    private BBDDManager bbddManager;
    private GestionTop5View top5View;

    /**
     * Constructor de top5Listener
     * @param mainView vista general
     * @param bbddManager controlar la bbdd
     * @param top5View grafica del top 5
     */
    public GestionTop5ViewListener(MainView mainView, BBDDManager bbddManager, GestionTop5View top5View) {
        this.mainView = mainView;
        this.bbddManager = BBDDManager.getInstance(Main.BBDD);
        this.top5View = top5View;
    }

    /**
     * Actualizar la vista del top 5. Se recalculan los platos con mayor numero de pedidos.
     */
    public void calculaTop5() {
        this.bbddManager.connect();
        String query = "SELECT * FROM Plato ORDER BY contador DESC LIMIT 5";
        ResultSet rs = BBDDManager.getInstance(Main.BBDD).readQuery(query);
        int [] values =  new int[5];
        String[] labels = new String[5];
        int i = 0;
        try {
            if(rs.next()){
                do{
                    values[i] = rs.getInt("contador");
                    labels[i] = rs.getString("nombre");
                    i++;
                } while(rs.next());
                top5View.getView().setLabels(labels);
                top5View.getView().setValues(values);
                top5View.getView().modificaBC();
                bbddManager.disconnect();
            }
            else {
                top5View.showError("Error, no hay platos en la base de datos");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
