import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import GUI.interfaces;
import database.database;
import database.poleInfo;

public class test {

    public static void main(String[] args) {
        try {
            database db = new database();
            List<poleInfo> pinfo = db.getInfo();

            interfaces frame = new interfaces(pinfo);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setTitle("Java-Poles_Management");
            frame.setVisible(true);

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    try {
                        List<poleInfo> pinfos = db.getInfo();
                        frame.update(pinfos);
                        frame.getNewGrids();

                        if (!frame.err & frame.contentChange & frame.save) {
                            db.SaveInfo(frame.contentList);
                            frame.contentChange = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 100, 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}