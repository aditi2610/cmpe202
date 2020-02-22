/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

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
        String out = super.display() ;
        String value = "";
        value = "Add Card\n";
        value +="Delete Card\n";
        value +="Billing\n";
        value +="Passcode\n\n";
        value +="About|Terms\n";
        value +="Help";
        out +=super.formatSpacing(value);
 
        return out ; 
    }

   
}
