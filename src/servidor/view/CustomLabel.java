package servidor.view;

import javax.swing.*;

public class CustomLabel extends JLabel {

    /**
     * Atributos de la clase
     */
    private String customId;

    /**
     * Constructor sin parámetros
     */
    public CustomLabel() {

    }

    /**
     * Constructor con parámetros
     * @param id: id de la label
     */
    public CustomLabel(String id){
        setId(id);
    }

    /**
     * Getters & Setters
     */
    public String getId() {
        return customId;
    }
    public void setId(String index) {
        this.customId = index;
    }
}
