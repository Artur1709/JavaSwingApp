package zz;

import java.awt.event.*;

public interface PulseSource {
	final static byte BURST_MODE = 0;
	final static byte CONTINOUS_MODE = 1;

	void addActionListener(ActionListener pl);// upraszczamy (powinna być nowa klasa PulseListener)

	void removeActionListener(ActionListener pl);// upraszczamy (powinna być nowa klasa PulseListener)

	void trigger(); // rozpocznij generacje

	void setMode(byte mode);

	byte getMode();

	void halt(); // zatrzymaj generacje

	void setPulseDelay(int ms);

	int getPulseDelay();

	void setPulseCount(int burst);
}