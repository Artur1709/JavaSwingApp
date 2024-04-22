package zz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		Systick cortex = new Systick();
		Generator g = new Generator(cortex);
		Galka knob = new Galka(g);
		knob.setBounds(318, 309, 140, 126);

		int min = 0;
		setTitle("Systick");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, -14, 760, 507);
		panel.setBackground(Color.GRAY);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(87, 64, 109, 50);
		textField.setEditable(false);
		panel.add(textField);

		textField.setColumns(10);

		panel.add(knob);

		JRadioButton RadioButton = new JRadioButton("Source Ext");
		RadioButton.setBackground(Color.LIGHT_GRAY);
		RadioButton.setBounds(227, 69, 140, 41);
		panel.add(RadioButton);
		RadioButton.addActionListener(e -> {
			if (RadioButton.isSelected()) {
				cortex.setSourceExternal();
			} else {
				cortex.setSourceInternal();

			}

		});

		JRadioButton RadioButton_2 = new JRadioButton("Enable");
		RadioButton_2.setBackground(Color.LIGHT_GRAY);
		RadioButton_2.setBounds(227, 116, 140, 41);
		panel.add(RadioButton_2);
		RadioButton_2.addActionListener(e -> {
			if (RadioButton_2.isSelected()) {
				cortex.setEnable();
			} else {
				cortex.setDisable();
			}

		});

		JRadioButton RadioButton_1 = new JRadioButton("Interrupt ( Enable )");
		RadioButton_1.setBackground(Color.LIGHT_GRAY);
		RadioButton_1.setBounds(227, 160, 140, 41);
		panel.add(RadioButton_1);
		RadioButton_1.addActionListener(e -> {
			if (RadioButton_1.isSelected()) {
				cortex.setInterruptEnable();

			} else {
				cortex.setInterruptDisable();

			}

		});
		SpinnerModel model = new SpinnerNumberModel(0, min, 100000, 1);
		SpinnerModel modelMode = new SpinnerNumberModel(0, min, 1, 1);

		JSpinner spinner = new JSpinner(model);
		spinner.setBounds(47, 267, 82, 36);
		panel.add(spinner);

		JButton btnNewButton = new JButton("set RVR");
		btnNewButton.setBounds(129, 267, 98, 36);
		panel.add(btnNewButton);
		SpinnerModel model1 = new SpinnerNumberModel(0, min, 100000, 1);

		JSpinner spinner_1 = new JSpinner(model1);
		spinner_1.setBounds(47, 315, 82, 29);
		panel.add(spinner_1);
		btnNewButton.addActionListener(e -> {
			cortex.setRVR((Integer) (spinner.getValue()));

		});

		JButton btnNewButton_1 = new JButton("set CVR");
		btnNewButton_1.setBounds(129, 314, 98, 30);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(e -> {
			cortex.setCVR((Integer) (spinner_1.getValue()));

		});

		JButton btnNewButton_2 = new JButton("set CSR");
		btnNewButton_2.setBounds(129, 355, 98, 29);
		panel.add(btnNewButton_2);
		SpinnerModel model2 = new SpinnerNumberModel(0, min, 7, 1);
		JSpinner spinner_2 = new JSpinner(model2);
		spinner_2.setBounds(47, 355, 82, 29);
		panel.add(spinner_2);
		btnNewButton_2.addActionListener(e -> {
			cortex.setCSR((Integer) (spinner_2.getValue()));

		});

		JLabel lblNewLabel = new JLabel("CSR");
		lblNewLabel.setBounds(47, 82, 46, 14);
		panel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(87, 125, 109, 50);
		textField_1.setEditable(false);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CVR");
		lblNewLabel_1.setBounds(47, 143, 46, 14);
		panel.add(lblNewLabel_1);

		textField_2 = new JTextField();
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(87, 198, 109, 50);
		textField_2.setEditable(false);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblRvr = new JLabel("RVR");
		lblRvr.setBounds(47, 216, 46, 14);
		panel.add(lblRvr);

		JButton btnNewButton_4 = new JButton("Tick Int");
		btnNewButton_4.addActionListener(e -> {
			cortex.tickInternal();

		});
		btnNewButton_4.setBounds(219, 225, 89, 23);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Tick Ext");
		btnNewButton_5.addActionListener(e -> {
			cortex.tickExternal();

		});
		btnNewButton_5.setBounds(318, 225, 89, 23);
		panel.add(btnNewButton_5);

		JButton btnNewButton_3 = new JButton("Reset");

		btnNewButton_3.setBounds(427, 73, 89, 23);
		panel.add(btnNewButton_3);

		JButton killButton = new JButton("Kill Thread");
		killButton.addActionListener(e -> {
			g.killThread();
		});
		killButton.setBounds(571, 107, 89, 23);
		panel.add(killButton);

		JButton genStartButton = new JButton("Start Generator");
		genStartButton.addActionListener(e -> {
			g.start();
			if (genStartButton.isEnabled()) {
				genStartButton.setVisible(false);
			}

		});
		genStartButton.setBounds(571, 78, 115, 23);
		panel.add(genStartButton);

		JLabel lblNewLabel_2 = new JLabel("MANUAL");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(269, 29, 64, 41);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("GENERATOR");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(571, 36, 98, 26);
		panel.add(lblNewLabel_3);

		JSpinner spinner_3 = new JSpinner(modelMode);
		spinner_3.setBounds(571, 225, 82, 36);
		panel.add(spinner_3);

		JButton btnNewButton_6 = new JButton("set Mode");
		btnNewButton_6.addActionListener(e -> {
			if ((int) spinner_3.getValue() == 0) {
				g.setMode((byte) 0);
			} else if ((int) spinner_3.getValue() == 1) {
				g.setMode((byte) 1);
			}

		});
		btnNewButton_6.setBounds(652, 225, 98, 36);
		panel.add(btnNewButton_6);

		JLabel lblNewLabel_4 = new JLabel("0 - BURST");
		lblNewLabel_4.setBounds(490, 229, 81, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("1 - CONTINOUS");
		lblNewLabel_5.setBounds(474, 247, 98, 14);
		panel.add(lblNewLabel_5);

		SpinnerModel model4 = new SpinnerNumberModel(0, min, 100000, 1);
		JSpinner spinner_4 = new JSpinner(model4);
		spinner_4.setBounds(571, 272, 82, 36);
		panel.add(spinner_4);
		SpinnerModel model5 = new SpinnerNumberModel(0, min, 100000, 1);
		JSpinner spinner_5 = new JSpinner(model5);
		spinner_5.setBounds(571, 319, 82, 36);
		panel.add(spinner_5);

		JButton btnNewButton_7 = new JButton("set Delay");
		btnNewButton_7.setBounds(652, 272, 98, 36);
		panel.add(btnNewButton_7);
		btnNewButton_7.addActionListener(e -> {
			g.setPulseDelay((Integer) (spinner_4.getValue()));
		});

		JButton btnNewButton_8 = new JButton("set Pulse ");
		btnNewButton_8.setBounds(652, 319, 98, 36);
		panel.add(btnNewButton_8);

		JLabel lblNewLabel_6 = new JLabel("DELAY KNOB");
		lblNewLabel_6.setBounds(351, 289, 69, 14);
		panel.add(lblNewLabel_6);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Set Enable");
		rdbtnNewRadioButton.setBounds(571, 139, 109, 23);
		panel.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.addActionListener(e -> {
			if (rdbtnNewRadioButton.isSelected()) {
				g.trigger();
				btnNewButton_6.setEnabled(false);
				btnNewButton_7.setEnabled(false);
				btnNewButton_8.setEnabled(false);
				
			} else if (!rdbtnNewRadioButton.isSelected()) {
				g.setDisable();
				btnNewButton_6.setEnabled(true);
				btnNewButton_7.setEnabled(true);
				btnNewButton_8.setEnabled(true);
				
			}

		});
		btnNewButton_8.addActionListener(e -> {
			g.setPulseCount((Integer) (spinner_5.getValue()));
		});

		btnNewButton_3.addActionListener(e -> {
			cortex.reset();
			g.reset();
			spinner.setValue((int) 0);
			spinner_1.setValue((int) 0);
			spinner_2.setValue((int) 0);
			spinner_3.setValue((int) 0);
			spinner_4.setValue((int) 0);
			spinner_5.setValue((int) 0);
			rdbtnNewRadioButton.setSelected(g.isEnable());

		});

		cortex.addActionListener(e -> {

			textField_2.setText(String.valueOf(cortex.getRVR()));
			textField_1.setText(String.valueOf(cortex.getCVR()));
			textField.setText(String.valueOf(cortex.getCSR()));
			RadioButton_2.setSelected(cortex.isEnableFlag());
			RadioButton.setSelected(cortex.isSourceFlag());
			RadioButton_1.setSelected(cortex.isInterruptFlag());
		});
		g.addActionListener(e -> {
			textField_2.setText(String.valueOf(cortex.getRVR()));
			textField_1.setText(String.valueOf(cortex.getCVR()));
			textField.setText(String.valueOf(cortex.getCSR()));
		});
		knob.addActionListener(e -> {
			spinner_4.setValue(knob.getDelay());
		});

	}
}
