package fr.gazzia29;

import java.util.Arrays;
import java.util.List;

public class Game {
	private String word;
	protected Player player;
	String displayedWord;

	public Game(String word) {
		this.word = word;
		this.displayedWord = newDisplayedWord();
		player = new Player(this);
		UI.clearConsole();
		UI.refreshDisplay(this);
	}

	protected boolean doesWordContain(char letter) {
		return word.indexOf(letter) != -1;
	}

	protected String newDisplayedWord() {
		String result = "";
		for (int i = 0; i < word.length(); i++) {
			result += "- ";
		}
		return result;
	}

	protected void revealLetterInWord(char letter) {
		List<String> WordArray = Arrays.asList(this.word.split(""));
		List<String> DisplayedWordArray = Arrays.asList(this.displayedWord.split(""));
		for (int i = 0; i < WordArray.size(); i++) {
			char l = WordArray.get(i).charAt(0);
			if (l == letter) {
				DisplayedWordArray.set(i * 2, Character.toString(letter));
			}
		}
		this.displayedWord = "";
		for (String l : DisplayedWordArray) {
			this.displayedWord += l;
		}
	}

	protected void addScore() {
		Score score = new Score(word, player.errors);
		App.bestScores.add(score);
	}
}
