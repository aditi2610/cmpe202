/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

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
        String value = super.display() ;
        value +="\nReload\n";
        value+="Refresh\n";
        value+="More Options\n";
        value +="Cancel";       
        //Screen should show the options “Reload, Refresh Balance, or More Options”.
        return value ; 
    }
}
