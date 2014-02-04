//Documentation of the methods are in the method

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class Display extends JComponent {
	BufferedImage background;
	
	//Color variables
	//colors picked from http://www.w3schools.com/tags/ref_colorpicker.asp
	//colors are named [tetrimo type] + ["" or "D"]
	//dark colors are 3 levels darker
	private static Color I = new Color(0x00cfcf);
	private static Color ID = new Color(0x009191);
	private static Color J = new Color(0x297acc);
	private static Color JD = new Color(0x1D558F);
	private static Color L = new Color(0xff8000);
	private static Color LD = new Color(0xb25a00);
	private static Color O = new Color(0xfbf579);
	private static Color OD = new Color(0xb0ac55);
	private static Color S = new Color(0x80db70);
	private static Color SD = new Color(0x4e994e);
	private static Color T = new Color(0xad44d6);
	private static Color TD = new Color(0x792496);
	private static Color Z = new Color(0xf94e4e);
	private static Color ZD = new Color(0xae3737);
	
	//array is I, J, L, O, S, T, Z
	private static Color[][] colors = {{I, J, L, O, S, T, Z}, {ID, JD, LD, OD, SD, TD, ZD}};

	//Dimension variables
	//offsets for image (distances from edge to start of game)
	public static int xoffset = 22;
	public static int yoffset = 16;
	//dimensions of the square pieces
	public static int squaredim = 16;
	
	public Display() {
		//load image
		try {
			background = ImageIO.read(new File("Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		setSize(Main.imgwidth, Main.imgheight);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		//draws the image
		g.drawImage(background, 0, 0, null);
		//paints the tetrimos
		paintGame(g);
	}
	
	private static void paintGame(Graphics g) {
		//runs through the grid and draws them if there is a square there
		for (int w = 0; w < Main.gamewidth; w++) {
			for (int h = 0; h < Main.gameheight - 2; h++) {
				if (Grid.grid[w][h].hassquare) paintTetrimo(g, w, h);
			}
		}
	}
	
	private static void paintTetrimo(Graphics g, int x, int y) {
		//sets the color to the dark type color
		g.setColor(colors[0][Grid.grid[x][y].type]);
		//draws from the top-left to the bottom-right
		g.fillRect(xoffset + x*16, findY(y) - 16, 16, 16);
		
		drawEdges(g, x, y);
	}
	
	private static void drawEdges(Graphics g, int x, int y) {
		//sets the color to the light type color
		g.setColor(colors[1][Grid.grid[x][y].type]);
		//draws the left line if there is no neighbor
		if (!Grid.grid[x][y].leftn) drawline(g, x, y, "left");
		//draws the right line if there is no neighbor
		if (!Grid.grid[x][y].rightn) drawline(g, x, y, "right");
		//draws the top line if there is no neighbor
		if (!Grid.grid[x][y].topn) drawline(g, x, y, "top");
		//draws the bottom line if there is no neighbor
		if (!Grid.grid[x][y].bottomn) drawline(g, x, y, "bottom");
	}
		
	public static int findY(int y) {
		//finds the y value of the coordinate
		//the reason this is necessary is that java draws the Y going down, but I have my grid-Y goes up - this translates it
		return (Main.imgheight - yoffset - y*squaredim);
	}
	
	private static void drawline(Graphics g, int x, int y, String side) {
		if (side.equals("left")) g.drawLine(xoffset + x*squaredim, findY(y+1), xoffset + x*squaredim, findY(y) -1);
		if (side.equals("right")) g.drawLine(xoffset + (x+1)*squaredim -1, findY(y) -1, xoffset + (x+1)*squaredim -1, findY(y+1));
		if (side.equals("top")) g.drawLine(xoffset + (x+1)*16 -1, findY(y+1), xoffset + x*16, findY(y+1));
		if (side.equals("bottom")) g.drawLine(xoffset + x*16, findY(y) - 1, xoffset + (x+1)*16 -1, findY(y) -1);
	}
}