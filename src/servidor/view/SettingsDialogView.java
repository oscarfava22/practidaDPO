package servidor.view;

import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class SettingsDialogView extends JDialog {

    private JPanel jpSettings;
    private JLabel jlSettings;

    public  SettingsDialogView() {

        setLayout(new BorderLayout());

        jpSettings = new JPanel(new BorderLayout());
        jlSettings = new JLabel("Settings");

        jpSettings.add(jlSettings, BorderLayout.CENTER);

        add(jpSettings, BorderLayout.CENTER);
        setResizable(false);
    }

    public void initView(MainViewModel mainViewModel) {

        setTitle(mainViewModel.getSettingsDialogTitle());
        setIconImage(mainViewModel.getTitleBarLogo().getImage());
        setSize(mainViewModel.getSettingsDialogSize());
        setLocationRelativeTo(null);
        setVisible(false);
    }

    public void registerControllers(MouseInputListener settingsDialogViewListener) {
        jlSettings.addMouseListener(settingsDialogViewListener);
    }


}
