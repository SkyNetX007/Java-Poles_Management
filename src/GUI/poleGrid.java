package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.poleInfo;

public class poleGrid extends JPanel {
    /**
     * A single GUI unit of pole info
     */
    private static final long serialVersionUID = 1L;

    public JTextField id = null, name = null, max = null, min = null, current = null;
    public JSlider slider = null, fact_slider = null;
    public JButton deleteButton = null;
    public JTextArea status = null;

    public int percentage = 0, fact_percentage = 0;

    public poleGrid(poleInfo info) {
        setLayout(new BorderLayout(5, 5));
        id = new JTextField(info.id);
        id.setPreferredSize(new Dimension(240, 30));
        name = new JTextField(info.name);
        name.setPreferredSize(new Dimension(240, 30));
        max = new JTextField(String.valueOf(info.max_height));
        max.setPreferredSize(new Dimension(80, 30));
        current = new JTextField(String.valueOf(info.current_height));
        current.setPreferredSize(new Dimension(80, 30));
        min = new JTextField(String.valueOf(info.min_height));
        min.setPreferredSize(new Dimension(80, 30));
        String statuss;
        if (info.current_height == info.fact_height)
            statuss = "Ready";
        else if (info.current_height > info.fact_height)
            statuss = "Going Up";
        else
            statuss = "Going Down";
        status = new JTextArea(statuss);
        percentage = (int) ((info.current_height - info.min_height) / (info.max_height - info.min_height) * 100);
        fact_percentage = (int) ((info.fact_height - info.min_height) / (info.max_height - info.min_height) * 100);
        slider = new JSlider(0, 100, percentage);
        if (fact_percentage < 100)
            fact_slider = new JSlider(0, 100, fact_percentage);
        else
            fact_slider = new JSlider(0, 100, 100);

        JPanel id_name = new JPanel();
        id_name.setPreferredSize(new Dimension(300, 80));
        id_name.setLayout(new FlowLayout());
        JTextArea jta1, jta2;
        jta1 = new JTextArea("ID: ");
        jta1.setPreferredSize(new Dimension(40, 30));
        jta1.setEditable(false);
        jta2 = new JTextArea("Name: ");
        jta2.setPreferredSize(new Dimension(40, 30));
        jta2.setEditable(false);
        id_name.add(jta1);
        id_name.add(id);
        id_name.add(jta2);
        id_name.add(name);

        JPanel min_max_current = new JPanel();
        min_max_current.setPreferredSize(new Dimension(300, 30));
        min_max_current.setLayout(new FlowLayout());
        min_max_current.add(min);
        min_max_current.add(current);
        min_max_current.add(max);
        min_max_current.add(status);

        JPanel sliders = new JPanel();
        sliders.setPreferredSize(new Dimension(300, 50));
        sliders.add(slider);
        sliders.add(fact_slider);

        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(new Dimension(50, 30));

        add(id_name, "North");
        add(min_max_current, "Center");
        add(sliders, "South");
        add(deleteButton, "East");

        setPreferredSize(new Dimension(320, 200));

        setVisible(true);
    }
}
