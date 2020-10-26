package fr.gazzia29;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class App {
	private static List<String> wordList;
	static ArrayList<Score> bestScores;

	protected static void createNewGame() {
		String randomWord = getRandomWord();
		new Game(randomWord);
	}

	protected static String getRandomWord() {
		return wordList.get(new Random().nextInt(wordList.size()));
	}

	public static void main(String[] args) {
		try {
			wordList = Files.readAllLines(Paths.get("words.txt"), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(wordList.get(0));
		bestScores = new ArrayList<Score>();
		new UI();
		UI.showMenuPrincipal();
	}
}