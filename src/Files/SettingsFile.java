package Files;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;

public class SettingsFile {

    private static final File DOCS_DIRECTORY = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath());
    private static final File ROOT_FOLDER = new File(DOCS_DIRECTORY+"\\Scavables");
    private static final File SHOWS_FOLDER = new File(ROOT_FOLDER+"\\Shows");
    private static final File settingsFile = new File(SHOWS_FOLDER+"\\settings.txt");

    public SettingsFile(){
        if(!SHOWS_FOLDER.exists()){
            if(SHOWS_FOLDER.mkdir()){
                System.out.println("Shows folder created");
            }
        }

    }

    public static void writeFrameSize(Dimension size){
        try {
            if(!settingsFile.exists()){
                if(settingsFile.createNewFile())
                    writeFrameSize(size);
            }else{
                FileWriter fw = new FileWriter(settingsFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Width: " + size.width);
                bw.newLine();
                bw.write("Height: " + size.height);
                bw.newLine();
                bw.close();
                fw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeFrameLocation(Point location){
        try {
            FileWriter fw = new FileWriter(settingsFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            StringBuilder str = new StringBuilder();
            str.append("Location: ").append(location.x).append(", ").append(location.y);
            bw.append(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dimension readFrameSize(){
        int width = 0, height = 0;
        try {
            FileReader fr = new FileReader(settingsFile);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String str = br.readLine();
                int length = str.length();
                if(str.contains("Width")){
                    width = Integer.parseInt(str.substring(7, length));
                } else if(str.contains("Height")){
                    height = Integer.parseInt(str.substring(8, length));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Dimension(width, height);
    }

    public static Point readFrameLocation(){
        Point point = null;
        String[] points;
        try {
            FileReader fr = new FileReader(settingsFile);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String str = br.readLine();
                int length = str.length();
                if(str.contains("Location")){
                    str = str.substring(9, length);
                    points = str.split(",");
                    point = new Point(Integer.parseInt(points[0].trim()), Integer.parseInt(points[1].trim()));
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return point;
    }
}
