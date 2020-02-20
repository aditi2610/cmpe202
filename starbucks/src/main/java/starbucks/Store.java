/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Store Screen */
public class Store extends Screen
{

    public Store()
    {

    }
    
    public String name() {
        return "Find Store" ; 
    }
    
    public String display() {
    	String value = super.display();
    	for(int i =0; i<9; i++) {
    		value+= " ";
    	}
    	value += "X\n";
    	for(int i=0; i<3; i++) {
    		value += " ";
    	}
    	value +="X\n";
    	for(int i=0;i<7; i++) {
    		value +=" ";
    	}
    	value += "X\n";
    	for(int i=0;i<6; i++) {
    		value +=" ";
    	}
    	value += "X\n";
    	for(int i=0;i<2; i++) {
    		value +=" ";
    	}
    	value += "X\n";
    	for(int i=0;i<11; i++) {
    		value +=" ";
    	}
    	value += "X\n";
    	for(int i=0;i<2; i++) {
    		value +=" ";
    	}
    	value += "X"; 	
    	return value;
    }

}
