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
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		System.err.println("Args: " + args);
		Device d = Device.getInstance();
		IApp app = (IApp) d;
//		Console c = System.console();
		BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
		String msg = "";
		for (;;) {
			System.out.print("\033[H\033[2J"); // clear the screen
			System.out.flush();
			System.out.println(app.screenContents());
			System.out.println(msg);
			System.out.print("=> ");
			String ch = c.readLine(); // get user command
			String cmd = ch.toLowerCase(); // convert to lower case
			cmd = cmd.replaceAll("\\s", ""); // remove all whitespaces

			/* process commands */
			msg = cmd;
			System.err.println(String.format("Input Command: %s", cmd)) ;
			msg = executeCmd(app, msg, cmd);
		}
	}

	private static String executeCmd(IApp app, String msg, String cmd) {
		if (cmd.startsWith("touch")) {
			msg = executeTouch(app, cmd);
		} else if (cmd.equals("a") || cmd.equals("b") || cmd.equals("c") || cmd.equals("d") || cmd.equals("e")) {
			String selection = cmd.toUpperCase();
			msg = "selected: " + selection;
			app.execute(selection);

		} else if (cmd.startsWith("prev")) {
			msg = "cmd: previous";
			app.prev();
		} else if (cmd.startsWith("next")) {
			msg = "cmd: next";
			app.next();
		} else if (cmd.equalsIgnoreCase("portrait")) {
			app.portrait();
		} else if (cmd.equalsIgnoreCase("landscape")) {
			app.landscape();
		} else if (cmd.startsWith("login")) {
			app.touch(1, 5); // 1
			app.touch(2, 5); // 2
			app.touch(3, 5); // 3
			app.touch(1, 6); // 4
		} else {
			msg = "";
		}
		return msg;
	}

	private static String executeTouch(IApp app, String cmd) {
		String msg;
		String parms = cmd.replaceFirst("touch", "");
		parms = parms.substring(1);
		parms = parms.substring(0, parms.length() - 1);
		String[] values = parms.split(",");
		System.err.println("Value: " + values);
		String x = values[0];
		String y = values[1];
		msg = "touch: x=" + x + " y=" + y;
		app.touch(Integer.parseInt(x), Integer.parseInt(y));
		return msg;
	}
}
