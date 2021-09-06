package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

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

public class DlgRectangle extends JDialog {
	
	/**
	 * 
	 */

	// Dialog
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	boolean isOk;
		
	// Text fields
	private JTextField txtUpperLeftPoint;
	private JTextField txtWidth;
	private JTextField txtHeight;
	
	// Labels
	private JLabel lblBorderColor;
	private JLabel lblInnerColor;
	private JLabel lblNumber;

	// Shape
	Rectangle tempRectangle;
	
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
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setTitle("Rectangle");
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
		gbc_lblNumber.insets = new Insets(0, 15, 5, 5);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 1;
		contentPanel.add(lblNumber, gbc_lblNumber);
		
		JLabel lblUpperLeftPoint = new JLabel("Upper left point: ");
		lblUpperLeftPoint.setForeground(fgPrimary);
		lblUpperLeftPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblUpperLeftPoint = new GridBagConstraints();
		gbc_lblUpperLeftPoint.anchor = GridBagConstraints.WEST;
		gbc_lblUpperLeftPoint.insets = new Insets(0, 15, 5, 5);
		gbc_lblUpperLeftPoint.gridx = 0;
		gbc_lblUpperLeftPoint.gridy = 2;
		contentPanel.add(lblUpperLeftPoint, gbc_lblUpperLeftPoint);
		
		FocusListener focusHighlighter = UserInterface.focusHighlighter;
		
		txtUpperLeftPoint = new JTextField();
		txtUpperLeftPoint.addFocusListener(focusHighlighter);
		txtUpperLeftPoint.setBackground(bgSecondary);
		txtUpperLeftPoint.setForeground(fgPrimary);
		txtUpperLeftPoint.setBorder(new LineBorder(borderSecondary, 1));
		txtUpperLeftPoint.setMinimumSize(new Dimension(50, 30));
		txtUpperLeftPoint.setMaximumSize(new Dimension(50, 30));
		txtUpperLeftPoint.setPreferredSize(new Dimension(50, 30));
		txtUpperLeftPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtUpperLeftPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUpperLeftPoint.setColumns(10);
		GridBagConstraints gbc_txtUpperLeftPoint = new GridBagConstraints();
		gbc_txtUpperLeftPoint.fill = GridBagConstraints.BOTH;
		gbc_txtUpperLeftPoint.insets = new Insets(0, 0, 5, 0);
		gbc_txtUpperLeftPoint.gridx = 1;
		gbc_txtUpperLeftPoint.gridy = 2;
		contentPanel.add(txtUpperLeftPoint, gbc_txtUpperLeftPoint);
		
