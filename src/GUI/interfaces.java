package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.poleInfo;

public class interfaces extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static int checkTime = 1000;
    private int save = 0;

    private boolean contentChange = false;
    private boolean filterChange = false;
    private boolean err = false;

    private String filter = "\0";

    public JScrollPane jsp = null;
    public JPanel jp = new JPanel();
    public JTextField filterBox = new JTextField();

    List<poleInfo> contentList = null;
    List<poleInfo> filterList = null;

    public interfaces(List<poleInfo> infoList) {
        this.setLayout(new BorderLayout());
        contentList = infoList;
        for (poleInfo i : contentList) {
            jp.add(new poleGrid(i));
        }
        jsp = new JScrollPane(jp);
        filterBox.setPreferredSize(new Dimension(200, 25));
        this.add(filterBox, "North");
        this.add(jsp, "Center");
        pack();
    }

    public void update() {
        if (filterBox.getText() != filter) {
            filter = filterBox.getText();
            filterChange = true;
        }
        if (filterChange) {
            filterList.clear();
            for (poleInfo i : contentList) {
                if (i.name.indexOf(filter) != -1 | i.id.indexOf(filter) != -1)
                    filterList.add(i);
            }
        }
    }

}
