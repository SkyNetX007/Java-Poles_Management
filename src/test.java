import java.util.List;

import javax.swing.JFrame;

import database.*;
import GUI.*;

public class test {
    public static void main(String[] args) {
        try {
            database db = new database();
            List<poleInfo> pinfo = db.getInfo();
            interfaces frame = new interfaces(pinfo);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Java-Poles_Management");
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}