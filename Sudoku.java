import java.util.Random;
import java.util.Scanner;

public class Sudoku {

	private static int[][] grid;
	private static boolean win;
	private static boolean[][] fill;

	/*
	 * Start with all values as -1 to symbolize empty space
	 */
	public static void initializeGrid() {
		grid = new int[9][9];
		fill = new boolean[9][9];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = -1;
			}
		}

	}

	/*
	 * Printing grid with Cheon's format (esta medio menso pero funciona)
	 */
	public static void printGrid() {
		System.out.println("\n+===+===+===+===+===+===+===+===+===+");
		for (int i = 0; i < grid.length; i++) {
			System.out.print("|");
			for (int j = 0; j < grid.length; j++) {
				if (j % 3 == 2) {
					System.out.print(" " + ((grid[j][i]==-1) ? " ":grid[j][i]) + " !");
				} else {
					System.out.print(" " + ((grid[j][i]==-1) ? " ":grid[j][i]) + " |");
				}
			}
			if (i % 3 == 2) {
				System.out.println("\n+===+===+===+===+===+===+===+===+===+");

			} else {
				System.out.println("\n+---+---+---+---+---+---+---+---+---+");
			}
		}
	}

	/*
	 * Inserting number with user input
	 */
	public static void insertNumber() {
		Scanner scr = new Scanner(System.in);
		int x, y, n;
		// while game is not over 
		while (!win) {
			System.out.println("\nSelect row, column and number");
			System.out.print("x = ");
			x = scr.nextInt();
			System.out.print("\ny = ");
			y = scr.nextInt();
			System.out.print("\nn = ");
			n = scr.nextInt();
			System.out.printf("\nx = %d, y = %d, n = %d\n", x, y, n);
			// check if valid number 
			if (!checkNum(x, y, n)) {
				System.out.println("\nNot a valid number");
				continue;
			}
			System.out.println("\nValid number, inserting " + n);
			//print grid and then check if game is over
			printGrid();
			checkWin();
		}
	}

	/*
	 * Checking if player win
	 */
	private static void checkWin() {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				sum += grid[i][j];
				// if there are still empty squares
				if (grid[i][j] == -1) {
					return;
				}
			}
		}
		// double check if sum of numbers is correct
		if (sum == 405) {
			win = true;
			System.out.println("YOU WIN!");
		}
	}

	/*
	 * Check if number inserted is valid 
	 */
	private static boolean checkNum(int x, int y, int n) {
		// check if is outside grid or if is not between 1 and 9
		if (n < 1 || n > 9 || x < 0 || y < 0 || x > 8 || y > 8) {
			return false;
		}
		// check if current space is not part of the pre-filled values 
		if(fill[x][y] == true) {
			System.out.println("Already filled with pre-values");
			return false;
		}
		
		// check column 
		if (!checkColumn(x, n)) {
			System.out.println("Number already in column");
			return false;
		}

		// check row
		if (!checkRow(y, n)) {
			System.out.println("Number already in row");
			return false;
		}

		// check square
		if (!checkSquare(x, y, n)) {
			System.out.println("Number already in square");
			return false;
		}

		// if number passes every test add to grid 
		grid[x][y] = n;
		return true;
	}

	/*
	 * Check square (numbers in same 3x3) 
	 * TODO: NOT READY YET :( 
	 */
	private static boolean checkSquare(int x, int y, int n) {

		return true;
	}

	private static boolean checkRow(int y, int n) {
		for (int row = 0; row < grid.length; row++) {
			if (grid[row][y] == n) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkColumn(int x, int n) {
		for (int col = 0; col < grid.length; col++) {
			if (grid[x][col] == n) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Adding pre-filled values
	 */
	private static void addRandomNumbers(int numbers) {
		Random rand = new Random();
		if (numbers == 0) {
			return;
		}
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomX = rand.nextInt(10);
		int randomY = rand.nextInt(10);
		int randomN = rand.nextInt(9) + 1;
		if (!checkNum(randomX, randomY, randomN)) {
			addRandomNumbers(numbers);
		} else {
			fill[randomX][randomY] = true;
			addRandomNumbers(numbers-1);
		}
	}
	public static void main(String[] args) {
		win = false;
		int numbers = 17;
		initializeGrid();
		addRandomNumbers(numbers);
		printGrid();
		insertNumber();

	}

}
