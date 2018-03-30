package game.of.life;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ParametersPanel extends JPanel {
	private static final long serialVersionUID = 7842871693609144548L;
	private JTextField propabilityField, gridSizeField, delayField;
	
	public ParametersPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		propabilityField = new JTextField(15);		
		propabilityField.setBackground(Color.WHITE);
		propabilityField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		propabilityField.setText("0.1");
		propabilityField.setHorizontalAlignment(JTextField.TRAILING);
		propabilityField.setEditable(true);
		
		JLabel label = new JLabel("Propability: "); 
		label.setLabelFor(propabilityField);
		
		this.add(label);
		this.add(propabilityField);
		
		gridSizeField = new JTextField(15);		
		gridSizeField.setBackground(Color.WHITE);
		gridSizeField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gridSizeField.setText("10");
		gridSizeField.setHorizontalAlignment(JTextField.TRAILING);
		gridSizeField.setEditable(true);
		
		label = new JLabel("Grid Size: "); 
		label.setLabelFor(gridSizeField);
		
		this.add(label);
		this.add(gridSizeField);
		
		delayField = new JTextField(15);		
		delayField.setBackground(Color.WHITE);
		delayField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delayField.setText("1.0");
		delayField.setHorizontalAlignment(JTextField.TRAILING);
		delayField.setEditable(true);
		
		label = new JLabel("Delay (sec): "); 
		label.setLabelFor(delayField);
		
		this.add(label);
		this.add(delayField);
	}
	
	public double getPropability() {
		return Double.valueOf(propabilityField.getText());
	}
	
	public int getRectangleSize(int sideLength) {
		int input = Integer.valueOf(gridSizeField.getText());
		return sideLength / input;
	}
	
	public int getDelay() {
		double input = Double.valueOf(delayField.getText());
		return (int) (input * 1000);
	}
}
