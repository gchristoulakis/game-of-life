package game.of.life;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class LifeMap {
	public static final int NW = 0;
	public static final int N  = 1;
	public static final int NE = 2;
	public static final int W  = 3;
	public static final int E  = 4;
	public static final int SW = 5;
	public static final int S  = 6;
	public static final int SE = 7;
	
	private int[][] life;
	private int width, height;

	public LifeMap(int width, int height) {
		this.width = width;
		this.height = height;
		life = new int[height][width];
		// System.out.println("[constructor] i:" + width + " j:" + height + " t:" + life[1][1]);
	}
	
	public LifeMap copy() {
		LifeMap clone = new LifeMap(width, height);
		clone.setLife(life);
		return clone;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;		
	}
	
	public void setLife(int[][] newLife) {
		// System.out.println("[set] i:" + width + " j:" + height + " t:" + life);
		life = new int[newLife.length][];
		for (int i = 0; i < newLife.length; i++) {
			life[i] = new int[newLife[i].length];
			System.arraycopy(newLife[i], 0, life[i], 0, newLife[i].length);
		}
	}
	
	public int[][] getLife() {
		return life;
	}
	
	public void generateLife(double propability) {
		// System.out.println("[generate] i:" + width + " j:" + height + " t:" + life);
		Random random = new Random();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				life[x][y] = (random.nextDouble() < propability ? 1 : 0);
			}
		}		
	}
	
	public void kill(int x, int y) {
		life[x][y] = 0;
	}
	
	public void live(int x, int y) {
		life[x][y] = 1;
	}
	
	public boolean isAlive(int x, int y) {
		return life[x][y] == 1;
	}
	
	public Map<Integer, Integer> neighbours(int x, int y) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
		map.put(NW, (x - 1 >= 0 	&& y - 1 >= 0  		? life[x - 1][y - 1] : 0));
		map.put(N,  (x - 1 >= 0 					 	? life[x - 1][y] 	 : 0));
		map.put(NE, (x - 1 >= 0 	&& y + 1 < width	? life[x - 1][y + 1] : 0));
		map.put(W,  (				   y - 1 >= 0 		? life[x][y - 1] 	 : 0));
		map.put(E,  (				   y + 1 < width	? life[x][y + 1] 	 : 0));
		map.put(SW, (x + 1 < height && y - 1 >= 0 		? life[x + 1][y - 1] : 0));
		map.put(S,  (x + 1 < height				 		? life[x + 1][y] 	 : 0));
		map.put(SE, (x + 1 < height && y + 1 < width 	? life[x + 1][y + 1] : 0));
		
		return map;
	}
	
	public int neighbourhood(int x, int y) {
		int sum = 0;
		
		sum += (x - 1 >= 0 	   && y - 1 >= 0  		? life[x - 1][y - 1] : 0);
		sum += (x - 1 >= 0 					 		? life[x - 1][y] 	 : 0);
		sum += (x - 1 >= 0 	   && y + 1 < width		? life[x - 1][y + 1] : 0);
		sum += (			      y - 1 >= 0 		? life[x][y - 1] 	 : 0);
		sum += (			      y + 1 < width		? life[x][y + 1] 	 : 0);
		sum += (x + 1 < height && y - 1 >= 0 		? life[x + 1][y - 1] : 0);
		sum += (x + 1 < height				 		? life[x + 1][y] 	 : 0);
		sum += (x + 1 < height && y + 1 < width		? life[x + 1][y + 1] : 0);
		
		return sum;
	}
	
	public int neighbourhoodWrapped(int x, int y) {
		int sum = 0;
		
		sum += life[(height + x - 1) % height][(width + y - 1) % width];
		sum += life[(height + x - 1) % height][y];
		sum += life[(height + x - 1) % height][(y + 1) % width];
		sum += life[x][(width + y - 1) % width];
		sum += life[x][(y + 1) % width];
		sum += life[(x + 1) % height][(width + y - 1) % width];
		sum += life[(x + 1) % height][y];
		sum += life[(x + 1) % height][(y + 1) % width];
		
		return sum;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\r\n");
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				sb.append("[" + (life[x][y] == 1 ? "X" : " ") + "]");
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
}