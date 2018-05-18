package reserva.controller;

import reserva.view.AutenticacioView;
import servidor.Main;
import servidor.model.Database.BBDDManager;
import servidor.model.ReservasManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacioListener implements ActionListener{

    //Atributos
    private AutenticacioView view;
    private ReservaListener controller;

    /**
     * Constructor con parámetros
     * @param view: AutenticacioView
     * @param controller: ReservaListener
     */
    public AutenticacioListener (AutenticacioView view, ReservaListener controller) {
        this.view = view;
        this.controller = controller;
    }

    /**
     * Función activa cuando se aprieta el botón para pedir una reserva.
     * Comprueba que los campos sean correctos
     * @param e
     */
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

    /**
     * Comprueba si los campos introducidos son correctos:
     *  - nombre no vacío
     *  - password no vacío
     *  - nombre + password existen en la base de datos
     * @return true si se cumplen todos los requisitos anteriores
     * @return false si no
     */
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
                return ReservasManager.isInBbdd(nombre, password);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }


}
