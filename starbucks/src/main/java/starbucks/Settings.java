/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.util.ArrayList;

/** Settings Screen */
public class Settings extends Screen
{
	IApp app;
    public Settings()
    {
     this.app = (IApp)Device.getInstance() ; 
    }
    public String name() {
        return "Settings" ; 
    } 
    
    public void touch(int x, int y) {
    	if(y ==1 && (x ==1 || x==2 || x==3)) {
    		this.app.execute("AddCard");
    	}
    }
   
    
    public String display() { 
        String value = super.display() ;
        value = "Add Card\nDeleteCard\nBilling\nPasscode\n\nAbout|Terms\nHelp";
        return value ; 
    }

   
}
