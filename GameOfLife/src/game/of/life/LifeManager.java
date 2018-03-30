package game.of.life;

import java.util.List;
import java.util.ArrayList;

public class LifeManager {
	private static final int UNDERPOPULATION = 2;
	private static final int OVERPOPULATION = 3;
	private static final int REPRODUCTION = 3;
	
	/*
	1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
	2. Any live cell with two or three live neighbours lives on to the next generation.
	3. Any live cell with more than three live neighbours dies, as if by overpopulation.
	4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	*/
	private double propability = 0.1;
	private LifeMap life;
	private CanvasPanel panel;
	private List<String> lifeHistory;
	private int period;
	private boolean wrapped = false;
	
	public LifeManager(int width, int height, CanvasPanel panel) {
		this.panel = panel;
		life = new LifeMap(width, height);
		lifeHistory = new ArrayList<String>();		
		repaint();
	}
	
	public void setSize(int width, int height) {
		life = new LifeMap(width, height);
		lifeHistory = new ArrayList<String>();
		repaint();
	}
	
	public void setWrapped(boolean wrapped) {
		this.wrapped = wrapped;
	}
	
	public void setPropability(double propability) {
		this.propability = propability;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public int getSteps() {
		return lifeHistory.size();
	}
	
	public void generateLife() {
		life.generateLife(propability);
		lifeHistory = new ArrayList<String>();
		lifeHistory.add(life.toString());
		repaint();
	}
	
	private String nextLifeCycle() {	
		int width = life.getWidth();
		int height = life.getHeight();
		LifeMap newLife = life.copy();
		// System.out.println("[nextLifeCycle] copy :" + newLife);
		
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				int neighbourhood = (wrapped ? life.neighbourhoodWrapped(x, y) : life.neighbourhood(x, y));
				// System.out.print("[" + x + ", " + y + "] neighbourhood(" + neighbourhood + "): " + life.neighbours(x, y));
				if ((life.isAlive(x, y) && neighbourhood < UNDERPOPULATION)
					|| (life.isAlive(x, y) && neighbourhood > OVERPOPULATION)) {
					// System.out.print(" killed");
					newLife.kill(x, y);
				}
				if (!life.isAlive(x, y) && neighbourhood == REPRODUCTION) {
					// System.out.print(" alive");
					newLife.live(x, y);
				}
				// System.out.println(".");
			}
		}
		// System.out.println("[nextLifeCycle] life:" + life);
		// System.out.println("[nextLifeCycle] new :" + newLife);

		life = newLife;
		
		return life.toString();
	}
	
	public void complement(int x, int y) {
		if (life.isAlive(x, y)) {
			life.kill(x, y);
		} else {
			life.live(x, y);			
		}
		repaint();
	}
	
	public boolean lifeCycle() {
		String newLife = nextLifeCycle();
		
		boolean stale = lifeHistory.contains(newLife);
		if (stale) {
			period = lifeHistory.size() - lifeHistory.indexOf(newLife);
		}
		
		lifeHistory.add(newLife);		
		repaint();
		
		return stale;
	}
	
	private void repaint() {
		panel.setLife(life.getLife());
		panel.repaint();		
	}
	
}