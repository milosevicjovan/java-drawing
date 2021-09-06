package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import java.awt.Cursor;
import javax.swing.DefaultListModel;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;

import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.border.LineBorder;

public class FrmDrawing extends JFrame {

	// Form
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup btnGroup = new ButtonGroup();
	protected PnlDrawing pnlDrawing;	
	
	// Shapes
	private Line tempLine; // tempLine is initialized on tglbtnLine click event
	private Shape selected; // for modifying and selecting shapes

	// TODO: cut/copy/paste
	Shape tempShape; // for copying and cuting; not implemented yet!
	
	// UI colors
	private Color bgPrimary = UserInterface.bgPrimary;
	private Color bgSecondary = UserInterface.bgSecondary;
	private Color fgPrimary = UserInterface.fgPrimary;
	private Color fgSecondary = UserInterface.fgSecondary;
	private Color borderPrimary = UserInterface.borderPrimary;
	private Color borderSecondary = UserInterface.borderSecondary;
	
	// UI button icons
	private String newFileIcon = UserInterface.newFileIcon;
	private String openFileIcon = UserInterface.openFileIcon;
	private String saveFileIcon = UserInterface.saveFileIcon;
	private String exportPngIcon = UserInterface.exportPngIcon;
	private String printIcon = UserInterface.printIcon;
	private String selectIcon = UserInterface.selectIcon;
	private String pointIcon = UserInterface.pointIcon;
	private String lineIcon = UserInterface.lineIcon;
	private String rectangleIcon = UserInterface.rectangleIcon;
	private String circleIcon = UserInterface.circleIcon;
	private String donutIcon = UserInterface.donutIcon;
	
	// Title
	private String title = UserInterface.title;
	
	//click point
    int x;
    int y;
    
    // Project file handling
    String projectName;
    File projectFile;
    
    // List and dlm
    // We are using string because we want to show names of objects and also their ordinal numbers in list
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	JList<String> list;
		
