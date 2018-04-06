package Entry.View.Panels.DatePickerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DatePickerPanel extends JPanel {

    public static final String DAY = "Day";

    private Integer[] days;
    private final JButton button;

    public DatePickerPanel(){
        setLayout(new GridLayout(3,1));
        JPanel firstPanel = new JPanel();
        //TODO hacer que empieze el dia de hoy
        days = new Integer[31];
        Integer[] months = new Integer[12];
        Integer[] years = new Integer[10];
        for(int i = 0; i< days.length; i++){
            days[i] = i+1;
        }
        for(int i=0;i<months.length;i++){
            months[i] = i+1;
        }
        for(int i=0;i<years.length;i++){
            years[i] = i+2018;
        }
        firstPanel.add(new ComboLabelPanel<>(DAY, days));
        firstPanel.add(new ComboLabelPanel<>("Month",months));
        firstPanel.add(new ComboLabelPanel<>("Year",years));

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
        secondPanel.add(new ComboLabelPanel<>("Hour",hours));
        secondPanel.add(new ComboLabelPanel<>("Minute",minutes));

        add(secondPanel);

        JPanel thirdPanel = new JPanel(new BorderLayout());
        button = new JButton("Reserve");
        thirdPanel.add(button,BorderLayout.PAGE_END);
        add(thirdPanel);

    }

    public void relateControllers(ActionListener reserve) {
        button.addActionListener(reserve);
    }

}
