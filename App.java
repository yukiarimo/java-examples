package newPrj; // Defines the package in which this class resides

import java.util.Scanner; // Imports the Scanner class for reading user input
import java.io.*; // Imports the java.io package for file handling

public class App { // Declares a public class named App

    private char[][] board; // Declares a private 2D char array for the game board
    private char currentPlayer; // Declares a private char to keep track of the current player
    private int winsX, winsO, ties; // Declares private integers to keep track of the scores

    public App() { // Constructor for the App class
        board = new char[3][3]; // Initializes the game board as a 3x3 char array
        currentPlayer = 'X'; // Sets the current player to 'X'
        winsX = 0; // Initializes the score for player X
        winsO = 0; // Initializes the score for player O
        ties = 0; // Initializes the count of ties
        initializeBoard(); // Calls the method to set up the game board
        loadResults(); // Calls the method to load results from the file
    }

    public void initializeBoard() { // Method to set up the game board
        for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
            for (int j = 0; j < 3; j++) { // Iterates over the columns of the board
                board[i][j] = '-'; // Sets each cell of the board to '-'
            }
        }
    }

    public void printBoard() { // Method to print the game board
        System.out.println("-------------"); // Prints the top border of the board
        for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
            System.out.print("| "); // Prints the left border of each row
            for (int j = 0; j < 3; j++) { // Iterates over the columns of the board
                System.out.print(board[i][j] + " | "); // Prints each cell of the board with borders
            }
            System.out.println(); // Prints a newline after each row
            System.out.println("-------------"); // Prints the bottom border of the board
        }
    }

    boolean isBoardFull() { // Method to check if the board is full
        for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
            for (int j = 0; j < 3; j++) { // Iterates over the columns of the board
                if (board[i][j] == '-') { // Checks if a cell is empty
                    return false; // Returns false if a cell is empty
                }
            }
        }
        return true; // Returns true if all cells are filled
    }

    public boolean checkForWin() { // Method to check for a win
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin()); // Returns true if any row, column, or diagonal has all same marks
    }

    private boolean checkRowsForWin() { // Method to check for a win in any row
        for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) { // Checks if all cells in a row have the same mark
                return true; // Returns true if a win is found
            }
        }
        return false; // Returns false if no win is found
    }

    private boolean checkColumnsForWin() { // Method to check for a win in any column
        for (int i = 0; i < 3; i++) { // Iterates over the columns of the board
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) { // Checks if all cells in a column have the same mark
                return true; // Returns true if a win is found
            }
        }
        return false; // Returns false if no win is found
    }

    private boolean checkDiagonalsForWin() { // Method to check for a win in any diagonal
        return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || checkRowCol(board[0][2], board[1][1], board[2][0]) == true); // Checks if all cells in a diagonal have the same mark
    }

    private boolean checkRowCol(char c1, char c2, char c3) { // Method to check if three chars are the same and not empty
        return ((c1 != '-') && (c1 == c2) && (c2 == c3)); // Returns true if all chars are the same and not empty
    }

    public void changePlayer() { // Method to change the current player
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Changes the current player
    }

    public boolean placeMark(int row, int col) { // Method to place a mark on the board
        if (row >= 0 && row < 3) { // Checks if the row is within the board
            if (col >= 0 && col < 3) { // Checks if the column is within the board
                if (board[row][col] == '-') { // Checks if the cell is empty
                    board[row][col] = currentPlayer; // Places the current player's mark on the board
                    return true; // Returns true if the mark was placed
                }
            }
        }
        return false; // Returns false if the mark was not placed
    }

    public void saveGame() { // Method to save the game
        try { // Tries to write the game to a file
            FileWriter writer = new FileWriter("game.txt"); // Creates a FileWriter for the game file
            writer.write(currentPlayer + "\n"); // Writes the current player to the file
            for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
                for (int j = 0; j < 3; j++) { // Iterates over the columns of the board
                    writer.write(board[i][j]); // Writes each cell of the board to the file
                }
                writer.write("\n"); // Writes a newline after each row
            }
            writer.close(); // Closes the FileWriter
        } catch (IOException e) { // Catches any IOExceptions
            e.printStackTrace(); // Prints the stack trace for any IOExceptions
        }
    }

    public void loadGame() { // Method to load a game
        try { // Tries to read the game from a file
            BufferedReader reader = new BufferedReader(new FileReader("game.txt")); // Creates a BufferedReader for the game file
            currentPlayer = (char)reader.read(); // Reads the current player from the file
            reader.readLine(); // Reads the newline after the current player
            for (int i = 0; i < 3; i++) { // Iterates over the rows of the board
                for (int j = 0; j < 3; j++) { // Iterates over the columns of the board
                    board[i][j] = (char)reader.read(); // Reads each cell of the board from the file
                }
                reader.readLine(); // Reads the newline after each row
            }
            reader.close(); // Closes the BufferedReader
        } catch (IOException e) { // Catches any IOExceptions
            e.printStackTrace(); // Prints the stack trace for any IOExceptions
        }
    }

    public void loadResults() { // Method to load the results
        try { // Tries to read the results from a file
            File file = new File("results.txt"); // Creates a File object for the results file
            if (!file.exists()) { // Checks if the file exists
                file.createNewFile(); // Creates the file if it doesn't exist
            } else { // If the file does exist
                BufferedReader reader = new BufferedReader(new FileReader(file)); // Creates a BufferedReader for the results file
                winsX = Integer.parseInt(reader.readLine()); // Reads the score for player X from the file
                winsO = Integer.parseInt(reader.readLine()); // Reads the score for player O from the file
                ties = Integer.parseInt(reader.readLine()); // Reads the count of ties from the file
                reader.close(); // Closes the BufferedReader
            }
        } catch (IOException e) { // Catches any IOExceptions
            e.printStackTrace(); // Prints the stack trace for any IOExceptions
        }
    }

    public void saveResults() { // Method to save the results
        try { // Tries to write the results to a file
            FileWriter writer = new FileWriter("results.txt"); // Creates a FileWriter for the results file
            writer.write(winsX + "\n"); // Writes the score for player X to the file
            writer.write(winsO + "\n"); // Writes the score for player O to the file
            writer.write(ties + "\n"); // Writes the count of ties to the file
            writer.close(); // Closes the FileWriter
        } catch (IOException e) { // Catches any IOExceptions
            e.printStackTrace(); // Prints the stack trace for any IOExceptions
        }
    }

    public static void main(String[] args) { // The main method
        App game = new App(); // Creates a new App object

        // Display the scores at the start of the game
        System.out.println("Scores at the start of the game:");
        System.out.println("Player X: " + game.winsX + " wins");
        System.out.println("Player O: " + game.winsO + " wins");
        System.out.println("Ties: " + game.ties);

        game.printBoard(); // Prints the game board

        Scanner scanner = new Scanner(System.in); // Creates a Scanner for user input

        while (!game.isBoardFull() && !game.checkForWin()) { // While the game is not over
            System.out.println("Player " + game.currentPlayer + ", enter your move (row column):"); // Prompts the current player for their move
            int row = scanner.nextInt(); // Reads the row from the user
            int col = scanner.nextInt(); // Reads the column from the user

            if (game.placeMark(row, col)) { // If the move is valid
                game.printBoard(); // Prints the game board
                game.changePlayer(); // Changes the current player
                game.saveGame(); // Saves the game
            } else { // If the move is not valid
                System.out.println("Invalid move. Try again."); // Tells the user their move was invalid
            }
        }

        if (game.checkForWin()) { // If there is a win
            game.changePlayer(); // Changes back to the winning player

            if (game.currentPlayer == 'X') { // If the winning player is X
                System.out.println("Player X wins!"); // Announces that player X wins
                game.winsX++; // Increments the win count for X
            } else if (game.currentPlayer == 'O') { // If the winning player is O
                System.out.println("Player O wins!"); // Announces that player O wins
                game.winsO++; // Increments the win count for O
            }
        } else { // If there is a tie
            System.out.println("It's a draw!"); // Announces that the game is a draw
            game.ties++; // Increments the tie count
        }

        game.saveResults(); // Saves the results

        // Display the scores at the end of the game
        System.out.println("Scores at the end of the game:");
        System.out.println("Player X: " + game.winsX + " wins");
        System.out.println("Player O: " + game.winsO + " wins");
        System.out.println("Ties: " + game.ties);

        scanner.close(); // Closes the Scanner
    }
}
