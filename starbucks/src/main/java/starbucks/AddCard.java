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
	private static StringBuilder cardNumber;
	private static StringBuilder cvv;
	private static Double balance = 0.0;
	
	public AddCard()
    {
    	this.app = (IApp)Device.getInstance();
    	this.kp= new KeyPad();
    	cardNumber= new StringBuilder();
    	cvv = new StringBuilder();
    	
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
    	value += "\n";
    	
    	return value;
    }
    
    public void prev()  {
       this.app.execute("E");   
       }
    
    public void next()  {
    	if(cardNumber.length() == 9 && cvv.length() == 3) {
    		this.app.execute("A");
    		this.setBalance(20.00);
    	}else {
    		cardNumber = new StringBuilder();
    		cvv= new StringBuilder();
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
    		else if(cardNumber.length() <9) {
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
    		if (cvv.length() == 0) {
    			break;
    		}
    		cvv.deleteCharAt(cvv.length() - 1);
    		break;
    	case " ":
    		break;
    	default:
    		if (cvv.length() == 3) {
    			break;
    		}
    		cvv.append(s);
    	}
    }
    
    private void setCard(int x, int y) {
    	String s = this.kp.getKey(x, y);
    	switch (s) {
    	case "X":
    		if (cardNumber.length() == 0) {
    			break;
    		}
    		cardNumber.deleteCharAt(cardNumber.length() - 1);
    		break;
    	case " ":
    		break;
    	default:
    		if (cardNumber.length() == 9) {
    			break;
    		}
    		cardNumber.append(s);
    	}
    }
    public String getCardNumber() {
    	if(cardNumber.length() ==0)
    		return "000000000";
    	else
		return cardNumber.toString();
	}
	public String getCvv() {
		if(cvv.length() ==0)
    		return "000";
    	else
    		return cvv.toString();
	}
	public String getBalance() {
		return String.format("%.2f", balance);
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
