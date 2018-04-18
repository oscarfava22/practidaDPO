package servidor.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva implements Serializable {

    private long id;
    private String title;
    private String date;

    public Reserva() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return (id + "%" + title + "%" + date);
    }

    //FIXME ponme donde deberia ir
    public Object[] toObjectArray(){
        Object[] data = new Object[];
        data[0] = id;
        data[1] = title;
        data[2] = date;
        return data;
    }
}
