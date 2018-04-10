package Entry.Controller;

import Entry.Constants.Constants;
import Entry.View.Entry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

/**
 * Controller for when the date of the year changes to mantain consistency in the possible dates
 */
public class DateController implements ItemListener{

    /**
     * The view to update
     */
    private Entry view;

    /**
     * A trigger because there was some asynchronous behaviour that synchronize wasn't protecting, and creating an infinite loop
     */
    private boolean toggle;

    /**
     * Creates a new date controller from view, putting the toggle at true
     * @param view the view
     */
    public DateController(Entry view) {
        this.view = view;
        toggle=true;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        //When updateView was used to modify and then select the item in the combo boxes
        //this event was triggered so this method was called again and again leading to error
        //so this toggles protects from calling this event multiple times in the same event
        if(toggle){
            toggle=false;
            updateView(true);
            toggle=true;
        }
    }

    /**
     * Updates the combo boxes from the view for them to have coherent data
     * @param keepCurrentSelection if the current selection has to be maintained as the same or not
     */
    public void updateView(boolean keepCurrentSelection){
        String[] data;
        //If current or selected date is needed
        if(keepCurrentSelection) {
            data = view.getSelectedDate().split(":");
        }else{
            data = Constants.DATE_FORMAT.format(new Date()).split(":");

        }
        //Initialize int data and arrays
        Integer[] year = new Integer[10];
        Integer[] month = new Integer[12];
        Integer[] hour = new Integer[24];
        Integer[] minute = new Integer[60];
        Integer[] numData = new Integer[data.length];
        for(int i=0;i<data.length;i++){
            numData[i] = Integer.parseInt(data[i]);
        }
        Integer[] day = getDay(numData);

        //Fill all the required data
        fillData(year, month, hour, minute, numData, day);
        //Update view
        view.updateTimes(year,month,day,hour,minute,keepCurrentSelection);
    }

    /**
     * Fills all the listeners with the corresponding data
     * @param year the year array
     * @param month the month array
     * @param hour the hour array
     * @param minute the minute array
     * @param numData the array with the base data
     * @param day the day array
     */
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

    /**
     * Creates an array with the appropriate duration depending on the year or the month
     * @param numData the data of the currently selected date
     * @return the correct lenghted array
     */
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
