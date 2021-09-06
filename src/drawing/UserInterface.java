package drawing;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JColorChooser;

/* 
 * This class is used for setting graphical user interface of our application
 * 
 * It has background, foreground and border colors for forms and dialogs, 
 * and colors that are applied when text field has gained focus
 * 
 * Second theme is commented out, but it shows how easy is to change theme
 * 
 * It also has method for changing color on focus, and method for choosing color from JColorChooser
 */
public class UserInterface {
	
	// Title of application
	public static String title = " Java Drawing Application v1.0";
	
	// Theme 1
	public static Color bgPrimary = new Color(0, 0, 51);
	public static Color bgSecondary = new Color(0, 0, 40);
	public static Color fgPrimary = Color.white;
	public static Color fgSecondary = Color.ORANGE;
	public static Color borderPrimary = Color.white;
	public static Color borderSecondary = Color.ORANGE;
	
	// Theme 2
	//	public static Color bgPrimary = new Color(153, 0, 0);
	//	public static Color bgSecondary = new Color(128, 0, 0);
	//	public static Color fgPrimary = Color.white;
	//	public static Color fgSecondary = new Color(255, 255, 51);
	//	public static Color borderPrimary = Color.white;
	//	public static Color borderSecondary = Color.white;
	
	// Button icons
	public static String newFileIcon = "src\\resources\\new-file-icon.png";
	public static String openFileIcon = "src\\resources\\open-file-icon.png";
	public static String saveFileIcon = "src\\resources\\save-file-icon.png";
	public static String exportPngIcon = "src\\resources\\export-png-image.png";
	public static String printIcon = "src\\resources\\print-image.png";
	public static String selectIcon = "src\\resources\\select.png";
	public static String pointIcon = "src\\resources\\point.png";
	public static String lineIcon = "src\\resources\\line.png";
	public static String rectangleIcon = "src\\resources\\rectangle.png";
	public static String circleIcon = "src\\resources\\circle.png";
	public static String donutIcon = "src\\resources\\donut.png";
	
	// For focus highlighter
	private static Color bgOnFocus = Color.ORANGE;
	private static Color fgOnFocus = Color.black;
	
	/*
	 * focusHighlighter is used to change background and foreground colors of text field when
	 * user interacts with that field
	 */
	public static FocusListener focusHighlighter = new FocusListener() {

        @Override
        public void focusGained(FocusEvent e) {
            e.getComponent().setBackground(bgOnFocus);
            e.getComponent().setForeground(fgOnFocus);
        }

        @Override
        public void focusLost(FocusEvent e) {
            e.getComponent().setBackground(bgSecondary);
            e.getComponent().setForeground(fgPrimary);
        }
    };
    
    // Used for choosing color from color picker
    public static Color getColorFromJColorChooser(String title, Color defaultColor) {
    	Color color = JColorChooser.showDialog(null, title, defaultColor);
		if (color==null) {
			color = defaultColor;
		}
		return color;
    }
}
