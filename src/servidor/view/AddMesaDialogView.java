package servidor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddMesaDialogView extends JDialog{

    public static final String POSITIVE_TAG = "Positive";
    public static final String NEGATIVE_TAG = "Negative";

    private JButton jbPositive;
    private JButton jbNegative;
    private JTextField jtNumCom;

    public AddMesaDialogView(JFrame parent){
        super(parent, "Añadir mesa");

        JPanel jpMessage = new JPanel(new BorderLayout());

        JLabel jlNmCom = new JLabel("Cuántos comensales?");
        jpMessage.add(jlNmCom, BorderLayout.LINE_START);
        jtNumCom = new JTextField();
        jpMessage.add(jtNumCom, BorderLayout.CENTER);

        JPanel jpButtons = new JPanel(new BorderLayout());
        jbPositive = new JButton("Añadir");
        jbNegative = new JButton("Cancelar");
        jpButtons.add(jbNegative, BorderLayout.LINE_START);
        jpButtons.add(jbPositive, BorderLayout.LINE_END);
        jpMessage.add(jpButtons,BorderLayout.PAGE_END);


        setContentPane(jpMessage);

        //posicion en la pantalla (centrado)
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initDialog(){
        setActionCommands();
        setVisible(true);
    }

    public void setActionCommands(){
        jbPositive.setActionCommand(POSITIVE_TAG);
        jbNegative.setActionCommand(NEGATIVE_TAG);
    }

    public void registerControllers(ActionListener listener){
        jbPositive.addActionListener(listener);
        jbNegative.addActionListener(listener);
    }


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
