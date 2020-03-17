package starbucks;

/** Centered Indentation for Decorator**/
public class CenteredIndentationDecorator extends IndentationDecorator {
	 
	/**
     * Return Display Component Contents
     * @return Display Component Contents
     */
	@Override
	public String display() {
		
		StringBuffer value= new StringBuffer();

		System.err.println("CenteredIndentationDecorator => Screen Width is:"+ this.width);
		
		if (this.screenContents == null || this.screenContents.isEmpty()) {
			return "";
		}
		String lines[] = this.screenContents.split("\\r?\\n");
		for(String line: lines) {
			if (line == "\n")
				value.append("\n");
			else {
				int w = line.length();
				int space = (this.width-w)/2;
				value.append(this.padSpaces(space+1));
				value.append(line);
				value.append("\n");			
			}
		}
	
        return value.toString();
	
	}
}
