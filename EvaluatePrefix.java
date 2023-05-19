import java.io.IOException;
import java.util.Stack;

public class EvaluatePrefix extends InfixToPrefix {

	// perform the operation with infix notation: <operand1> <operator> <operand2>
	// for example, performOperation('-','5','2') will perform "5 - 2" and return 3.
	public static Double performOperation(char operator, Double operand1, Double operand2) {

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

	public static Double evaluatePrefix(String prefix) {
		// declaring variables
		Stack<Double> myStack = new Stack<Double>();
		int i = prefix.length() - 1; // counter variable, from back
		String temp = ""; // temporary string variable, later convert to double
		double runningTotal = 0; // running total variable
		// pl("prefix: " + prefix);
		myStack.push(0.0); // to avoid empty stack exception if the first number is negative
		
		while (i >= 0) {
			// pl("e: " + prefix.charAt(i) + "\tstack: " + myStack + "\ti: " + i);

			if (isOperand(prefix.charAt(i))) {
				while (isOperand(prefix.charAt(i))) {
					temp += prefix.charAt(i);
					i--;
				}
				temp = reverseOrder(temp); // reverse so number is correct

				myStack.push(Double.parseDouble(temp));
				temp = ""; // reset temp
			} else if (isOperator(prefix.charAt(i))) {
				runningTotal = performOperation(prefix.charAt(i), myStack.pop(), myStack.pop());

				myStack.push(runningTotal);
				i--;
			} else
				i--; // for spaces
		}

		return runningTotal;
	}

	// main method
	public static void main(String[] args) throws IOException {

		char[] charArray = getUserInput(); // get user input in a char[] array

		String result = convertToPrefix(charArray); // convert to prefix

		double finalAnswer = evaluatePrefix(result); // evaluate prefix
		// ADD CODE BELOW THIS LINE TO PERFORM THE OPERATIONS AS DESCRIBED IN VIDEO.
		// BY THE END OF THE CODE, THE STACK SHOULD ONLY HAVE ONE DOUBLE OBJECT IN IT
		// pl("The prefix expression is: " + result);
		// output the finalAnswer to the user
		System.out.println("The expression evaluates to: " + finalAnswer);

	}

}
