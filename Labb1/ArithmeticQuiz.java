package Labb_1;

import java.util.Scanner;
import java.util.Random;

public class ArithmeticQuiz {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in); // Create a Scanner object
		Random rand = new Random(); // Create random function to determine type of question

		int score = 0; // Score starts at 0

		char[] operators = { '+', '-', '*', '/' }; //

		for (int i = 1; i <= 10; i++) {

			char op = operators[rand.nextInt(4)]; // Randomize a number between 0-3, pick the operator corresponding to
													// the number
			ArithmeticProblem problem = new ArithmeticProblem(op); //

			System.out.println(problem.getQuestion()); // Gets question from ArithmeticProblem and prints it

			System.out.println("Enter answer:");
			int Answer = scan.nextInt(); // Read user input

			if (Answer == problem.getAnswer()) { // Checks if the answer is correct, adds 10 coins to score if true
				System.out.println("Correct! +10 coins");
				score += 10;

			} else { // If wrong gives you a second chance to answer
				System.out.println("Wrong, Try again!");
				int Answer2 = scan.nextInt();

				if (Answer2 == problem.getAnswer()) {
					System.out.println("Correct! +5 coins");
					score += 5;

				} else {
					System.out.println("Wrong again, + 0 points");
				}
			}
		}
		System.out.println("Your score is: " + score);
		scan.close();
	}
}
