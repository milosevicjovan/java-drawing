package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class DlgPoint extends JDialog {

	// Dialog
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	boolean isOk;
	
	// Text fields
	private JTextField txtPoint;
	
	// Labels
	private JLabel lblBorderColor;
	private JLabel lblNumber;	
	
	// Shape
	Point tempPoint;
	
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
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setTitle("Point");
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
		
		FocusListener focusHighlighter = UserInterface.focusHighlighter;
		
		JLabel lblCoordinates = new JLabel("Coordinates:");
		lblCoordinates.setForeground(fgPrimary);
		lblCoordinates.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblCoordinates = new GridBagConstraints();
		gbc_lblCoordinates.anchor = GridBagConstraints.WEST;
		gbc_lblCoordinates.insets = new Insets(0, 20, 5, 5);
		gbc_lblCoordinates.gridx = 0;
		gbc_lblCoordinates.gridy = 3;
		contentPanel.add(lblCoordinates, gbc_lblCoordinates);
		
		txtPoint = new JTextField();
		txtPoint.setMaximumSize(new Dimension(50, 30));
		txtPoint.setMinimumSize(new Dimension(50, 30));
		txtPoint.addFocusListener(focusHighlighter);
		txtPoint.setBackground(bgSecondary);
		txtPoint.setForeground(fgPrimary);
		txtPoint.setBorder(new LineBorder(borderSecondary, 1));
		txtPoint.setPreferredSize(new Dimension(50, 30));
		txtPoint.setHorizontalAlignment(SwingConstants.CENTER);
		txtPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPoint.setColumns(10);
		GridBagConstraints gbc_txtPoint = new GridBagConstraints();
		gbc_txtPoint.fill = GridBagConstraints.BOTH;
		gbc_txtPoint.insets = new Insets(0, 0, 5, 0);
		gbc_txtPoint.gridx = 1;
		gbc_txtPoint.gridy = 3;
		contentPanel.add(txtPoint, gbc_txtPoint);
		
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
						
						if (txtPoint.getText().trim().isEmpty())
						{
							
								isOk = false;
								JOptionPane.showMessageDialog(null, "Some fields are empty!",
														"Error", JOptionPane.ERROR_MESSAGE);
							
								return;
						} 
						
						try {
							
							StringBuilder sb = new StringBuilder(txtPoint.getText());
							
							sb.deleteCharAt(0);
							sb.deleteCharAt(sb.length()-1);
							
							String strPoint = sb.toString();
							
							int x = Integer.parseInt((strPoint.split(","))[0]); 
							int y = Integer.parseInt(((strPoint.split(","))[1]).trim()); 
							
							tempPoint = new Point();
							
							tempPoint.setX(x);
							tempPoint.setY(y);

							// tempPoint selected is set to true because this dialog will be open only when certain line is selected.
							// When the dialog is closed, we want that point to stay selected.
							tempPoint.setSelected(true);
							tempPoint.setColor(getBorderColor());				
							
							isOk = true;
							setVisible(false);
						} 
						catch (Exception ex) {
							isOk = false;
							//	If user enters invalid data, detailed message will be displayed in MessageDialog,
							//	because every shape throws exception for invalid values.
							// In this case x and y coordinates of the point must be greater or equal to 0.
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

	public JTextField getTxtPoint() {
		return txtPoint;
	}

	public void setTxtPoint(JTextField txtPoint) {
		this.txtPoint = txtPoint;
	}

	public void setIndexOfPoint(int index) {
		this.lblNumber.setText(Integer.toString(index) + ".");
	}
	
	public void setBorderColor(Color color) {
		this.lblBorderColor.setBackground(color);
	}
	
	public Color getBorderColor() {
		return lblBorderColor.getBackground();
	}
}
