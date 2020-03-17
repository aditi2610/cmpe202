package starbucks;

public class LeftIndentationDecorator extends IndentationDecorator {
	
	@Override
	public String display() {
		StringBuffer value= new StringBuffer("");
		System.err.println("LeftIndentationDecorator => Screen Width is:"+ this.width);
		
		if (this.screenContents == null || this.screenContents.length()==0) {
			return "";
		}
//		String lines[] = this.screenContents.split("\\r?\\n");
//		for(String line: lines) {
//			if (line == "\n")
//				value.append("\n");
//			else {
//				int w = line.length();
//				int space =(this.width-w)/2;
//				value.append(this.padSpaces(space+1));
//				value.append(line);
//				value.append("\n");
//			}
//		}
		value.append(this.screenContents);
		return value.toString() ;
	}


}
