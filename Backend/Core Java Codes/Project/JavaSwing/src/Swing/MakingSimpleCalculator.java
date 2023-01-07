package Swing;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

class CalculatorApplication extends JFrame implements ActionListener{
	JTextArea input1, input2;
	JLabel result;
	JButton add, sub, mul, div;
	
	public CalculatorApplication() {
		input1 = new JTextArea();
		input2 = new JTextArea();
		
		input1.setBounds(100, 50, 200, 50);
		input1.setToolTipText("Enter Number Input.");
		input1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		input2.setBounds(100, 100, 200, 50);
		input2.setToolTipText("Enter Number Input.");
		input2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		result = new JLabel("Result : NaN");
		result.setBounds(100, 150, 200, 50);
		
		
		add = new JButton("+");
		sub = new JButton("-");
		mul = new JButton("*");
		div = new JButton("/");
		
		add.setBounds(50, 200, 50, 50);
		sub.setBounds(150, 200, 50, 50);
		mul.setBounds(50, 250, 50, 50);
		div.setBounds(150, 250, 50, 50);
		
		add.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		div.addActionListener(this);
		
		this.add(input1);
		this.add(input2);
		this.add(result);
		this.add(add);
		this.add(sub);
		this.add(mul);
		this.add(div);
		
		this.setSize(400, 800);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i1, i2;
		
		try {
			i1 = Integer.parseInt(input1.getText());
		}catch (NumberFormatException numberFormatException) {
			result.setText("Input 1 is not a Correct Number : "+ numberFormatException.getMessage());
			return;
		}
		catch (Exception e2) {
			result.setText("Something went wrong with Input 1.");
			return;
		}
		
		try {
			i2 = Integer.parseInt(input2.getText());
		}catch (NumberFormatException numberFormatException) {
			result.setText("Input 2 is not a Correct Number : "+ numberFormatException.getMessage());
			return;
		}catch (Exception e2) {
			result.setText("Something went wrong with Input 2.");
			return;
		}
		
		int res;
		if(e.getSource() == add)
		{
			res = i1+i2;
		}else if(e.getSource() == sub)
		{
			res = i1 - i2;
		}
		else if(e.getSource()== mul)
		{
			res = i1*i2;
		}
		else if(e.getSource() == div)
		{
			try {
				res = i1/i2;
			}catch (ArithmeticException e2) {
				result.setText("Cannot Divide by 0.");
				return;
			}
		}
		else 
		{
			result.setText("Invalid Operation.");
			return;
		}
		
		result.setText("Result : "+ res);
	}
}

public class MakingSimpleCalculator {
	public static void main(String[] args) {
		CalculatorApplication calculatorApplication = new CalculatorApplication();
	}
}