		JLabel lblWidth = new JLabel("Width: ");
		lblWidth.setForeground(fgPrimary);
		lblWidth.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.WEST;
		gbc_lblWidth.insets = new Insets(0, 15, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 3;
		contentPanel.add(lblWidth, gbc_lblWidth);
		
		txtWidth = new JTextField();
		txtWidth.setMaximumSize(new Dimension(50, 30));
		txtWidth.setMinimumSize(new Dimension(50, 30));
		txtWidth.addFocusListener(focusHighlighter);
		txtWidth.setBackground(bgSecondary);
		txtWidth.setForeground(fgPrimary);
		txtWidth.setBorder(new LineBorder(borderSecondary, 1));
		txtWidth.setPreferredSize(new Dimension(50, 30));
		txtWidth.setHorizontalAlignment(SwingConstants.CENTER);
		txtWidth.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtWidth.setColumns(10);
		GridBagConstraints gbc_txtWidth = new GridBagConstraints();
		gbc_txtWidth.fill = GridBagConstraints.BOTH;
		gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
		gbc_txtWidth.gridx = 1;
		gbc_txtWidth.gridy = 3;
		contentPanel.add(txtWidth, gbc_txtWidth);
		
		JLabel lblHeight = new JLabel("Height: ");
		lblHeight.setForeground(fgPrimary);
		lblHeight.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.WEST;
		gbc_lblHeight.insets = new Insets(0, 15, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 4;
		contentPanel.add(lblHeight, gbc_lblHeight);
		
		txtHeight = new JTextField();
		txtHeight.setMinimumSize(new Dimension(50, 30));
		txtHeight.setMaximumSize(new Dimension(50, 30));
		txtHeight.addFocusListener(focusHighlighter);
		txtHeight.setBackground(bgSecondary);
		txtHeight.setForeground(fgPrimary);
		txtHeight.setBorder(new LineBorder(borderSecondary, 1));
		txtHeight.setPreferredSize(new Dimension(50, 30));
		txtHeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeight.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtHeight.setColumns(10);
		GridBagConstraints gbc_txtHeight = new GridBagConstraints();
		gbc_txtHeight.fill = GridBagConstraints.BOTH;
		gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
		gbc_txtHeight.gridx = 1;
		gbc_txtHeight.gridy = 4;
		contentPanel.add(txtHeight, gbc_txtHeight);
		
		JLabel lblBorderColorLabel = new JLabel("Border color:");
		lblBorderColorLabel.setForeground(fgPrimary);
		lblBorderColorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblBorderColorLabel = new GridBagConstraints();
		gbc_lblBorderColorLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBorderColorLabel.insets = new Insets(0, 15, 5, 5);
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
		
		JLabel lblInnerColorLabel = new JLabel("Inner color:");
		lblInnerColorLabel.setForeground(fgPrimary);
		lblInnerColorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblInnerColorLabel = new GridBagConstraints();
		gbc_lblInnerColorLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInnerColorLabel.insets = new Insets(0, 15, 0, 5);
		gbc_lblInnerColorLabel.gridx = 0;
		gbc_lblInnerColorLabel.gridy = 7;
		contentPanel.add(lblInnerColorLabel, gbc_lblInnerColorLabel);
		
		lblInnerColor = new JLabel("");
		lblInnerColor.setPreferredSize(new Dimension(0, 20));
		lblInnerColor.setBorder(new LineBorder(borderPrimary));
		lblInnerColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblInnerColor.setBackground(Color.white);
		lblInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setInnerColor(UserInterface.getColorFromJColorChooser("Choose inner color", getInnerColor()));
			}
		});
		lblInnerColor.setOpaque(true);
		lblInnerColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
		gbc_lblInnerColor.fill = GridBagConstraints.BOTH;
		gbc_lblInnerColor.gridx = 1;
		gbc_lblInnerColor.gridy = 7;
		contentPanel.add(lblInnerColor, gbc_lblInnerColor);
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
						
						if (txtUpperLeftPoint.getText().trim().isEmpty() ||
								txtWidth.getText().trim().isEmpty() ||
								txtHeight.getText().trim().isEmpty())
						{
							
								isOk = false;
								JOptionPane.showMessageDialog(null, "Some fields are empty!",
														"Error", JOptionPane.ERROR_MESSAGE);
							
								return;
						} 
						
						try {
							tempRectangle = new Rectangle();
							StringBuilder sb = new StringBuilder(txtUpperLeftPoint.getText());
							sb.deleteCharAt(0);
							sb.deleteCharAt(sb.length()-1);
							String strPoint = sb.toString();
							
							int x = Integer.parseInt((strPoint.split(","))[0]);
							int y = Integer.parseInt(((strPoint.split(","))[1]).trim());

							tempRectangle.setUpperLeftPoint(new Point(x,y));
							tempRectangle.setSelected(false);
							tempRectangle.setWidth(Integer.parseInt(getTxtWidth().getText().toString()));
							tempRectangle.setHeight(Integer.parseInt(getTxtHeight().getText().toString()));
							tempRectangle.setInnerColor(getInnerColor());
							tempRectangle.setColor(getBorderColor());													
							
							isOk = true;
							setVisible(false);
						} 
						catch (Exception ex) {
							isOk = false;
							//	If user enters invalid data, detailed message will be displayed in MessageDialog,
							//	because every shape throws exception for invalid values.
							//  In this case height and width must be greater than 0.
							//  x and y coordinates of upper left point must be greater or equal to 0.
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

	public JTextField getTxtUpperLeftPoint() {
		return txtUpperLeftPoint;
	}

	public void setTxtUpperLeftPoint(JTextField txtUpperLeftPoint) {
		this.txtUpperLeftPoint = txtUpperLeftPoint;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public void setIndexOfRectangle(int index) {
		this.lblNumber.setText(Integer.toString(index) + ".");
	}
	
	public void setInnerColor(Color color) {
		this.lblInnerColor.setBackground(color);
	}
	
	public Color getInnerColor() {
		return lblInnerColor.getBackground();
	}
	
	public void setBorderColor(Color color) {
		this.lblBorderColor.setBackground(color);
	}
	
	public Color getBorderColor() {
		return lblBorderColor.getBackground();
	}
}
