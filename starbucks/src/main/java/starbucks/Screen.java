/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/**
 * Base Class for Screens.
 * 
 * Provides Common Functionality
 * For Setting Up the Composite and 
 * Chain of Responsibility Patterns.
 * 
 */
public class Screen implements IScreen, IDisplayComponent
{	
	IndentationDecorator decorator;
	/** Display Components */
	private ArrayList<IDisplayComponent> components = new ArrayList<IDisplayComponent>() ;

	/** Front of Event Chain */
	private ITouchEventHandler chain ;

	/** Constructor */
	public Screen()
	{
	
	}

	/**
	 * Send Touch Events to the Chain
	 * @param x Touch X Coord.
	 * @param y Touch Y Coord.
	 */
	public void touch(int x, int y) {
		if(chain != null)
			chain.touch(x, y) ;
	}
	/**
	 * Add Display Component to Screen
	 * @param c Display Component
	 */
	public void addSubComponent( IDisplayComponent c )
	{
		components.add( c ) ;
		if (components.size() == 1 )
		{
			chain = (ITouchEventHandler) c ;
		}
		else
		{
			ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2) ;
			prev.setNext( (ITouchEventHandler) c ) ;
		}
	}

	/**
	 * Get Display Contents
	 * @return Display Contents
	 */
	public String display() { 
		StringBuffer value = new StringBuffer("") ;
		for (IDisplayComponent c : components )
		{
			System.err.println( "Screen: " + c.getClass().getName() ) ;
			value.append(c.display());
			value .append("\n");
		}
		return value.toString() ; 
	}

	/**
	 * Get Class Name of Current Screen
	 * @return Class Name of Current Screen
	 */
	public String name() {
		return (this.getClass().getName()).split("\\.")[1] ; 
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prev() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNext(IScreen s, String n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrev(IScreen s, String n) {
		// TODO Auto-generated method stub
		
	}
	


}
