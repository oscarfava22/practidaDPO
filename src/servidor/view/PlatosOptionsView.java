package servidor.view;

import servidor.controller.PlatosOptionsViewListener;
import servidor.model.Plato;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PlatosOptionsView extends JPanel {

    private JScrollPane jspMain;
    private JPanel jpMain;

    private JPanel jpPlatoOptions;
    private JButton jbAddPlato;

    private JPanel jpId;
    private JLabel jlId;
    private JTextField jtfId;
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


    public PlatosOptionsView() {

        setLayout(new BorderLayout());

        jpMain = new JPanel(new BorderLayout());

        jpPlatoOptions = new JPanel();
        jpPlatoOptions.setLayout(new BoxLayout(jpPlatoOptions, BoxLayout.Y_AXIS));

        jlId = new JLabel("Id:");
        jtfId = new JTextField();
        jtfId.setPreferredSize(new Dimension(100,30));
        jtfId.setEditable(false);
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

    public void setOptionsText(Plato plato) {

        jtfId.setText(plato.getId());
        jtfTitle.setText(plato.getTitle());
        jtfPrice.setText(plato.getPrice());
        jtfUnits.setText(plato.getUnits());
    }

    public void resetTextFields() {
        jtfId.setText(null);
        jtfTitle.setText(null);
        jtfPrice.setText(null);
        jtfUnits.setText(null);
    }

    public void setEditState(boolean state) {

        editState = state;
        jtfId.setEditable(false);
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

    public void setAddProductState(boolean state) {

        addState = state;
        resetTextFields();
        jtfId.setEditable(false);
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

    public void registerControllers(PlatosOptionsViewListener platosOptionsViewListener) {

        jbEdit.addMouseListener(platosOptionsViewListener);
        jbUpdate.addMouseListener(platosOptionsViewListener);
        jbCancel.addMouseListener(platosOptionsViewListener);
        jbDelete.addMouseListener(platosOptionsViewListener);
        jbAddPlato.addMouseListener(platosOptionsViewListener);
    }

    public boolean getEditState() {
        return editState;
    }

    public boolean getAddState() {
        return addState;
    }

    public String getIdText() { return jtfId.getText(); }

    public String getTitleText() {
        return jtfTitle.getText();
    }

    public String getPriceText() {
        return jtfPrice.getText();
    }

    public String getUnitsText() {
        return jtfUnits.getText();
    }
}
