package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private URL base;
	private Image image;
	private Image character;
	private Graphics second;
	private Robot robot;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/Laser_Robot.png");
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {
			robot.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(character, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Move down");
			break;

		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			System.out.println("Stop moving down");
			break;

		case KeyEvent.VK_DOWN:
			robot.stop();
			break;

		case KeyEvent.VK_LEFT:
			robot.stop();
			break;

		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
