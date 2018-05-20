package servidor.view;

import servidor.controller.PedidosListListener;
import servidor.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

/**
 * Panel que contiene la informacion visual de todos los pedidos disponibles en el programa.
 * Este panel utiliza un JTable para representar la informacion referente a los pedidos.
 */
public class PedidosView extends JPanel {

    private JPanel jpPedidos;
    private JScrollPane jspPedidos;
    private JPanel jpMain;

    private String[] columnNames = {"Id", "Reservation Name", "Date", "Nº of Persons" , "# of Products", "# of Pending Products"};

    private Object[][] pedidos = {{"","","","","",""}};

    private JTable jtPedidos;

    /**
     * Contructor que inicializa los componentes necesario.
     */
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

    /**
     * Permite inicializar la vista de pedidos con la lista de pedidos que se recibe como parametro.
     * @param pedidosList lista que se recibe de la cual extraer informacion y mostrarla por pantalla.
     */
    public void initView(LinkedList<Pedido> pedidosList) {

        LinkedList<Pedido> temp = new LinkedList<>();

        for (Pedido pedido : pedidosList) {
            if (pedido.getReserva().getState() == 2) {
                temp.add(pedido);
            }
        }

        Pedido[] pedidosArray = new Pedido[temp.size()];
        pedidosArray = temp.toArray(pedidosArray);

        pedidos = new Object[pedidosArray.length][];
        for(int i = 0; i < pedidosArray.length; i++){
            if (pedidosArray[i].getReserva().getState() == 2) {
                pedidos[i] = pedidosArray[i].toObjectArray();
            }
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

        remove(jtPedidos.getTableHeader());
        add(jtPedidos.getTableHeader(), BorderLayout.NORTH);
        updateUI();
    }

    /**
     * Permite registrar todos los componentes relevantes al controlador que se reciba como parametro.
     * @param pedidosListListener
     */
    public void registerControllers (PedidosListListener pedidosListListener) {
        jtPedidos.getSelectionModel().removeListSelectionListener(pedidosListListener);
        jtPedidos.getSelectionModel().addListSelectionListener(pedidosListListener);
        jtPedidos.updateUI();
        jpPedidos.updateUI();
    }

    /**
     * Permite obtener el JTable relativo a los pedidos.
     * @return JTable de los pedidos.
     */
    public JTable getJtPedidos () {
        return jtPedidos;
    }

    /**
     * Permite obtener el valor numerico de la fila seleccionada en el JTable de los pedidos.
     * @return el indice de la fila seleccionada.
     */
    public int getSelectedRow() {
        return jtPedidos.getSelectedRow();
    }
}
