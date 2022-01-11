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
        //Frame size
        Dimension size = SettingsFile.readFrameSize();
        if (size != null) {
            frame.setPreferredSize(size);
        } else {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            frame.setPreferredSize(new Dimension(screenWidth / 2, screenHeight / 2));
        }

        frame.pack();

        //Frame location
        Point location = SettingsFile.readFrameLocation();
        if (location != null) {
            frame.setLocation(location);
        } else {
            frame.setLocationRelativeTo(null);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SettingsFile.writeFrameSize(frame.getSize());
                SettingsFile.writeFrameLocation(frame.getLocationOnScreen());
                ShowsFile.writeToFile(GUI.showInfoArrayList);
            }
        });
    }
}
