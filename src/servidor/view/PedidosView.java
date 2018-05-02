package servidor.view;

import servidor.controller.PedidosListListener;
import servidor.model.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PedidosView extends JPanel {

    private JPanel jpPedidos;
    private JScrollPane jspPedidos;
    private JPanel jpMain;

    private String[] columnNames = {"Id", "Title", "Date", "# of Products", "# of Pending Products"};

    private Object[][] pedidos = {{"","","","",""}};

    private JTable jtPedidos;

    public PedidosView() {

        setLayout(new BorderLayout());

        jtPedidos = new JTable(pedidos, columnNames);

        jpPedidos = new JPanel(new BorderLayout());
        jpPedidos.add(jtPedidos, BorderLayout.CENTER);

        jspPedidos = new JScrollPane();
        jspPedidos.getViewport().setView(jpPedidos);
        jspPedidos.setBorder(BorderFactory.createEmptyBorder());

        jpMain = new JPanel(new BorderLayout());
        jpMain.add(jspPedidos, BorderLayout.CENTER);

        add(jtPedidos.getTableHeader(), BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);
    }

    public void initView(LinkedList<Reserva> reservas) {
        Reserva[] reservaArray = new Reserva[reservas.size()];
        reservaArray = reservas.toArray(reservaArray);

        pedidos = new Object[reservaArray.length][];
        for(int i = 0; i < reservaArray.length; i++){
            pedidos[i] = reservaArray[i].toObjectArray();
        }
        //System.out.println(reservas.toString());
        //jtPedidos = null;
        //jpPedidos.removeAll();

        /*Object[] obj2 = reservas.toArray();

        System.out.println(reservas.get(0).toString().split("%").length);

        Object[][] obj;
        obj = new Object[reservas.get(0).toString().split("%").length][reservas.size()];

        for(int i = 0; i < reservas.size(); i++) {

            //obj[i+]
        }


        System.out.println(obj2.length);
        System.out.println(obj2[0]);*/

        jtPedidos.setModel(new DefaultTableModel());
        jtPedidos = new JTable(pedidos, columnNames);
        jpPedidos.removeAll();

        jpPedidos.add(jtPedidos, BorderLayout.CENTER);
        jpPedidos.updateUI();

        add(jtPedidos.getTableHeader(), BorderLayout.NORTH);

    }

    public void registerControllers (PedidosListListener pedidosListListener) {
        jtPedidos.getSelectionModel().addListSelectionListener(pedidosListListener);
    }

    public JTable getJtPedidos () {
        return jtPedidos;
    }

    public int getSelectedRow() {
        return jtPedidos.getSelectedRow();
    }

    public int getSelectedColumn() {
        return jtPedidos.getEditingColumn();
    }
}
