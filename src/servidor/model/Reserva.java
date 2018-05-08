package servidor.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva implements Serializable {

    private long id;
    private String name;
    private Date date;
    private int amount;

    public Reserva() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
    }

    public Reserva(long id, String name, Date date, int amount) {
        setReserva(id, name, date, amount);
    }

    public void setReserva(long id, String name, Date date, int amount) {
        setId(id);
        setName(name);
        setDate(date);
        setAmount(amount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return (id + "%" + name + "%" + date + "%" + amount);
    }

    //FIXME ponme donde deberia ir
    public Object[] toObjectArray(){
        Object[] data = new Object[5];
        data[0] = id;
        data[1] = name;
        data[2] = date;
        data[3] = 25;
        data[4] = 15;
        return data;
    }
}
