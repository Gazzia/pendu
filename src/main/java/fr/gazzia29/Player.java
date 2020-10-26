package fr.gazzia29;

import java.util.ArrayList;

public class Player {
	protected int errors;
	Game game;
	ArrayList<Character> lettersTried;

	public Player() {
		lettersTried = new ArrayList<Character>();
	}

	protected boolean isLetterAlreadyTried(char lettre) {
		return lettersTried.contains(lettre);
	}

	protected void guess(char lettre) {
		if (isLetterAlreadyTried(lettre)) {
			UI.sayLetterAlreadyTried();
			UI.refreshDisplay(game);
		} else {
			lettersTried.add(lettre);
			if (game.doesWordContain(lettre)) {
				UI.sayGoodLetter();
				game.revealLetterInWord(lettre);
			} else {
				UI.sayWrongLetter();
				this.addError();
			}
			UI.refreshDisplay(game);
		}
	}

	protected void addError() {
		errors++;
	}

	protected boolean isDead() {
		return errors >= 8;
	}

	protected boolean hasWon() {
		return !this.isDead() && !game.displayedWord.contains("-");
	}

	public Player(Game game) {
		this.game = game;
		this.errors = 0;
		this.lettersTried = new ArrayList<Character>();
	}

}
