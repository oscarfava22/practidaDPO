package servidor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddMesaDialogView extends JDialog{

    /**
     * COnstantes de la clase
     */
    public static final String POSITIVE_TAG = "Positive";
    public static final String NEGATIVE_TAG = "Negative";

    /**
     * Atributos de la clase
     * jbPositive (JButton) = Botón para añadir la mesa
     * jbNegative (JButton) = Botón para cancelar la operación
     * jtNumCom (JTextField) = Campo de texto para introducir el número de comensales de la mesa que será creada
     */
    private JButton jbPositive;
    private JButton jbNegative;
    private JTextField jtNumCom;

    /**
     * Dialog para añadir una nueva mesa.
     * Éste pide el número de comensales de los que será la mesa y obtiene un id de mesa generado
     * por SerialMesasGenerator
     * @param parent: JFrame
     */
    public AddMesaDialogView(JFrame parent){
        super(parent, "Añadir mesa");

        JPanel jpMessage = new JPanel(new BorderLayout());

        JLabel jlNmCom = new JLabel("Cuántos comensales?");
        jpMessage.add(jlNmCom, BorderLayout.LINE_START);
        jtNumCom = new JTextField();
        jpMessage.add(jtNumCom, BorderLayout.CENTER);

        //Añadir los botones a la pantalla
        JPanel jpButtons = new JPanel(new BorderLayout());
        jbPositive = new JButton("Añadir");
        jbNegative = new JButton("Cancelar");
        jpButtons.add(jbNegative, BorderLayout.LINE_START);
        jpButtons.add(jbPositive, BorderLayout.LINE_END);
        jpMessage.add(jpButtons,BorderLayout.PAGE_END);


        setContentPane(jpMessage);
        //posicion en la pantalla (centrado)
        setSize(new Dimension(400,100));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Procedimiento que inicializa la pantalla y la hace visible
     */
    public void initDialog(){
        setActionCommands();
        setVisible(true);
    }

    /**
     * Añade los ActionCommands a los botones
     */
    public void setActionCommands(){
        jbPositive.setActionCommand(POSITIVE_TAG);
        jbNegative.setActionCommand(NEGATIVE_TAG);
    }

    /**
     * Procedimiento que registra los controladores a los botones
     * @param listener: ActionListener
     */
    public void registerControllers(ActionListener listener){
        jbPositive.addActionListener(listener);
        jbNegative.addActionListener(listener);
    }

    /**
     * @return int: numero de comensales de la mesa
     */
    public int getNumComensales(){
        String numComensalesString = jtNumCom.getText().toString();
        return Integer.parseInt(numComensalesString);
    }

    /**
     * @return true si el campo de texto introducido es del tipo entero y es mayor a 0
     * @return false si el campo de texto contiene carácteres distintos a números, es decir que no es
     *  puramente un entero
     */
    public boolean isNumComensalesCorrect(){
        boolean correct = false;

        String numComensalesString = jtNumCom.getText().toString();

        int numCom;
        try {
            numCom = Integer.parseInt(numComensalesString);
        }catch (NumberFormatException nfe){
            numCom = 0;
        }
        if (numCom > 0){
            correct = true;
        }
        return correct;
    }

}
