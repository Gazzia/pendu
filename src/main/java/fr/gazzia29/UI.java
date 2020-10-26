package fr.gazzia29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UI {
	private static Scanner scanner;
	private static String separator;

	protected final static void clearConsole() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	protected static void print(Object o) {
		System.out.println(o);
	}

	protected static void showMenuPrincipal() {
		print(separator);
		print("## PENDU - Menu Principal");
		print("Que voulez-vous faire ?\n");
		print("1) Nouvelle partie");
		print("2) Tableau des scores\n");
		print("Choix: ");
		String result = scanner.nextLine();
		if (result.equals("1")) {
			App.createNewGame();
		} else if (result.equals("2")) {
			listScores();
		} else {
			clearConsole();
			print("Je n'ai pas compris la commande..");
			showMenuPrincipal();
		}
	}

	protected static String getLettersTried(ArrayList<Character> lettersTried) {
		List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
		String result = "";

		for (int i = 0; i < alphabet.size(); i++) {
			char letter = alphabet.get(i);
			if (lettersTried.contains(letter)) {
				result += "  ";
			} else {
				result += letter + " ";
			}
		}

		return result;
	}

	protected static void sayLetterAlreadyTried() {
		clearConsole();
		print("Cette lettre a déjà été essayée !");
	}

	protected static void sayAtLeastOneLetter() {
		clearConsole();
		print("Entrez au moins une lettre !");
	}

	protected static void sayWrongLetter() {
		clearConsole();
		print("Cette lettre n'est pas dans le mot..");
	}

	protected static void sayGoodLetter() {
		clearConsole();
		print("Bien joué !");
	}

	protected static void askForLetter(Game game) {
		print("Entrez une lettre :");
		String line = scanner.nextLine().toLowerCase();
		if (line.length() > 0) {
			char lettre = line.charAt(0);
			game.player.guess(lettre);
		} else {
			clearConsole();
			sayAtLeastOneLetter();
			refreshDisplay(game);
		}

	}

	public static void refreshDisplay(Game game) {
		// clearConsole();
		if (game.player.isDead()) {
			print("Vous avez perdu !");
		}
		if (game.player.hasWon()) {
			game.addScore();
			print("Vous avez gagné ! Bravo !");
		}
		print(separator);
		print(getDessin(game.player.errors));
		print(game.displayedWord);
		print(separator);
		print(getLettersTried(game.player.lettersTried));
		print(separator);
		if (!game.player.isDead() && !game.player.hasWon()) {
			askForLetter(game);
		} else {
			print("\n Appuyez sur entrée pour revenir au menu..");
			scanner.nextLine();
			UI.clearConsole();
			UI.showMenuPrincipal();
		}
	}

	public static String getDessin(int errors) {
		String result = "";
		switch (errors) {
			case 1:
				result = "_____________\n" + " | /       |\n" + " |/\n" + " |\n" + " |\n" + " |\n" + "_|____________\n";
				break;
			case 2:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |\n" + " |\n" + " |\n"
						+ "_|____________\n";
				break;
			case 3:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |         |\n" + " |\n" + " |\n"
						+ "_|____________\n";
				break;
			case 4:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |        -|\n" + " |\n" + " |\n"
						+ "_|____________\n";
				break;
			case 5:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |        -|-\n" + " |\n" + " |\n"
						+ "_|____________\n";
				break;
			case 6:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |        -|-\n" + " |         /\n"
						+ " |\n" + "_|____________\n";
				break;
			case 7:
				result = "_____________\n" + " | /       |\n" + " |/        O\n" + " |        -|-\n"
						+ " |         /\\\n" + " |\n" + "_|____________\n";
				break;
			case 8:
				result = "_____________\n" + " | /       |\n" + " |/        X\n" + " |        -|-\n"
						+ " |         /\\\n" + " |\n" + "_|____________\n";
				break;
			default:
				result = "";

		}
		return result;
	}

	protected static void listScores() {
		clearConsole();
		if (App.bestScores.size() > 0) {
			print("Meilleurs scores :\n");
			Comparator<Score> comp = Comparator.comparing(s -> s.essais);
			Collections.sort(App.bestScores, comp.thenComparing(s -> s.word.length()));
			for (int i = 0; i < App.bestScores.size(); i++) {
				Score score = App.bestScores.get(i);
				print(i + ") " + score.essais + " erreurs : " + score.word);
			}
		} else {
			print("Aucun score à afficher");
		}
		print("\n Appuyez sur entrée pour revenir au menu..");
		scanner.nextLine();
		UI.clearConsole();
		UI.showMenuPrincipal();
	}

	public UI() {
		scanner = new Scanner(System.in);
		separator = "___________________________________";
		clearConsole();
	}
}
