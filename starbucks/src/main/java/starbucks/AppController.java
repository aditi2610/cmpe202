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
	private IScreen addCard;

	private IMenuCommand displayMyCardsPay;
	private IMenuCommand displayMyCardsOptions;
	private IMenuCommand displayMyCardMoreOptions;
	private IMenuCommand displayAddCard;

	private Double balance;
	private CardNumber cardNumber;
	private Cvv cvv;
    private Spacer sp ;
    //private IApp app ;
    private KeyPad kp ;
	private boolean focusCvv;

	public AppController() {
		mycards = new MyCards();
		frame = new Frame(mycards);
		cardNumber = new CardNumber();
		cvv = new Cvv();
		instantiateClasses();
		balance = 0.0;
		setReceivers();
		setMenus();
	}

	/**
	 * Instantiates all the classes required by the class
	 */
	private void instantiateClasses() {
		instantiateDependentClasses();

		// setup command pattern
		displayMyCards = new MenuCommand();
		displayPayments = new MenuCommand();
		displayRewards = new MenuCommand();
		doStore = new MenuCommand();
		displaySettings = new MenuCommand();

		displayMyCardsPay = new MenuCommand();
		displayMyCardsOptions = new MenuCommand();
		displayMyCardMoreOptions = new MenuCommand();
		displayAddCard = new MenuCommand();
	}
	
	/**
	 * Instantiates all the classes required by the class
	 */
	private void instantiateDependentClasses() {
		myCardsPay = new MyCardsPay(cardNumber);
		myCardsOptions = new MyCardsOptions();
		myCardsMoreOptions = new MyCardsMoreOptions();
		addCard = new AddCard(cardNumber, cvv);
		store = new Store();
		rewards = new Rewards();
		payments = new Payments();
		settings = new Settings(cardNumber, cvv);
		kp = new KeyPad() ;
        sp = new Spacer() ;
		
		((IDisplayComponent)addCard).addSubComponent(cardNumber);
		((IDisplayComponent)addCard).addSubComponent(cvv);
	
		((IDisplayComponent)addCard).addSubComponent(sp);
		((IDisplayComponent)addCard).addSubComponent(kp);
		
		((IKeyPadSubject)kp).attach(cardNumber);
		((IKeyPadSubject)kp).attach(cvv);
		
	}

	/**
	 * Sets receivers
	 * 
	 */
	private void setReceivers() {

		displayMyCards.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(mycards);
			}
		});
		displayPayments.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(payments);
			}
		});
		displayRewards.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(rewards);
			}
		});
		doStore.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(store);
			}
		});
		displaySettings.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(settings);
			}
		});

		displayMyCardsPay.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(myCardsPay);
			}
		});

		displayMyCardsOptions.setReceiver(new IMenuReceiver() {
			/**
			 * sets the current screen to params
			 */
			@Override
			public void doAction() {
				frame.setCurrentScreen(myCardsOptions);

			}
		});
		displayMyCardMoreOptions.setReceiver(new IMenuReceiver() {
			/**
			 * sets the current screen to params
			 */
			@Override
			public void doAction() {
				frame.setCurrentScreen(myCardsMoreOptions);

			}
		});

		displayAddCard.setReceiver(new IMenuReceiver() {
			/**
			 * sets the current screen to params
			 */
			@Override
			public void doAction() {
				frame.setCurrentScreen(addCard);

			}
		});

	}

	/**
	 * Set Menus for the screen
	 */
	private void setMenus() {
		frame.setMenuItem("A", displayMyCards);
		frame.setMenuItem("B", displayPayments);
		frame.setMenuItem("C", displayRewards);
		frame.setMenuItem("D", doStore);
		frame.setMenuItem("E", displaySettings);

		frame.setInMenuScreen("MyCard", displayMyCards);
		frame.setInMenuScreen("A1", displayMyCardsPay);
		frame.setInMenuScreen("MyCardOptions", displayMyCardsOptions);
		frame.setInMenuScreen("MyCardMoreOptions", displayMyCardMoreOptions);
		frame.setInMenuScreen("AddCard", displayAddCard);
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
	}

	/**
	 * Execute Menu Bar Command
	 *
	 * @param c Menu Bar Option (A, B, C, D or E)
	 */
	public void execute(String c) {
		if(c.equals("A") || c.equals("B"))
			frame.cmd(c);
		if(c.equals("C") || c.equals("D"))
			frame.cmd(c);
		if(c.equals("E"))
			frame.cmd(c);
		if(c.equals("MyCard"))
			frame.selectMyCard();
		if(c.equals("A1"))
			frame.selectA1();
		if(c.equals("MyCardOptions"))
			frame.selectCardOptions();
		if(c.equals("MyCardMoreOptions"))
			frame.selectCardMoreOptions();
		if(c.equals("AddCard"))
			frame.selectAddCard();
		
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

	/**
	 * @return the balance
	 */
	@Override
	public String getBalance() {
		return String.format("%.2f", this.balance);
	}

	/**
	 * sets balance
	 * 
	 * @param d d is the balance to be set
	 * 
	 */
	@Override
	public void setBalance(Double d) {
		this.balance = d;

	}


	/**
	 * @return the card number
	 */
	public StringBuilder getCardNumber() {
		return this.cardNumber.getCardNum();
	}

	/**
	 * @return the Cvv
	 */
	public StringBuilder getCvv() {
		return this.cvv.getCvv();
	}

	/**
	 * return focus defualt is false
	 * 
	 * @return if the focus is on Cvv or not
	 */
	public boolean isFocusCvv() {
		return focusCvv;
	}

	/**
	 * 
	 * @param focusCvv sets the focus
	 */
	public void setFocusCvv(boolean focusCvv) {
		this.focusCvv = focusCvv;
	}

}
