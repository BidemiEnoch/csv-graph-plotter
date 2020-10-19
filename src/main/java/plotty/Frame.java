package plotty;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.FlowLayout;

public final class Frame extends JFrame {
    public Frame(int width, int height, String title) {
        setSize(width, height);
        setTitle(title);
        setVisible(true);
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    public void add(JComponent obj) {
        getContentPane().add(obj);
    }
}
