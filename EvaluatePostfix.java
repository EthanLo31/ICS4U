import java.io.IOException;
import java.util.Stack;

public class EvaluatePostfix extends InfixToPostfix {

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
