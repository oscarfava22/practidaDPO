package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class DateController implements ActionListener{
    private Entry view;
    private boolean toggle;

    public DateController(Entry view) {
        this.view = view;
        toggle=true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(toggle){
            toggle=false;
            updateView(true);
            toggle=true;
        }

    }

    public void updateView(boolean keepCurrentSelection){
        String[] data;
        if(keepCurrentSelection) {
            data = view.getSelectedDate().split(":");
        }else{
            data = Constants.DATE_FORMAT.format(new Date()).split(":");

        }
        Integer[] year = new Integer[10];
        Integer[] month = new Integer[12];
        Integer[] hour = new Integer[24];
        Integer[] minute = new Integer[60];
        Integer[] numData = new Integer[data.length];
        for(int i=0;i<data.length;i++){
            numData[i] = Integer.parseInt(data[i]);
        }
        Integer[] day = getDay(numData);

        fillData(year, month, hour, minute, numData, day);
        view.updateTimes(year,month,day,hour,minute,keepCurrentSelection);
    }

    private void fillData(Integer[] year, Integer[] month, Integer[] hour, Integer[] minute, Integer[] numData, Integer[] day) {
        for(int i=0;i<year.length;i++){
            year[i] = numData[0] + i;
        }

        for(int i=0;i<month.length;i++){
            month[i] = ((numData[1]+i-1)%month.length)+1;
        }

        for(int i=0;i<day.length;i++){
            day[i] = ((numData[2]+i-1)%day.length)+1;
        }

        for(int i=0;i<hour.length;i++){
            hour[i] = (numData[3]+i)%hour.length;
        }

        for(int i=0;i<minute.length;i++){
            minute[i] = (numData[4]+i)%minute.length;
        }
    }

    private Integer[] getDay(Integer[] numData) {
        Integer[] day;
        switch (numData[1]){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = new Integer[31];
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = new Integer[30];
                break;
            default:
                day = new Integer[numData[0]%4==0 && (numData[0]%100!=0||numData[0]%400==0)?
                        29:28];
                break;
        }
        return day;
    }

}
