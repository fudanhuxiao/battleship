package battleship;

import java.util.*;

public class BattleshipGame {
	private Ocean ocean;
	private boolean doesContinue;

	public BattleshipGame() {
		ocean = new Ocean();
		doesContinue = true;
	}

	public void runGame() {
		Scanner scanner;
		String input;
		String choice;
		scanner = new Scanner(System.in);
		System.out.println("Let's start the game Battleship!");
		System.out.println("The initial ocean display is: ");
		ocean.print();
		ocean.placeAllShipsRandomly();
		while (doesContinue == true) {
			System.out.println("Please enter the row and column numbers you wants to shoot at (Format: row number,column number): ");
			input = scanner.nextLine();
			String[] strNum = input.split(",");
			int[] intNum = new int[2];
			for (int index=0; index<2; index++) {
				intNum[index] = Integer.parseInt(strNum[index]);
			}
			ocean.shootAt(intNum[0], intNum[1]);
			if (ocean.isOccupied(intNum[0], intNum[1]) == true) {
				System.out.println("hit");
				if (ocean.getShipArray()[intNum[0]][intNum[1]].isSunk() == true) {
					System.out.println("You just sank a " + ocean.getShipArray()[intNum[0]][intNum[1]].getShipType());
				}
			} else {
				System.out.println("miss");
			}
			ocean.print();
			if (ocean.isGameOver() == true) {
				System.out.println("Game is over! Your final score is " + ocean.getShotsFired());
				System.out.println("Would you like to play the game Battleship again? Please enter Y for yes or N for no: ");
				choice = scanner.nextLine();
				if (choice.equals("Y")) {
					doesContinue = true;
				} else {
					doesContinue = false;
				}
			}
		}
		scanner.close();
	}

	public static void main(String[] args) {
		BattleshipGame game = new BattleshipGame();
		game.runGame();
	}
}
