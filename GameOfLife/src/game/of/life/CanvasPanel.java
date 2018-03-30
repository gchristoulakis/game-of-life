package game.of.life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = 7842871693609144549L;

	private int width, height, rectangleSize;
	private int[][] life;
	
	public CanvasPanel(int width, int height, int rectangleSize) {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.width = width;
		this.height = height;
		this.rectangleSize = rectangleSize;
	}
	
	public void setLife(int[][] life) {
		this.life = life;
	}
	
	public void setRectangleSize(int rectangleSize) {
		this.rectangleSize = rectangleSize;
	}
	
	private void printGrid(Graphics g) {
		// width x height hex grid
		g.setColor(Color.GRAY);
		int gridSize = width / rectangleSize;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				g.drawRect(i * rectangleSize, j * rectangleSize, rectangleSize, rectangleSize);
			}				
		}		
	}

	private void printLife(Graphics g) {		
		g.setColor(Color.BLACK);
		for (int x = 0; x < life.length; x++) {
			for (int y = 0; y < life[x].length; y++) {
				if (life[x][y] > 0) {
					g.fillRect(x * rectangleSize, y * rectangleSize, rectangleSize, rectangleSize);
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		printGrid(g);
		printLife(g);
	}
	
	public int coerceLocationToCellX(int x, int y) {
		return x / rectangleSize;
	}
	
	public int coerceLocationToCellY(int x, int y) {
		return y / rectangleSize;		
	}
}
