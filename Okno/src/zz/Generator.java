package zz;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Generator extends Thread implements PulseSource, Runnable {
	JTextArea poleTekstowe;
	ActionListener al;
	Systick s;
	boolean enable;
	private boolean alive;
	private int delay;
	private byte mode;
	private int pulse;
	final static byte BURST_MODE = 0;
	final static byte CONTINOUS_MODE = 1;

	public Generator(Systick s1) {
		s = s1;
		enable = false;
		alive = true;
	}

	public void setTextArea(JTextArea pt) {
		poleTekstowe = pt;
	}

	public void run() {
		while (alive) {
			if (isEnable() && (mode == CONTINOUS_MODE)) {
				try {
					Thread.sleep(delay);
					s.tickExternal();
				} catch (Exception e) {

					e.printStackTrace();
				}
				if (al != null) {
					al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Tick"));
				}

			} else if (isEnable() && (mode == BURST_MODE) && pulse != 0) {
				try {
					if (pulse == 0) {
						enable = false;
					}
					pulse--;
					s.tickExternal();
					Thread.sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (al != null) {
					al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Tick"));
				}
			} else {
				try {
					Thread.sleep(1);
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void addActionListener(ActionListener pl) {
		al = AWTEventMulticaster.add(al, pl);

	}

	public void removeActionListener(ActionListener pl) {

		al = AWTEventMulticaster.remove(al, pl);
	}

	public boolean isEnable() {
		return enable;
	}

	public void setDisable() {
		enable = false;
	}

	public void killThread() {
		alive = false;
	}

	public void trigger() {
		enable = true;

	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public byte getMode() {

		return mode;
	}

	public void halt() {
		enable = false;
	}

	public void setPulseDelay(int ms) {
		delay = ms;

	}

	public int getPulseDelay() {
		return delay;
	}

	public void setPulseCount(int burst) {

		pulse = burst;
	}

	public void reset() {
		enable = false;
		alive = true;
		pulse = 0;
		delay = 0;
	}

}