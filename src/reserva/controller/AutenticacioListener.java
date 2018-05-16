package reserva.controller;

import reserva.view.AutenticacioView;
import servidor.Main;
import servidor.model.Database.BBDDManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacioListener implements ActionListener{

    private AutenticacioView view;
    private ReservaListener controller;

    public AutenticacioListener (AutenticacioView view, ReservaListener controller) {

        this.view = view;
        this.controller = controller;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case AutenticacioView.ACCESS:

                boolean correct = comprovaCampsCorrectes();
                if (correct){
                    controller.setViewVisible();
                    view.setVisible(false);
                }

                break;
        }
    }

    public boolean comprovaCampsCorrectes() {
        String nombre = view.getName();
        String password = view.getPassword();

        if (nombre.isEmpty()){
            if (password.isEmpty()){
                JOptionPane.showMessageDialog(null, "Los campos 'nombre' y 'password' están vacíos",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "El campo 'nombre' está vacío",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }else if (password.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo 'password' está vacío",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try {
                return isInBbdd(nombre, password);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public boolean isInBbdd(String nombre, String password) throws SQLException {
        //TODO: Corregir error aqui
        BBDDManager bbdd = BBDDManager.getInstance(Main.BBDD);
        bbdd.connect();
        String query = new StringBuilder()
                .append("SELECT * FROM Cliente AS c WHERE c.password LIKE '")
                .append(password)
                .append("' AND c.nombre LIKE '")
                .append(nombre)
                .append("';")
                .toString();

        ResultSet rs = bbdd.readQuery(query);

        if (!rs.next()){
            bbdd.disconnect();
            JOptionPane.showMessageDialog(null, "Algún campo es incorrecto",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            bbdd.disconnect();
            return true;
        }
    }
}
