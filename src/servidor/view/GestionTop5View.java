package servidor.view;

import servidor.model.MainViewModel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GestionTop5View extends JPanel {

    private JLabel jlTitle;

    public GestionTop5View() {

        setLayout(new BorderLayout());



        jlTitle = new JLabel("Top 5");
        jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(jlTitle, BorderLayout.NORTH);

    }

    public void initView(MainViewModel mainViewModel) {
        jlTitle.setText(mainViewModel.getTop5());
    }

    public void registerControllers(MouseInputListener gestionTop5ViewListener) {

        jlTitle.addMouseListener(gestionTop5ViewListener);
    }
}
