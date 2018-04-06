package Entry.Model;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DateUpdater implements Runnable{
    private boolean run=false;
    private Timer t = new Timer();
    @Override
    public void run() {
        updateView(new Date());
        if(run){
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    DateUpdater.this.run();
                }
            }, 60000L);
        }
    }

    private void updateView(Date date) {
        //TODO fillme
    }
}
