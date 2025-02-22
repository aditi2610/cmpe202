/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.io.Console;
import java.io.IOException;

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
			msg = executeCmd(app, cmd);
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
	private static String executeCmd(IApp app, String cmd) {
		String msg = " ";
		if (cmd.startsWith("touch")) msg = executeTouch(app, cmd);
		if (cmd.startsWith("prev")) {msg = "cmd: previous";
			((IApp)app).prev();}
		if (cmd.startsWith("next")) {
			msg = "cmd: next";
			((IApp)app).next();}
		if (cmd.equalsIgnoreCase("portrait")) app.portrait();
		if (cmd.equalsIgnoreCase("landscape")) app.landscape();
		if (cmd.startsWith("login")) {
			((IApp)app).touch(1, 5); // 1
			((IApp)app).touch(2, 5); // 2
			((IApp)app).touch(3, 5); // 3
			((IApp)app).touch(1, 6); // 4
			}
		if (handleMenuOption(app, cmd)) msg = "selected: " + cmd.toUpperCase();
		return msg;
	}
	
	/**
	 * executes L
	 * 
	 * @param app instance of IApp
	 * @param cmd passed
	 * @return boolean true/false
	 */
	private static boolean handleMenuOption(IApp app, String cmd) {
		if (cmd.equals("a") || cmd.equals("b") || cmd.equals("e")) {
			app.execute(cmd.toUpperCase());
			return true;
		}
		if (cmd.equals("c") || cmd.equals("d")) {
			app.execute(cmd.toUpperCase());
			return true;
		}
		
		return false;
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
