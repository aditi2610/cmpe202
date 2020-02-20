/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
	Device d;
	IApp app;
    public MyCardsPay()
    {
    	this.d = Device.getInstance();
    	this.app = (IApp)d;
    }
    public String name() {
        return "My Cards" ; 
    }
    
    public void touch(int x, int y) {
        if(x ==3 && y ==3) {
     	  this.app.execute("A");
        }
        
     }
    
    public String display() {
    	String value = super.display();
    	//TODO this needs to be fetched from somewhere else.
    	value += "[000000000]\n";
    	value += "\n";
    	value += "\n";
    	value += "Scan Now";
    	
    	return value;
    }
    

}

