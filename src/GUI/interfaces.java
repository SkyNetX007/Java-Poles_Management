package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.*;

public class interfaces extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean contentChange = false;
    public boolean filterChange = false;
    public boolean err = false;
    public boolean save = false;

    private String filter = "";

    public JScrollPane jsp = null;
    public JPanel jp = new JPanel();
    public JTextField filterBox = new JTextField();
    public JButton addButton = new JButton("ADD NEW POLE");

    public List<poleInfo> contentList = null;
    List<poleInfo> filterList = null;

    List<poleGrid> gridList = new ArrayList<poleGrid>();

    public interfaces(List<poleInfo> infoList) {
        this.setLayout(new BorderLayout());
        contentList = new ArrayList<poleInfo>(infoList);
        filterList = infoList;
        jp.setLayout(new FlowLayout());
        jp.setPreferredSize(new Dimension(800, 400));
        for (poleInfo i : contentList) {
            poleGrid p = new poleGrid(i);
            p.deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentList.remove(i);
                    filterList.remove(i);
                    int i = 1;
                    Iterator<poleInfo> cit = contentList.iterator(), fit = filterList.iterator();
                    while (cit.hasNext()) {
                        cit.next().No = i;
                        fit.next().No = i;
                        i++;
                    }
                    gridList.remove(p);
                    jp.remove(p);
                    contentChange = true;
                }
            });
            gridList.add(p);
            jp.add(p);
        }
        jsp = new JScrollPane(jp);
        filterBox.setPreferredSize(new Dimension(200, 25));
        this.add(filterBox, "North");
        this.add(jsp, "Center");
        this.add(addButton, "South");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poleInfo pi = new poleInfo();
                pi.No = contentList.size() + 1;
                pi.id = new String("12345");
                pi.name = new String("NEW_POLE");
                contentList.add(pi);
                filterList.add(pi);
                poleGrid p = new poleGrid(pi);
                p.deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        contentList.remove(pi);
                        filterList.remove(pi);
                        int i = 1;
                        Iterator<poleInfo> cit = contentList.iterator(), fit = filterList.iterator();
                        while (cit.hasNext()) {
                            cit.next().No = i;
                            fit.next().No = i;
                            i++;
                        }
                        gridList.remove(p);
                        jp.remove(p);
                        contentChange = true;
                    }
                });
                jp.add(p);
                gridList.add(p);
                contentChange = true;
            }
        });
        jp.setPreferredSize(new Dimension(800, 30 + 150 * ((filterList.size() + 1) / 2)));
        setPreferredSize(new Dimension(800, 400));
        pack();
    }

    public void getNewGrids() {
        if (filterChange) {
            for (poleGrid i : gridList) {
                jp.remove(i);
            }
            gridList.clear();
            for (poleInfo i : filterList) {
                poleGrid p = new poleGrid(i);
                p.deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        contentList.remove(i);
                        filterList.remove(i);
                        int i = 1;
                        Iterator<poleInfo> cit = contentList.iterator(), fit = filterList.iterator();
                        while (cit.hasNext()) {
                            cit.next().No = i;
                            fit.next().No = i;
                            i++;
                        }
                        gridList.remove(p);
                        jp.remove(p);
                        contentChange = true;
                    }
                });
                gridList.add(p);
                jp.add(p);
            }
            jp.updateUI();
            jp.setPreferredSize(new Dimension(800, 30 + 150 * ((filterList.size() + 1) / 2)));
        }
        if (contentChange) {
            jp.setPreferredSize(new Dimension(800, 30 + 150 * ((filterList.size() + 1) / 2)));
            jp.updateUI();
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
                double gmin = 0;
                double gmax = 0;
                double gcur = 0;
                try {
                    gmin = Double.valueOf(g.min.getText());
                    gmax = Double.valueOf(g.max.getText());
                    gcur = Double.valueOf(g.current.getText());
                } catch (NumberFormatException e) {
                    if (!err) {
                        JOptionPane.showMessageDialog(this, "Error detected, auto-saving will be stopped.");
                    }
                    err = true;
                    return;
                }
                // 检测输入错误
                if (g.min.getText().length() == 0 | g.max.getText().length() == 0 | g.current.getText().length() == 0
                        | gmin >= gmax | gmin > gcur | gcur > gmax | gid.length() == 0 | gname.length() == 0) {
                    if (!err) {
                        JOptionPane.showMessageDialog(this, "Error detected, auto-saving will be stopped.");
                    }
                    err = true;
                    return;
                }
                // 检测输入变化
                if (!p.name.equals(gname)) {
                    p.name = new String(gname);
                    contentChange = true;
                }
                if (!p.id.equals(gid)) {
                    p.id = new String(gid);
                    contentChange = true;
                }
                if (gmin != p.min_height) {
                    p.min_height = gmin;
                    contentChange = true;
                }
                if (gcur != p.current_height) {
                    p.current_height = gcur;
                    contentChange = true;
                }
                if (gmax != p.max_height) {
                    p.max_height = gmax;
                    contentChange = true;
                }
                if (g.percentage != g.slider.getValue()) {
                    g.percentage = g.slider.getValue();
                    gcur = p.current_height = g.percentage * (p.max_height - p.min_height) / 100 + p.min_height;
                    g.current.setText(String.valueOf(p.current_height));
                    contentChange = true;
                }

                if (contentChange) {
                    g.percentage = (int) ((gcur - gmin) / (gmax - gmin) * 100);
                    g.slider.setValue(g.percentage);
                }
            }
            err = false;
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
        save = !save;
    }

}
