/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main Entry Point.
 */
final class Main {

	/**
	 * Prevent Construction.
	 */
	private Main() {
		// Utility Class
		return;
	}

	/**
	 * Main App Entry Point.
	 * 
	 * @param args No args expected.
	 *
	 */
	public static void main(final String[] args) {
		for (String v : args) {
			System.err.print("Args: " + v + " ");
		}
		System.err.println();
		Device d = Device.getInstance();
		IApp app = (IApp) d;
		Console c = System.console();
		String msg = "";
		for (;;) {
			flushAndPrintScreenContents(app, msg);
			String ch = c.readLine(); // get user command
			// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			// String ch = bf.readLine();
			String cmd = ch.toLowerCase(); // convert to lower case
			cmd = cmd.replaceAll("\\s", ""); // remove all whitespaces

			/* process commands */
			msg = cmd;
			System.err.println(String.format("Input Command: %s", cmd));
			msg = executeCmd(app, msg, cmd);
		}
	}

	/**
	 * 
	 * @param app instance of IApp
	 * @param msg prints the msg
	 */
	private static void flushAndPrintScreenContents(IApp app, String msg) {
		System.out.print("\033[H\033[2J"); // clear the screen
		System.out.flush();
		System.out.println(app.screenContents());
		System.out.println(msg);
		System.out.print("=> ");

	}

	/**
	 * executes whenever any command is executed
	 * 
	 * @param app instance of IApp
	 * @param msg cmd passed through args
	 * @param cmd cmd passed
	 * @return String contents
	 */
	private static String executeCmd(IApp app, String msg, String cmd) {
		if (cmd.startsWith("touch")) {
			msg = executeTouch(app, cmd);
		}
		if (cmd.startsWith("prev")) {
			msg = "cmd: previous";
			app.prev();
		}
		if (cmd.startsWith("next")) {
			msg = "cmd: next";
			app.next();
		}
		if (cmd.equalsIgnoreCase("portrait")) {
			app.portrait();
		}
		if (cmd.equalsIgnoreCase("landscape")) {
			app.landscape();
		}
		if (cmd.startsWith("login")) {
			app.touch(1, 5); // 1
			app.touch(2, 5); // 2
			app.touch(3, 5); // 3
			app.touch(1, 6); // 4
		} else {
			switch (cmd) {
				case "a":
				case "b":
				case "c":
				case "d":
				case "e":
					String selection = cmd.toUpperCase();
					msg = "selected: " + selection;
					app.execute(selection);
					break;
				default:
					msg = " ";
			}

		}
		return msg;
	}

	/**
	 * this would execute whenever the command starts with touch
	 * 
	 * @param app instance of Iapp
	 * @param cmd entered by the user
	 * @return String to be displayed
	 */
	private static String executeTouch(IApp app, String cmd) {
		String msg;
		String parms = cmd.replaceFirst("touch", "");
		parms = parms.substring(1);
		parms = parms.substring(0, parms.length() - 1);
		String[] values = parms.split(",");
		for (String s : values) {
			System.err.print("Value: " + s + " ");
		}
		System.err.println("");
		String x = values[0];
		String y = values[1];
		msg = "touch: x=" + x + " y=" + y;
		app.touch(Integer.parseInt(x), Integer.parseInt(y));
		return msg;
	}
}
