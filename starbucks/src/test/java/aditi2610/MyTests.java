package aditi2610;

/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import starbucks.Device;
import starbucks.IApp;

public class MyTests
{

    IApp app ;

    public MyTests()
    {
    }

    @Before
    public void setUp()
    {
        app = (IApp) Device.getNewInstance() ;
    }

    /*
     * What happens if I entered half of the digits in card number and pressed previous
     * Assert : Card number should be empty and not retain values, since card details have
     * not been saved.
     */
    @Test
    public void MyCardTest1()
    {
        String[] lines ;
        assertEquals("", app.screen().trim());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen().trim());
        app.touch(1,1) ; // Add New Card
        assertEquals("Add Card", app.screen().trim());
        // Card Id digits
        app.touch(1,5); // 1
        app.touch(2,5); // 2
        app.touch(3,5); // 3
        app.touch(1,6); // 4
       // 
        app.prev();
        app.touch(1,1);
        app.display() ;
        lines = app.screenContents().split("\n"); 
        assertEquals("[]", lines[4].trim());
  
        }

    /*
     * the app should retain the values unless the new card has been saved successfully
     */
    @Test
    public void MyCardTest2()
    {
        String[] lines ;
        assertEquals("", app.screen().trim());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen().trim());
        app.touch(1,1) ; // Add New Card
        assertEquals("Add Card", app.screen().trim());
        // Card Id digits
        app.touch(1,5); // 1
        app.touch(2,6); // 5
        app.touch(3,7); // 9
        app.touch(3,6); // 6
        app.touch(2,6); // 5
        app.touch(2,5); // 2
        app.touch(1,7); // 7
        app.touch(1,6); // 4
        app.touch(1,6); // 4
        app.touch(2,3); // focus on card code
        // Card Code digits
        app.touch(1,7); // 7
        app.touch(2,6); // 5
        app.touch(3,5); // 3
        // check digit entry
        //save card
        //card details have been saved now.
        app.next();
        app.touch(3,3);    
        lines = app.screenContents().split("\n");  
        assertEquals("[159652744]", lines[4].trim());
 
        app.execute("E");
        //Trying to add new Card now
        app.touch(1, 1);
        //Entered 3 digits in card and pressed prev in between.
        app.touch(1,5); // 1
        app.touch(2,6); // 5
        app.touch(3,7); // 9
        // prev has been pressed before saving the card details.
        app.prev();

        app.execute("A");
        //MyCards Screen should show the saved card values. 
        app.touch(3, 3);
        app.display() ;
        lines = app.screenContents().split("\n");  
        assertEquals("[159652744]", lines[4].trim());
    }
    
    /************* SETTING ORIENTATION BY CHAGING THE DEVICE ORIENTATION *********/
    
    /*
     * The screen should follow the orientation, even when the Device Orientation has been changed, while entering the values,
     * the screen should adhere to the orientation.
     */
    @Test
    public void MyCardTest3()
    {
        String[] lines ;
        assertEquals("", app.screen().trim());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        //set the device orientation to landscape
        Device.getInstance().setLandscapeOrientation(); 
        app.display();
        lines = app.screenContents().split("\n");  
        assertEquals("            My Cards", app.screen());
        
    }
    
    
    /*
     * Even if the device Orientation has been set to Landscape, while the app is active, 
     * the screen should change its orientation and should follow the behavior by not going
     * to My cards pay screen 
     */
    
    @Test
    public void MyCardTest4()
    {
        String[] lines ;
        assertEquals("", app.screen().trim());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        //set the device orientation to landscape
        app.display();
        lines = app.screenContents().split("\n");  
        assertEquals("My Cards", app.screen().trim());
        Device.getInstance().setLandscapeOrientation(); 
        app.touch(3, 3);
        app.display();
        lines = app.screenContents().split("\n");
        System.out.println("Lines is: "+ lines [3] + " " +lines[4]+ " "+ lines[5] );
        assertEquals("              $0.00",lines[5]);
        
        
    }
    
    @After
    public void tearDown()
    {
    }
}

