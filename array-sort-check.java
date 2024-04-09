public class Main {
    // Main method
    public static void main(String[] args) {
        // Test cases
        int[] array1 = {0, 1, 4, 8, 9, 12, 15}; // Sorted array. Returns true.
        int[] array2 = {1, 7, 9, 3, 10}; // Unsorted array. Returns false.

        System.out.println(isSorted(array1, array1.length));
        System.out.println(isSorted(array2, array2.length));
    }

    // Method to check if an array is sorted
    public static boolean isSorted(int[] array, int index) {
        if (index == 1 || index == 0) {
            return true;
        }

        // Check if the current element is greater than or equal to the previous element. If not, return false.
        return (array[index - 1] >= array[index - 2]) && isSorted(array, index - 1);
    }
}
