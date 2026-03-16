package Labb_1;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Spellcheck {

	public static WordList dictionary = new WordList();

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter a word");

		for (;;) {

			String word = scan.nextLine();

			if (word.equals("Exit")) {
				break;
			}

			if (word.isEmpty()) {
				continue;
			}

			if (dictionary.contains(word)) {
				System.out.println("This word is spelled correctly");
			} else {

				System.out.println("Word not found, suggestions:");

				ArrayList<String> offer = Suggestions(new StringBuilder(word));

				if (offer.isEmpty()) {
					System.out.println("No suggestions found");
				} else {

					for (String s : offer) {
						System.out.println("   " + s);
					}
				}
			}
		}
		scan.close();
	}

	private static ArrayList<String> Suggestions(StringBuilder word) {

		ArrayList<String> list = new ArrayList<>();

		// Delete 1 character
		for (int i = 0; i < word.length(); i++) {
			StringBuilder sb = new StringBuilder(word); // Stringbuilder inside the loop so we create a new string every
														// loop, if not we keep changing the same string
			sb.deleteCharAt(i); 						// Deletes character with stringbuilder
			String maybeWord = sb.toString();
			if (!maybeWord.isEmpty() && dictionary.contains(maybeWord)) { 	// Check if string is empty and (&&) if the
																			// words exists in dictionary
				list.add(maybeWord); 					// If both true, adds it to suggestion array
			}
		}

		// Add 1 character
		for (int i = 0; i < word.length(); i++) {
			for (char j = 'a'; j <= 'z'; j++) { 		// Create a loop that goes through the alphabet and gives (j) a letter
				StringBuilder sb = new StringBuilder(word);
				sb.insert(i, j); 						// Inserts the new letter at the specified index (i)
				String maybeWord = sb.toString();
				if (dictionary.contains(maybeWord)) { 	// Checks if the new word exists and if it does adds it to the
														// list of suggestions
					list.add(maybeWord);
				}
			}
		}

		// Change 1 character
		for (int i = 0; i < word.length(); i++) {
			char primary = word.charAt(i); 				// Saves the original letter at this position
			for (char j = 'a'; j <= 'z'; j++) { 		// Create a loop that goes through the alphabet and gives (j) a letter
				if (j == primary)
					continue; 							// Checks if it is the original letter and skips it
				StringBuilder sb = new StringBuilder(word);
				sb.setCharAt(i, j); 					// setCharAt instead of insert because we want to replace the old letter
				String maybeWord = sb.toString();
				if (dictionary.contains(maybeWord)) {
					list.add(maybeWord);
				}
			}
		}

		// Swap character
		for (int i = 0; i < word.length() - 1; i++) {
			StringBuilder sb = new StringBuilder(word);
			char temp = sb.charAt(i); //
			sb.setCharAt(i, sb.charAt(i + 1)); //
			sb.setCharAt(i + 1, temp); //
			String maybeWord = sb.toString();
			if (dictionary.contains(maybeWord)) { 	// Checks if the new word exists and if it does adds it to the list of
													// suggestions
				list.add(maybeWord);
			}
		}

		// Space
		for (int i = 0; i < word.length() - 1; i++) { 	//
			String newWord = word.substring(0, i); 		//
			String newWord2 = word.substring(i); 		//
			if (dictionary.contains(newWord) && dictionary.contains(newWord2)) { 	// Checks if both of the new words
																					// exists and if they do adds them
																					// to the list of suggestions
				list.add(newWord + " " + newWord2); 	// Here duplicates can occur
			}
		}

		return list;
	}
}