package Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelListBehavior {

    public JPanel panelList = new JPanel();

    PanelListBehavior(JFrame frame){
        panelList.setName("List");
        panelList.setPreferredSize(new Dimension((int) (frame.getContentPane().getWidth() * 0.2), frame.getContentPane().getHeight()));
    }

    void addToPanelList(JTextField searchBar, JTable table, JButton addButton){
        panelList.setBorder(new LineBorder(Color.BLACK));
        panelList.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        panelList.add(searchBar, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 1;
        panelList.add(table, c);

        c.gridy = 2;
        c.weighty = 0.05;
        panelList.add(addButton, c);
    }
}
