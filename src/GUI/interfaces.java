package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
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

    private boolean contentChange = false;
    private boolean filterChange = false;
    private boolean err = false;

    private String filter = "";

    public JScrollPane jsp = null;
    public JPanel jp = new JPanel();
    public JTextField filterBox = new JTextField();

    List<poleInfo> contentList = null;
    List<poleInfo> filterList = null;

    List<poleGrid> gridList = new ArrayList<poleGrid>();

    public interfaces(List<poleInfo> infoList) {
        this.setLayout(new BorderLayout());
        contentList = new ArrayList<poleInfo>(infoList);
        filterList = infoList;
        for (poleInfo i : contentList) {
            poleGrid p = new poleGrid(i);
            gridList.add(p);
            jp.add(p);
        }
        jsp = new JScrollPane(jp);
        filterBox.setPreferredSize(new Dimension(200, 25));
        this.add(filterBox, "North");
        this.add(jsp, "Center");
        pack();
    }

    public void getNewGrids() {
        if (contentChange | filterChange) {
            jp.removeAll();
            gridList.clear();
            for (poleInfo i : filterList) {
                poleGrid p = new poleGrid(i);
                gridList.add(p);
                jp.add(p);
            }
            this.add(filterBox, "North");
            pack();
        }
    }

    public void update() {
        filterChange = false;
        if (!filterBox.getText().equals(filter)) {
            filter = filterBox.getText();
            filterChange = true;
        }

        // 检查添加/删除或内容变化
        if (filterList.size() != gridList.size()) {
            contentChange = true;
        } else {
            Iterator<poleInfo> infoit = filterList.iterator();
            Iterator<poleGrid> gridit = gridList.iterator();
            while (infoit.hasNext()) {
                poleInfo p = infoit.next();
                poleGrid g = gridit.next();
                String gid = g.id.getText();
                String gname = g.name.getText();
                double gmin = Double.valueOf(g.min.getText());
                double gmax = Double.valueOf(g.max.getText());
                double gcur = Double.valueOf(g.current.getText());
                if (!p.id.equals(gid) | !p.name.equals(gname) | p.current_height != gcur | p.min_height != gmin
                        | p.max_height != gmax) {
                    contentChange = true;
                    break;
                }
            }
        }

        // 重新填充过滤结果
        if (filterChange) {
            filterList.clear();
            if (!filter.equals("")) {
                for (poleInfo i : contentList) {
                    if (i.name.indexOf(filter) != -1 | i.id.indexOf(filter) != -1)
                        filterList.add(i);
                }
            } else {
                filterList = new ArrayList<poleInfo>(contentList);
            }
        }
        System.out.println(contentChange + ", " + filterChange);
    }

}
