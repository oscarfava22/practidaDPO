package servidor.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class PedidosView extends JPanel {

    private JPanel jpPedidos;
    private JScrollPane jspPedidos;
    private JPanel jpMain;

    private String[] columnNames = {"Id", "Title", "Date", "# of Products", "# of Pending Products"};
    private Object[][] pedidos = {{1,"Title 1", "20/04/2018", 20, 15},
            {1,"Title 1", "20/04/2018", 20, 15},
            {1,"Title 1", "20/04/2018", 20, 15},
            {1,"Title 1", "20/04/2018", 20, 15}};
    private JTable jtPedidos;

    private boolean selected = false;

    public PedidosView() {

        setLayout(new BorderLayout());

        jpPedidos = new JPanel(new BorderLayout());

        jtPedidos = new JTable(pedidos, columnNames);

        //jtPedidos.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = jtPedidos.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = jtPedidos.getSelectedRows();
                int[] selectedColumns = jtPedidos.getSelectedColumns();

                /*for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) jtPedidos.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);*/

                selected = !selected;

                if(selected) {
                    System.out.println(jtPedidos.getSelectedRow());
                }
            }

        });



        jpPedidos.add(jtPedidos, BorderLayout.CENTER);

        jspPedidos = new JScrollPane();
        jspPedidos.getViewport().setView(jpPedidos);
        jspPedidos.setBorder(BorderFactory.createEmptyBorder());

        jpMain = new JPanel(new BorderLayout());

        jpMain.add(jspPedidos, BorderLayout.CENTER);

        add(jtPedidos.getTableHeader(), BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);

    }
}
