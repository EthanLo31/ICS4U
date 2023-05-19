import java.io.*;
// import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPrefix extends InfixToPostfix {

	public static String convertToPrefix(char[] c) {

		// make copy of the array
		char[] charArray = c.clone();

		// IMPORTANT: swap any brackets present bc we are going to reverse the array
		String temp = new String(charArray);
		temp = temp.replaceAll("[(]", "`"); // use '`' as a palceholder
		// pl("temp: " + temp);
		temp = temp.replaceAll("[)]", "(");
		// pl("temp: " + temp);
		temp = temp.replaceAll("`", ")");
		// pl("temp: " + temp);

		charArray = temp.toCharArray(); // convert back to char[]


		// reverse the array
		reverseOrder(charArray);

		// // convert to postfix and copy back to array
		// charArrayCopy = convertToPostfix(charArrayCopy).toCharArray();

		// // reverse again
		// reverseOrder(charArrayCopy);

		// // convert to string and return
		// return new String(charArrayCopy);

		// create a string to hold the final prefix expression
		String result = "";

		// Stack<String> operandStack = new Stack<String>();
		Stack<Character> operatorStack = new Stack<Character>();

		// conversion
		int i = 0; // counter
		// pl("array length: " + charArray.length);

		while (i < charArray.length) {
			// pl("e: " + charArray[i] + "\tstack: " + operatorStack + "\ti: " + i +
			// 		"\tresut: " + result);
			if (operands.contains(charArray[i] + "")) {
				temp = " ";
				while (i < charArray.length && isOperand(charArray[i])) {
					temp += charArray[i];
					// pl(charArray[i] + "");
					if (i < charArray.length - 1)
						i++;
					else {
						i = charArray.length;
						// break;
					}
				}
				// temp += " ";

				result += temp;
			} else if (operators.contains(charArray[i] + "")) {

				if (operatorStack.isEmpty()) {
					operatorStack.push(charArray[i]);
					i++;

				} else if (charArray[i] == '(') {
					operatorStack.push(charArray[i]);
					i++;

				} else if (charArray[i] == ')') {
					// operatorStack.pop();
					while (operatorStack.peek() != '(') {
						result += operatorStack.pop();
					}
					operatorStack.pop();
					i++;

				} else if (hasHigherPrec(charArray[i], operatorStack.peek())) {
					operatorStack.push(charArray[i]);
					i++;

				} else if (!hasHigherPrec(charArray[i], operatorStack.peek())) {

					while (!operatorStack.isEmpty() && hasHigherPrec(operatorStack.peek(), charArray[i])) {
						result += operatorStack.pop();
						// i++;
					}
					operatorStack.push(charArray[i]);
					i++;
				}
			}
			// if (i < charArray.length - 1)
			// i++;
			// else {
			// i = charArray.length;
			// break;
			// }

		}
		while (!operatorStack.empty()) {
			result += operatorStack.pop();
		}

		return reverseOrder(result);
	}

	public static String reverseOrder(String s) { // reverses the order of an array
		// convert to char[]
		char[] charArray = s.toCharArray();

		List<Character> reverse = new ArrayList<Character>();

		// copy elements into a list
		for (int i = 0; i < charArray.length; i++) {
			reverse.add(charArray[i]);
		}

		// reverse the list
		java.util.Collections.reverse(reverse);

		// copy back into aray
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = reverse.get(i);
		}

		return new String(charArray);
	}

	public static void reverseOrder(char[] charArray) { // reverses the order of an array
		List<Character> reverse = new ArrayList<Character>();

		// copy elements into a list
		for (int i = 0; i < charArray.length; i++) {
			reverse.add(charArray[i]);
		}

		// reverse the list
		java.util.Collections.reverse(reverse);

		// copy back into aray
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = reverse.get(i);
		}
	}

	public static void main(String[] args) throws IOException {

		char[] charArray = getUserInput(); // get user input in a char[] array
		// String result = convertToPostfix(charArray); // convert to postfix
		String result = convertToPrefix(charArray); // convert to prefix

		// ADD CODE BELOW THIS LINE TO PERFORM THE OPERATIONS AS DESCRIBED IN VIDEO.
		// BY THE END OF THE CODE, 'result' SHOULD HAVE THE POSTFIX EXPRESSION IN IT

		// outputs the postfix expression to the user
		System.out.println("\nThe prefix expression is: " + result);
		// pl("Stack: " + myStack);

	}
}
