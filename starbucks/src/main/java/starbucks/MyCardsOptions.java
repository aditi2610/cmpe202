/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import starbucks.Device.ORIENTATION_MODE;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   Device d;
   IApp app;
    public MyCardsOptions()
    {
       this.d = Device.getInstance();
       this.app =(IApp)d;
    }
    public String name() {
        return "My Cards" ; 
    }
    
    public void touch(int x, int y) {
        if((x ==1 || x==2 || x==3) && y==7) {	  
        	this.app.execute("MyCardMoreOptions");
        }
        
     }
    public String display() { 
    	if(d.getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE)
    	{
    		d.setPortraitOrientation();
    	}
        String value = super.display() ;
        value +="\nReload\n";
        value+="Refresh\n";
        value+="More Options\n";
        value +="Cancel";  
        value += "\n";
        //Screen should show the options “Reload, Refresh Balance, or More Options”.
        return value ; 
    }
}
