package zz;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Systick implements Cortex_M0_SysTick_Interface {
	
	ActionListener al;
	private int CVR; 
	private int RVR; 
	private int CSR; 

	private boolean csrEnable;     
	private boolean csrTickInt;    
	private boolean csrClkSrc;     
	private boolean csrCountFlag;  

	public void tickInternal() {
		if (isEnableFlag() && !csrClkSrc) {
			CVR--;
			if (CVR == 0) {
				CSR = CSR | (1 << 15);
				csrCountFlag = true;
			}
			if (CVR < 0 && RVR != 0) {
				CVR = RVR;
			} else if (CVR < 0) {
				CVR = 0;
				CSR = CSR & ~(1 << 0);
				csrEnable = false;
			}
		}
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Tick internal"));
	}

	public void tickExternal() {
		if (isEnableFlag() && csrClkSrc) {
			CVR--;
			if (CVR == 0) {
				CSR = CSR | (1 << 15);
				csrCountFlag = true;

			}
			if (CVR == -1) {
				CVR = RVR;
			}
		}
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Tick External"));
	}

	public void setRVR(int value) {

		RVR = value & ((1 << 24) - 1);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "RVR set"));

	}

	public void setCVR(int value) {
		CSR = CSR & ~(1 << 15);
		csrCountFlag = false;
		CVR = 0;
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CVR set"));
	}

	public void setCSR(int value) {
		value &= (1 << 16) - 1;
		csrEnable = ((value & 1) != 0);
		csrTickInt = ((value & 1 << 1) != 0);
		csrClkSrc = ((value & 1 << 2) != 0);
		csrCountFlag = ((value & 1 << 15) != 0);
		if (csrEnable) {
			CSR = CSR | (1 << 0);
		} else
			CSR = CSR & ~(1 << 0);

		if (csrTickInt) {
			CSR = CSR | (1 << 1);
		} else
			CSR = CSR & ~(1 << 1);

		if (csrClkSrc) {
			CSR = CSR | (1 << 2);
		} else
			CSR = CSR & ~(1 << 2);

		if (csrCountFlag) {
			CSR = CSR | (1 << 15);
		} else
			CSR = CSR & ~(1 << 15);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "CSR set"));

	}

	public void reset() {
		CSR = 0;
		csrEnable = false;
		csrTickInt = false;
		csrClkSrc = false;
		csrCountFlag = false;
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Reset"));
	}

	public void setEnable() {
		csrEnable = true;
		CSR = CSR | (1 << 0);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Enable set"));
	}

	public void setDisable() {
		csrEnable = false;
		CSR = CSR & ~(1 << 0);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Disable set"));
	}

	public void setSourceExternal() {
		csrClkSrc = false;
		CSR = CSR & ~(1 << 2);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Source External"));
	}

	public void setSourceInternal() {
		csrClkSrc = true;
		CSR = CSR | (1 << 2);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Source Internal"));
	}

	public void setInterruptEnable() {
		csrTickInt = true;
		CSR = CSR | (1 << 1);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Interrupt enable"));
	}

	public void setInterruptDisable() {
		csrTickInt = false;
		CSR = CSR & ~(1 << 1);
		if (al != null)
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "interrupt disable"));
	}

	public int getCVR() {
		return CVR;
	}

	public int getRVR() {
		return RVR;
	}

	public int getCSR() {
		csrCountFlag = false;
		CSR = CSR & ~(1 << 15);
		return CSR;
	}

	public boolean getEnabled() {
		csrCountFlag = false;
		CSR = CSR & ~(1 << 15);
		return csrEnable;
	}

	public boolean getInterrupt() {
		csrCountFlag = false;
		CSR = CSR & ~(1 << 15);
		return csrTickInt;
	}

	public boolean getSource() {
		csrCountFlag = false;
		CSR = CSR & ~(1 << 15);
		return csrClkSrc;
	}

	public boolean getCountFlag() {
		csrCountFlag = false;
		CSR = CSR & ~(1 << 15);
		return csrCountFlag;
	}

	public boolean isCountFlag() {
		return csrCountFlag;
	}

	public boolean isEnableFlag() {
		return csrEnable;
	}

	public boolean isInterruptFlag() {
		return csrTickInt;
	}

	public boolean isSourceFlag() {
		return csrClkSrc;
	}

	public void addActionListener(ActionListener l) {
		al = AWTEventMulticaster.add(al, l);
	}

	public void removActionListener(ActionListener l) {
		al = AWTEventMulticaster.remove(al, l);
	}

}
