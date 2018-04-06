package Entry.View.Panels.DatePickerPanel;

import javax.swing.*;

public  class ComboLabelPanel<T> extends JPanel{
    private  JComboBox<T> comboBox;

    public ComboLabelPanel(String title,T[] data){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        comboBox = new JComboBox<>(data);
        JLabel tit = new JLabel(title);
        this.add(tit);
        this.add(comboBox);
    }

    public T getSelectedItem(){
        return (T)comboBox.getSelectedItem();
    }
}
