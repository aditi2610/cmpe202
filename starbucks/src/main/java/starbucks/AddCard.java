/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{
    public AddCard()
    {
    }
    public String name() {
        return "Add Card" ; 
    } 
    
    public String display() {
    	String value = super.display();
    	value += "Enter a New Card";
    	//change this to input field
    	value +="[123456789]\n[123]";
    	return value;
    }
}
