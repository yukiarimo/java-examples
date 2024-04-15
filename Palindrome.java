public class Palindrome {
    public boolean isPalindrome(String str) {
        // Base case: if the string is empty or has only one character, it's a palindrome
        if (str.length() <= 1) {
            return true;
        }
        
        // Check if the first and last characters are the same
        if (str.charAt(0) == str.charAt(str.length() - 1)) {
            // Recursively check the substring between the first and last characters
            return isPalindrome(str.substring(1, str.length() - 1));
        }
        
        // If the first and last characters are different, it's not a palindrome
        return false;
    }
    
    public boolean isSpacePalindrome(String str) {
        // Base case: if the string is empty or has only one character, it's a palindrome
        if (str.length() <= 1) {
            return true;
        }
        
        // Get the first and last characters
        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);
        
        // If the first character is a space, recursively check the substring without the first character
        if (first == ' ') {
            return isSpacePalindrome(str.substring(1));
        }
        
        // If the last character is a space, recursively check the substring without the last character
        if (last == ' ') {
            return isSpacePalindrome(str.substring(0, str.length() - 1));
        }
        
        // If the first and last characters are the same, recursively check the substring between them
        if (first == last) {
            return isSpacePalindrome(str.substring(1, str.length() - 1));
        }
        
        // If the first and last characters are different, it's not a palindrome
        return false;
    }

    // Main method to test the Palindrome class
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        
        // Test the isPalindrome method
        System.out.println(palindrome.isPalindrome("radar")); // Should print: true
        System.out.println(palindrome.isPalindrome("hello")); // Should print: false

        // Test the isSpacePalindrome method
        System.out.println(palindrome.isSpacePalindrome(" r a d a r ")); // Should print: true
        System.out.println(palindrome.isSpacePalindrome(" h e l l o ")); // Should print: false
    }
}
