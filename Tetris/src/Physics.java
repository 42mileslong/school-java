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
			Grid.grid[x][y -1].hassquare = true;
			Grid.grid[x][y -1].active = true;
			Grid.grid[x][y -1].tetrimoid = Grid.grid[x][y].tetrimoid;
			Grid.grid[x][y -1].type = Grid.grid[x][y].type;
			
			Grid.grid[x][y].hassquare = false;
			Grid.grid[x][y].active = false;
			Grid.grid[x][y].tetrimoid = 0;
			Grid.grid[x][y].type = 0;
		}
	}
}
