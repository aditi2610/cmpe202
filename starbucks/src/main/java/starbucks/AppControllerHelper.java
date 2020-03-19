package starbucks;
/**
 * Provides Receiver to App controller class
 * AppControllerHelper
 * @author aditimangal
 *
 */
public class AppControllerHelper {

	
	/**
	 * Sets receivers
	 * @param displayPayments displaysPayments
	 * @param displayRewards rewards
	 * @param frame frame
	 * @param payments rewards
	 * @param rewards rewards
	 */
	public void setReceiverPaymentAndRewards(IMenuCommand displayPayments, IMenuCommand displayRewards , IFrame frame, IScreen payments,IScreen rewards ) {
		displayPayments.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				((IFrame)frame).setCurrentScreen(payments);
			}
		});
		displayRewards.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				((IFrame)frame).setCurrentScreen(rewards);
			}
		});
	}
	
	/**
	 * Sets receivers
	 * @param displayStore rewards
	 * @param frame frame
	 * @param store rewards
	 */
	public void setReceiverStore(IMenuCommand displayStore , IFrame frame, IScreen store ) {
		displayStore.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				((IFrame)frame).setCurrentScreen(store);
			}
		});
	}
	
	
	/**
	 * Sets receivers
	 * @param displaySettings displaysPayments
	 * @param displayAddCard rewards
	 * @param frame frame
	 * @param settings settings
	 * @param addCard addCard
	 */
	public void setReceiverSettingAndAddCard(IMenuCommand displaySettings, IMenuCommand displayAddCard , IFrame frame, IScreen settings,IScreen addCard ) 
	{
		displaySettings.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(settings);
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
	 * Sets receivers
	 * @param displayMyCardsOptions displayMyCardsOptions
	 * @param displayMyCardMoreOptions displayMyCardMoreOptions
	 * @param frame frame
	 * @param myCardsOptions myCardsOptions
	 * @param myCardsMoreOptions myCardsMoreOptions
	 */
	public void setReceiverMyCardsOptions(IMenuCommand displayMyCardsOptions,IMenuCommand displayMyCardMoreOptions, IFrame frame, IScreen myCardsOptions,IScreen myCardsMoreOptions )
	{
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
	}
	
	/**
	 * Sets receivers
	 * @param displayMyCards displayMyCards
	 * @param displayMycardsPay displayMycardsPay
	 * @param frame frame
	 * @param mycards mycards
	 * @param myCardsPay myCardsPay
	 */
	public void setReceiverMyCards(IMenuCommand displayMyCards,IMenuCommand displayMycardsPay, IFrame frame, IScreen mycards, IScreen myCardsPay ) {
		displayMyCards.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(mycards);
			}
		});
		displayMycardsPay.setReceiver(new IMenuReceiver() {
			/** Command Action */
			public void doAction() {
				frame.setCurrentScreen(myCardsPay);
			}
		});
	}

}