package game.of.life;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import net.miginfocom.swing.MigLayout;

public class GameWindow {
	private static final int RECT_SIZE = 25;
	private static final int CANVAS_WIDTH = 250;
	private static final int CANVAS_HEIGHT = 250;
	private static final int STEP_DELAY = 1000;
	
	private JFrame frame;
	private CanvasPanel panelCanvas;
	private ParametersPanel panelParameters;
	private DisplayPanel panelDisplay;
    private LifeManager lifeManager;
	private Timer gameTimer;
	private int stepDelay = STEP_DELAY; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameWindow() {
		initializeUI();
		initializeGame();
	}

	private void initializeUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new MigLayout("", 
							"[:250:250, grow, left][::200px, grow, right]",
							"[130px, grow][20px, grow][50px, grow][::50px, grow, bottom]"));

		panelParameters = new ParametersPanel();
		frame.getContentPane().add(panelParameters, "cell 1 0, w 200!, growy, spany 1");		
		
		panelDisplay = new DisplayPanel();
		frame.getContentPane().add(panelDisplay, "cell 1 1, w 200!, growy, spany 2");	
		
		panelCanvas = new CanvasPanel(CANVAS_WIDTH, CANVAS_HEIGHT, RECT_SIZE);
		frame.getContentPane().add(panelCanvas, "cell 0 0, grow, spany 3");		

		JPanel panelButtons = new JPanel();
		frame.getContentPane().add(panelButtons, "cell 0 3, growy, spanx 2");

		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
		});
		panelButtons.add(startBtn);

		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopGame();
				resetGame();
			}
		});
		panelButtons.add(resetBtn);

		JButton pauseBtn = new JButton("Stop");
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopGame();
			}
		});
		panelButtons.add(pauseBtn);
		
        frame.setResizable(false);
		
		int rows = CANVAS_WIDTH / RECT_SIZE;
		int columns = CANVAS_HEIGHT / RECT_SIZE;
		lifeManager = new LifeManager(rows, columns, panelCanvas);
		
        new LifeMouseListener(lifeManager, panelCanvas);
	}
	
	private void initializeGame() {
		gameTimer = new Timer(stepDelay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean stale = lifeManager.lifeCycle();
				if (stale) stopGame();
			}
		});
		gameTimer.setRepeats(true);
	}
	
	private void startGame() {
		gameTimer.start();
	}
	
	private void stopGame() {
		panelDisplay.setStepCount(lifeManager.getSteps());
		panelDisplay.setPeriod(lifeManager.getPeriod());
		gameTimer.stop();
	}
	
	private void resetGame() {
		stepDelay = panelParameters.getDelay();
		initializeGame();
		
		int rectangleSize = panelParameters.getRectangleSize(CANVAS_WIDTH);
		int rows = CANVAS_WIDTH / rectangleSize;
		int columns = CANVAS_HEIGHT / rectangleSize;
		
		panelCanvas.setRectangleSize(rectangleSize);
		
		lifeManager.setPropability(panelParameters.getPropability());
		lifeManager.setSize(rows, columns);
		lifeManager.generateLife();
	}

}
