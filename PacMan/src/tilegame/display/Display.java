package tilegame.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private Dimension dimension;

	public Display(String t, int width, int height) {
		title = t;
		dimension = new Dimension(width, height);
		createDisplay();
	}

	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(dimension);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setBackground(Color.BLACK);
		addToFrame(canvas);
		canvas.setFocusable(false);

	}

	public void addToFrame(Component c) {
		frame.add(c);

		if (c instanceof Canvas)
			frame.pack(); // adjust the window to the canvas
	}

	public void closeFrame() {
		frame.setVisible(false);
		frame.dispose();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame() {
		return frame;
	}
}
