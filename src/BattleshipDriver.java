//----------------------------------------------
// Assignment 4 Q1
// Written by: Shabia Saeed - 40154081
// For Comp 248 Section R - FALL 2020
// 03-12-2020
//----------------------------------------------
import java.util.Scanner;
public class BattleshipDriver {

	public static void main(String[] args) {
		
		
																

		/*Program Description:
		 * Implementation of Battleship game grid class and Battleship driver class.
		 * This program plays a version of the game of Battleship against the computer which is played on a 8x8 grid.
		 * The goal of the game is to sink all of your opponent’s ships before your opponent sinks yours.
		 * The program then takes the input of each player of their secret places of 6 ships and 4 grenades on the grid.
		 * The program allows each player, in turn, to “shoot a rocket” on the grid. 
		 * The program simulates the game and display the grid after each turn.
		 * The game goes on until one player has all his/her ships sunk and he/she loses the game.
		 * Once the winner is declared, the grid is then displayed showing the position of all ships and grenades.
		 * Finally, it will exit, displaying a message.
		 */

		Battleship grid = new Battleship(); //Creating object of class Battleship
		Scanner scan = new Scanner(System.in); //Making Scanner class Object to take user input

		System.out.println("Hi, let's play Battleship!"); //Printing welcome message
		System.out.println();
		int number = 1;
		int i = 0;
		int j = 0;
		
		while (i < 6) { // Using while loop for the user to enter the 6 coordinates
			System.out.print("Enter the coordinates of your ship #" + number + ": "); // Asking student to enter coordinates
			String coordinate = scan.next(); // Taking user input
			coordinate.toLowerCase(); // Assigning to lower case to take user input if types in lower case

			boolean isLong = grid.countInput(coordinate.toUpperCase()); // Seeing if the user types a big coordinate, since 
			// it will be out of bonds
			if (isLong == true) {  
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(coordinate.toUpperCase()); // Checking if the number entered is greater than 8
			if (valid == false) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			} else {
				grid.isRow(coordinate);
				grid.isCol(coordinate);
				boolean tileCheck = grid.checkGrid();
				// System.out.println(tileCheck); //Checking to make sure if the program was working
				if (tileCheck == true) { // If statement used to check if the user already entered these coordinates
					System.out.println("sorry, coordinates already being used. try again.");
					continue;
				} else {
					grid.setShip();
					i++; // Incrementing only if they put a valid coordinate
					number++;
				}
			}
		}
		
		System.out.println();
		number = 1;
		
		while (j < 4) { // Now for the grenade coordinates
			System.out.print("Enter the coordinates of your grenade #" + number + ": ");
			String coordinate2 = scan.next(); // Taking user input
			coordinate2.toLowerCase(); // Assigning to lower case to take user input if types in lower case
		
			boolean isBig = grid.countInput(coordinate2.toUpperCase()); //Seeing if the user types a big coordinate, since 
			// it will be out of bonds
			if (isBig == true) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(coordinate2.toUpperCase());  //Checking if coordinate entered is out of bonds
			// System.out.println(valid);
			if (valid == false)
				System.out.println("sorry, coordinates out of the grid. try again.");
			else { // Or else Using the users input and placing in the grid
				grid.isRow(coordinate2);
				grid.isCol(coordinate2);
				boolean Checkcheck = grid.checkGrid();
				//System.out.println(tileCheck); //Making sure if the program was working
				if (Checkcheck == true) {
					System.out.println("sorry, coordinates already being used. try again.");
					continue;
				} else {
					grid.setGrenade();
					j++; // Incrementing only if they put a valid coordinate
					number++;
				}
			}
		}
		System.out.println();
		// Setting the coordinates for the computer
		int n = 0;
		int m = 0;
		while (n < 6) { // Using loops for ships and grenades to see if the computer  entered a coordinate thats already being used 
			grid.randomRow();
			grid.randomCol();
			boolean check = grid.checkGrid();
			if (check == true) {
				continue;
			} else {
				grid.setCShip();
				n++;
			}
		}

		while (m < 4) { // Repeating but for grenades this time
			grid.randomRow();
			grid.randomCol();
			boolean check = grid.checkGrid();
			if (check == true) {
				continue;
			} else {
				grid.setCGrenade();
				m++;
			}
		}
		Battleship gridCopy = new Battleship(grid); /*Making a direct copy of the object so that
		 when a winner is declared, the grid can be easily printed and since a new grid
		 is printed in the beginning, this copy is used to determine if the user entered a coordinate 
		 where it hits a ship or grenade.
		 grid.board(gridCopy);  Checking to see if the program works*/
		
		grid.StartOverGrid(); // Deleting the initial grid since a copy is already made
		// Using the initial object to print out a new fresh grid.
		System.out.println();
		System.out.println("OK, the computer placed its ships and grenades at random. Let's play.");
		System.out.println();
		int turns = 0;

		while (turns < 1000) { // And now the game begins......!!!!
			System.out.print("position of your rocket: "); 
			String gcoordinate = scan.next(); // Taking user input
			boolean isLong = grid.countInput(gcoordinate); // Checking if the user entered a coordinate out of bounds
			if (isLong == true) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			}
			boolean valid = grid.isOut(gcoordinate.toUpperCase()); // Checking if the user entered a coordinate out of bounds
			if (valid == false) {
				System.out.println("sorry, coordinates out of the grid. try again.");
				continue;
			} else { // Or else setting the coordinates the user entered
				grid.isRow(gcoordinate.toUpperCase());
				grid.isCol(gcoordinate);
				boolean Check1 = grid.alreadyEnt(); // Checking if the user already entered these coordinates
				// System.out.println(tileCheck); // Checking if the program was working
				if (Check1 == true) {
					System.out.print("position already called.");
					grid.board(); // Calling method to print grid
					continue;
				} else { // Or else launch a rocket
					grid.launchRocket(gridCopy);
					boolean hitGrenade = grid.hitGrenade(gridCopy);
					if(hitGrenade == true) {
						grid.randomMove(gridCopy);
						grid.launchRocket(gridCopy);
					}

				}
				grid.randomMove(gridCopy); // And now for the computer's turn
				grid.launchRocket(gridCopy);
				boolean hitG = grid.hitGrenade(gridCopy);
				if (hitG == true) { // If the computer hits a grenade, his turn must be skipped, so ask the user for
					// his coordinate twice. under here will be the first time
					
				
					while (turns < 1000) {
						System.out.print("position of your rocket: ");
						String gcoordinate2 = scan.next(); // Taking user input
						boolean isLonge = grid.countInput(gcoordinate2); // Checking if the user entered a coordinate out of bounds
						if (isLonge == true) {
							System.out.println("This coordinate is out of bounds. Please enter another coordinate.");
							continue;
						}
						boolean out = grid.isOut(gcoordinate2.toUpperCase());
						if (out == false) {
							System.out.println("This coordinate is out of bounds. Please enter a valid coordinate.");
							continue;
						} else { // Or else setting the coordinates the user entered
							grid.isRow(gcoordinate2.toUpperCase());
							grid.isCol(gcoordinate2);
							boolean Check2 = grid.alreadyEnt(); // Checking if the user already entered these coordinates
							if (Check2 == true) {
								System.out.print("position already called");
								grid.board(); // Calling method to print grid
								continue;
							} else {
								
								grid.launchRocket(gridCopy);
								boolean hitGrenadeE = grid.hitGrenade(gridCopy);
								if(hitGrenadeE == true) {
									grid.randomMove(gridCopy);
									grid.launchRocket(gridCopy);
								}
							
							}
						
						}
						break;
					}
				}

			}
			turns++; // Incrementing the number of turns
		}
		System.out.println("THANK YOU FOR PLAYING ALONG"); //Printing a closing message
		
		scan.close(); //Closing scanner class
	}
}

