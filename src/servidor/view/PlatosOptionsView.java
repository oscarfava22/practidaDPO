package servidor.view;

import servidor.model.Plato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.*;

/**
 *
 */
public class PlatosOptionsView extends JPanel {

    private JScrollPane jspMain;
    private JPanel jpMain;

    private JPanel jpPlatoOptions;
    private JButton jbAddPlato;

    private JPanel jpId;
    private JLabel jlId;
    private JTextField jtfId;

    private JPanel jpType;
    private JLabel jlType;
    private JComboBox jcbType;
    private String[] productsTypes = { "Entrante", "Plato Principal", "Postre", "Bebidas" };

    private JPanel jpTitle;
    private JLabel jlTitle;
    private JTextField jtfTitle;
    private JPanel jpPrice;
    private JLabel jlPrice;
    private JTextField jtfPrice;
    private JPanel jpUnits;
    private JLabel jlUnits;
    private JTextField jtfUnits;

    private JPanel jpButtons;
    private JButton jbEdit;
    private JButton jbUpdate;
    private JButton jbCancel;
    private JButton jbDelete;

    private boolean editState = false;
    private boolean addState = false;

    private Border compounBorder =  BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(10,10,10,10));

    /**
     *
     */
    public PlatosOptionsView() {

        setLayout(new BorderLayout());

        jpMain = new JPanel(new BorderLayout());

        jpPlatoOptions = new JPanel();
        jpPlatoOptions.setLayout(new BoxLayout(jpPlatoOptions, BoxLayout.Y_AXIS));

        jlId = new JLabel("Id:");
        jtfId = new JTextField();
        jtfId.setPreferredSize(new Dimension(100,30));
        jtfId.setEditable(false);
        jlType = new JLabel("Type:");
        jcbType = new JComboBox(productsTypes);
        jcbType.setEditable(editState);
        jcbType.setEnabled(editState);
        jcbType.setSelectedItem(null);
        jlTitle = new JLabel("Title:");
        jtfTitle = new JTextField();
        jtfTitle.setPreferredSize(new Dimension(100,30));
        jtfTitle.setEditable(false);
        jlPrice =  new JLabel("Price:");
        jtfPrice = new JTextField();
        jtfPrice.setPreferredSize(new Dimension(100,30));
        jtfPrice.setEditable(false);
        jlUnits = new JLabel("Units:");
        jtfUnits = new JTextField();
        jtfUnits.setPreferredSize(new Dimension(100,30));
        jtfUnits.setEditable(false);

        jbEdit = new JButton("Edit");
        jbEdit.setEnabled(false);
        jbEdit.setFocusPainted(false);
        jbUpdate = new JButton("Update");
        jbUpdate.setEnabled(false);
        jbUpdate.setFocusPainted(false);
        jbCancel = new JButton("Cancel");
        jbCancel.setFocusPainted(false);
        jbCancel.setEnabled(false);
        jbDelete = new JButton("Delete");
        jbDelete.setFocusPainted(false);
        jbDelete.setEnabled(false);

        jpId = new JPanel(new GridLayout(1,2,10,10));
        jpId.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        jpType = new JPanel(new GridLayout(1,2,10,10));
        jpType.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        jpTitle = new JPanel(new GridLayout(1,2,10,10));
        jpTitle.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        jpPrice = new JPanel(new GridLayout(1,2,10,10));
        jpPrice.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        jpUnits = new JPanel(new GridLayout(1,2,10,10));
        jpUnits.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        jpButtons = new JPanel(new GridLayout(2,2,10,10));
        jpButtons.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        jpId.add(jlId);
        jpId.add(jtfId);
        jpType.add(jlType);
        jpType.add(jcbType);
        jpTitle.add(jlTitle);
        jpTitle.add(jtfTitle);
        jpPrice.add(jlPrice);
        jpPrice.add(jtfPrice);
        jpUnits.add(jlUnits);
        jpUnits.add(jtfUnits);

        jpButtons.add(jbEdit);
        jpButtons.add(jbUpdate);
        jpButtons.add(jbCancel);
        jpButtons.add(jbDelete);

        jpPlatoOptions.add(jpId);
        jpPlatoOptions.add(jpType);
        jpPlatoOptions.add(jpTitle);
        jpPlatoOptions.add(jpPrice);
        jpPlatoOptions.add(jpUnits);
        jpPlatoOptions.add(jpButtons);

        jbAddPlato = new JButton("Add Product");
        jbAddPlato.setFocusPainted(false);

        jpMain.add(jpPlatoOptions, BorderLayout.NORTH);
        jpMain.add(jbAddPlato, BorderLayout.SOUTH);
        jpMain.setBorder(compounBorder);

        jspMain = new JScrollPane();
        jspMain.getViewport().setView(jpMain);
        jspMain.setBorder(BorderFactory.createEmptyBorder());

        add(jspMain, BorderLayout.CENTER);
    }

    /**
     *
     * @param gestionCartaViewListener
     */
    public void registerControllers(MouseInputListener gestionCartaViewListener) {
        jbEdit.addMouseListener(gestionCartaViewListener);
        jbUpdate.addMouseListener(gestionCartaViewListener);
        jbCancel.addMouseListener(gestionCartaViewListener);
        jbDelete.addMouseListener(gestionCartaViewListener);
        jbAddPlato.addMouseListener(gestionCartaViewListener);
    }

    /**
     *
     * @return
     */
    public String getIdText() { return jtfId.getText(); }

    /**
     *
     * @return
     */
    public String getType() {
        return String.valueOf(jcbType.getSelectedIndex());
    }

    /**
     *
     * @return
     */
    public String getTitleText() {
        return jtfTitle.getText();
    }

    /**
     *
     * @return
     */
    public String getPriceText() {
        return jtfPrice.getText();
    }

    /**
     *
     * @return
     */
    public String getUnitsText() {
        return jtfUnits.getText();
    }

    /**
     *
     * @param plato
     */
    public void setTextFields(Plato plato) {
        jtfId.setText(String.valueOf(plato.getId()));
        jcbType.setSelectedIndex(Integer.parseInt(plato.getType()));
        jtfTitle.setText(plato.getTitle());
        jtfPrice.setText(String.valueOf(plato.getPrice()));
        jtfUnits.setText(String.valueOf(plato.getUnits()));
    }

    /**
     *
     */
    public void resetTextFields() {
        jtfId.setText(null);
        jcbType.setSelectedItem(null);
        jtfTitle.setText(null);
        jtfPrice.setText(null);
        jtfUnits.setText(null);
    }

    /**
     *
     * @return
     */
    public boolean getEditState() {
        return editState;
    }

    /**
     *
     * @param state
     */
    public void setEditState(boolean state) {

        editState = state;
        jtfId.setEditable(false);
        jcbType.setEnabled(editState);
        jtfTitle.setEditable(editState);
        jtfPrice.setEditable(editState);
        jtfUnits.setEditable(editState);
        jbEdit.setEnabled(!editState);
        jbUpdate.setText("Update");
        jbUpdate.setEnabled(editState);
        jbCancel.setEnabled(editState);
        jbDelete.setEnabled(editState);
        jbAddPlato.setEnabled(!editState);
    }

    /**
     *
     * @return
     */
    public boolean getAddState() {
        return addState;
    }

    /**
     * 
     * @param state
     */
    public void setAddProductState(boolean state) {

        addState = state;
        resetTextFields();

        jtfId.setEditable(false);
        jcbType.setEnabled(addState);

        if(addState) {
            jcbType.setSelectedIndex(0);
        } else {
            jcbType.setSelectedItem(null);
        }

        jcbType.setEditable(addState);
        jtfTitle.setEditable(addState);
        jtfPrice.setEditable(addState);
        jtfUnits.setEditable(addState);
        jbEdit.setEnabled(editState);

        if(addState) {
            jbUpdate.setText("Create");
        } else {
            jbUpdate.setText("Update");
        }

        jbUpdate.setEnabled(addState);
        jbCancel.setEnabled(addState);
        jbDelete.setEnabled(editState);
        jbAddPlato.setEnabled(!addState);
    }
}
