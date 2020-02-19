/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {

	private IScreen mycards;
	private IScreen store;
	private IScreen rewards;
	private IScreen payments;
	private IScreen settings;
	private IMenuCommand displayMyCards;
	private IMenuCommand displayPayments;
	private IMenuCommand displayRewards;
	private IMenuCommand doStore;
	private IMenuCommand displaySettings;
	private IFrame frame;
	
	
	private IScreen myCardsPay;
	private IScreen myCardsOptions;
	private IScreen myCardsMoreOptions;
	
	private IMenuCommand displayMyCardsPay;
	private IMenuCommand displayMyCardsOptions;
	private IMenuCommand displayMyCardMoreOptions;

	public AppController() {
		mycards = new MyCards();
		frame = new Frame(mycards);

		myCardsPay = new MyCardsPay();
		myCardsOptions = new MyCardsOptions();
		myCardsMoreOptions = new MyCardsMoreOptions();
		
		store = new Store();
		rewards = new Rewards();
		payments = new Payments();
		settings = new Settings();

		// setup command pattern
		displayMyCards = new MenuCommand();
		displayPayments = new MenuCommand();
		displayRewards = new MenuCommand();
		doStore = new MenuCommand();
		displaySettings = new MenuCommand();
		
		displayMyCardsPay = new MenuCommand();
		displayMyCardsOptions = new MenuCommand();
		displayMyCardMoreOptions = new MenuCommand();

		displayMyCards.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(mycards);
					}
				}
		);
		displayPayments.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(payments);
					}
				}
		);
		displayRewards.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(rewards);
					}
				}
		);
		doStore.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(store);
					}
				}
		);
		displaySettings.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(settings);
					}
				}
		);

		displayMyCardsPay.setReceiver(
				new IMenuReceiver() {
					/** Command Action */
					public void doAction() {
						frame.setCurrentScreen(myCardsPay);
					}
				}
		);

		displayMyCardsOptions.setReceiver(new IMenuReceiver() {
			
			@Override
			public void doAction() {
				frame.setCurrentScreen(myCardsOptions);
				
			}
		});
		displayMyCardMoreOptions.setReceiver(new IMenuReceiver() {
			
			@Override
			public void doAction() {
				frame.setCurrentScreen(myCardsMoreOptions);
				
			}
		});
		frame.setMenuItem("A", displayMyCards);
		frame.setMenuItem("B", displayPayments);
		frame.setMenuItem("C", displayRewards);
		frame.setMenuItem("D", doStore);
		frame.setMenuItem("E", displaySettings);
		
		frame.setInMenuScreen("A1", displayMyCardsPay);
		frame.setInMenuScreen("MyCardOptions", displayMyCardsOptions );
		frame.setInMenuScreen("MyCardMoreOptions", displayMyCardMoreOptions);
	}


	/**
	 * Switch to Landscape Mode
	 */
	public void landscape() {
		frame.landscape();
	}

	/**
	 * Switch to Portait Mode
	 */
	public void portrait() {
		frame.portrait();
	}

	/**
	 * Send In Touch Events
	 *
	 * @param x X Coord
	 * @param y Y Coord
	 */
	public void touch(int x, int y) {
		frame.touch(x, y);
	}

	/**
	 * Display Current Screen
	 */
	public void display() {
		System.err.println("" + screen());
		frame.display();
//        should contain a “display()” method to display the current Screen Name
//        as well as well as call the “display()” method of the current Screen
//        each time the Screen changes
	}

	/**
	 * Execute Menu Bar Command
	 *
	 * @param c Menu Bar Option (A, B, C, D or E)
	 */
	public void execute(String c) {
		switch(c) {
			case "A":
			case "B":
			case "C":
			case "D":
			case "E":
				frame.cmd(c);
				break;
			case "A1":
				frame.selectA1();
				break;
			case "MyCardOptions":
				frame.selectCardOptions();
				break;
			case "MyCardMoreOptions":
				frame.selectCardMoreOptions();
				break;
		}
	}

	/**
	 * Navigate to Previous Screen
	 */
	public void prev() {
		frame.previousScreen();
	}

	/**
	 * Navigate to Next Screen
	 */
	public void next() {
		frame.nextScreen();
	}

	/**
	 * Get Current Screen Name
	 *
	 * @return Screen Name
	 */
	public String screen() {
		return frame.screen();
	}

	/**
	 * Get Current Screen Contents
	 *
	 * @return Current Screen Contents
	 */
	public String screenContents() {
		return frame.contents();
	}
 
	public IFrame getFrame() {
		return frame;
	}
}
