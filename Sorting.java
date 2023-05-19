import java.io.*;
import java.security.spec.EdDSAParameterSpec;
import java.util.Random;

public class Sorting {

	static void p(String text) { // shortcut to print
		System.out.print(text);
	}

	static void pl(String text) { // shortcut to println
		System.out.println(text);
	}

	// check if the "values" array is sorted.
	// return true if it is sorted, false if it not.
	public static boolean isSorted(int[] values) {

		for (int i = 0; i < values.length - 1; i++) {

			if (values[i] > values[i + 1])
				return false;

		}
		return true;

	}

	// Swaps the values at two indices in the "values" array.
	public static void swap(int[] values, int indexA, int indexB) {

		int temp = values[indexA];
		values[indexA] = values[indexB];
		values[indexB] = temp;

	}

	// Perform a Bubble Sort on the entire "values" array.
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void bubbleSort(int[] values) {

		for (int i = 0; i < values.length; i++) { // outer array loop
			for (int j = 0; j < values.length - 1; j++) { // inner swap loop

				if (values[j] > values[j + 1])
					swap(values, j, j + 1);

			}
		}

	}

	public static void bubbleSort2(int values[]) {
		boolean swapped = false;

		for (int i = 0; i < values.length; i++) {

			swapped = false;

			for (int j = 0; j < values.length - 1; j++) {

				if (values[j] > values[j + 1]) {
					swap(values, j, j + 1);
					swapped = true;
				}

			}

			if (swapped == false)
				i = values.length;

		}
	}

	// Perform a Selection Sort on the entire "values" array.
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void selectionSort(int[] values) {

		for (int i = 0; i < values.length - 1; i++) {
			int minIndex = i;
			int minValue = values[i];

			for (int j = i; j < values.length; j++) {
				if (values[j] < minValue) {
					minIndex = j;
					minValue = values[j];
				}
			}
			if (minIndex != i)
				swap(values, i, minIndex);
		}

	}

	// Perform a Merge Sort on an array called "values".
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void mergeSort(int[] values) {
		// DO NOT CHANGE THIS METHOD
		// pl("" + (values.length - 1));
		mergeSort(values, 0, values.length - 1);
	}

	// Perform a Merge Sort on a section of the "values" array only from the
	// "startIndex" to the "endIndex".
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void mergeSort(int[] values, int startIndex, int endIndex) {
		// spliting...
		if (startIndex < endIndex) {
			int middleIndex = startIndex + (endIndex - startIndex) / 2;

			mergeSort(values, startIndex, middleIndex);
			mergeSort(values, middleIndex + 1, endIndex);
			merge(values, startIndex, middleIndex, endIndex);
		}

	}

	public static void merge(int[] values, int startIndex, int middleIndex, int endIndex) {
		// merging method
		int[] tempR = new int[endIndex - middleIndex], tempL = new int[middleIndex - startIndex + 1];
		// pl("s: " + startIndex + "\tm: " + middleIndex + "\te: " + endIndex + "\tTemp:
		// " + tempL.length);
		int leftIndex = 0, rightIndex = 0, realIndex = startIndex;

		// pl("S: "+realIndex);

		for (int i = 0; i < tempL.length; i++) // copy left array
			tempL[i] = values[i + startIndex];
		for (int i = 0; i < tempR.length; i++)// copy right array
			tempR[i] = values[i + 1 + middleIndex];

		while (leftIndex < tempL.length && rightIndex < tempR.length) {

			if (tempL[leftIndex] <= tempR[rightIndex]) {
				values[realIndex] = tempL[leftIndex];
				leftIndex++;
			} else {
				values[realIndex] = tempR[rightIndex];
				rightIndex++;
			}
			realIndex++;

		}
		while (rightIndex < tempR.length) {
			values[realIndex] = tempR[rightIndex];
			rightIndex++;
			realIndex++;
		}
		while (leftIndex < tempL.length) {
			values[realIndex] = tempL[leftIndex];
			leftIndex++;
			realIndex++;
		}

	}

	// Perform a Quick Sort on the "values" array.
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void quickSort(int[] values) throws IOException{
		// DO NOT CHANGE THIS METHOD
		quickSort(values, 0, values.length - 1);
	}

