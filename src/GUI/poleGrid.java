package GUI;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import database.poleInfo;

public class poleGrid extends JPanel {
    /**
     * A single GUI unit of pole info
     */
    private static final long serialVersionUID = 1L;

    public JTextField id = null, name = null, max = null, min = null, current = null;
    public JSlider slider = null;

    public poleGrid(poleInfo info) {
        setLayout(null);
        setSize(200, 100);
        setFont(new Font("宋体", Font.BOLD, 24));
        setLayout(new BorderLayout(5, 5));
        id = new JTextField(info.id);
        name = new JTextField(info.name);
        max = new JTextField(String.valueOf(info.max_height));
        current = new JTextField(String.valueOf(info.current_height));
        min = new JTextField(String.valueOf(info.min_height));
        int percentage = (int) ((info.current_height - info.min_height) / (info.max_height - info.min_height) * 100);
        slider = new JSlider(0, 100, percentage);

        JPanel id_name = new JPanel();
        id_name.setLayout(new FlowLayout());
        JTextArea jta1, jta2;
        jta1 = new JTextArea("ID: ");
        jta1.setEditable(false);
        jta2 = new JTextArea("Name: ");
        jta2.setEditable(false);
        id_name.add(jta1);
        id_name.add(id);
        id_name.add(jta2);
        id_name.add(name);

        add(id_name, "North");
        add(max, "West");
        add(current, "Center");
        add(min, "East");
        add(slider, "South");

        setVisible(true);
    }
}