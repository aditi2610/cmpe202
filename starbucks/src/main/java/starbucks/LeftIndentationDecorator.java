package starbucks;

/** Left Indentation for Decorator**/
public class LeftIndentationDecorator extends IndentationDecorator {
	 
	/**
     * Return Display Component Contents
     * @return Display Component Contents
     */
	@Override
	public String display() {
		StringBuffer value= new StringBuffer("");
		System.err.println("LeftIndentationDecorator => Screen Width is:"+ this.width);
		
		if (this.screenContents == null || this.screenContents.length()==0) {
			return "";
		}

		value.append(this.screenContents);
		return value.toString() ;
	}


}
