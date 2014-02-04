//Physics class: makes blocks fall, clears lines, and checks whether there is "fusion"

//Documentation of the methods are in the method
public class Physics {
	
	public static void dropcall() {
		for (int x = 0; x < Main.gamewidth; x++) {
			for (int y = 0; y < Main.gameheight; y++) {
				drop(x, y);
			}
		}
	}
	
	public static void drop(int x, int y) {
		if (Grid.grid[x][y].hassquare && Grid.grid[x][y].active) {
			if (y-1 >= 0) {
				Square square = Grid.grid[x][y];
				Square belowSquare = Grid.grid[x][y -1];
				
				belowSquare.hassquare = true;
				belowSquare.active = true;
				belowSquare.tetrimoid = Grid.grid[x][y].tetrimoid;
				belowSquare.type = Grid.grid[x][y].type;
				
				square.hassquare = false;
				square.active = false;
				square.tetrimoid = 0;
				square.type = 0;
			}
		}
	}
}
