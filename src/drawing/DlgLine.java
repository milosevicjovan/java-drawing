package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.event.FocusListener;

public class DlgLine extends JDialog {

	// Dialog
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	boolean isOk;

	// Text fields
	private JTextField txtStartPoint;
	private JTextField txtEndPoint;
	
	// Labels
	private JLabel lblBorderColor;
	private JLabel lblNumber;	
	
	// Shape
	Line tempLine;	
	
	// UI colors
	private Color bgPrimary = UserInterface.bgPrimary;
	private Color bgSecondary = UserInterface.bgSecondary;
	private Color fgPrimary = UserInterface.fgPrimary;
	private Color fgSecondary = UserInterface.fgSecondary;
	private Color borderPrimary = UserInterface.borderPrimary;
	private Color borderSecondary = UserInterface.borderSecondary;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Line");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 305, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(bgPrimary);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{119, 136, 0};
		gbl_contentPanel.rowHeights = new int[]{17, 44, 30, 30, 30, 24, 24, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		lblNumber = new JLabel("Index in stack: ");
		lblNumber.setForeground(fgSecondary);
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.anchor = GridBagConstraints.WEST;
		gbc_lblNumber.insets = new Insets(0, 20, 5, 5);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 1;
		contentPanel.add(lblNumber, gbc_lblNumber);
		
		JLabel lblStartPoint = new JLabel("Start point:");
		lblStartPoint.setForeground(fgPrimary);
		lblStartPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.anchor = GridBagConstraints.WEST;
		gbc_lblStartPoint.insets = new Insets(0, 20, 5, 5);
		gbc_lblStartPoint.gridx = 0;
		gbc_lblStartPoint.gridy = 2;
		contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		
		FocusListener focusHighlighter = UserInterface.focusHighlighter;
		
		txtStartPoint = new JTextField();
		txtStartPoint.addFocusListener(focusHighlighter);
		txtStartPoint.setBackground(bgSecondary);
		txtStartPoint.setForeground(fgPrimary);
		txtStartPoint.setBorder(new LineBorder(borderSecondary, 1));
		txtStartPoint.setMinimumSize(new Dimension(50, 30));
		txtStartPoint.setMaximumSize(new Dimension(50, 30));
		txtStartPoint.setPreferredSize(new Dimension(50, 30));
		txtStartPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtStartPoint.setColumns(10);
		GridBagConstraints gbc_txtStartPoint = new GridBagConstraints();
		gbc_txtStartPoint.fill = GridBagConstraints.BOTH;
		gbc_txtStartPoint.insets = new Insets(0, 0, 5, 0);
		gbc_txtStartPoint.gridx = 1;
		gbc_txtStartPoint.gridy = 2;
		contentPanel.add(txtStartPoint, gbc_txtStartPoint);
		
		JLabel lblEndPoint = new JLabel("End point:");
		lblEndPoint.setForeground(fgPrimary);
		lblEndPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.anchor = GridBagConstraints.WEST;
		gbc_lblEndPoint.insets = new Insets(0, 20, 5, 5);
		gbc_lblEndPoint.gridx = 0;
		gbc_lblEndPoint.gridy = 3;
		contentPanel.add(lblEndPoint, gbc_lblEndPoint);
		
		txtEndPoint = new JTextField();
		txtEndPoint.setMaximumSize(new Dimension(50, 30));
		txtEndPoint.setMinimumSize(new Dimension(50, 30));
		txtEndPoint.addFocusListener(focusHighlighter);
		txtEndPoint.setBackground(bgSecondary);
		txtEndPoint.setForeground(fgPrimary);
		txtEndPoint.setBorder(new LineBorder(borderSecondary, 1));
		txtEndPoint.setPreferredSize(new Dimension(50, 30));
		txtEndPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEndPoint.setColumns(10);
		GridBagConstraints gbc_txtEndPoint = new GridBagConstraints();
		gbc_txtEndPoint.fill = GridBagConstraints.BOTH;
		gbc_txtEndPoint.insets = new Insets(0, 0, 5, 0);
		gbc_txtEndPoint.gridx = 1;
		gbc_txtEndPoint.gridy = 3;
		contentPanel.add(txtEndPoint, gbc_txtEndPoint);
		
		JLabel lblBorderColorLabel = new JLabel("Border color:");
		lblBorderColorLabel.setForeground(fgPrimary);
		lblBorderColorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblBorderColorLabel = new GridBagConstraints();
		gbc_lblBorderColorLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBorderColorLabel.insets = new Insets(0, 20, 5, 5);
		gbc_lblBorderColorLabel.gridx = 0;
		gbc_lblBorderColorLabel.gridy = 6;
		contentPanel.add(lblBorderColorLabel, gbc_lblBorderColorLabel);
		
		lblBorderColor = new JLabel("");
		lblBorderColor.setPreferredSize(new Dimension(0, 20));
		lblBorderColor.setBorder(new LineBorder(borderPrimary));
		lblBorderColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBorderColor.setBackground(Color.black);
		lblBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setBorderColor(UserInterface.getColorFromJColorChooser("Choose border color", getBorderColor()));
			}
		});
		lblBorderColor.setOpaque(true);
		lblBorderColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblBorderColor = new GridBagConstraints();
		gbc_lblBorderColor.fill = GridBagConstraints.BOTH;
		gbc_lblBorderColor.insets = new Insets(0, 0, 5, 0);
		gbc_lblBorderColor.gridx = 1;
		gbc_lblBorderColor.gridy = 6;
		contentPanel.add(lblBorderColor, gbc_lblBorderColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(bgSecondary);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(bgPrimary);
				okButton.setForeground(fgSecondary);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if (txtStartPoint.getText().trim().isEmpty() ||
								txtEndPoint.getText().trim().isEmpty())
						{
							
								isOk = false;
								JOptionPane.showMessageDialog(null, "Some fields are empty!",
														"Error", JOptionPane.ERROR_MESSAGE);
							
								return;
						} 
						
						try {
							
							StringBuilder sb;
							sb = new StringBuilder(txtStartPoint.getText());
							
							sb.deleteCharAt(0);
							sb.deleteCharAt(sb.length()-1);
							
							String strPoint;
							
							strPoint = sb.toString();
							
							int x; 
							int y; 
							
							x = Integer.parseInt((strPoint.split(","))[0]);
							y = Integer.parseInt(((strPoint.split(","))[1]).trim());
							
							tempLine = new Line();
							
							tempLine.setStartPoint(new Point(x, y));
							
							sb = new StringBuilder(txtEndPoint.getText());
							sb.deleteCharAt(0);
							sb.deleteCharAt(sb.length()-1);
							strPoint = sb.toString();
							
							x = Integer.parseInt((strPoint.split(","))[0]);
							y = Integer.parseInt(((strPoint.split(","))[1]).trim());
							
							tempLine.setEndPoint(new Point(x,y));
							// tempLine selected is set to true because this dialog will be open only when certain line is selected.
							// When the dialog is closed, we want that line to stay selected.
							tempLine.setSelected(true);
							tempLine.setColor(getBorderColor());
																																								
							isOk = true;
							setVisible(false);
						} 
						catch (Exception ex) {
							isOk = false;
							//	If user enters invalid data, detailed message will be displayed in MessageDialog,
							//	because every shape throws exception for invalid values.
							//	x and y coordinates for both start and end point must be greater or equal to 0.
							JOptionPane.showMessageDialog(null, "The values you entered are not valid!\n" + ex.getMessage(),
																"Error", JOptionPane.ERROR_MESSAGE);							
						}						
					}
				});
				okButton.setPreferredSize(new Dimension(80, 30));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(bgPrimary);
				cancelButton.setForeground(fgSecondary);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setPreferredSize(new Dimension(80, 30));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtStartPoint() {
		return txtStartPoint;
	}

	public void setTxtStartPoint(JTextField txtStartPoint) {
		this.txtStartPoint = txtStartPoint;
	}

	public JTextField getTxtEndPoint() {
		return txtEndPoint;
	}

	public void setTxtEndPoint(JTextField txtEndPoint) {
		this.txtEndPoint = txtEndPoint;
	}

	public void setIndexOfLine(int index) {
		this.lblNumber.setText(Integer.toString(index) + ".");
	}
	
	public void setBorderColor(Color color) {
		this.lblBorderColor.setBackground(color);
	}
	
	public Color getBorderColor() {
		return lblBorderColor.getBackground();
	}
}
