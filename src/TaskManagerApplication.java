import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * Application for Task Manager GUI (start of execution) 
 *
 * @author Sabirah Shuaybi
 * @author Tseki Lhamo
 */

public class TaskManagerApplication {
	
	//Window/Frame dimensions
    private static final int FRAME_WIDTH = 920;
    private static final int FRAME_HEIGHT = 770;

    /**
     * Create a JFrame that holds a TaskManagerGUI.
     **/
    public static void main(String[] args)
    {
        JFrame taskFrame;
        //Set a universal font type to be used throughout the application
        setUIFont (new javax.swing.plaf.FontUIResource("Tahoma", Font.PLAIN, 20));

        //Create a new JFrame to hold an A Task Manager
        taskFrame = new JFrame("My Task Manager");

        //Set size of frame
        taskFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //Create an TaskManagerGUI and add it to frame
        taskFrame.getContentPane().add(new TaskManagerGUI());

        //Exit normally on closing the window
        taskFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Show frame
        taskFrame.setVisible(true);
    }
    
    /**
     * Sets the UI font across the application 
     * Source: stackoverflow
     * @param f
     */
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
          }
        } 
}
