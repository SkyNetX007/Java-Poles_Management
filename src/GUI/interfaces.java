package GUI;

import java.awt.BorderLayout;
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

    public JScrollPane jsp = null;
    public JPanel jp = new JPanel();

    public interfaces(List<poleInfo> infoList) {
        this.setLayout(new BorderLayout());
        for (poleInfo i : infoList) {
            jp.add(new poleGrid(i));
        }
        jsp = new JScrollPane(jp);
        this.setContentPane(jsp);
        pack();
    }

}
