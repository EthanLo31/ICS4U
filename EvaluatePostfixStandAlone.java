import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class EvaluatePostfixStandAlone {

	/*
	 * he following was duplicated from InfixToPostfix.java
	 */

	////////////////////////////////////////////////////////////////
	static String operands = "0123456789";
	static String operators = "+-*/()";

	static void p(String text) { // shortcut to print
		System.out.print(text);
	}

	static void pl(String text) { // shortcut to println
		System.out.println(text);
	}

	// returns true if parameter c is one of: +, -, *, /
	public static boolean isOperator(char c) {
		if (operators.contains(c + ""))
			return true;
		else
			return false;
	}

	// returns true if parameter c is in the range from '0' to '9'.
	// note that in ASCII, '0' comes before '1'
	public static boolean isOperand(char c) {
		if (operands.contains(c + ""))
			return true;
		else
			return false;
	}

	// return true if op1's operator has higher precedence than op2's operator.
	// with only the four basic operators this can happen in a few ways:
	// 1. if op1 is * or /, and op2 is + or - (* or / before + or -)
	// 2. if op1 is * or /, and op2 is * or / (Left-to-Right for * or /)
	// 3. if op1 is + or -, and op2 is + or - (Left-to-Right for + or -)
	public static boolean hasHigherPrec(char op1, char op2) {

		if (op2 == '(')
			return true;
		else if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
			return true;
		else if ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-'))
			return false;
		else if ((op1 == '*' || op1 == '/') && (op2 == '*' || op2 == '/'))
			return false;
		else
			return false;

	}

	public static boolean isBalanced(char[] c) { // testing if the parentheses are balanced
		String validParentheses = "()";

		// the followingn was adapted from Assigment 4's BalancedParentheses.java
		Stack<Character> myStack = new Stack<Character>();

		for (int i = 0; i < c.length; i++) {
			// pl("c: " + c[i]);
			if (!validParentheses.contains(c[i] + "")) // if not a parentheses
				continue;

			else if (c[i] == '(') // if opening
				myStack.push(c[i]);

			else {
				if (myStack.empty()) // if closing, and stack is empty
					return false;
				else
					myStack.pop();
			}
		}
		if (myStack.empty())
			return true;
		else
			return false;
	}

	public static String convertToPostfix(char[] charArray) {
		Stack<Character> myStack = new Stack<Character>();

		// create a string to hold the final postfix expression
		String result = "";

		// conversion
		int i = 0; // counter
		// pl("array length: " + charArray.length);

		for (int j = 0; j < charArray.length; j++) {
			// pl("i: " + i);
			if (operands.contains(charArray[i] + "")) { // if number
				result += " ";
				while (isOperand(charArray[i])) {
					result += charArray[i];

					if (i < charArray.length - 1)
						i++;
					else {
						j = charArray.length;
						break;
					}
				}
				// pl("r: " + result);
			} else if (operators.contains(charArray[i] + "")) {
				// pl("X");
				if (myStack.empty())
					myStack.push(charArray[i]);

				else if (charArray[i] == '(')
					myStack.push(charArray[i]);

				else if (charArray[i] == ')') {
					while (myStack.peek() != '(') {
						result += myStack.pop();
					}
					myStack.pop();
				}

				else if (hasHigherPrec(charArray[i], (char) myStack.peek()) || charArray[i] == '(')
					myStack.push(charArray[i]);

				else if (!hasHigherPrec(charArray[i], (char) myStack.peek())) {
					while (!myStack.empty() && !hasHigherPrec(charArray[i], (char) myStack.peek())) {
						result += myStack.pop();
					}
					myStack.push(charArray[i]);
				}
				if (i < charArray.length - 1)
					i++;
				else {
					j = charArray.length;
					break;
				}

			}
			// pl("" + result);
		}

		while (!myStack.empty()) {
			result += myStack.pop();
		}

		return result;
	}

	public static char[] getUserInput() throws IOException {
		// ask user for a postfix expression
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		// declaring variables
		boolean valid = false;
		char[] charArray = null;

		while (!valid) { // validation loop
			valid = true;

			System.out.print("Please enter your infix expression: ");
			String userInput = stdin.readLine();

			// convert the userInput string to a char[] array, remove spaces
			charArray = userInput.replaceAll(" ", "").toCharArray();

			char c = ' '; // vairable for efficency, to reduce array lookups

			for (int i = 0; i < charArray.length; i++) {
				c = charArray[i];

				if (!(operands.contains(c + "") || operators.contains(c + ""))) {
					pl("Invalid input!");
					valid = false;
					break;
				}
			}
			if (!isBalanced(charArray)) {
				pl("Invalid input!");
				valid = false;
			}
		}

		return charArray;
	}

	///////////////////////////////////////////////////////////////
	// perform the operation with infix notation: <operand1> <operator> <operand2>
	// for example, performOperation('-','5','2') will perform "5 - 2" and return 3.
	public static Double performOperation(char operator, Double operand2, Double operand1) {

		switch (operator) {
			case '+':
				return (operand1 + operand2);
			case '-':
				return (operand1 - operand2);
			case '*':
				return (operand1 * operand2);
			case '/':
				return (operand1 / operand2);
			default:
				return null;
		}
	}

	public static Double evaluatePostfix(String postfix) {
		// declaring variables
		Stack<Double> myStack = new Stack<Double>();
		int i = 0; // counter variable
		String temp = ""; // temporary string variable, later convert to double
		double runningTotal = 0; // running total variable

		myStack.push(0.0); // to avoid empty stack exception if the first number is negative
		while (i < postfix.length()) {
			// pl("e: " + postfix.charAt(i) + "\tstack: " + myStack + "\ti: " + i);

			if (isOperand(postfix.charAt(i))) {
				while (isOperand(postfix.charAt(i))) {
					temp += postfix.charAt(i);
					i++;
				}

				myStack.push(Double.parseDouble(temp));
				temp = ""; // reset temp
			} else if (isOperator(postfix.charAt(i))) {
				runningTotal = performOperation(postfix.charAt(i), myStack.pop(), myStack.pop());

				myStack.push(runningTotal);
				i++;
			} else
				i++; // for spaces
		}

		return runningTotal;
	}

	// main method
	public static void main(String[] args) throws IOException {

		char[] charArray = getUserInput(); // get user input in a char[] array
		String result = convertToPostfix(charArray); // convert to postfix

		double finalAnswer = evaluatePostfix(result); // evaluate postfix
		// ADD CODE BELOW THIS LINE TO PERFORM THE OPERATIONS AS DESCRIBED IN VIDEO.
		// BY THE END OF THE CODE, THE STACK SHOULD ONLY HAVE ONE DOUBLE OBJECT IN IT
		// pl("The postfix expression is: " + result);
		// output the finalAnswer to the user
		System.out.println("The expression evaluates to: " + finalAnswer);

	}

}
