package game.of.life;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LifeMouseListener implements MouseListener {
	private static final long serialVersionUID = 7842871693609144543L;

	private LifeManager lifeManager;
	private	CanvasPanel canvasPanel;
	
	public LifeMouseListener(LifeManager lifeManager, CanvasPanel canvasPanel) {
		this.lifeManager = lifeManager;
		this.canvasPanel = canvasPanel;
		canvasPanel.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
        //eventOutput("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);
    }
     
    public void mouseReleased(MouseEvent e) {
        //eventOutput("Mouse released (# of clicks: " + e.getClickCount() + ")", e);
    }
     
    public void mouseEntered(MouseEvent e) {
        //eventOutput("Mouse entered", e);
    }
     
    public void mouseExited(MouseEvent e) {
        //eventOutput("Mouse exited", e);
    }
     
    public void mouseClicked(MouseEvent e) {
        //eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
		int x = canvasPanel.coerceLocationToCellX(e.getX(), e.getY());
		int y = canvasPanel.coerceLocationToCellY(e.getX(), e.getY());
		lifeManager.complement(x, y);
    }
	
	void eventOutput(String eventDescription, MouseEvent e) {
		String NEWLINE = System.getProperty("line.separator");
        System.out.println(eventDescription + " detected on " + e.getX() + ", " + e.getY() + "." + NEWLINE);
    }
}
