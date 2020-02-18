/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** My Cards Screen */
public class MyCards extends Screen
{

    public MyCards()
    {
    	
    }

    public String name() {
        return "My Cards" ; 
    }
    public void touch(int x, int y) {
       if(x ==3  && y ==3) {
    	  
       }
       if(x ==2 && y == 4) {
    	   //navigate to my card options Screen
       }  
    }
    
    public String display() { 
        String value = "" ;
        //SHOW BALANCE OF ACTIVE CARD! 
        return value ; 
    }
}
