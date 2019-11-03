import java.util.List;

import database.*;

public class test {
    public static void main(String[] args) {
        try {
            database db = new database();
            List<poleInfo> pinfo = db.getInfo();
            for (poleInfo info : pinfo) {
                System.out.println(info.name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}