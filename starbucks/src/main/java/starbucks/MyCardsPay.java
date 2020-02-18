/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
    public MyCardsPay()
    {

    }
    public String name() {
        return "My Cards" ; 
    }
    
    public void touch(int x, int y) {
        if(x ==3 && y ==3) {
     	   //navigate to my cards main Screen
        }
        
     }
    
    public String display() { 
        String value = "" ;
        //SHOULD PRINT SCAN NOW, along with card id of active card
        return value ; 
    }

}

