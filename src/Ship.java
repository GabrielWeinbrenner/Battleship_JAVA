// Uses this class to declare all the ships
public class Ship {
	boolean orientation; // True means horizontal, False means vertical
	int size;
	String alpha = "ABCDEFGHIJ";
	String row;
	int column;
	// Sets the ships 
	public Ship(boolean isHori, int s, String r, int c) {
		this.orientation = isHori; 
		this.size = s;
		this.row = r;
		this.column = c;
	}
	
	public int getRow() {
		return alpha.indexOf(this.row);
	}
	public int getColumn() {
		return this.column ;
	}
	public boolean getOrientation() {
		return orientation;
	}
	public int getSize() {
		return this.size;
	}
}
