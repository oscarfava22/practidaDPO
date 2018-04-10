package Entry.View.Panels.DatePickerPanel;

import Entry.Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Allows to pick a date for the reservation
 */
public class DatePickerPanel extends JPanel {

    /**
     * JButton to finish selecting the date
     */
    private final JButton button;

    /**
     * Panel of the day
     */
    private final ComboLabelPanel<Integer> day;

    /**
     * Panel of the month
     */
    private final ComboLabelPanel<Integer> month;

    /**
     * Panel of the year
     */
    private final ComboLabelPanel<Integer> year;

    /**
     * Panel of the hour
     */
    private final ComboLabelPanel<Integer> hour;

    /**
     * Panel of the minute
     */
    private final ComboLabelPanel<Integer> minute;

    /**
     * Creates a new date picker panel
     */
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

    /**
     * Updates the data set of all jcomboboxes to select the date
     * @param year the new year dataset
     * @param month the new month dataset
     * @param day the new day dataset
     * @param hour the new hour dataset
     * @param minute the new minute dataset
     * @param keepCurrentSelection if the current selection has to change
     */
    public void updateTimes(Integer[] year, Integer[] month, Integer[] day, Integer[] hour, Integer[] minute
                                ,boolean keepCurrentSelection){
        this.year.setItems(year,keepCurrentSelection);
        this.month.setItems(month,keepCurrentSelection);
        this.day.setItems(day,keepCurrentSelection);
        this.hour.setItems(hour,keepCurrentSelection);
        this.minute.setItems(minute,keepCurrentSelection);
    }

    /**
     * Relates the controllers for the datepicker
     * @param reserve ActionListener to finish the reservation
     * @param dateController ItemListener to update years and months
     */
    public void relateControllers(ActionListener reserve, ItemListener dateController) {
        button.addActionListener(reserve);
        year.relateListeners(dateController);
        month.relateListeners(dateController);
        hour.relateListeners(dateController);
    }

    /**
     * Returns the time selected by the picker separated by ':'
     * @return the time currently selected
     */
    public String getSelectedTime() {
        return new StringBuilder().append(year.getSelectedItem()).append(":").append(month.getSelectedItem())
                .append(":").append(day.getSelectedItem()).append(":").append(hour.getSelectedItem())
                .append(":").append(minute.getSelectedItem()).toString();
    }
}
