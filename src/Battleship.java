import java.util.Scanner;
public class Battleship {
	Scanner sc = new Scanner(System.in);
	// Declares ships
	Ship Carrier, Battleship, Cruiser, Destroyer, Submarine;
	String[][] grid = new String[10][10]; 
	// Declares letters to get the indices
	String alpha = "ABCDEFGHIJ";
	// Used to display the columns
	String[] n = {"1","2","3","4","5","6","7","8","9","10"};
	// Declares the current grid 
	public Battleship() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				grid[i][j] = "-";
			}
		}
	}
	// Displays the given grid based on the parameters
	public void displayGrid(String[][] dgrid) {
		String s = "  ";
		for(int i = 0; i < n.length; i++) s+=n[i] + " ";
		s+="\n";
		for(int i = 0; i < dgrid.length; i++) {
			s+=alpha.substring(i,i+1) + " ";
			for(int j = 0; j < dgrid.length; j++) {
				s+=dgrid[i][j] + " ";
				
			}
			s+="\n";
		}
		System.out.println(s);
	}
	// Sets the current battle
	public void setBattle() {
		displayGrid(grid);
		String row;
		int col;
		boolean b = false;
		// Loops from 5 - 1 to show the length asking user for the inputs
		for(int i = 5; i > 1; i--) {
			do {
				sc.reset();
				System.out.println("Now you need to place a ship of length " + i);
				boolean r = false;
				do {
					System.out.println("Which row? (A-J) ");
					row = (sc.next()).toUpperCase();
					for(int j = 0; j < alpha.length(); j++) if(row.equals(alpha.substring(j,j+1))) r = true;
					if(r == false) { System.out.println("Enter valid row (A-J)"); }
				}while(!(r));
				boolean h = false;
				do {
					System.out.println("Which column? (1-10)");
					col = sc.nextInt() - 1;
					if(col >= 0 && col <= 9) h = true;
					if(h == false) System.out.println("Enter valid column (1-10)");
				}while(!(h));
				boolean s = false;
				do {
					System.out.println("Horizontal or Vertical? (H or V) ");
					String whichone = sc.next();
					if(whichone.equalsIgnoreCase("Horizontal") || whichone.equalsIgnoreCase("H")) { b = true; s = true;}
					else if(whichone.equalsIgnoreCase("Vertical") || whichone.equalsIgnoreCase("V")) { b = false; s = true;}
					if(s == false) { System.out.println("Enter valid orientation (H or V)");}
				}while(!(s));	
			}while(!(checkInput(b, i, row, col)));
			if(i == 5) { Carrier = new Ship(b,i,row,col); setShips(Carrier);} 
			else if(i == 4) {  Battleship = new Ship(b,i,row,col);setShips(Battleship); }
			else if(i == 3) {  Cruiser = new Ship(b,i,row,col);setShips(Cruiser);}
			else if(i == 2) {  Destroyer = new Ship(b,i,row,col); setShips(Destroyer);}
		}
		// Repeats again for size 3 ship
		do {
			sc.reset();
			System.out.println("Now you need to place a ship of length 3");
			boolean r = false;
			do {
				System.out.println("Which row? (A-J) ");
				row = (sc.next()).toUpperCase();
				for(int j = 0; j < alpha.length(); j++) if(row.equals(alpha.substring(j,j+1))) r = true;
				if(r == false) { System.out.println("Enter valid row (A-J)"); }
			}while(!(r));
			boolean h = false;
			do {
				System.out.println("Which column? (1-10)");
				col = sc.nextInt() - 1;
				if(col >= 0 && col <= 9) h = true;
				if(h == false) System.out.println("Enter valid column (1-10)");
			}while(!(h));
			boolean s = false;
			do {
				System.out.println("Horizontal or Vertical? (H or V) ");
				String whichone = sc.next();
				if(whichone.equalsIgnoreCase("Horizontal") || whichone.equalsIgnoreCase("H")) { b = true; s = true;}
				else if(whichone.equalsIgnoreCase("Vertical") || whichone.equalsIgnoreCase("V")) { b = false; s = true;}
				if(s == false) { System.out.println("Enter valid orientation (H or V)");}
			}while(!(s));	
		}while(!(checkInput(b, 3, row, col)));
		Submarine = new Ship(b,3,row,col); setShips(Submarine);
	}
	// Sets the ships on the current grid to string "X"
	public void setShips(Ship s) {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid.length; j++) {
				if(s.getRow() == i && s.getColumn() == j) {
					grid[i][j] = "X";
					if(s.getOrientation()) { for(int g = 1; g < s.getSize(); g++) { grid[i][j+g] = "X"; } }
					else { for(int g = 1; g < s.getSize(); g++) { grid[i+g][j] = "X"; } }
				}
					
			}
		}
	}
	// Checks if the ship is in the boundaries of the grid and does not conflict with other ships
	public boolean checkInput(boolean hori,int size, String r, int c) {
		System.out.println(r + " (" + alpha.indexOf(r.toUpperCase()) + ") " + c + " " + grid[alpha.indexOf(r.toUpperCase())][c]);
		if(grid[alpha.indexOf(r)][c].equals("X")) return false;
		if(hori) {
			if(c + size > 10 ) return false;
			for(int g = 1; g < size; g++) { if(grid[alpha.indexOf(r.toUpperCase())][c+g].equals("X")) return false; } 
			return true;
		}else {
			if(alpha.indexOf(r) + size > 10) return false;
			for(int g = 1; g < size; g++) { if(grid[alpha.indexOf(r.toUpperCase())+g][c].equals("X")) return false; } 
			return true;
		}
	}
	// Creates empty grid for the playing grid
	public String[][] createEmptyGrid() {
		String[][] dgrid = new String[10][10];
		for(int i = 0; i < dgrid.length; i++) {
			for(int j = 0; j < dgrid.length; j++) {
				dgrid[i][j] = "-";
			}
		}
		return dgrid;
	}
	// The play function for the user
	public void playGame() {
		String[][] custom = createEmptyGrid();
		int col, amount = 0, hits = 0;
		String row;		
		// Asks for the users inputs until they reach a total of 17 hits
		do {
			sc.reset();
			displayGrid(custom);
			boolean r = false;
			do {
				System.out.println("Which row? (A-J) ");
				row = (sc.next()).toUpperCase();
				for(int j = 0; j < alpha.length(); j++) if(row.equals(alpha.substring(j,j+1))) r = true;
				if(r == false) { System.out.println("Enter valid row (A-J)"); }
			}while(!(r));
			boolean h = false;
			do {
				System.out.println("Which column? (1-10)");
				col = sc.nextInt() - 1;
				if(col >= 0 && col <= 9) h = true;
				if(h == false) System.out.println("Enter valid column (1-10)");
			}while(!(h));
			outerloop:
			for(int i = 0 ; i< grid.length; i++) {
				for(int j = 0; j < grid.length; j++) {
					if(grid[i][j].equals("X") && (alpha.indexOf(row) == i && col == j)) {
						custom[alpha.indexOf(row)][col] = "X";
						hits++;
						System.out.println("You got a Hit!!");
						break outerloop;
					}else { custom[alpha.indexOf(row.toUpperCase())][col] = "O"; }
				}
			}
			amount++;
		}while(hits != 17);
		System.out.println("YOU HAVE WON 17/17!!\nIt took " + amount + " tries");
	}
	
}
