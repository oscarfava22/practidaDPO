package model;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginModel {

    private final static String username = "B1";
    private final static String password = "B12018";
    //private final char[] correctPassword = {'B', '1', '2', '0', '1', '8'};
    private boolean loginState = false;

    public LoginModel() {

    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public boolean verifyLogin(String user, char[] pass) {

        boolean ok;
        int error = 0;

        if (!verifyUsername(user)) {
            error = 1;
            if(!verifyPassword2(pass)){
                error = 2;
            }
        } else {
            if (!verifyPassword2(pass)) {
                error = 3;
            }
        }

        switch (error) {
            case 0:
                loginState = true;
                System.out.println("Login Correct");
                JOptionPane.showMessageDialog(
                        new Frame(),
                        "Login Correct",
                        "Login Verification",
                        JOptionPane.INFORMATION_MESSAGE);

                break;
            case 1:
                loginState = false;
                JOptionPane.showMessageDialog(
                        new Frame(),
                        "Invalid Username",
                        "Login Verification",
                        JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                loginState = false;
                JOptionPane.showMessageDialog(
                        new Frame(),
                        "Invalid Username and Password",
                        "Login Verification",
                        JOptionPane.ERROR_MESSAGE);

                break;
            case 3:
                loginState = false;
                JOptionPane.showMessageDialog(
                        new Frame(),
                        "Invalid Password",
                        "Login Verification",
                        JOptionPane.ERROR_MESSAGE);
                break;

        }

        return error == 0;
    }

    private boolean verifyUsername(String user) {
        return username.equals(user);
    }

    private boolean verifyPassword(String pass) {
        return password.equals(pass);
    }

    public boolean verifyPassword2(char[] input) {

        char[] correctPassword = {'B', '1', '2', '0', '1', '8'};
        boolean isCorrect = true;

        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }

        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }



    public boolean getLoginState() {
        return loginState;
    }
}
