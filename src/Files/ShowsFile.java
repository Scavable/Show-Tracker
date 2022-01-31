package Files;

import Main.ShowInfo;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;

public class ShowsFile {

    private static final File DOCS_DIRECTORY = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath());
    private static final File ROOT_FOLDER = new File(DOCS_DIRECTORY+"\\Scavables");
    private static final File SHOWS_FOLDER = new File(ROOT_FOLDER+"\\Shows");
    static File showsFile = new File(SHOWS_FOLDER + "\\shows.txt");

    public ShowsFile(){
        if(!SHOWS_FOLDER.exists()){
            if(SHOWS_FOLDER.mkdir()){
                System.out.println("Shows folder created");
            }
        }
    }
    public static void writeToFile(ArrayList<ShowInfo> info){

        try {
            FileWriter fw = new FileWriter(showsFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(ShowInfo show : info){
                bw.write(show.title + ", " + show.season + ", " + show.episode + ", " + show.time);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ShowInfo> readFile(){
        ArrayList<ShowInfo> shows = new ArrayList<>();
        try{
            FileReader fr = new FileReader(showsFile);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()){
                String[] str = br.readLine().split(",");
                str[0] = str[0].trim();
                str[1] = str[1].trim();
                str[2] = str[2].trim();
                str[3] = str[3].trim();
                shows.add(new ShowInfo(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3]));

            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return shows;
    }
}