	// Labels
	JLabel lblSelected;
	JLabel lblShapeInfo;
	JLabel lblBorderColor, lblBorderColorLabel,  lblInnerColor,
			lblInnerColorLabel, lblInitialColorLabel, lblInitialColor;
	JLabel lblPrintInfo;
	JLabel lblCount;
	JLabel lblInfo;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					// Displays form at the center of the screen
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setTitle(title);
		resetProject(); // Setting projectFile to null and projectName to 'untitled.drawing'
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(bgPrimary);
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlNorth.rowHeights = new int[]{30, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		JToggleButton tglbtnRectangle = new JToggleButton("");
		tglbtnRectangle.setMinimumSize(new Dimension(48, 48));
		tglbtnRectangle.setMaximumSize(new Dimension(48, 48));
		tglbtnRectangle.setForeground(Color.WHITE);
		tglbtnRectangle.setBackground(bgSecondary);
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideInitialColor();
				setDrawingInfo("Rectangle", 0);
			}
		});
		
				JToggleButton tglbtnPoint = new JToggleButton("");
				tglbtnPoint.setMaximumSize(new Dimension(48, 48));
				tglbtnPoint.setMinimumSize(new Dimension(48, 48));
				tglbtnPoint.setForeground(Color.WHITE);
				tglbtnPoint.setBackground(bgSecondary);
				tglbtnPoint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent Arg0) {
						showInitialColor();
						setDrawingInfo("Point", 0);
					}
				});
				
				JButton btnNewFile = new JButton("");
				btnNewFile.addActionListener(new ActionListener() {
					// creating new project
					public void actionPerformed(ActionEvent arg0) {
						pnlDrawing.getShapes().clear();
						pnlDrawing.repaint();
						dlm.clear();
						resetProject();
						lblInfo.setText("");
						hideShapeInfo();
						showNumberOfElements();
					}
				});
				btnNewFile.setBackground(bgSecondary);
				btnNewFile.setForeground(Color.white);
				btnNewFile.setMinimumSize(new Dimension(48, 48));
				btnNewFile.setMaximumSize(new Dimension(48, 48));
				btnNewFile.setPreferredSize(new Dimension(48, 48));
				btnNewFile.setIcon(new ImageIcon(newFileIcon));
				btnNewFile.setBorderPainted(false);
				btnNewFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				// Remove the spacing between the image and button's borders
				btnNewFile.setMargin(new Insets(0, 0, 0, 0));
				// Remove the border
				btnNewFile.setBorder(null);
				GridBagConstraints gbc_btnNewFile = new GridBagConstraints();
				gbc_btnNewFile.insets = new Insets(5, 10, 5, 5);
				gbc_btnNewFile.gridx = 0;
				gbc_btnNewFile.gridy = 0;
				pnlNorth.add(btnNewFile, gbc_btnNewFile);
				
				JButton btnOpenFile = new JButton("");
				btnOpenFile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						/*
						 * This method opens new project file from file system
						 * 
						 * To choose the file we are using getFileFromJFileChooser method defined at the end of this class
						 * 
						 * reader iterates through the file and reads line by line which are separated by semi-colon
						 * 
						 * Depending on the first string, new shape is created and added to list of shapes in pnlDrawing
						 * 
						 * After adding all the objects from file, panel is repainted
						 * and projectName and file are updated
						 * 
						 * Whole block of code is surrounded by try-catch block in case of unusual exception
						 */
						try {
							BufferedReader reader;
							
							File fileToOpen = getFileFromJFileChooser("Select project to open", 
											  new FileNameExtensionFilter("Drawing project files", "drawing", "drawing"), 
											  ".drawing", 0);
							
							if (fileToOpen==null) {
								return;
							}
							
							reader = new BufferedReader(new FileReader(fileToOpen.getAbsolutePath()));
							String line = reader.readLine();
							
							pnlDrawing.getShapes().clear();
							dlm.clear();
							
							while (line != null) {
								String[] shapeArray = line.split(";");
								switch (shapeArray[0]) {
								case "Point": {
									Point point = new Point();
									point.setX(Integer.parseInt(shapeArray[1]));
									point.setY(Integer.parseInt(shapeArray[2]));
									point.setColor(new Color(Integer.parseInt(shapeArray[3]), true));
									point.setSelected(false);
									pnlDrawing.getShapes().add(point);
									dlm.addElement(getDlmShapeText("Point"));
									break; }
								case "Line": {
									int startX = Integer.parseInt(shapeArray[1]);
									int startY = Integer.parseInt(shapeArray[2]);
									int endX = Integer.parseInt(shapeArray[3]);
									int endY = Integer.parseInt(shapeArray[4]);
									Color color = new Color(Integer.parseInt(shapeArray[5]), true);
									Line drawingLine = new Line(new Point(startX, startY), new Point(endX, endY),
														 false, color);
									pnlDrawing.getShapes().add(drawingLine);
									dlm.addElement(getDlmShapeText("Line"));
									break; }
								case "Rectangle": {
									int upperX = Integer.parseInt(shapeArray[1]);
									int upperY = Integer.parseInt(shapeArray[2]);
									int width = Integer.parseInt(shapeArray[3]);
									int height = Integer.parseInt(shapeArray[4]);
									Color borderColor = new Color(Integer.parseInt(shapeArray[5]));
									Color innerColor = new Color(Integer.parseInt(shapeArray[6]));
									Rectangle rectangle = new Rectangle(new Point(upperX, upperY),height,width,false,borderColor,innerColor);
									pnlDrawing.getShapes().add(rectangle);
									dlm.addElement(getDlmShapeText("Rectangle")); 
									break; }
								case "Circle": {
									int centerX = Integer.parseInt(shapeArray[1]);
									int centerY = Integer.parseInt(shapeArray[2]);
									int radius = Integer.parseInt(shapeArray[3]);
									Color borderColor = new Color(Integer.parseInt(shapeArray[4]));
									Color innerColor = new Color(Integer.parseInt(shapeArray[5]));
									Circle circle = new Circle(new Point(centerX, centerY), radius, false, borderColor, innerColor);
									pnlDrawing.getShapes().add(circle);
									dlm.addElement(getDlmShapeText("Circle"));
									break; }
								case "Donut": {
									int centerX = Integer.parseInt(shapeArray[1]);
									int centerY = Integer.parseInt(shapeArray[2]);
									int radius = Integer.parseInt(shapeArray[3]);
									int innerRadius = Integer.parseInt(shapeArray[4]);
									Color borderColor = new Color(Integer.parseInt(shapeArray[5]));
									Color innerColor = new Color(Integer.parseInt(shapeArray[6]));
									Donut donut = new Donut(new Point(centerX, centerY), radius, innerRadius, false, borderColor, innerColor);
									pnlDrawing.getShapes().add(donut);
									dlm.addElement(getDlmShapeText("Donut"));
									break; }
								}
								line = reader.readLine();
							}
							pnlDrawing.repaint();
							showNumberOfElements();
							list.ensureIndexIsVisible(dlm.getSize()-1);
							
							setProject(fileToOpen, fileToOpen.getName());
							
							reader.close();
						} catch (Exception ex) {
							setTitle(title);
							JOptionPane.showMessageDialog(null, ex.getMessage(),
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnOpenFile.setIconTextGap(0);
				btnOpenFile.setIcon(new ImageIcon(openFileIcon));
				btnOpenFile.setBorderPainted(false);
				btnOpenFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnOpenFile.setMargin(new Insets(0, 0, 0, 0));
				btnOpenFile.setBorder(null);
				btnOpenFile.setBackground(bgSecondary);
				btnOpenFile.setPreferredSize(new Dimension(48, 48));
				btnOpenFile.setMinimumSize(new Dimension(48, 48));
				btnOpenFile.setMaximumSize(new Dimension(48, 48));
				GridBagConstraints gbc_btnOpenFile = new GridBagConstraints();
				gbc_btnOpenFile.insets = new Insets(5, 0, 5, 5);
				gbc_btnOpenFile.gridx = 1;
				gbc_btnOpenFile.gridy = 0;
				pnlNorth.add(btnOpenFile, gbc_btnOpenFile);
				
				JButton btnSaveFile = new JButton("");
				btnSaveFile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						/*
						 * This method does opposite from previously described method:
						 * 
						 * It saves all shapes from list of shapes in pnlDrawing into the file in file system
						 * 
						 * Shapes are saved as semi-colon separated strings (each shape in one line)
						 * 
						 * If project from file system is already open, user will be asked if 
						 * he wants to save it to new file, or to existing one
						 * 
						 * This method is also surrounded by try-catch block
						 */
						try {
							
							File fileToSave;
							
							if (projectFile!=null) {
							    String[] dialogButtons = { "Save to current project file", "Save as new project file" };

							    int dialogResult = JOptionPane.showOptionDialog(null, "Please select how you want to save project.",
							    					"Attention", JOptionPane.INFORMATION_MESSAGE, JOptionPane.QUESTION_MESSAGE, 
							    					null, dialogButtons, dialogButtons[0]);

							    if (dialogResult == 0) {
							    	fileToSave = projectFile;
							    } else if (dialogResult == 1) {
									fileToSave = getFileFromJFileChooser("Save project", 
											  new FileNameExtensionFilter("Drawing project files", "drawing", "drawing"), 
											  ".drawing", 1);
							    } else {
							    	return;
							    }
							} else {
								fileToSave = getFileFromJFileChooser("Save project", 
										  new FileNameExtensionFilter("Drawing project files", "drawing", "drawing"), 
										  ".drawing", 1);
							}
											
							if (fileToSave==null) {
								return;
							}
							
							if (fileToSave.exists()) {
								fileToSave.delete();
							}
							
							fileToSave.createNewFile();
							
							BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave, true));
							
							for (Shape s: pnlDrawing.getShapes()) {
								if (s instanceof Point) { writer.append(((Point)s).getFileData()); }
								if (s instanceof Line) { writer.append(((Line)s).getFileData()); }
								if (s instanceof Rectangle) { writer.append(((Rectangle)s).getFileData()); }
								if (s instanceof Circle && (!(s instanceof Donut))) { writer.append(((Circle)s).getFileData()); }
								if (s instanceof Donut) { writer.append(((Donut)s).getFileData()); }
								writer.append("\n");
							}
							
							setProject(fileToSave, fileToSave.getName());
							
							writer.close();
							
						}			
						catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(),
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				btnSaveFile.setBackground(bgSecondary);
				btnSaveFile.setIcon(new ImageIcon(saveFileIcon));
				btnSaveFile.setBorderPainted(false);
				btnSaveFile.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnSaveFile.setMargin(new Insets(0, 0, 0, 0));
				btnSaveFile.setBorder(null);
				btnSaveFile.setPreferredSize(new Dimension(48, 48));
				btnSaveFile.setMinimumSize(new Dimension(48, 48));
				btnSaveFile.setMaximumSize(new Dimension(48, 48));
				GridBagConstraints gbc_btnSaveFile = new GridBagConstraints();
				gbc_btnSaveFile.insets = new Insets(5, 0, 5, 5);
				gbc_btnSaveFile.gridx = 2;
				gbc_btnSaveFile.gridy = 0;
				pnlNorth.add(btnSaveFile, gbc_btnSaveFile);
				
				JButton btnExportPng = new JButton("");
				btnExportPng.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						exportAsPng();
					}
				});
				
				btnExportPng.setBackground(bgSecondary);
				btnExportPng.setIcon(new ImageIcon(exportPngIcon));
				btnExportPng.setBorderPainted(false);
				btnExportPng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnExportPng.setMargin(new Insets(0, 0, 0, 0));
				btnExportPng.setBorder(null);
				btnExportPng.setPreferredSize(new Dimension(48, 48));
				btnExportPng.setMinimumSize(new Dimension(48, 48));
				btnExportPng.setMaximumSize(new Dimension(48, 48));
				GridBagConstraints gbc_btnExportPng = new GridBagConstraints();
				gbc_btnExportPng.insets = new Insets(5, 0, 5, 5);
				gbc_btnExportPng.gridx = 3;
				gbc_btnExportPng.gridy = 0;
				pnlNorth.add(btnExportPng, gbc_btnExportPng);
				
				JButton btnPrint = new JButton("");
				btnPrint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {		
						/*
						 * Printing picture from panel
						 * 
						 * At the bottom left side of printed document will be added lblPrintInfo label
						 * which will show current date, time and project name
						 * 
						 * After printing, label's visibility will be set to false
						 *
						 */
						try {
							
							lblPrintInfo = new JLabel(getPrintInfo(), JLabel.LEFT);	
							
							lblPrintInfo.setFont(new Font("Calibri", Font.BOLD, 20));
							lblPrintInfo.setOpaque(true);
							lblPrintInfo.setBackground(Color.white);							
							lblPrintInfo.setLocation(10, 560);
							lblPrintInfo.setSize(500, 40);
							lblPrintInfo.setVisible(false);
							pnlDrawing.add(lblPrintInfo);
							
							lblPrintInfo.setVisible(true);
							
							unselectAllShapes();
							
							pnlDrawing.repaint();
							
							 PrinterJob printerJob = PrinterJob.getPrinterJob();
							 printerJob.setJobName(projectName);
							  
							 printerJob.setPrintable(new Printable() {    
							    public int print(Graphics pg, PageFormat pf, int pageNum){
							      if (pageNum > 0){
							      return Printable.NO_SUCH_PAGE;
							      }
							      
							      Graphics2D g2 = (Graphics2D) pg;
							      g2.translate(pf.getImageableX(), pf.getImageableY());
							      	      
							      pnlDrawing.paint(g2);
							      
							      return Printable.PAGE_EXISTS;
							    }
							  });
							 if (printerJob.printDialog() == false) {
								  lblPrintInfo.setVisible(false);
								  return;
							 }						
							 
							 printerJob.print();
							        
					   } catch (PrinterException ex) {
								  JOptionPane.showMessageDialog(null, ex.getMessage(),
											"Error", JOptionPane.ERROR_MESSAGE);
					   } finally {
							 lblPrintInfo.setVisible(false);
					   }
					}
				});
				btnPrint.setBackground(bgSecondary);
				btnPrint.setIcon(new ImageIcon(printIcon));
				btnPrint.setBorderPainted(false);
				btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnPrint.setMargin(new Insets(0, 0, 0, 0));
				btnPrint.setBorder(null);
				btnPrint.setPreferredSize(new Dimension(48, 48));
				btnPrint.setMinimumSize(new Dimension(48, 48));
				btnPrint.setMaximumSize(new Dimension(48, 48));
				GridBagConstraints gbc_btnPrint = new GridBagConstraints();
				gbc_btnPrint.insets = new Insets(5, 0, 5, 5);
				gbc_btnPrint.gridx = 4;
				gbc_btnPrint.gridy = 0;
				pnlNorth.add(btnPrint, gbc_btnPrint);
				
				JToggleButton tglbtnSelect = new JToggleButton("");
				tglbtnSelect.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						hideInitialColor();
						setDrawingInfo("",0);
					}
				});
				tglbtnSelect.setIcon(new ImageIcon(selectIcon));
				tglbtnSelect.setBorderPainted(false);
				tglbtnSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tglbtnSelect.setMargin(new Insets(0, 0, 0, 0));
				tglbtnSelect.setBorder(null);
				tglbtnSelect.setMinimumSize(new Dimension(48, 48));
				tglbtnSelect.setMaximumSize(new Dimension(48, 48));
				tglbtnSelect.setPreferredSize(new Dimension(48, 48));
				tglbtnSelect.setForeground(Color.WHITE);
				tglbtnSelect.setBackground(bgSecondary);
				btnGroup.add(tglbtnSelect);
				
				GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
				gbc_tglbtnSelect.insets = new Insets(5, 100, 5, 5);
				gbc_tglbtnSelect.gridx = 5;
				gbc_tglbtnSelect.gridy = 0;
				pnlNorth.add(tglbtnSelect, gbc_tglbtnSelect);
				tglbtnPoint.setPreferredSize(new Dimension(48, 48));
				tglbtnPoint.setIcon(new ImageIcon(pointIcon));
				tglbtnPoint.setBorderPainted(false);
				tglbtnPoint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tglbtnPoint.setMargin(new Insets(0, 0, 0, 0));
				tglbtnPoint.setBorder(null);
				GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
				gbc_tglbtnPoint.anchor = GridBagConstraints.NORTHWEST;
				gbc_tglbtnPoint.insets = new Insets(5, 95, 5, 5);
				gbc_tglbtnPoint.gridx = 6;
				gbc_tglbtnPoint.gridy = 0;
				pnlNorth.add(tglbtnPoint, gbc_tglbtnPoint);
				
				btnGroup.add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("");
		tglbtnLine.setIcon(new ImageIcon(lineIcon));
		tglbtnLine.setBorderPainted(false);
		tglbtnLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnLine.setMargin(new Insets(0, 0, 0, 0));
		tglbtnLine.setBorder(null);
		tglbtnLine.setMaximumSize(new Dimension(48, 48));
		tglbtnLine.setMinimumSize(new Dimension(48, 48));
		tglbtnLine.setForeground(Color.WHITE);
		tglbtnLine.setBackground(bgSecondary);
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Arg0) {
				showInitialColor();
				/*
				 *
				 * Ensuring that line doesn't have start point.
				 * If it has start point, that point will be removed before drawing new one
				 *
				 */
				if (tempLine!=null) {
					if (tempLine.getStartPoint()!=null) {
						pnlDrawing.getShapes().remove(tempLine.getStartPoint());
						pnlDrawing.repaint();
						hideShapeInfo();
					}
				}
				tempLine = new Line();
				setDrawingInfo("Line",0);
			}
		});
		tglbtnLine.setPreferredSize(new Dimension(48, 48));
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnLine.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnLine.gridx = 7;
		gbc_tglbtnLine.gridy = 0;
		pnlNorth.add(tglbtnLine, gbc_tglbtnLine);
		btnGroup.add(tglbtnLine);
		tglbtnRectangle.setPreferredSize(new Dimension(48, 48));
		tglbtnRectangle.setIcon(new ImageIcon(rectangleIcon));
		tglbtnRectangle.setBorderPainted(false);
		tglbtnRectangle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnRectangle.setMargin(new Insets(0, 0, 0, 0));
		tglbtnRectangle.setBorder(null);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnRectangle.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 8;
		gbc_tglbtnRectangle.gridy = 0;
		pnlNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
		btnGroup.add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("");
		tglbtnCircle.setIcon(new ImageIcon(circleIcon));
		tglbtnCircle.setBorderPainted(false);
		tglbtnCircle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnCircle.setMargin(new Insets(0, 0, 0, 0));
		tglbtnCircle.setBorder(null);
		tglbtnCircle.setMaximumSize(new Dimension(48, 48));
		tglbtnCircle.setMinimumSize(new Dimension(48, 48));
		tglbtnCircle.setForeground(Color.WHITE);
		tglbtnCircle.setBackground(bgSecondary);
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideInitialColor();
				setDrawingInfo("Circle",0);
			}
		});
		tglbtnCircle.setPreferredSize(new Dimension(48, 48));
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnCircle.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 9;
		gbc_tglbtnCircle.gridy = 0;
		pnlNorth.add(tglbtnCircle, gbc_tglbtnCircle);
		btnGroup.add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("");
		tglbtnDonut.setIcon(new ImageIcon(donutIcon));
		tglbtnDonut.setBorderPainted(false);
		tglbtnDonut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnDonut.setMargin(new Insets(0, 0, 0, 0));
		tglbtnDonut.setBorder(null);
		tglbtnDonut.setMinimumSize(new Dimension(48, 48));
		tglbtnDonut.setMaximumSize(new Dimension(48, 48));
		tglbtnDonut.setForeground(Color.WHITE);
		tglbtnDonut.setBackground(bgSecondary);
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideInitialColor();
				setDrawingInfo("Donut",0);
			}
		});
		tglbtnDonut.setPreferredSize(new Dimension(48, 48));
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.anchor = GridBagConstraints.NORTHWEST;
		gbc_tglbtnDonut.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnDonut.gridx = 10;
		gbc_tglbtnDonut.gridy = 0;
		pnlNorth.add(tglbtnDonut, gbc_tglbtnDonut);
		btnGroup.add(tglbtnDonut);
		
		lblInitialColorLabel = new JLabel("Color: ");
		lblInitialColorLabel.setVisible(false);
		lblInitialColorLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblInitialColorLabel.setPreferredSize(new Dimension(48, 48));
		lblInitialColorLabel.setMinimumSize(new Dimension(48, 48));
		lblInitialColorLabel.setMaximumSize(new Dimension(48, 48));
		lblInitialColorLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblInitialColorLabel.setForeground(fgPrimary);
		lblInitialColorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblColorLabel = new GridBagConstraints();
		gbc_lblColorLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblColorLabel.insets = new Insets(0, 100, 5, 5);
		gbc_lblColorLabel.gridx = 13;
		gbc_lblColorLabel.gridy = 0;
		pnlNorth.add(lblInitialColorLabel, gbc_lblColorLabel);
		
		lblInitialColor = new JLabel("");
		lblInitialColor.setBorder(new LineBorder(borderPrimary, 1, true));
		lblInitialColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setInitialColor(UserInterface.getColorFromJColorChooser("Choose border color", 
												getInitialColor()));
			}
		});
		lblInitialColor.setVisible(false);
		lblInitialColor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInitialColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblInitialColor.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblInitialColor.setMaximumSize(new Dimension(20, 20));
		lblInitialColor.setMinimumSize(new Dimension(20, 20));
		lblInitialColor.setPreferredSize(new Dimension(20, 20));
		lblInitialColor.setOpaque(true);
		lblInitialColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		setInitialColor(Color.RED);
		GridBagConstraints gbc_lblInitialColor = new GridBagConstraints();
		gbc_lblInitialColor.anchor = GridBagConstraints.EAST;
		gbc_lblInitialColor.insets = new Insets(5, 0, 5, 0);
		gbc_lblInitialColor.gridx = 14;
		gbc_lblInitialColor.gridy = 0;
		pnlNorth.add(lblInitialColor, gbc_lblInitialColor);
		
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(bgPrimary);
		contentPane.add(pnlWest, BorderLayout.WEST);
		
		JPanel pnlEast = new JPanel();
		pnlEast.setPreferredSize(new Dimension(200, 10));
		pnlEast.setBackground(bgPrimary);
		contentPane.add(pnlEast, BorderLayout.EAST);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setForeground(fgPrimary);
		btnModify.setBackground(bgSecondary);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Modifying shapes
				 * 
				 * getting index by selected shape, and opening appropriate dialog
				 *
				 */
				int index = pnlDrawing.getShapes().indexOf(selected);
				if (selected instanceof Point) {
					Point point = (Point)selected;
					
					DlgPoint dlgPoint = new DlgPoint();
					
					dlgPoint.getTxtPoint().setText(point.toString());
					
					dlgPoint.setIndexOfPoint(index+1);
					dlgPoint.setBorderColor(point.getColor());
					
					dlgPoint.setLocationRelativeTo(null);
					dlgPoint.setVisible(true);
					
					if (dlgPoint.isOk) { 
						pnlDrawing.getShapes().set(index, dlgPoint.tempPoint);
						pnlDrawing.repaint();
						showShapeInfo(dlgPoint.tempPoint);
					}
					
				}
				else if (selected instanceof Line) {
					Line line = (Line)selected;
					
					DlgLine dlgLine = new DlgLine();
					
					dlgLine.getTxtStartPoint().setText(line.getStartPoint().toString());
					dlgLine.getTxtEndPoint().setText(line.getEndPoint().toString());
					
					dlgLine.setIndexOfLine(index+1);
					dlgLine.setBorderColor(line.getColor());
					
					dlgLine.setLocationRelativeTo(null);
					dlgLine.setVisible(true);
					
					if (dlgLine.isOk) {
						pnlDrawing.getShapes().set(index, dlgLine.tempLine);
						pnlDrawing.repaint();
						showShapeInfo(dlgLine.tempLine);
					}
					
				}
				else if (selected instanceof Rectangle) {
					Rectangle rectangle = (Rectangle)selected;					
					
					DlgRectangle dlgRectangle = new DlgRectangle();
					
					dlgRectangle.getTxtUpperLeftPoint().setText(rectangle.getUpperLeftPoint().toString());
					dlgRectangle.getTxtWidth().setText(Integer.toString(rectangle.getWidth()));
					dlgRectangle.getTxtHeight().setText(Integer.toString(rectangle.getHeight()));
					dlgRectangle.setIndexOfRectangle(index+1);
					dlgRectangle.setBorderColor(rectangle.getColor());
					dlgRectangle.setInnerColor(rectangle.getInnerColor());
					dlgRectangle.setLocationRelativeTo(null);
					dlgRectangle.setVisible(true);
					
					if (dlgRectangle.isOk) {
						dlgRectangle.tempRectangle.setSelected(true);
						pnlDrawing.getShapes().set(index, dlgRectangle.tempRectangle);
						pnlDrawing.repaint();
						showShapeInfo(dlgRectangle.tempRectangle);
					}
				}
				else if (selected instanceof Circle && !(selected instanceof Donut)) {
					Circle circle = (Circle)selected;

					DlgCircle dlgCircle = new DlgCircle();
					
					dlgCircle.getTxtCenterPoint().setText(circle.getCenter().toString());
					dlgCircle.getTxtRadius().setText(Integer.toString(circle.getRadius()));
					dlgCircle.setInnerColor(circle.getInnerColor());
					dlgCircle.setBorderColor(circle.getColor());
					dlgCircle.setIndexOfCircle(index+1);
					dlgCircle.setLocationRelativeTo(null);
					dlgCircle.setVisible(true);
					
					if (dlgCircle.isOk) {
						dlgCircle.tempCircle.setSelected(true);
						pnlDrawing.getShapes().set(index, dlgCircle.tempCircle);
						pnlDrawing.repaint();
						showShapeInfo(dlgCircle.tempCircle);
					}
				}
				else if (selected instanceof Donut) {
					Donut donut = (Donut)selected;

					DlgDonut dlgDonut = new DlgDonut();
					
					dlgDonut.getTxtCenterPoint().setText(donut.getCenter().toString());
					dlgDonut.getTxtRadius().setText(Integer.toString(donut.getRadius()));
					dlgDonut.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
					dlgDonut.setInnerColor(donut.getInnerColor());
					dlgDonut.setBorderColor(donut.getColor());
					dlgDonut.setIndexOfDonut(index+1);
					dlgDonut.setLocationRelativeTo(null);
					dlgDonut.setVisible(true);
					
					if (dlgDonut.isOk) {
						dlgDonut.tempDonut.setSelected(true);
						pnlDrawing.getShapes().set(index, dlgDonut.tempDonut);
						pnlDrawing.repaint();
						showShapeInfo(dlgDonut.tempDonut);
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1, 2));
		
		
		lblCount = new JLabel("Number of elements: 0");
		lblCount.setForeground(fgPrimary);
		lblCount.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(fgPrimary);
		btnDelete.setBackground(bgSecondary);
		btnDelete.addActionListener(new ActionListener() {
			/*
			 *
			 * Removing shape by calling deleteShape() method
			 * 
			 * User is asked for permission to delete
			 * 
			 */
			public void actionPerformed(ActionEvent Arg0) {
				if (dlm.getSize() <= 0){
					JOptionPane.showMessageDialog(null, "List is empty!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (selected == null) {
					JOptionPane.showMessageDialog(null, "There is no object selected!",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					int elementAt = list.getSelectedIndex();
					String selectedElement = dlm.get(elementAt);
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete " + 
													selectedElement + " ?","Warning", dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						deleteShape(elementAt);
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		lblSelected = new JLabel("");
		lblSelected.setForeground(fgPrimary);
		lblSelected.setHorizontalTextPosition(SwingConstants.LEFT);
		lblSelected.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblShapeInfo = new JLabel("");
		lblShapeInfo.setForeground(fgSecondary);
		lblShapeInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblShapeInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblBorderColor = new JLabel("");
		lblBorderColor.setVisible(false);
		lblBorderColor.setBorder(new LineBorder(borderPrimary));
		lblBorderColor.setOpaque(true);
		lblBorderColor.setBackground(bgPrimary);
		lblBorderColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblBorderColorLabel = new JLabel("Border color: ");
		lblBorderColorLabel.setVisible(false);
		lblBorderColorLabel.setForeground(fgPrimary);
		lblBorderColorLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBorderColorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblInnerColorLabel = new JLabel("Inner color:");
		lblInnerColorLabel.setVisible(false);
		lblInnerColorLabel.setForeground(fgPrimary);
		lblInnerColorLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblInnerColorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblInnerColor = new JLabel("");
		lblInnerColor.setBorder(new LineBorder(borderSecondary));
		lblInnerColor.setVisible(false);
		lblInnerColor.setOpaque(true);
		lblInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInnerColor.setBackground(bgPrimary);
		
		GroupLayout gl_pnlEast = new GroupLayout(pnlEast);
		gl_pnlEast.setHorizontalGroup(
			gl_pnlEast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlEast.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlEast.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlEast.createSequentialGroup()
							.addGroup(gl_pnlEast.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addGroup(gl_pnlEast.createSequentialGroup()
									.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblCount, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(lblSelected, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(lblShapeInfo, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
							.addGap(12))
						.addGroup(gl_pnlEast.createSequentialGroup()
							.addGap(0, 0, Short.MAX_VALUE)
							.addGroup(gl_pnlEast.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlEast.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInnerColorLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInnerColor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlEast.createSequentialGroup()
									.addComponent(lblBorderColorLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblBorderColor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGap(71))))
		);
		gl_pnlEast.setVerticalGroup(
			gl_pnlEast.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlEast.createSequentialGroup()
					.addGroup(gl_pnlEast.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModify)
						.addComponent(btnDelete))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCount, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSelected, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblShapeInfo, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlEast.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInnerColor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInnerColorLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlEast.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblBorderColorLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		list = new JList<String>();
		list.setBackground(bgSecondary);
		list.setForeground(fgPrimary);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * Selecting shape on panel when user select shape at dlm
				 */
				unselectAllShapes();
				
				if (dlm.getSize() <= 0) {
					return;
				}
				
				int index = list.getSelectedIndex();
				Shape s = pnlDrawing.getShapes().get(index);
				s.setSelected(true);
				pnlDrawing.repaint();
				
				showShapeInfo(s);
				
			}
		});
		list.setBorder(null);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(dlm);
		pnlEast.setLayout(gl_pnlEast);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(10, 40));
		pnlSouth.setBackground(bgPrimary);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JLabel lblMousePosition = new JLabel("Mouse position:");
		lblMousePosition.setForeground(fgSecondary);
		lblMousePosition.setAlignmentX(0.5f);
		lblMousePosition.setFont(new Font("Tahoma", Font.PLAIN, 14));		
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(fgSecondary);
		lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInfo.setAlignmentX(0.5f);
		
		GroupLayout gl_pnlSouth = new GroupLayout(pnlSouth);
		gl_pnlSouth.setHorizontalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMousePosition)
					.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
					.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(197))
		);
		gl_pnlSouth.setVerticalGroup(
			gl_pnlSouth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlSouth.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_pnlSouth.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMousePosition)
						.addComponent(lblInfo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		pnlSouth.setLayout(gl_pnlSouth);
		pnlDrawing = new PnlDrawing();	
		pnlDrawing.addMouseMotionListener(new MouseMotionAdapter() {
			/*
			 *  We are using this method to change cursor when moving over the objects at the panel
			 *  
			 *  When there is no object bellow, cursor should be cross,
			 *  and where is shape bellow, it should turn to hand cursor
			 *  
			 *  This changing should be done without flickering and lag
			 */
			@Override
			public void mouseMoved(MouseEvent e) {
				lblMousePosition.setText("Mouse position: " + 
							Integer.toString(e.getX()) + ", " + Integer.toString(e.getY()));

				if (tglbtnSelect.isSelected()) {
					int size = pnlDrawing.getShapes().size();
					
					/*
					 * Iterating from last element in shape list because of flickering;
					 * Flickering happens because iterating from first element may detect another elements 
					 * on lower positions that contain our mouse cursor point
					 */
					
					for (int i = size-1; i>=0; i--) {
						if (pnlDrawing.getShapes().get(i).contains(e.getX(), e.getY())) {
							pnlDrawing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							return; //NAJJACA STVAR BRE
						} else {
							pnlDrawing.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));					
						}
					}		
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				/*
				 * This method is used for moving shapes by clicking on them 
				 * and dragging around the panel
				 * 
				 * It is implemented by calling moveBy method defined in Moveable interface
				 * 
				 * When mouse button is released, shape information (coordinates) will be updated
				 * 
				 */
				if (selected == null) {
					return;
				}
				if (SwingUtilities.isLeftMouseButton(e)) {
					if (selected == null) {
						return;
					}
					if (SwingUtilities.isLeftMouseButton(e)) {
				        selected.moveBy(e.getX()-x, e.getY()-y);
				        x=e.getX();
				        y=e.getY();
				        pnlDrawing.repaint();
				    }
			    }
			}
		});
				
		pnlDrawing.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				unselectAllShapes();
				
				hideShapeInfo();
				
				pnlDrawing.repaint();
				
				// Iterating from last element down to first, because on shapes intersections we want to select last shape
				int size = pnlDrawing.getShapes().size();
				
				if (tglbtnSelect.isSelected()) {
					for (int i = size-1; i>=0; i--) {
						if (pnlDrawing.getShapes().get(i).contains(e.getX(), e.getY())) {	
							pnlDrawing.getShapes().get(i).setSelected(true);
							selected = pnlDrawing.getShapes().get(i);
							list.ensureIndexIsVisible(i);
							showShapeInfo(selected);
							pnlDrawing.repaint();		
							return;						
						} 
					}
				}
				
				/*
				 *
				 * This part will check if line drawing was started but switched to different object before it's finished,
				 * then it will remove start point of that line and continue to draw new selected object
				 * 
				 * It is surrounded by try-catch because it may happen to throw unusual exception,
				 * Null pointers are slippery slope
				 *
				 */
				try {
					if (tempLine!=null) {
						if (!tglbtnLine.isSelected() && tempLine.getStartPoint() != null) {		
							pnlDrawing.getShapes().remove(tempLine.getStartPoint());
							pnlDrawing.repaint();
							tempLine.setStartPoint(null);
						}
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
				
				// In code bellow we draw shapes by user's selection choice
				if (tglbtnPoint.isSelected() && selected==null) {
					dlm.addElement(getDlmShapeText("Point"));
					pnlDrawing.getShapes().add(new Point(e.getX(), e.getY(), false, getInitialColor()));					
				}
				if (tglbtnLine.isSelected() && selected==null) {
					if (tempLine.getStartPoint() == null) {
						tempLine.setStartPoint(new Point(e.getX(), e.getY(), false, getInitialColor()));
						pnlDrawing.getShapes().add(tempLine.getStartPoint());
						pnlDrawing.repaint();
						showShapeInfo(tempLine.getStartPoint());
						setDrawingInfo("Line",1);
					} else {
						tempLine.setEndPoint(new Point(e.getX(), e.getY(), false, getInitialColor()));
						pnlDrawing.getShapes().add(tempLine.getEndPoint());						
						pnlDrawing.repaint();
						tempLine.setSelected(false);
						tempLine.setColor(getInitialColor());
						dlm.addElement((dlm.getSize() + 1) + ". Line");
						pnlDrawing.getShapes().remove(pnlDrawing.getShapes().size()-1);
						pnlDrawing.getShapes().remove(pnlDrawing.getShapes().size()-1);
						
						pnlDrawing.getShapes().add(tempLine);
						
						tempLine = null;
						tempLine = new Line();
						setDrawingInfo("Line",0);
					}
				}
				if (tglbtnRectangle.isSelected() && selected==null) {
					Point upperLeftPoint = new Point(e.getX(), e.getY(), false, Color.red);
					
					// Drawing point at click position
					pnlDrawing.getShapes().add(upperLeftPoint);
					showShapeInfo(upperLeftPoint);
					pnlDrawing.repaint();
					
					setDrawingInfo("Rectangle",1);
					
					DlgRectangle dlgRectangle = new DlgRectangle();
					
					dlgRectangle.getTxtUpperLeftPoint().setText(upperLeftPoint.toString());
					dlgRectangle.getTxtWidth().setText("");
					dlgRectangle.getTxtHeight().setText("");
					dlgRectangle.setInnerColor(Color.white);
					dlgRectangle.setBorderColor(Color.black);
					
					dlgRectangle.setIndexOfRectangle(pnlDrawing.getShapes().size());
					
					dlgRectangle.setLocationRelativeTo(null);
					
					dlgRectangle.setVisible(true);
					
					if (dlgRectangle.isOk) {
						pnlDrawing.getShapes().add(dlgRectangle.tempRectangle);
						dlm.addElement(getDlmShapeText("Rectangle"));
					}
					
					pnlDrawing.getShapes().remove(upperLeftPoint);	
					setDrawingInfo("Rectangle",0);
					hideShapeInfo();
				}
				if (tglbtnCircle.isSelected() && selected==null) {
					Point circleCenter = new Point(e.getX(), e.getY(), false, Color.red);
					
					// Drawing point at click position
					pnlDrawing.getShapes().add(circleCenter);
					showShapeInfo(circleCenter);
					pnlDrawing.repaint();
					
					setDrawingInfo("Circle",1);
					
					DlgCircle dlgCircle = new DlgCircle();
					
					dlgCircle.getTxtCenterPoint().setText(circleCenter.toString());
					dlgCircle.getTxtRadius().setText("");
					dlgCircle.setInnerColor(Color.white);
					dlgCircle.setBorderColor(Color.black);
					dlgCircle.setIndexOfCircle(pnlDrawing.getShapes().size());
					dlgCircle.setLocationRelativeTo(null);
					dlgCircle.setVisible(true);
					
					if (dlgCircle.isOk) {
						pnlDrawing.getShapes().add(dlgCircle.tempCircle);
						dlm.addElement(getDlmShapeText("Circle"));
					}
					
					pnlDrawing.getShapes().remove(circleCenter);
					setDrawingInfo("Circle",0);
					hideShapeInfo();
				}
				if (tglbtnDonut.isSelected() && selected==null) {
					Point donutCenter = new Point(e.getX(), e.getY(), false, Color.red);
					
					// Drawing point at click position
					pnlDrawing.getShapes().add(donutCenter);
					showShapeInfo(donutCenter);
					pnlDrawing.repaint();
					
					setDrawingInfo("Donut",1);
					
					DlgDonut dlgDonut = new DlgDonut();
					
					dlgDonut.getTxtCenterPoint().setText(donutCenter.toString());
					dlgDonut.getTxtRadius().setText("");
					dlgDonut.getTxtInnerRadius().setText("");
					dlgDonut.setBorderColor(Color.black);
					dlgDonut.setInnerColor(Color.white);
					dlgDonut.setIndexOfDonut(pnlDrawing.getShapes().size());
					dlgDonut.setLocationRelativeTo(null);
					dlgDonut.setVisible(true);
					
					if (dlgDonut.isOk) {
						pnlDrawing.getShapes().add(dlgDonut.tempDonut);
						dlm.addElement(getDlmShapeText("Donut"));
					}
					
					pnlDrawing.getShapes().remove(donutCenter);
					setDrawingInfo("Donut",0);
					hideShapeInfo();
				}
				showNumberOfElements();
				list.ensureIndexIsVisible(dlm.getSize()-1);
				pnlDrawing.repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// getting current coordinates
				if (selected != null) {
					x = e.getX();
					y = e.getY();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {		
				/* 
				 * Updating shape information when mouse button is released
				 * 
				 * Used when moving objects around the panel
				 * 
				 */
				if (selected!=null) {
					showShapeInfo(selected);
				}

			}
		});		
		pnlDrawing.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlDrawing.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		pnlDrawing.setBackground(Color.white);					
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		// pnlDrawing's layout must be set to null for displaying label with printing information (date-time stamp and project name)
		pnlDrawing.setLayout(null);
	}
	
	/*
	 *
	 * This method exports panel to PNG image
	 * 
	 * Early described method 'getFileFromJFileChooser' is used for choosing destination file
	 * 
	 * If file exists it will be removed and then recreated
	 *
	 */
	private void exportAsPng() {
		try {
			unselectAllShapes();
			
			pnlDrawing.repaint();
			
			Dimension size = pnlDrawing.getSize();
			BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			pnlDrawing.paint(g);
		
			File fileToExport = getFileFromJFileChooser("Select where to export PNG", 
					  new FileNameExtensionFilter("PNG images", "png", "png"), 
					  ".png", 1);
			
			if (fileToExport==null) {
				return;
			}
			
			if (fileToExport.exists()) {
				fileToExport.delete();
			}
			
			ImageIO.write(image, "png", fileToExport);
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
     *
	 * Method used for deleting shape from panel
	 * 
	 * It is removed from pnlDrawing.getShapes() list and from dlm
	 * 
	 * Ordinal numbers in dlm are updated at the end
	 * 
	 */
	private void deleteShape(int elementAt) {
		try {	
			dlm.remove(elementAt);
			showNumberOfElements();
			pnlDrawing.getShapes().remove(elementAt);
			pnlDrawing.repaint();
			
			hideShapeInfo();
			
			// Updating ordinal numbers in list
			for (int i=0; i<dlm.getSize(); i++) {
				Shape s = pnlDrawing.getShapes().get(i);
				
				if (s instanceof Point) {
					dlm.set(i, (i+1) + ". Point");
				} else if (s instanceof Line) {
					dlm.set(i, (i+1) + ". Line");
				} else if (s instanceof Rectangle) {
					dlm.set(i, (i+1) + ". Rectangle");
				} else if (s instanceof Circle) {
					dlm.set(i, (i+1) + ". Circle");
				} else if (s instanceof Donut) {
					dlm.set(i, (i+1) + ". Donut");
				}
			}
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "There is no object selected!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* 
	 * Hiding shape information 
	 */
	private void hideShapeInfo() {
		
		lblSelected.setText("");
		lblShapeInfo.setText("");

		lblInnerColorLabel.setVisible(false);
		lblInnerColor.setVisible(false);
		
		lblBorderColor.setVisible(false);
		lblBorderColorLabel.setVisible(false);
		
		list.clearSelection();
		
		selected = null;
		
	}
	
	/*
	 * 
	 * Showing shape information
	 * 
	 * DecimalFormat class is used for rounding number to 2 decimals
	 * 
	 * Depending on shape type, certain information will be displayed or hidden
	 * 
	 * Html is used to format labels
	 * 
	 */
	private void showShapeInfo(Shape shape) {
		DecimalFormat df = new DecimalFormat("#.00");
		
		hideShapeInfo();
		
		selected = shape;
		
		lblBorderColor.setBackground(shape.getColor());
		lblBorderColor.setVisible(true);
		lblBorderColorLabel.setVisible(true);
		
		if (shape instanceof SurfaceShape) {
			lblInnerColor.setBackground(((SurfaceShape)shape).getInnerColor());
			lblInnerColorLabel.setVisible(true);
			lblInnerColor.setVisible(true);
		}
		
		if (shape instanceof Line) {
			int index = pnlDrawing.getShapes().indexOf(shape);
			list.setSelectedIndex(index);
			lblSelected.setText("Element type: Line");
			
			Line line = (Line)shape;
			lblShapeInfo.setText("<html>Start point: " + line.getStartPoint() 
								+"<br>End point: " + line.getEndPoint() 
								+"<br>Length: " + df.format(line.length()) 
								+"</html>");
			return;
		}	
		if (shape instanceof Point) {
			int index = pnlDrawing.getShapes().indexOf(shape);
			list.setSelectedIndex(index);
			lblSelected.setText("Element type: Point");
			
			Point point = (Point)shape;
			lblShapeInfo.setText("Position: " + point);
			return;
		}
		if (shape instanceof Rectangle) {
			int index = pnlDrawing.getShapes().indexOf(shape);
			list.setSelectedIndex(index);
			lblSelected.setText("Element type: Rectangle");
			
			Rectangle rectangle = (Rectangle)shape;
			lblShapeInfo.setText("<html>Upper left point: " + rectangle.getUpperLeftPoint() 
								+"<br>Width: " + rectangle.getWidth()
								+"<br>Height: " + rectangle.getHeight()
								+"<br>Area: " + df.format(rectangle.area())
								+"<br>Circumference: " + df.format(2 * (rectangle.getWidth() + rectangle.getHeight()))															
								+"</html>");																	
			return;
		}
		// Ensuring that selected element isn't donut because donut is inherited from circle
		if (shape instanceof Circle && (!(shape instanceof Donut))) {
			int index = pnlDrawing.getShapes().indexOf(shape);
			list.setSelectedIndex(index);
			lblSelected.setText("Element type: Circle");
			
			Circle circle = (Circle)shape;
			lblShapeInfo.setText("<html>Center point: " + circle.getCenter()
								+"<br>Radius: " + circle.getRadius()
								+"<br>Area: " + df.format(circle.area())
								+"<br>Circumference: " + df.format(2 * circle.getRadius() * Math.PI)															
								+"</html>");
			return;
		}
		if (shape instanceof Donut) {
			int index = pnlDrawing.getShapes().indexOf(shape);
			list.setSelectedIndex(index);
			lblSelected.setText("Element type: Donut");
			
			Donut donut = (Donut)shape;
			lblShapeInfo.setText("<html>Center point: " + donut.getCenter()
								+"<br>Radius: " + donut.getRadius()
								+"<br>Inner radius: " + donut.getInnerRadius()
								+"<br>Area: " + df.format(donut.area())
								+"<br>Circumference: " + df.format(2 * donut.getRadius() * Math.PI)															
								+"<br>Inner circumference: " + df.format(2 * donut.getInnerRadius() * Math.PI)															
								+"</html>");
			return;
		}
	}
	
	/* 
	 * Getting display text for new shape (together with ordinal number)
     */
	private String getDlmShapeText(String shape) {
		String num = Integer.toString(dlm.getSize()+1);
		return num + ". " + shape;
	}
	
	/*
	 * This method returns File that user selected in file system
	 * 
	 * It is used both for saving and opening files 
	 * 
	 * dialogType should be 0 if we want to open project and 1 if we want to save png or project
	 * 
	 * When user saves the project, it will be checked if extension '.drawing' is entered
	 * If it is not, it will be automatically added so user does not have to think about that
	 * Same is for exporting PNG
	 */
	private File getFileFromJFileChooser(String dialogTitle, FileNameExtensionFilter extensionFilter, String extension, int dialogType) {
		try {
			JFrame parentFrame = new JFrame();
		 
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle(dialogTitle);   
			FileNameExtensionFilter filter = extensionFilter;
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			
			int userSelection = 0;
			
			if (dialogType==0) { 
				userSelection = fileChooser.showOpenDialog(parentFrame); 
			} else if (dialogType==1) {
				fileChooser.setSelectedFile(new File(projectName));
				userSelection = fileChooser.showSaveDialog(parentFrame);
			}
		
			String filePath = "";
		
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
		    
				filePath = file.getAbsolutePath();
		    
				if ((!filePath.endsWith(extension)) && dialogType==1) {
					filePath += extension;
				}
		    
				return new File(filePath);
		    		    
			} else {
				return null;
			}
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	/* 
	 * Setting appropriate text messages in lblInfo label 
	 */
	private void setDrawingInfo(String shape, int step) {
		//clear info
		lblInfo.setText("");
		
		switch(shape) {
			case "Point": {
				lblInfo.setText("Select point location!");
				break;
			}
			case "Line": {
				if (step==0) {
					lblInfo.setText("Select start point of line!");
				}
				else if (step==1) {
					lblInfo.setText("Select end point of line!");
				}
				break;
			}
			case "Rectangle": {
				if (step==0) {
					lblInfo.setText("Select upper left point of rectangle!");
				}
				else if (step==1) {
					lblInfo.setText("Enter width and height of rectangle!");
				}
				break;
			}
			case "Circle": {
				if (step==0) {
					lblInfo.setText("Select center of circle!");
				}
				else if (step==1) {
					lblInfo.setText("Enter radius of circle!");
				}
				break;
			}
			case "Donut": {
				if (step==0) {
					lblInfo.setText("Select center of donut!");
				}
				else if (step==1) {
					lblInfo.setText("Enter radius and inner radius of donut!");
				}
				break;
			}			
		}
	}
	
	/* 
	 * Unselecting all shapes in pnlDrawing.getShapes() list 
	 */
	private void unselectAllShapes() {
		for (Shape s: pnlDrawing.getShapes()) {
			s.setSelected(false);
			pnlDrawing.repaint();
		}
	}
	
	/* 
	 * Hiding initial color 
	 */
	private void hideInitialColor() {
		lblInitialColorLabel.setVisible(false);
		lblInitialColor.setVisible(false);
	}
	
	/* 
	 * Showing initial color
	 */
	private void showInitialColor() {
		lblInitialColorLabel.setVisible(true);
		lblInitialColor.setVisible(true);
	}
	
	/*
	 *  Showing number of elements in list
	 */
	private void showNumberOfElements() {
		lblCount.setText("Number of elements: " + dlm.getSize());
	}
	
	/* 
	 * Getting current time stamp together with project name
	 * 
	 * Used for printing images
	 */
	private String getPrintInfo() {
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String strTimeStamp = dateFormat.format(date);  
		return (strTimeStamp + " - " + projectName);	
	}
	
	/*
	 * Setting project file and name
	 */
	private void setProject(File file, String name) {
		this.projectFile = file;
		this.projectName = name;
		lblInfo.setText("Project: " + projectName);	
	}
	
	/* 
	 * Resetting project file to null and name to 'untitled.drawing'
	 */
	private void resetProject() {
		this.projectFile = null;
		this.projectName = "untitled.drawing";
	}
	
	/* 
	 * Getting initial color
	 */
	private Color getInitialColor() {
		return lblInitialColor.getBackground();
	}
	
	/* 
	 * Setting initial color
	 */
	private void setInitialColor(Color color) {
		lblInitialColor.setBackground(color);
	}
}