	// Perform a Quick Sort on a section of the "values" array only from the
	// "startIndex" to the "endIndex".
	// The array should be sorted IN PLACE, and in increasing order of the values.
	public static void quickSort(int[] values, int startIndex, int endIndex) throws IOException{
		// BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		if ((endIndex - startIndex) > 1) {

			Random randomElement = new Random();
			int pivot = randomElement.nextInt(startIndex, endIndex);
			// pl("S: " + startIndex + "\tP: " + pivot + "\tE: " + endIndex);
			swap(values, pivot, endIndex);
			// int pivot = values[randomElement.nextInt(startIndex, endIndex)];
			pivot=values[endIndex];
			// int pivot = values[endIndex];

			int l = startIndex, r = endIndex-1;
			// int r=endIndex-1;

			while (l < r) {
				// pl("L: " + l + "\tR: " + r + "\tl: " + values[l] + " r: " + values[r] + "\tP: " + pivot);
				while (values[r] >= pivot && r > startIndex && r>l)
					r--;

				// while (values[l] < pivot && l < endIndex-1 && l<=r)
				// 	l++;

				if (values[l] >= pivot) {
					swap(values, l, r);
					r--;
				}
				l++;

			}



			// for (int q=startIndex;q<endIndex;q++)
			// pl(""+values[q]);

			// pl("\n P: "+pivot);
			// 	String f = stdin.readLine();

			swap(values, l, endIndex);

			// if (startIndex < l - 1)
			quickSort(values, startIndex, l-1);
			// if (l < endIndex)
			quickSort(values, l, endIndex);

		}
	}

	// A method to output the "values" array to a file, one line at a time, with the
	// first line being the number of elements in the array.
	public static void storeValuesToFile(int[] values, String filename) throws Exception {
		Writer fileout = new FileWriter(filename);
		fileout.write("" + values.length + "\n");
		for (int i = 0; i < values.length; i++) {
			fileout.write("" + values[i] + "\n");
		}
		fileout.close();
	}

	// A method to input an array from a file, where the first line is the number of
	// elements, and then each element is on a new line after that.
	public static int[] readValuesFromFile(String filename) throws Exception {
		FileReader file = new FileReader(filename);
		BufferedReader filein = new BufferedReader(file);
		int numValues = Integer.parseInt(filein.readLine());
		int[] values = new int[numValues];
		for (int i = 0; i < numValues; i++) {
			values[i] = Integer.parseInt(filein.readLine());
		}
		filein.close();
		return values;
	}

	// MAIN METHOD
	public static void main(String[] args) throws Exception {
		int timeStart = (int) System.currentTimeMillis(); // get start time

		// read values from input file
		int[] values = readValuesFromFile("unsorted.txt");
		// for (int i = 0; i < values.length; i++)
		// pl("" + values[i]);
		// ASCII art (definatly not copied)
		pl("      ___");
		pl("    __| |");
		pl("  __| | |");
		pl("__| | | |");
		pl("| | | | |");
		pl("---------");

		// ask user which sort method to use
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Which sort algorithm do you want to use? [b,s,m,q]:\n");
		char algorithm = stdin.readLine().charAt(0);

		if (algorithm == 'b') {
			bubbleSort(values);
		} else if (algorithm == 's') {
			selectionSort(values);
		} else if (algorithm == 'm') {
			mergeSort(values);
		} else if (algorithm == 'q') {
			quickSort(values);
		}

		// store the (hopefully) sorted array to an output file. You may look at the
		// contents of this file after you run your code, if you need to inspect the
		// array contents after the sort method.
		storeValuesToFile(values, "sorted.txt");

		for (int i = 0; i < values.length; i++)
			pl("" + values[i]);
		// check if the array was properly sorted and give a message to the user.
		if (isSorted(values)) {
			System.out.println("The values are sorted!");
		} else {
			System.out.println("The values are NOT sorted.");
		}

		// program length
		int timeFin = (int) System.currentTimeMillis();
		pl("\n\t[Program finished in " + ((((double) timeFin - (double) timeStart) / 1000)) + " seconds]");
	}
}