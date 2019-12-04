package godday.xin.web.Swing;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Set;

public class Jframe_Test {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleJframe simpleJframe = new SimpleJframe();
                simpleJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                simpleJframe.setVisible(true);
            }
        });
    }
}
class SimpleJframe extends JFrame{
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;
    public SimpleJframe() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setLocation(dimension.width/4, dimension.height/4);
        setTitle("市场部app");
        setResizable(false);
        setSize(dimension.width/2, dimension.height/2);
    }
}
