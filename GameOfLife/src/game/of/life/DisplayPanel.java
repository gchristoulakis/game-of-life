package game.of.life;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DisplayPanel extends JPanel {
	private static final long serialVersionUID = 7842871693609144539L;

	private JTextField periodField, stepsField;
	
	public DisplayPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		stepsField = new JTextField(15);		
		stepsField.setBackground(Color.WHITE);
		stepsField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stepsField.setText("");
		stepsField.setHorizontalAlignment(JTextField.TRAILING);
		stepsField.setEditable(false);
		
		JLabel label = new JLabel("Step Count: "); 
		label.setLabelFor(stepsField);
		
		this.add(label);
		this.add(stepsField);
			
		periodField = new JTextField(15);		
		periodField.setBackground(Color.WHITE);
		periodField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		periodField.setText("");
		periodField.setHorizontalAlignment(JTextField.TRAILING);
		periodField.setEditable(false);
		
		label = new JLabel("Period: "); 
		label.setLabelFor(periodField);
		
		this.add(label);
		this.add(periodField);
	}
	
	public void setStepCount(int count) {
		stepsField.setText(String.valueOf(count));
	}
	
	public void setPeriod(int period) {
		periodField.setText(String.valueOf(period));
	}
}
