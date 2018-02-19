package edu.utep.cs.cs4330.sudoku.model;

import java.util.Random;

/** An abstraction of Sudoku puzzle. */
public class Board {

    /** Size of this board (number of columns/rows). */
    public final int size;


    public int[][] grid;
    public boolean win;

    /** Create a new board of the given size. */
    public Board(int size) {
        this.size = size;

        // WRITE YOUR CODE HERE ...
        initializeGrid();
        addRandomNumbers(17);
    }



    public void initializeGrid() {
        grid = new int[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = -1;
            }
        }

    }

    public void insertNumber(int x, int y, int n) {
        // while game is not over
        while (!win) {
            // check if valid number
            if (!checkNum(x, y, n)) {
                System.out.println("Not a valid number");
                continue;
            }
            System.out.println("Valid number, inserting " + n);
            checkWin();
        }
    }

    private boolean checkNum(int x, int y, int n) {
        // check if is outside grid or if is not between 1 and 9
        if (n < 1 || n > 9 || x < 0 || y < 0 || x > 8 || y > 8) {
            return false;
        }
        // check if current space is not part of the pre-filled values
        if(grid[x][y] > 0) {
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

    private boolean checkSquare(int x, int y, int n) {
        int row = (int) (Math.floor((x/3))) * 3;
        int col = (int) (Math.floor((y/3))) * 3;

        for(int i = row; i < row+3; i++){
            for(int j = col; j < col+3; j++){
                if(grid[i][j] == n)
                    return false;
            }
        }
        return true;
    }


    private boolean checkRow(int y, int n) {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][y] == n) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int x, int n) {
        for (int col = 0; col < grid.length; col++) {
            if (grid[x][col] == n) {
                return false;
            }
        }
        return true;
    }

    private void checkWin() {
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



    private void addRandomNumbers(int numbers) {
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
            addRandomNumbers(numbers-1);
        }
    }


    /** Return the size of this board. */
    public int size() {
    	return size;
    }

    // WRITE YOUR CODE HERE ..
}
