package newPrj;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class project {

	public static void main(String[] args) {
		int[] array = new int[100]; // Instantiate an array of 100 integers.
		Scanner scanner = new Scanner(System.in);
		int choice; // main variable

		do {
			// Display the menu
			String leftAlignFormat = "| %-2s | %-30s                |%n";

			System.out.format("+----+-----------------------------------------------+%n");
			System.out.format("| #  | Menu                                          |%n");
			System.out.format("+----+-----------------------------------------------+%n");
			System.out.format(leftAlignFormat, "0", "Exit the program");
			System.out.format(leftAlignFormat, "1", "Populate the array randomly");
			System.out.format("| %-2s | %-30s |%n", "2", "Populate the array sequentially from 0 to 100");
			System.out.format(leftAlignFormat, "3", "Display the array");
			System.out.format(leftAlignFormat, "4", "Shuffle the array");
			System.out.format(leftAlignFormat, "5", "Find a number in the array");
			System.out.format(leftAlignFormat, "6", "Find the highest value");
			System.out.format("+----+-----------------------------------------------+%n");
			System.out.print("\n");
			System.out.print("> ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				populateArrayRandomly(array);
				break;
			case 2:
				populateArraySequentially(array);
				break;
			case 3:
				displayArray(array);
				break;
			case 4:
				shuffleArray(array);
				break;
			case 5:
				System.out.print("Enter a number to find: ");
				int number = scanner.nextInt();
				int index = find(array, number);
				if (index != -1) {
					System.out.println("Number found at index: " + index);
				} else {
					System.out.println("Number not found in the array.");
				}
				break;
			case 6:
				int max = findHighestValue(array);
				System.out.println("The highest value in the array is: " + max);
				break;
			case 0:
				System.out.println("Exiting the program.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
			
			System.out.print("\n");2

		} while (choice != 0);
	}

	// Method to populate the array randomly
	public static void populateArrayRandomly(int[] array) {
		// Create a new Random object to generate random numbers
		Random random = new Random();

		// Loop through the entire array
		for (int i = 0; i < array.length; i++) {
			// Assign a random integer between 0 and 99 to the current array element
			array[i] = random.nextInt(100); // Populate with random numbers between 0 and 99
		}

		// Print a message indicating that the array is populated with random values
		System.out.println("Array populated with random values.");
	}

	// Method to populate the array sequentially from 0 to 99
	public static void populateArraySequentially(int[] array) {
		// Loop through the entire array
		for (int i = 0; i < array.length; i++) {
			// Assign values from 0 to 99 sequentially to the array elements
			array[i] = i;
		}

		// Print a message indicating that the array is populated sequentially
		System.out.println("Array populated sequentially.");
	}

	// Method to display the array
	public static void displayArray(int[] array) {
		// Print the contents of the array as a formatted string
		System.out.println("Array contents: " + Arrays.toString(array));
	}

	// Method to shuffle the array
	public static void shuffleArray(int[] array) {
		// Create a new Random object to shuffle the array
		Random random = new Random();

		// Loop through the array in reverse order
		for (int i = array.length - 1; i > 0; i--) {
			// Generate a random index within the remaining portion of the array
			int index = random.nextInt(i + 1);

			// Swap the elements at the current and randomly selected indices
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}

		// Print a message indicating that the array is shuffled
		System.out.println("Array shuffled.");
	}

	// Method to find the index of a number in the array
	public static int find(int[] array, int number) {
		// Loop through the entire array
		for (int i = 0; i < array.length; i++) {
			// Check if the current array element is equal to the specified number
			if (array[i] == number) {
				return i; // Return the index where the number is found
			}
		}
		// If the number is not found, return -1
		return -1;
	}

	// Method to find the highest value in the array
	public static int findHighestValue(int[] array) {
		// Initialize a variable to store the maximum value and set it to the first
		// element
		int max = array[0];

		// Loop through the array to find the highest value
		for (int i = 1; i < array.length; i++) {
			// If the current element is greater than the current maximum, update the
			// maximum
			if (array[i] > max) {
				max = array[i];
			}
		}

		// Return the highest value found in the array
		return max;
	}

}
