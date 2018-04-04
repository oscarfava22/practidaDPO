package view;

import model.MainViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class LoginDialogView extends JDialog {

    private JPanel jpMain;

    private JPanel jpLogin;
    private JPanel jpUsername;
    private JPanel jpPassword;

    private JLabel jlUsername;
    private JTextField jtfUsername;
    private JLabel jlPassword;
    private JTextField jtfPassword;
    private JPasswordField jpfPassword;


    private JButton jbSend;

    public LoginDialogView() {

        setLayout(new BorderLayout());

        jpMain = new JPanel(new BorderLayout());

        jpLogin = new JPanel(new GridLayout(2,1));
        jpUsername = new JPanel(new BorderLayout());
        jpPassword = new JPanel(new BorderLayout());

        jlUsername = new JLabel("Username:");
        jtfUsername = new JTextField();
        jtfUsername.setPreferredSize(new Dimension(300,30));
        jlPassword = new JLabel("Password:");
        jtfPassword = new JTextField();
        jtfPassword.setPreferredSize(new Dimension(300,30));

        jpfPassword = new JPasswordField();
        jpfPassword.setPreferredSize(new Dimension(300,30));

        jpUsername.add(jlUsername, BorderLayout.CENTER);
        jpUsername.add(jtfUsername, BorderLayout.SOUTH);
        jpUsername.setBorder(new EmptyBorder(10,10,10,10));

        jpPassword.add(jlPassword, BorderLayout.CENTER);
        jpPassword.add(jpfPassword, BorderLayout.SOUTH);
        jpPassword.setBorder(new EmptyBorder(10,10,10,10));

        jpLogin.add(jpUsername);
        jpLogin.add(jpPassword);

        jbSend = new JButton("Send");
        jbSend.setFocusPainted(false);

        jpMain.add(jpLogin, BorderLayout.CENTER);
        jpMain.add(jbSend, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel jl = new JLabel(new ImageIcon("data/MySQL_logo.png"));
        jl.setBorder(new EmptyBorder(10,10,10,10));
        add(jl, BorderLayout.NORTH);
        add(jpMain, BorderLayout.CENTER);

        setResizable(false);
    }

    public void initView(MainViewModel mainViewModel) {

        setTitle(mainViewModel.getLoginDialogTitle());
        setIconImage(mainViewModel.getTitleBarLogo().getImage());
        setSize(mainViewModel.getLoginDialogSize());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void registerControllers(MouseInputListener loginDialogViewListener) {
        jbSend.addMouseListener(loginDialogViewListener);
        jbSend.setActionCommand("Send");
    }

    public String getJtfUsernameText() {
        return jtfUsername.getText();
    }

    public String getJtfPasswordText() {
        return jtfPassword.getText();
    }

    public char[] getJpfPassword(){
        return jpfPassword.getPassword();
    }

}
