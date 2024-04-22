package zz;

import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Galka extends JComponent implements MouseListener, MouseMotionListener, ActionListener {
	Generator g1;
	ActionListener al;
	int xKlik;
	int yKlik;
	int delayKnob;

	public Galka(Generator gen) {
		addMouseListener(this);
		addMouseMotionListener(this);
		g1 = gen;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		int r = (Math.min(getWidth(), getHeight())) / 2;
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		g.fillOval(x - r, y - r, r * 2, r * 2);
		int rm = r / 5;
		double kat = Math.atan2((yKlik - y), (xKlik - x));
		int xm = x + (int) (Math.cos(kat) * 0.82 * r);
		int ym = y + (int) (Math.sin(kat) * 0.82 * r);
		g.setColor(Color.blue);
		g.fillOval(xm - rm, ym - rm, rm * 2, rm * 2);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

		int delay;
		xKlik = arg0.getX();
		yKlik = arg0.getY();
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		double angle = (180d / Math.PI) * Math.atan2((yKlik - y), (xKlik - x));

		if (angle >= 0) {
			delay = (int) (360 - angle) * 20;
			delayKnob = delay;
		} else {
			angle = angle + 360;
			delay = (int) (360 - angle) * 20;
			delayKnob = delay;
		}

		//System.out.println(delay);
		//System.out.println(angle);
		g1.setPulseDelay(delayKnob);
		repaint();
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Delay"));
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public int getDelay() {
		return delayKnob;
	}

	public void addActionListener(ActionListener pl) {
		al = AWTEventMulticaster.add(al, pl);

	}

	public void removeActionListener(ActionListener pl) {
		al = AWTEventMulticaster.remove(al, pl);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
