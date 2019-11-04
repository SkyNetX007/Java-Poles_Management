package GUI;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import database.poleInfo;

public class interfaces extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JScrollPane jsp = new JScrollPane();
    public JPanel jp = new JPanel();

    public interfaces(List<poleInfo> infoList) {
        for (poleInfo i : infoList) {
            jp.add(new poleGrid(i));
        }
        this.setContentPane(jp);
        pack();
    }

}
