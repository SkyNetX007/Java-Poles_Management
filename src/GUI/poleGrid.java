package GUI;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class poleGrid extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public poleGrid(String _id, String _name, double min, double cur, double max) {
        setLayout(new BorderLayout(5, 5));
        getContentPane().add("NORTH", new JTextField(_id));
        getContentPane().add("NORTH", new JTextField(_name));
        getContentPane().add("WEST", new JTextField(String.valueOf(min)));
        getContentPane().add("CENTER", new JTextField(String.valueOf(cur)));
        getContentPane().add("EAST", new JTextField(String.valueOf(max)));
        int percentage = (int) ((cur - min) / (max - min) * 100);
        getContentPane().add("SOUTH", new JSlider(0, 100, percentage));

    }
}