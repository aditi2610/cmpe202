/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import starbucks.Device.ORIENTATION_MODE;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
	/**
	 * Landscape Starategy class
	 * @author aditimangal
	 *
	 */
    private final class LandscapeStrategyClass implements IOrientationStrategy {
		/**
		 * Display Screen Contents
		 * @param s Reference to Screen
		 */
		public void display(IScreen s){System.out.println( contents(s) ) ;}

		/**
             * Display Contents of Frame + Screen 
             * @param  s Screen to Display
             * @return   Contents for Screen
             */
            public String contents(IScreen s) 
            { 
            	String out = "" ; 
            	out += "================================\n" ;
                out += s.name() + "\n" ;
                out += "================================\n" ;
                out+= s.display();
                out +=  "================================\n" ;
                dumpLines( out ) ;
                return out;            }

		/** Don't Respond in Landscaope Mode */
		public void selectA() {  }

		/** Don't Respond in Landscaope Mode */
		public void selectB() {  }

		/** Don't Respond in Landscaope Mode */
		public void selectC() {  }

		/** Don't Respond in Landscaope Mode */
		public void selectD() {  }

		/** Don't Respond in Landscaope Mode */
		public void selectE() {  }
	}
    
    /**
     * Portrait Starategy class
     * @author aditimangal
     *
     */
	private final class PortraitStarategy implements IOrientationStrategy {
		/**
		 * Display Screen Contents
		 * 
		 * @param s Reference to Screen
		 */
		public void display(IScreen s) {
			System.out.println(contents(s));
		}

		/**
		 * Return String / Lines for Frame and Screen
		 * 
		 * @param s [description]
		 * @return [description]
		 */
		public String contents(IScreen s) {
			String out = "";
			out += "===============\n";
			out += s.name() + "\n";
			out += "===============\n";
			out += s.display();
			out += "===============\n";
			out += "[A][B][C][D][E]\n";
			dumpLines(out);
			return out;
		}

		/** Select Command A */
		public void selectA() {
			menuA.invoke();
		}

		/** Select Command B */
		public void selectB() {
			menuB.invoke();
		}

		/** Select Command C */
		public void selectC() {
			menuC.invoke();
		}

		/** Select Command D */
		public void selectD() {
			menuD.invoke();
		}

		/** Select Command E */
		public void selectE() {
			menuE.invoke();
		}
	}

	private IScreen current ;
    private IMenuInvoker menuA = new MenuOption() ;  
    private IMenuInvoker menuB = new MenuOption() ;
    private IMenuInvoker menuC = new MenuOption() ;
    private IMenuInvoker menuD = new MenuOption() ;
    private IMenuInvoker menuE = new MenuOption() ;
    
    private IOrientationStrategy portraitStrategy ;
    private IOrientationStrategy landscapeStrategy ;
    private IOrientationStrategy currentStrategy ;
    
    private IMenuInvoker menuMyCard = new MenuOption();
    private IMenuInvoker menuA1 = new MenuOption() ;
    private IMenuInvoker menuCardOptions = new MenuOption();
    private IMenuInvoker menuCardMoreOptions = new MenuOption();
    private IMenuInvoker menuAddCard = new MenuOption();

    /**
     * Return Screen Name
     * @return Screen Name
     */
    public String screen() { return current.name() ; }

    /** Switch to Landscape Strategy */
    public void landscape() { 
    	currentStrategy = landscapeStrategy ; }

    /** Switch to Portrait Strategy */
    public void portrait()  { currentStrategy = portraitStrategy ; }  

    /** Nav to Previous Screen */
    public void previousScreen() {
        current.prev();
    }

    /** Nav to Next Screen */
    public void nextScreen() {
        current.next();
    }
   /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
          String[] lines = str.split("\r\n|\r|\n");
          for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
          }
    }

    /**
     * 
     * @param initial Sets default screen for a frame
     */
    public Frame(IScreen initial)
    {
		current = initial;
		portraitStrategy = new PortraitStarategy();

        landscapeStrategy = new LandscapeStrategyClass() ;     

       if(currentStrategy == null) {
    	   currentStrategy = Device.getInstance().getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE? landscapeStrategy : portraitStrategy;
       }
    }

    /**
     * Change the Current Screen
     * @param s Screen Object
     */
    public void setCurrentScreen( IScreen s )
    {
        current = s ;
    }

    /**
     * Configure Selections for Command Pattern 
     * @param slot A, B, ... E
     * @param c    Command Object
     */
    public void setMenuItem( String slot, IMenuCommand c )
    {
        if ( "A".equals(slot) ) { menuA.setCommand(c) ;  }
        if ( "B".equals(slot) ) { menuB.setCommand(c) ; }
        if ( "C".equals(slot) ) { menuC.setCommand(c) ; }
        if ( "D".equals(slot) ) { menuD.setCommand(c) ; } 
        if ( "E".equals(slot) ) { menuE.setCommand(c) ; }   
    }
    /**
     * Configure Screens which can be navigated from one to another screen
     * @param slot Mycard, A1, ... AddCard
     * @param c    Command Object
     */
    public void setInMenuScreen( String slot, IMenuCommand c )
    {
    	if("MyCard".equals(slot)) {menuMyCard.setCommand(c);}
        if ( "A1".equals(slot) ) { menuA1.setCommand(c) ;  }
        if("MyCardOptions".equals(slot)) {menuCardOptions.setCommand(c);}
        if("MyCardMoreOptions".equals(slot)) {menuCardMoreOptions.setCommand(c);}
        if("AddCard".equals(slot)) {menuAddCard.setCommand(c);}
    }

    /** 
     * Send Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y)
    {
        if ( current != null )
            current.touch(x,y) ;
    }

    /**
     * Get Contents of the Frame + Screen 
     * @return Frame + Screen Contents
     */
    public String contents() 
    { 
        if ( current != null )
        {
        	if(!(current.getClass().equals(MyCards.class) || (current.getClass().equals(MyCardsPay.class)))) {
        		currentStrategy = portraitStrategy;}
            return currentStrategy.contents( current ) ; 
        } 
        else 
        {return "" ;}
    }

    /** Display Contents of Frame + Screen */
    public void display()
    {
        if ( current != null )
        {
            currentStrategy.display( current ) ;
        }
    }
 
    /**
     *  Execute a Command 
     * @param c Command
     */
    public void cmd( String c ) 
    {
        if ( "A".equals(c) ) { selectA() ; }
        if ( "B".equals(c) ) { selectB() ; }
        if ( "C".equals(c) ) { selectC() ; }
        if ( "D".equals(c) ) { selectD() ; }        
        if ( "E".equals(c) ) { selectE() ; }        
    }

    /** Select Command A */
    public void selectA() { currentStrategy.selectA() ;  }

    /** Select Command B */
    public void selectB() { currentStrategy.selectB() ;  }

    /** Select Command C */
    public void selectC() { currentStrategy.selectC() ;  }

    /** Select Command D */
    public void selectD() { currentStrategy.selectD() ;  }

    /** Select Command E */
    public void selectE() { currentStrategy.selectE() ; }
    
    /** Invoke MyCards Pay screen */
    public void selectA1() { menuA1.invoke();}
    
    /** Invoke My card Menu options  */
    public void selectCardOptions() { menuCardOptions.invoke();}
    
    /** Invoke My card more Menu options  */
    public void selectCardMoreOptions() { menuCardMoreOptions.invoke();}
    
    /** Invoke Add Card */
    public void selectAddCard() { menuAddCard.invoke();}
    
    /** Invoke My Card */
    public void selectMyCard() { menuMyCard.invoke();}

}
