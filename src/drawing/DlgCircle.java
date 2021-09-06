package drawing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

public class DlgCircle extends JDialog {
	
	// Dialog
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	boolean isOk;
	
	// Text fields
	private JTextField txtCenterPoint;
	private JTextField txtRadius;
	
	// Labels
	private JLabel lblNumber;
	private JLabel lblInnerColor;
	private JLabel lblBorderColor;
	
	// Shape
	Circle tempCircle;
	
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
			DlgCircle dialog = new DlgCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle() {
		setTitle("Circle");	
		setModal(true);
		setResizable(false);
		setBackground(bgPrimary);
		setBounds(100, 100, 280, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(bgPrimary);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {121, 104, 0};
		gbl_contentPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNumber = new JLabel("Index in stack: ");
			lblNumber.setForeground(fgSecondary);
			lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNumber.insets = new Insets(0, 25, 5, 5);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 1;
		contentPanel.add(lblNumber, gbc_lblNumber);
		JLabel lblCenterPoint = new JLabel("Center point:");
		lblCenterPoint.setForeground(fgPrimary);
		lblCenterPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCenterPoint = new GridBagConstraints();
		gbc_lblCenterPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCenterPoint.insets = new Insets(0, 25, 5, 5);
		gbc_lblCenterPoint.gridx = 0;
		gbc_lblCenterPoint.gridy = 2;
		contentPanel.add(lblCenterPoint, gbc_lblCenterPoint);
		
		FocusListener focusHighlighter = UserInterface.focusHighlighter;
		
		txtCenterPoint = new JTextField();
		txtCenterPoint.addFocusListener(focusHighlighter);
		txtCenterPoint.setBackground(bgSecondary);
		txtCenterPoint.setForeground(fgPrimary);
		txtCenterPoint.setBorder(new LineBorder(borderSecondary, 1));
		txtCenterPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtCenterPoint.setPreferredSize(new Dimension(6, 30));
		txtCenterPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCenterPoint.setColumns(10);
		GridBagConstraints gbc_txtCenterPoint = new GridBagConstraints();
		gbc_txtCenterPoint.insets = new Insets(0, 0, 5, 0);
		gbc_txtCenterPoint.anchor = GridBagConstraints.NORTH;
		gbc_txtCenterPoint.fill = GridBagConstraints.BOTH;
		gbc_txtCenterPoint.gridx = 1;
		gbc_txtCenterPoint.gridy = 2;
		contentPanel.add(txtCenterPoint, gbc_txtCenterPoint);
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setForeground(fgPrimary);
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRadius.insets = new Insets(0, 25, 5, 5);
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 3;
		contentPanel.add(lblRadius, gbc_lblRadius);
		
		txtRadius = new JTextField();		
		txtRadius.addFocusListener(focusHighlighter);
		txtRadius.setBackground(bgSecondary);
		txtRadius.setForeground(fgPrimary);
		txtRadius.setBorder(new LineBorder(borderSecondary, 1));
		txtRadius.setHorizontalAlignment(SwingConstants.CENTER);
		txtRadius.setPreferredSize(new Dimension(6, 30));
		txtRadius.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtRadius.setColumns(10);
		GridBagConstraints gbc_txtRadius = new GridBagConstraints();
		gbc_txtRadius.anchor = GridBagConstraints.NORTH;
		gbc_txtRadius.fill = GridBagConstraints.BOTH;
		gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
		gbc_txtRadius.gridx = 1;
		gbc_txtRadius.gridy = 3;
		contentPanel.add(txtRadius, gbc_txtRadius);
		JLabel lblBorderColorLabel = new JLabel("Border color:");
		lblBorderColorLabel.setForeground(fgPrimary);
		lblBorderColorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblBorderColorLabel = new GridBagConstraints();
		gbc_lblBorderColorLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblBorderColorLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBorderColorLabel.insets = new Insets(0, 25, 5, 5);
		gbc_lblBorderColorLabel.gridx = 0;
		gbc_lblBorderColorLabel.gridy = 5;
		contentPanel.add(lblBorderColorLabel, gbc_lblBorderColorLabel);
		
		lblBorderColor = new JLabel("");
		lblBorderColor.setBorder(new LineBorder(borderPrimary));
		lblBorderColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBorderColor.setBackground(Color.black);
		lblBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setBorderColor(UserInterface.getColorFromJColorChooser("Choose border color", getBorderColor()));
			}
		});
		lblBorderColor.setOpaque(true);
		lblBorderColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBorderColor.setBackground(Color.BLACK);
		GridBagConstraints gbc_lblBorderColor = new GridBagConstraints();
		gbc_lblBorderColor.anchor = GridBagConstraints.WEST;
		gbc_lblBorderColor.fill = GridBagConstraints.BOTH;
		gbc_lblBorderColor.insets = new Insets(0, 0, 5, 0);
		gbc_lblBorderColor.gridx = 1;
		gbc_lblBorderColor.gridy = 5;
		contentPanel.add(lblBorderColor, gbc_lblBorderColor);
		JLabel lblInnerColorLabel = new JLabel("Inner color:");
		lblInnerColorLabel.setForeground(Color.white);
		lblInnerColorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblInnerColorLabel = new GridBagConstraints();
		gbc_lblInnerColorLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblInnerColorLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInnerColorLabel.insets = new Insets(0, 25, 5, 5);
		gbc_lblInnerColorLabel.gridx = 0;
		gbc_lblInnerColorLabel.gridy = 6;
		contentPanel.add(lblInnerColorLabel, gbc_lblInnerColorLabel);
		
		lblInnerColor = new JLabel("");
		lblInnerColor.setBorder(new LineBorder(fgPrimary));
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
		lblInnerColor.setBackground(Color.BLACK);
		GridBagConstraints gbc_lblInnerColor = new GridBagConstraints();
		gbc_lblInnerColor.insets = new Insets(0, 0, 5, 0);
		gbc_lblInnerColor.anchor = GridBagConstraints.WEST;
		gbc_lblInnerColor.fill = GridBagConstraints.BOTH;
		gbc_lblInnerColor.gridx = 1;
		gbc_lblInnerColor.gridy = 6;
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
					public void actionPerformed(ActionEvent e) {
						// Checking if all values are entered.
						if (txtCenterPoint.getText().trim().isEmpty() ||
								txtRadius.getText().trim().isEmpty())
						{
							
								isOk = false;
								JOptionPane.showMessageDialog(null, "Some fields are empty!",
														"Error", JOptionPane.ERROR_MESSAGE);
							
								return;
						} 
						
						try {
						StringBuilder sb = new StringBuilder(txtCenterPoint.getText());
						sb.deleteCharAt(0);
						sb.deleteCharAt(sb.length()-1);
						String strPoint = sb.toString();
						
						int x = Integer.parseInt((strPoint.split(","))[0]);
						int y = Integer.parseInt(((strPoint.split(","))[1]).trim());
						
						tempCircle = new Circle();
						tempCircle.setSelected(false);
						tempCircle.setCenter(new Point(x, y));
						tempCircle.setRadius(Integer.parseInt(getTxtRadius().getText().toString()));
						tempCircle.setColor(getBorderColor());
						tempCircle.setInnerColor(getInnerColor());
						
						isOk = true;
						setVisible(false);	
						}
						catch (Exception ex) {
							isOk = false;
							//	If user enters invalid data, detailed message will be displayed in MessageDialog,
							//	because every shape throws exception for invalid values.
							//	In this case radius>0, and x,y coordinates of circle center must be greater or equal to 0.
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setPreferredSize(new Dimension(80, 30));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtCenterPoint() {
		return txtCenterPoint;
	}

	public void setTxtCenterPoint(JTextField txtCenterPoint) {
		this.txtCenterPoint = txtCenterPoint;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}
	
	public Color getInnerColor() {
		return lblInnerColor.getBackground();
	}
	
	public void setInnerColor(Color color) {
		this.lblInnerColor.setBackground(color);
	}
	
	public Color getBorderColor() {
		return lblBorderColor.getBackground();
	}
	
	public void setBorderColor(Color color) {
		this.lblBorderColor.setBackground(color);
	}
	
	public void setIndexOfCircle(int index) {
		this.lblNumber.setText(Integer.toString(index) + ".");
	}
}
