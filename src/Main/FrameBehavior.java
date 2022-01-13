package Main;

import Files.SettingsFile;
import Files.ShowsFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameBehavior {

    public final JFrame frame = new JFrame("Shows");

    FrameBehavior(){
        Dimension size = SettingsFile.readFrameSize();
        setFrameSize(size);

        Point location = SettingsFile.readFrameLocation();
        setFrameLocation(location);

        frame.pack();
        setFrameVisibility(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SettingsFile.writeFrameSize(frame.getSize());
                SettingsFile.writeFrameLocation(frame.getLocationOnScreen());
                ShowsFile.writeToFile(GUI.showInfoArrayList);
            }
        });
    }

    void setFrameSize(Dimension size){
        if (size.width > 0 && size.height > 0) {
            frame.setPreferredSize(size);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            frame.setPreferredSize(new Dimension(screenWidth / 2, screenHeight / 2));
        }
    }

    void setFrameSize(int width, int height){

    }

    void setFrameLocation(Point location){
        if (location != null) {
            frame.setLocation(location);
        } else {
            frame.setLocationRelativeTo(null);
        }
    }

    void setFrameVisibility(Boolean status){
        frame.setVisible(status);
    }

    void setDefaultCloseOperation(int closeOperation){
        frame.setDefaultCloseOperation(closeOperation);
    }

    void addToFrame(Component panelList, Component panelInfo){
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.weighty = 1;
        frame.getContentPane().add(panelList, constraints);

        constraints.gridx = 1;
        constraints.weightx = 3;
        frame.getContentPane().add(panelInfo, constraints);
    }
}
