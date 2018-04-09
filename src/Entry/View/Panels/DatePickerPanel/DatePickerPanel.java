package Entry.View.Panels.DatePickerPanel;

import Entry.Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatePickerPanel extends JPanel {

    private final JButton button;
    private final ComboLabelPanel<Integer> day;
    private final ComboLabelPanel<Integer> month;
    private final ComboLabelPanel<Integer> year;
    private final ComboLabelPanel<Integer> hour;
    private final ComboLabelPanel<Integer> minute;

    public DatePickerPanel(){
        setLayout(new GridLayout(3,1));
        JPanel firstPanel = new JPanel();
        day = new ComboLabelPanel<>("Day");
        firstPanel.add(day);
        month = new ComboLabelPanel<>("Month");
        firstPanel.add(month);
        year = new ComboLabelPanel<>("Year");
        firstPanel.add(year);

        add(firstPanel);

        JPanel secondPanel = new JPanel();
        Integer[] hours = new Integer[24];
        Integer[] minutes = new Integer[60];
        for(int i=0;i<hours.length;i++){
            hours[i] = i;
        }
        for(int i=0;i<minutes.length;i++){
            minutes[i] = i;
        }
        hour = new ComboLabelPanel<>("Hour");
        secondPanel.add(hour);
        minute = new ComboLabelPanel<>("Minute");
        secondPanel.add(minute);

        add(secondPanel);

        JPanel thirdPanel = new JPanel(new BorderLayout());
        button = new JButton("Reserve");
        thirdPanel.add(button,BorderLayout.PAGE_END);
        add(thirdPanel);

    }

    public void updateTimes(Integer[] year, Integer[] month, Integer[] day, Integer[] hour, Integer[] minute
                                ,boolean keepCurrentSelection){
        this.year.setItems(year,keepCurrentSelection);
        this.month.setItems(month,keepCurrentSelection);
        this.day.setItems(day,keepCurrentSelection);
        this.hour.setItems(hour,keepCurrentSelection);
        this.minute.setItems(minute,keepCurrentSelection);
    }

    public void relateControllers(ActionListener reserve, ActionListener dateController) {
        button.addActionListener(reserve);
        year.relateListeners(dateController);
        month.relateListeners(dateController);
        hour.relateListeners(dateController);
    }

    public String getSelectedTime() {
        return new StringBuilder().append(year.getSelectedItem()).append(":").append(month.getSelectedItem())
                .append(":").append(day.getSelectedItem()).append(":").append(hour.getSelectedItem())
                .append(":").append(minute.getSelectedItem()).toString();
    }
}
