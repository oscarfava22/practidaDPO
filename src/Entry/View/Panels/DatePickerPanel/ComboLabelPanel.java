package Entry.View.Panels.DatePickerPanel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Creates a label-comboBox pair
 * @param <T> the type of the combo box
 */
class ComboLabelPanel<T> extends JPanel{

    /**
     * Combobox of the pair
     */
    private  JComboBox<T> comboBox;

    /**
     * Creates the pair with a given title
     * @param title the title of the pair
     */
    ComboLabelPanel(String title){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        comboBox = new JComboBox<>();
        JLabel tit = new JLabel(title);
        this.add(tit);
        this.add(comboBox);
    }

    /**
     * Returns the currently selected item
     * @return the currently selected item
     */
    T getSelectedItem(){
        return (T)comboBox.getSelectedItem();
    }

    /**
     * Sets all the new items of the comboBox
     * @param newItems the new items of the combo box
     * @param keepCurrentSelection if the selected item has to be mantined
     */
    void setItems(T[] newItems,boolean keepCurrentSelection){
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

    /**
     * Relates the Item listenr to the JComboBox
     * @param al ItemListener for the combo box
     */
    void relateListeners(ItemListener al){
        comboBox.addItemListener(al);
    }
}
