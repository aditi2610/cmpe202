/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import starbucks.Device.ORIENTATION_MODE;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
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


//
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
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    private int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) {
                return 0;
            }

        int lines = 0;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
                lines++;
        }

        return lines ;
    }

    /** 
     * Helper:  Pad lines for a Screen 
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    private String padLines(int num) {
        StringBuffer lines = new StringBuffer() ;
        for ( int i = 0; i<num; i++ ) {
            System.err.print(".") ;
            lines .append( "\n") ;
        }
        System.err.println("") ;
        return lines.toString() ;
    }
    
    /**
     * Helper:  Pad Spaces for a Line
     * @param  num Num Spaces to Pad
     * @return     Padded Line
     */
    private String padSpaces(int num) {
        StringBuffer spaces = new StringBuffer() ;
        for ( int i = 0; i<num; i++ )
            spaces .append( " " );           
        return spaces.toString() ;     
    }            

    /**
     * 
     * @param initial Sets default screen for a frame
     */
    public Frame(IScreen initial)
    {
        current = initial ;

        portraitStrategy = new IOrientationStrategy() 
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }         

                /**
             * Return String / Lines for Frame and Screen
             * @param  s [description]
             * @return   [description]
             */
            public String contents(IScreen s) 
            { 
                String out = "" ;
                out += "===============\n" ;
                int nameLen = s.name().length() ;
                if (nameLen < 14 ) {
                    int pad = (14 - nameLen) / 2 ;
                    out += padSpaces( pad ) ;
                }
                out += " " +s.name() + "\n" ;
                out += "===============\n" ;
                String screen = s.display();
                int cnt1 = countLines( screen ) ;
                int pad1 = (10 - cnt1) / 2;
                out += padLines( pad1 ) ;
                out += screen  ;
                int cnt2 = countLines( out ) ;
                int pad2 = 13 - cnt2 ;
                String padlines = padLines( pad2 ) ;
                out += padlines ;
                out +=  "===============\n" ;
                out +=  "[A][B][C][D][E]\n" ;
                dumpLines( out ) ;
                return out ;             
            }

            /** Select Command A */
            public void selectA() { menuA.invoke() ; }

            /** Select Command B */
            public void selectB() { menuB.invoke() ; }

            /** Select Command C */
            public void selectC() { menuC.invoke() ; }

            /** Select Command D */
            public void selectD() { menuD.invoke() ; }

            /** Select Command E */
            public void selectE() { menuE.invoke(); }

        } ;

        landscapeStrategy = new IOrientationStrategy() 
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }         

           /**
             * Display Contents of Frame + Screen 
             * @param  s Screen to Display
             * @return   Contents for Screen
             */
            public String contents(IScreen s) 
            { 
			
            	String out = "" ; 
            	out += "================================\n" ;
            	int nameLen = s.name().length() ;
                if (nameLen < 31  ) {
                    int pad = (31 - nameLen) / 2 ;
                    out += padSpaces( pad ) ;
                }
                out +=  " " +s.name() + "\n" ;
                out += "================================\n" ;
                String screen = s.display() ;
                int cnt1 = countLines( screen ) ;
                int pad1 = (6 - cnt1) / 2;
                out += padLines( pad1 ) ;
                out += screen  ;
                int cnt2 = countLines( out ) ;
                int pad2 = 9 - cnt2 ;
                String padlines = padLines( pad2 ) ;
                out += padlines ;
                out +=  "================================\n" ;
                dumpLines( out ) ;
                return out;
            }

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
       } ;     

       if(currentStrategy == null) {
    	  if( Device.getInstance().getDeviceOrientation() == ORIENTATION_MODE.LANDSCAPE)
    		  currentStrategy = landscapeStrategy;
    	  else
    		  currentStrategy = portraitStrategy;
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
    	
    	currentStrategy = Device.getInstance().getDeviceOrientation().equals(ORIENTATION_MODE.LANDSCAPE) ? landscapeStrategy : portraitStrategy;
        if ( current != null )
        {
        	if(!(current.getClass().equals(MyCards.class) || (current.getClass().equals(MyCardsPay.class)))) {
        		currentStrategy = portraitStrategy;
        	}
            return currentStrategy.contents( current ) ; 
        } 
        else 
        {
            return "" ;
        }
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
