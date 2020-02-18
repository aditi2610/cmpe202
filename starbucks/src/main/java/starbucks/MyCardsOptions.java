/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   
    public MyCardsOptions()
    {
       
    }
    public String name() {
        return "My Cards" ; 
    }
    
    public void touch(int x, int y) {
        if((x ==1 || x==2 || x==3) && y ==7) {
     	   //navigate to my cards more options screen
        }
        
     }
    public String display() { 
        String value = "" ;
        //Screen should show the options “Reload, Refresh Balance, or More Options”.
        return value ; 
    }
}
