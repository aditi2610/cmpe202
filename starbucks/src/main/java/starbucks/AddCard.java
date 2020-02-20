/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.util.Arrays;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{
	IApp app;
	KeyPad kp;
	boolean focusCVV = false;
	StringBuilder cardNumber;
	StringBuilder cvv;
    public AddCard()
    {
    	this.app = (IApp)Device.getInstance();
    	this.kp= new KeyPad();
    	this.cardNumber= new StringBuilder(9);
    	this.cvv = new StringBuilder(3);
    	
    }
    public String name() {
        return "Add Card" ; 
    } 
    
    public String display() {
    	String value = super.display();
    	value += createStringForCard(cardNumber, 9);
    	value += "\n";
    	value += createStringForCard(cvv, 3);
    	value += "\n";
    	value += kp.display();
    	
    	return value;
    }
    
    public void prev()  {
       this.app.execute("E");   
       }
    
    public void next()  {
    	if(this.cardNumber.length() == 9 && this.cvv.length() == 3) {
    		//go to next screen
    	}else {
    		this.cardNumber = new StringBuilder();
    		this.cvv= new StringBuilder();
    	}
        
     }
    
    public void touch(int x, int y) {
    	if(y == 2 && (x == 1 || x==2 || x==3)) {
    		this.focusCVV = false;
    		
    	} 
    	else if(x==2 && y==3) {
    		this.focusCVV = true;
    	}else {	
    		if(!((x > 0 && x < 4) && (y > 4  && y < 9))) {
    			return;
    		}

    		if(this.focusCVV) {
    			setCvv(x, y);
    		}
    		else if(this.cardNumber.length() <9) {
    			setCard(x, y);
    		}
    	}
    	
    }
    
    private String createStringForCard(StringBuilder s, int reqLength) {
    	char[] spaces = new char[reqLength - s.length()];
    	Arrays.fill(spaces, ' ');
    	return "["+s.toString() + new String(spaces)+"]";
    }
    
    private void setCvv(int x, int y) {
    	String s = this.kp.getKey(x, y);
    	switch (s) {
    	case "X":
    		if (this.cvv.length() == 0) {
    			break;
    		}
    		this.cvv.deleteCharAt(this.cvv.length() - 1);
    		break;
    	case " ":
    		break;
    	default:
    		if (this.cvv.length() == 3) {
    			break;
    		}
    		this.cvv.append(s);
    	}
    }
    
    private void setCard(int x, int y) {
    	String s = this.kp.getKey(x, y);
    	switch (s) {
    	case "X":
    		if (this.cardNumber.length() == 0) {
    			break;
    		}
    		this.cardNumber.deleteCharAt(this.cardNumber.length() - 1);
    		break;
    	case " ":
    		break;
    	default:
    		if (this.cardNumber.length() == 9) {
    			break;
    		}
    		this.cardNumber.append(s);
    	}
    }
}
