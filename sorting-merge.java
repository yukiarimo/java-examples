// just package
package test;

//Import necessary classes
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Define the Main class
public class Test {
	public static void main(String[] args) {
		// Access the file and create the file reader
		File myObj = new File("numbers.txt");
		Scanner myReader = null;

		// Try reading from the Scanner
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e) {
			// If the file is not found, print the stack trace and exit the program
			e.printStackTrace();
		}

		// First line is the size of the array
		int size = Integer.parseInt(myReader.nextLine());
		int[] list = new int[size];

		// Keep track of the indices
		int y = 0;
		// Read in all data.
		while (myReader.hasNextLine()) {
			list[y] = Integer.parseInt(myReader.nextLine());
			y++;
		}

		// Your code goes here :) -> Close the Scanner to free resources
		myReader.close();

		// Sort the array using the Merge Sort algorithm.
		mergeSort(list, 0, list.length - 1);

		// Calculate and print the mean, median, and mode of the array
		System.out.printf("Mean: %.2f\n", calculateMean(list));
		System.out.printf("Median: %.2f\n", calculateMedian(list));
		System.out.println("Mode: " + calculateMode(list));
	}

	/*
	Merge Sort was chosen for its efficient time complexity, ideal for handling large datasets, and its divide and conquer approach.
	Another reason for choosing Merge Sort is that it is a stable sort, meaning that the order of equal elements is preserved.
	*/

	// Method to perform Merge Sort on the array
	public static void mergeSort(int[] array, int left, int right) {
		if (left < right) {
			// Find the middle point to divide the array into two halves
			int middle = (left + right) / 2;

			// Sort the first and second halves
			mergeSort(array, left, middle);
			mergeSort(array, middle + 1, right);

			// Merge the sorted halves
			merge(array, left, middle, right);
		}
	}

	// Method to merge two subarrays of array[]
	public static void merge(int[] array, int left, int middle, int right) {
		// Find sizes of two subarrays to be merged. +1 to include the middle element
		int[] leftArray = new int[middle - left + 1];
		int[] rightArray = new int[right - middle];

		// Copy data to temp arrays
		for (int i = 0; i < leftArray.length; i++)
			leftArray[i] = array[left + i];

		for (int i = 0; i < rightArray.length; i++)
			rightArray[i] = array[middle + i + 1];

		// Initial indexes of first and second subarrays
		int leftIndex = 0, rightIndex = 0;

		// Initial index of merged subarray array
		for (int i = left; i < right + 1; i++) {
			if (leftIndex < leftArray.length
					&& (rightIndex >= rightArray.length || leftArray[leftIndex] <= rightArray[rightIndex])) {
				array[i] = leftArray[leftIndex];
				leftIndex++;
			} else {
				array[i] = rightArray[rightIndex];
				rightIndex++;
			}
		}
	}

	// Method to calculate the mean of the array
	public static double calculateMean(int[] array) {
		double sum = 0;

		for (int j : array) {
			sum += j;
		}

		return sum / array.length;
	}

	// Method to calculate the median of the array
	public static double calculateMedian(int[] array) {
		// Find the middle index
		int middle = array.length / 2;

		if (array.length % 2 == 0) {
			// If even, return the average of the two middle numbers
			return (array[middle - 1] + array[middle]) / 2.0;
		} else {
			// If odd, return the middle number
			return array[middle];
		}
	}

	// Method to calculate the mode of the array
	public static int calculateMode(int[] array) {
		// Initialize variables to keep track of the mode
		int maxValue = 0, maxCount = 0, i, j;

		// Loop through array to find the mode
		for (i = 0; i < array.length; ++i) {
			int count = 0;

			for (j = 0; j < array.length; ++j) {
				if (array[j] == array[i])
					++count;
			}

			if (count > maxCount) {
				maxCount = count;
				maxValue = array[i];
			} else if (count == maxCount) {
				// In case of a tie, choose the lower value
				maxValue = Math.min(maxValue, array[i]);
			}
		}

		return maxValue;
	}
}