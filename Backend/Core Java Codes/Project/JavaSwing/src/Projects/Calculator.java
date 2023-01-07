package Projects;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author Warlock Perry
 * Class to handle the Business Logic of the Calculator App.
 */
class CalculateResult{
//	Constants.
	private static String INVALID_INPUT = "Invalid Input";
	private static String DIVIDE_BY_ZERO = "Cannot Divide By 0";
	private static String SOMETHING_WENT_WRONG = "Something Went Wrong";
	
	
	
	/**
	 * This Function First Splits the Input string by white space.
	 * Then finds the postfix expression of the input string.
	 * and Finally calculated the postfix expression using stack and returns.
	 * the result if the string is valid else returns the Exception message.
	 * @param string It's the Input String that contains Numbers and Operators.
	 * @return	The Calculated result of the Input String.
	 */
	public static String Calculate(String string)
	{
//		Remove the White spaces at start and end of string.
		string = string.trim();
		
//		Split using white space.
		String[] infixStrings = string.split("\\s");
		
//		If last element is not a number then it's a invalid string.
		if(!IsFloat(infixStrings[infixStrings.length - 1]))
			return INVALID_INPUT;
		
//		Stores postfix values of infix string.
		ArrayList<String> postfixStrings = infixToPostFix(infixStrings);
		
//		Dequeue to help calculate result of Postfix string.
		Deque<Float> resStack = new ArrayDeque<Float>();
		try {
			for(int i=0; i<postfixStrings.size(); i++)
			{
				String valString = postfixStrings.get(i);
				
				if(IsFloat(valString))
				{
					resStack.push(Float.parseFloat(valString));
				}
				else 
				{
					if(valString.equals("+"))
					{
						float y = resStack.pop(), x = resStack.pop();
						resStack.push((float)(x+y));
					}
					else if(valString.equals("-"))
					{
						float y = resStack.pop(), x = resStack.pop();
						resStack.push(x-y);
					}
					else if(valString.equals("*"))
					{
						float y = resStack.pop(), x = resStack.pop();
						resStack.push((float)x*y);
					}
					else if(valString.equals("/"))
					{
						float y = resStack.pop(), x = resStack.pop();
						if(y == 0)	return DIVIDE_BY_ZERO;
						resStack.push((float)x/y);
					}
					else if(valString.equals("^"))
					{
						float y = resStack.pop(), x = resStack.pop();
						resStack.push((float)Math.pow(x, y));
					}
					else {
						return SOMETHING_WENT_WRONG;
					}
				}
			}
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
//		returns the stack top which is the result.
		return resStack.peek().toString();
	}

	

	/**
	 * @param infixStrings all the substring of infix string splitted by space.
	 * @return Array of Elements of postfixString splitted by space.
	 */
	private static ArrayList<String> infixToPostFix(String[] infixStrings)
	{
		ArrayList<String> postfixStrings = new ArrayList<String>();
		Deque<String> stkDeque = new ArrayDeque<String>();
		
		for(int i=0; i<infixStrings.length; i++)
		{
			String valString = infixStrings[i];
			
			if(IsFloat(valString))
			{
				postfixStrings.add(valString);
			}
			else {
				while (!stkDeque.isEmpty()
	                       && Prec(valString) <= Prec(stkDeque.peek())) {
	 
	                    postfixStrings.add(stkDeque.peek());
	                    stkDeque.pop();
	                }
	                stkDeque.push(valString);
			}
		}
		
		while(!stkDeque.isEmpty())
		{
			postfixStrings.add(stkDeque.pop());
		}
		
		return postfixStrings;
	}
	

	/**
	 * @param str Input String That Maybe a Number.
	 * @return true if it's a Float number false otherwise.
	 */
	public static boolean IsFloat(String str)
	{
		try {
			Float.parseFloat(str);
		}
		catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * @param ch operator as a string.
	 * @return the precedence of the operator. Higher the number more the precedence.
	 */
	private static int Prec(String ch)
    {
        if(ch == "+" || ch == "-")	return 1;
        else if(ch == "*" || ch == "/")	return 2;
        else if (ch == "^") return 3;
        
        return -1;
    }
}





/**
 * @author Warlock Perry
 * class to make GUI of the calculator APP.
 */
class CalculatorApp extends JFrame implements ActionListener{
	// Constants.
	private int SCREEN_WIDTH = 250, SCREEN_HEIGHT = 300;
	
	// Helper
	private JButton clear, back, equal;
	// Operators
	private JButton pow, div, sub, add, mul;
	// Inputs
	private JButton one, two, three, four, five, six, seven, eight, nine, zero, dZero, dot;
	// Input Box
	private JTextField inpuJTextField;
	// Does same as the name suggests.
	private boolean shouldClearInputField = false;
	
	
	
	public CalculatorApp() {
		super("Calculator App");
		
		SetupGUI();
		SetActionListner();
	}
	
	
	
	/**
	 * Sets the GUI Visible on the App
	 */
	private void SetupGUI()
	{
//		Initializing all the JButtons with object and Proper Symbols.
		clear = new JButton("C");
		back = new JButton("<-");
		equal = new JButton("=");
		
		pow = new JButton("^");
		div = new JButton("/");
		sub = new JButton("-");
		add = new JButton("+");
		mul = new JButton("*");
		
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3"); 
		four = new JButton("4"); 
		five = new JButton("5"); 
		six = new JButton("6"); 
		seven = new JButton("7"); 
		eight = new JButton("8"); 
		nine = new JButton("9"); 
		zero = new JButton("0"); 
		dZero = new JButton("00"); 
		dot = new JButton("."); 
		
		inpuJTextField = new JTextField("0");
		inpuJTextField.setBackground(Color.darkGray);
		inpuJTextField.setEnabled(false);
		inpuJTextField.setHorizontalAlignment(JTextField.RIGHT);
		
		
		
		Container cntP = this.getContentPane();
//		Spring Layout for JFrame.
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
//		This panel Contains all the Buttons.
		JPanel downJPanel = new JPanel();
//		This panel Contains the TextField where our input and out to the input will be visible.
		JPanel upJPanel = new JPanel();
		upJPanel.setLayout(new BorderLayout(5,5));
		upJPanel.add(inpuJTextField);
		
//		Adding all the buttons to this panel.
//		This panel uses Grid Layout to arrange all the buttons onto the panel.
		downJPanel.setBackground(Color.BLACK);
		downJPanel.setLayout(new GridLayout(5, 4, 5, 5));
		downJPanel.add(clear);	downJPanel.add(pow); downJPanel.add(back); 	downJPanel.add(div);
		downJPanel.add(seven); 	downJPanel.add(eight);		downJPanel.add(nine);	downJPanel.add(mul);
		downJPanel.add(four);	downJPanel.add(five);		downJPanel.add(six);	downJPanel.add(sub);
		downJPanel.add(one);	downJPanel.add(two);		downJPanel.add(three);	downJPanel.add(this.add);
		downJPanel.add(dZero);	downJPanel.add(zero);		downJPanel.add(dot);	downJPanel.add(equal);
		
//		Putting Constraints on upJpanel and downJpanel to fit into the JFrame.
		springLayout.putConstraint(SpringLayout.NORTH, upJPanel, 10, springLayout.NORTH, cntP);
		springLayout.putConstraint(SpringLayout.EAST, upJPanel, -10, springLayout.EAST, cntP);
		springLayout.putConstraint(SpringLayout.WEST, upJPanel, 10, springLayout.WEST, cntP);
		springLayout.putConstraint(SpringLayout.SOUTH, upJPanel, -40, springLayout.NORTH, downJPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, downJPanel, -10, springLayout.SOUTH, cntP);
		springLayout.putConstraint(SpringLayout.WEST, downJPanel, 10, springLayout.WEST, cntP);
		springLayout.putConstraint(SpringLayout.EAST, downJPanel, -10, springLayout.EAST, cntP);
		
		
		this.add(upJPanel);	this.add(downJPanel);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	/**
	 * Sets the Action Listener on all the buttons.
	 */
	private void SetActionListner()
	{
		clear.addActionListener(this);
		back.addActionListener(this);
		equal.addActionListener(this);
		
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		zero.addActionListener(this);
		dZero.addActionListener(this);
		dot.addActionListener(this);
		
		pow.addActionListener(this);
		div.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		add.addActionListener(this);		
	}

	
	
	
	/**
	 * Handles Making of the Input String That is visible on TextField.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object clickedObject = e.getSource();
		String string = inpuJTextField.getText();
		
		if(shouldClearInputField) {
			string = "0";
			shouldClearInputField = false;
		}
		
		if(clickedObject == clear)
		{
			string = "0";
		}
		else if(clickedObject == back)
		{
			if(!string.isEmpty()) {
				string = string.trim();
				string = string.substring(0, string.length()-1);
			}
		}
		else if(clickedObject == equal)
		{
			string = CalculateResult.Calculate(string);
			
//			If Calculated result of input string is not a valid number then clear the Input when next time any button is pressed.
			if(!CalculateResult.IsFloat(string))
				shouldClearInputField  = true;
		}
		else if(clickedObject == one)
		{
				string += "1";
		}
		else if(clickedObject == two)
		{
				string += "2";
		}
		else if(clickedObject == three)
		{
				string += "3";
		}
		else if(clickedObject == four)
		{
				string += "4";
		}
		else if(clickedObject == five)
		{
				string += "5";
		}
		else if(clickedObject == six)
		{
				string += "6";
		}
		else if(clickedObject == seven)
		{
				string += "7";
		}
		else if(clickedObject == eight)
		{
				string += "8";
		}
		else if(clickedObject == nine)
		{
				string += "9";
		}
		else if(clickedObject == zero)
		{
				string += "0";
		}
		else if(clickedObject == dZero)
		{
				string += "00";
		}
		else if(clickedObject == dot)
		{
				string += ".";
		}
		else if(clickedObject == pow)
		{
			string += " ^ ";
		}
		else if(clickedObject == add)
		{
			string += " + ";
		}
		else if(clickedObject == sub)
		{
			string += " - ";
		}
		else if(clickedObject == mul)
		{
			string += " * ";
		}
		else if(clickedObject == div)
		{
			string += " / ";
		}
		
		inpuJTextField.setText(string);
	}
}

public class Calculator {
	public static void main(String[] args) {
		new CalculatorApp();
	}
}
