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

        jtPedidos = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        DefaultTableModel model = new DefaultTableModel();

        model.setDataVector(pedidos, columnNames);

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

        jtPedidos = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        for (int i = 0; i < jtPedidos.getRowCount(); i++) {
            for (int j = 0; j < jtPedidos.getColumnCount(); j++) {
                jtPedidos.isCellEditable(i,j);
            }
        }

        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(pedidos, columnNames);
        jtPedidos.setModel(model);


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
