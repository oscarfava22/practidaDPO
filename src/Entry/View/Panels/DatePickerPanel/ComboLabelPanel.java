package Entry.View.Panels.DatePickerPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public  class ComboLabelPanel<T> extends JPanel{
    private  JComboBox<T> comboBox;

    public ComboLabelPanel(String title){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        comboBox = new JComboBox<>();
        JLabel tit = new JLabel(title);
        this.add(tit);
        this.add(comboBox);
    }

    public T getSelectedItem(){
        return (T)comboBox.getSelectedItem();
    }

    public void setItems(T[] newItems,boolean keepCurrentSelection){
        T selectedItem = (T)comboBox.getSelectedItem();
        comboBox.removeAllItems();
        for(T item:newItems){
            comboBox.addItem(item);
        }
        if(keepCurrentSelection){
            comboBox.setSelectedItem(selectedItem);
        }else {
            comboBox.setSelectedIndex(0);
        }
        getParent().paintAll(getParent().getGraphics());
    }

    public void relateListeners(ItemListener al){
        comboBox.addItemListener(al);
    }
}
