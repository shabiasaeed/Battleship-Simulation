//----------------------------------------------
// Assignment 4 Q1
// Written by: Shabia Saeed - 40154081
// For Comp 248 Section R - FALL 2020
// 03-12-2020
//----------------------------------------------
import java.util.Random;
public class Battleship {
	
	/*Class Description:
	 * Initializing and using methods
	 * Using a 2dimensional array of objects to represent a position in the grid which contains the following:
	 * the type of element at that position (a ship, a grenade or nothing).
	 * the owner of the element (the user or the computer).
	 * whether the position has already been called or not.
	 */


	//Initializing
	private char grid[][] = new char[8][8]; 
	private boolean winner;
	private int row;
	private int col;
	private int player1;
	private int player2;
	


	public Battleship() { // Method to print grid with the "_"
		row = 0;
		col = 0;
		for (int i = 0; i < grid.length; i++) {  // Using for loop to print the 8x8 grid
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '_';
			}
		}
	}

	public Battleship(Battleship copyGrid) {  // Method to print the copy of the grid
		for (int i = 0; i < grid.length; i++) { // Using for loop to print the 8x8 grid
			for (int j = 0; j < grid[i].length; j++) {
				this.grid[i][j] = copyGrid.grid[i][j];
			}
		}
	}

	public int isRow(String coordinate) {  // Method to return the rows
		switch (coordinate.charAt(0)) { // Using switch method for rows
		case 'A':
		case 'a':
			row = 0;
			break;
		case 'B':
		case 'b':
			row = 1;
			break;
		case 'C':
		case 'c':
			row = 2;
			break;
		case 'D':
		case 'd':
			row = 3;
			break;
		case 'E':
		case 'e':
			row = 4;
			break;
		case 'F':
		case 'f':
			row = 5;
			break;
		case 'G':
		case 'g':
			row = 6;
			break;
		case 'H':
		case 'h':
			row = 7;
			break;

		}

		return (row);
	}

	public int isCol(String coordinate) { //Method to return the columns
		switch (coordinate.charAt(1)) { // Using switch method for columns
		case '1':
			col = 0;
			break;
		case '2':
			col = 1;
			break;
		case '3':
			col = 2;
			break;
		case '4':
			col = 3;
			break;
		case '5':
			col = 4;
			break;
		case '6':
			col = 5;
			break;
		case '7':
			col = 6;
			break;
		case '8':
			col = 7;
			break;

		}
		return (col);
	}

	public String toString() { //Method to print the same grid if the user entered the same coordinate or out of grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		return "";
	}
	//Method to assign the character ‘s’ if the ship belongs to the user 
	public void setShip() {
		grid[this.col][this.row] = 's';
	}
	//Method to assign the character ‘S’ if the ship belongs to the computer
	public void setCShip() {
		grid[this.col][this.row] = 'S';
	}
	//Method to assign the character ‘g’ if the grenade belongs to the user
	public void setGrenade() {
		grid[this.col][this.row] = 'g';
	}
	//Method to assign ‘G’ if the grenade belongs to the computer
	public void setCGrenade() {
		grid[this.col][this.row] = 'G';
	}


	public boolean isOut(String coordinate) {  //Method to see if grid is out
		// coordinate.toLowerCase();
		char first = coordinate.charAt(0);
		char second = coordinate.charAt(1);
		return (first >= 'A' && first <= 'H' && second >= '1' && second < '9');

	}

	public boolean checkGrid() { // Method to see if the coordinate already exists
		return (grid[this.col][this.row] == 'G' || grid[this.col][this.row] == 'S' || grid[this.col][this.row] == 'g'
				|| grid[this.col][this.row] == 's');
	}

	public boolean countInput(String coordinate) { // Method to see if the length is more than 2
		return (coordinate.length() > 2);
	}

	public int randomRow() { // Method for the computer to play it's turn and choose a row on random
		this.row = (int) (Math.random() * ((8 - 1) + 1) - 1);
		return row;
	}

	public int randomCol() { // Method for the computer to play it's turn and choose a column on random
		this.col = (int) (Math.random() * ((8 - 1) + 1) - 1);
		return col;
	}

	public void randomMove(Battleship gridCopy) { //Method to see if the coordinate was already chosen before
		int k = 0;
		randomRow();
		randomCol();
		boolean valid = alreadyEnt();
		if (valid == true) {
			randomMove(gridCopy); // checking to see if the coordinate is already entered and if it is then randomize again
			return;
		}
		if (gridCopy.grid[this.col][this.row] == 'S' || gridCopy.grid[this.col][this.row] == 'G') {
			randomRow();
			randomCol();
		}
		switch (this.row) { //Using switch to check the rows
		case 0: {
			System.out.print("position of my rocket: A");
			break;
		}
		case 1: {
			System.out.print("position of my rocket: B");
			break;
		}
		case 2: {
			System.out.print("position of my rocket: C");
			break;
		}

		case 3: {
			System.out.print("position of my rocket: D");
			break;
		}
		case 4: {
			System.out.print("position of my rocket: E");
			break;
		}
		case 5: {
			System.out.print("position of my rocket: F");
			break;
		}
		case 6: {
			System.out.print("position of my rocket: G");
			break;
		}
		case 7: {
			System.out.print("position of my rocket: H");
			break;
		}
		}

		switch (this.col) { //Using switch to check the rows
		case 0: {
			System.out.println("1");
			break;
		}
		case 1: {
			System.out.println("2");
			break;
		}
		case 2: {
			System.out.println("3");
			break;
		}

		case 3: {
			System.out.println("4");
			break;
		}
		case 4: {
			System.out.println("5");
			break;
		}
		case 5: {
			System.out.println("6");
			break;
		}
		case 6: {
			System.out.println("7");
			break;
		}
		case 7: {
			System.out.println("8");
			break;
		}
		}

	}

	public void board() { // Method to print grid if the output is nothing and the grid has to be repeated
		System.out.println();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void board(Battleship realGrid) { // Method to print real grid if the output is nothing
		System.out.println(); 
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < realGrid.grid[i].length; j++) {
				System.out.print(realGrid.grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean alreadyEnt() {   //Method to see if coordinate was already entered
		return (grid[this.col][this.row] == '*' || grid[this.col][this.row] == 'G' || grid[this.col][this.row] == 'S'
				|| grid[this.col][this.row] == 's' || grid[this.col][this.row] == 'g');
	}

	public void launchRocket(Battleship realGrid) { // Method to launch rocket after coordinate has been entered
		if (realGrid.grid[this.col][this.row] == '_') {
			System.out.print("nothing."); // Printing if no action is taken place 
			grid[this.col][this.row] = '*';
			board();
		} else if (realGrid.grid[this.col][this.row] == 's' || realGrid.grid[this.col][this.row] == 'S') {
			System.out.print("ship hit."); // Printing if the coordinate hits a ship
			this.grid[this.col][this.row] = realGrid.grid[this.col][this.row];
			if (realGrid.grid[this.col][this.row] == 'S') {
				this.player1++;
				if (this.player1 == 6) {
					System.out.print(" You win!"); // Printing final result
					board(realGrid); // Printing final grid
					System.exit(0); // Exiting system after the game has ended
				}

			} else if (realGrid.grid[this.col][this.row] == 's') {
				this.player2++;
				if (this.player2 == 6) {
					System.out.print(" You lose!"); // Printing final result
					board(realGrid); // Printing final grid
					System.exit(0); // Exiting system after the game has ended
				}
			}
			board();
		}

		else if (realGrid.grid[this.col][this.row] == 'g' || realGrid.grid[this.col][this.row] == 'G') {
			System.out.print("boom! grenade."); // Printing if the coordinate entered hits a grenade
			this.grid[this.col][this.row] = realGrid.grid[this.col][this.row];
			board();
		}
	}

	public void StartOverGrid() { //Method for starting a fresh new grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '_';
			}
		}
	}

	public boolean hitGrenade(Battleship gridCopy) { //Method for hitting the grenade
		return (gridCopy.grid[this.col][this.row] == 'G' || gridCopy.grid[this.col][this.row] == 'g');

	}

	public boolean equals(Battleship gridCopy) { //Method to see if the grid equals the grid copy
		return (this.grid == gridCopy.grid);
	}
}

