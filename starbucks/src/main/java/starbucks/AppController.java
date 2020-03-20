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
	private IMenuCommand displayStore;
	private IMenuCommand displaySettings;
	private IFrame frame;

	private IScreen myCardsPay;
	private IScreen myCardsOptions;
	private IScreen myCardsMoreOptions;
	private IScreen addCard;
	private IScreen addCardScreen;

	private IMenuCommand displayMyCardsPay;
	private IMenuCommand displayMyCardsOptions;
	private IMenuCommand displayMyCardMoreOptions;
	private IMenuCommand displayAddCard;

	private Double balance;
	private CardNumber cardNumber;
	private Cvv cvv;
    private Spacer sp ;
    private KeyPad kp ;
	private boolean focusCvv;
	private AppControllerHelper receiverHelper;

	public AppController() {
		mycards = new CenteredIndentationDecorator(new MyCards());
		frame = new Frame(mycards);
		cardNumber = new CardNumber();
		cvv = new Cvv();
		instantiateClasses();
		balance = 0.0;
		receiverHelper = new AppControllerHelper();
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
		displayStore = new MenuCommand();
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
		myCardsPay = new CenteredIndentationDecorator(new MyCardsPay(cardNumber));
		myCardsOptions = new LeftIndentationDecorator(new MyCardsOptions());
		myCardsMoreOptions = new LeftIndentationDecorator(new MyCardsMoreOptions());
		
		addCardScreen = new AddCard(cardNumber, cvv);
		store = new Store();
		rewards = new LeftIndentationDecorator(new Rewards());
		payments = new LeftIndentationDecorator( new Payments());
		settings = new CenteredIndentationDecorator(new Settings(cardNumber, cvv));
		kp = new KeyPad() ;
        sp = new Spacer() ;
		
		((IDisplayComponent)addCardScreen).addSubComponent(cardNumber);
		((IDisplayComponent)addCardScreen).addSubComponent(cvv);
	
		((IDisplayComponent)addCardScreen).addSubComponent(sp);
		((IDisplayComponent)addCardScreen).addSubComponent(kp);
		
		addCard = new CenteredIndentationDecorator(addCardScreen);
		
		((IKeyPadSubject)kp).attach(cardNumber);
		((IKeyPadSubject)kp).attach(cvv);
		
		
	}
	
	/**
	 * Sets receivers
	 * 
	 */
	public void setReceivers() {
		((AppControllerHelper)receiverHelper).setReceiverMyCardsOptions(displayMyCardsOptions, displayMyCardMoreOptions, frame, myCardsOptions, myCardsMoreOptions);
		((AppControllerHelper)receiverHelper).setReceiverSettingAndAddCard(displaySettings, displayAddCard, frame, settings, addCard);
		((AppControllerHelper)receiverHelper).setReceiverPaymentAndRewards(displayPayments , displayRewards, frame, payments, rewards);
		((AppControllerHelper)receiverHelper).setReceiverMyCards(displayMyCards, displayMyCardsPay, frame, mycards, myCardsPay);
		((AppControllerHelper)receiverHelper).setReceiverStore(displayStore, frame, store);
		
	}

	
	/**
	 * Set Menus for the screen
	 */
	private void setMenus() {
		((IFrame)frame).setMenuItem("A", displayMyCards);
		((IFrame)frame).setMenuItem("B", displayPayments);
		((IFrame)frame).setMenuItem("C", displayRewards);
		((IFrame)frame).setMenuItem("D", displayStore);
		((IFrame)frame).setMenuItem("E", displaySettings);

		((IFrame)frame).setInMenuScreen("MyCard", displayMyCards);
		((IFrame)frame).setInMenuScreen("A1", displayMyCardsPay);
		((IFrame)frame).setInMenuScreen("MyCardOptions", displayMyCardsOptions);
		((IFrame)frame).setInMenuScreen("MyCardMoreOptions", displayMyCardMoreOptions);
		((IFrame)frame).setInMenuScreen("AddCard", displayAddCard);
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
		if(c.equals("MyCard"))
			((IFrame)frame).selectMyCard();
		if(c.equals("A1"))
			((IFrame)frame).selectA1();
		if(c.equals("MyCardOptions"))
			((IFrame)frame).selectCardOptions();
		if(c.equals("MyCardMoreOptions"))
			((IFrame)frame).selectCardMoreOptions();
		if(c.equals("AddCard"))
			((IFrame)frame).selectAddCard();
		else
			((IFrame)frame).cmd(c);
		
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
