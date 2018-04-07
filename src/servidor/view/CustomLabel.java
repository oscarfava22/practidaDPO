package servidor.view;

import javax.swing.*;

public class CustomLabel extends JLabel {

    private String customId;

    public CustomLabel() {

    }

    public CustomLabel(String id){
        setId(id);
    }

    public String getId() {
        return customId;
    }

    public void setId(String index) {
        this.customId = index;
    }
}
