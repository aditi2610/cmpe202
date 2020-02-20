/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * My Cards Screen
 */
public class MyCards extends Screen {
	Device d;
	IApp app;
	AddCard addCard;
    public MyCards() {
    	this.d = Device.getInstance();
    	this.app = (IApp) d;
    	addCard = new AddCard();
    }
 
    public String name() {
        return "My Cards";
    }

    public void touch(int x, int y) {
        if (x == 3 && y == 3) {
        	this.app.execute("A1");
        }
        if (x == 2 && y == 4) {
           this.app.execute("MyCardOptions");
        }
    }

    public String display() { 
        String value = super.display() ;
        value += "$" +addCard.getBalance();
        //SHOULD PRINT SCAN NOW, along with card id of active card
        return value ; 
    }
}
