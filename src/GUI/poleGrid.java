package GUI;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import database.poleInfo;

public class poleGrid extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JTextField id = null, name = null, max = null, min = null, current = null;
    public JSlider slider = null;

    public poleGrid(poleInfo info) {
        setLayout(new BorderLayout(5, 5));
        id = new JTextField(info.id);
        name = new JTextField(info.name);
        max = new JTextField(String.valueOf(info.min_height));
        current = new JTextField(String.valueOf(info.current_height));
        min = new JTextField(String.valueOf(info.min_height));
        int percentage = (int) ((info.current_height - info.current_height) / (info.max_height - info.min_height)
                * 100);

        getContentPane().add("NORTH", new JTextField(info.id));
        getContentPane().add("NORTH", new JTextField(info.name));
        getContentPane().add("WEST", new JTextField(String.valueOf(info.min_height)));
        getContentPane().add("CENTER", new JTextField(String.valueOf(info.current_height)));
        getContentPane().add("EAST", new JTextField(String.valueOf(info.max_height)));

        getContentPane().add("SOUTH", new JSlider(0, 100, percentage));

    }
}