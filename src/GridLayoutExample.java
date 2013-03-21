import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class GridLayoutExample{

    public JPanel createContentPane (){
        
        JPanel totalGUI = new JPanel();
        
        // We create a JPanel with the GridLayout.
        JPanel mainPanel = new JPanel(new GridLayout(3, 4, 10, 30));
        
        JPanel red = createSquareJPanel(Color.red, 50);
        JPanel blue = createSquareJPanel(Color.blue, 50);
        JPanel green = createSquareJPanel(Color.green, 50);
        JPanel orange = createSquareJPanel(Color.orange, 50);
        JPanel yellow = createSquareJPanel(Color.yellow, 50);
        JPanel pink = createSquareJPanel(Color.pink, 50);
        JPanel cyan = createSquareJPanel(Color.cyan, 50);
        JPanel gray = createSquareJPanel(Color.gray, 50);
        JPanel black = createSquareJPanel(Color.black, 50);
        JPanel magenta = createSquareJPanel(Color.magenta, 50);
      
        mainPanel.add(red);
        mainPanel.add(blue);
        mainPanel.add(green);
        mainPanel.add(orange);
        mainPanel.add(yellow);
        mainPanel.add(pink);
        mainPanel.add(cyan);
        mainPanel.add(gray);
        mainPanel.add(black);
        mainPanel.add(magenta);

        totalGUI.add(mainPanel);
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // In this method, we create a square JPanel of a colour and set size
    // specified by the arguments.
    
    private JPanel createSquareJPanel(Color color, int size)
    {
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(color);
        tempPanel.setMinimumSize(new Dimension(size, size));
        tempPanel.setMaximumSize(new Dimension(size, size));
        tempPanel.setPreferredSize(new Dimension(size, size));
        return tempPanel;
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] GridLayout [=]");

        GridLayoutExample demo = new GridLayoutExample();
        frame.setContentPane(demo.createContentPane());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}