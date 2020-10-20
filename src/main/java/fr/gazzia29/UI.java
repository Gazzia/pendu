package fr.gazzia29;

import java.util.Scanner;

public class UI {
	Scanner scanner;
	String separator;

	public final void clearConsole() {
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	public static void print(Object o) {
		System.out.println(o);
	}

	public UI() {
		scanner = new Scanner(System.in);
		separator = "___________________________________";
	}
}
