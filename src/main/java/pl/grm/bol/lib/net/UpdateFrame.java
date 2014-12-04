package pl.grm.bol.lib.net;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import pl.grm.bol.lib.BLog;
import pl.grm.bol.lib.Config;
import pl.grm.bol.lib.TypeOfProject;
import Effect.BarConstant;
import Effect.EffectProgressBarCoord;
import Effect.EffectProgressBarUI;

public class UpdateFrame extends JFrame implements PropertyChangeListener {
	private static final long	serialVersionUID	= 1L;
	private JButton				buttonUpdate		= new JButton("Update");
	private JLabel				labelProgress		= new JLabel("Progress:");
	private JProgressBar		progressBar;
	private EffectProgressBarUI	barUI;
	private TypeOfProject		runningType;
	public Object				obj;
	private BLog				logger;
	
	/**
	 * @param title
	 *            Title of updater window
	 * @param updater
	 *            Type Of Project which started updater
	 * @param logger
	 *            BLog type of logger from upper object
	 */
	public UpdateFrame(String title, TypeOfProject updater, BLog logger) {
		this(title, updater);
		this.logger = logger;
	}
	
	/**
	 * @param title
	 *            Title of updater window
	 * @param updater
	 *            Type Of Project which started updater
	 */
	public UpdateFrame(String title, TypeOfProject updater) {
		super(title);
		setRunningType(updater);
		setLAF();
		getContentPane().setLayout(new BorderLayout(0, 0));
		setPreferredSize(setupBounds());
		labelProgress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelProgress.setForeground(Color.LIGHT_GRAY);
		labelProgress.setBackground(Color.DARK_GRAY);
		labelProgress.setOpaque(true);
		setBackground(Color.DARK_GRAY);
		getContentPane().add(labelProgress, BorderLayout.WEST);
		createProgressBar();
		getContentPane().add(progressBar);
		buttonUpdate.setEnabled(false);
		buttonUpdate.setMnemonic('u');
		buttonUpdate.setFont(buttonUpdate.getFont().deriveFont(
				buttonUpdate.getFont().getStyle() | Font.BOLD));
		buttonUpdate.setForeground(Color.WHITE);
		buttonUpdate.setBackground(Color.DARK_GRAY);
		getContentPane().add(buttonUpdate, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(400, 110);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				startUpdate();
			}
		});
	}
	
	/**
	 * Update the progress bar's state whenever the progress of download
	 * changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("progress")) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
	}
	
	/** setLookAndFeel */
	private static void setLAF() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** creates better look progress bar */
	private void createProgressBar() {
		progressBar = new JProgressBar();
		barUI = new EffectProgressBarUI(progressBar);
		barUI.setRoundCorner(EffectProgressBarCoord.RoundOVAL);
		barUI.setDarkLightColors(Config.PROGRESS_BAR_COLORS);
		barUI.setBorder(1);
		barUI.setBorderColor(Color.GRAY);
		barUI.setHighQuality(true);
		barUI.setShadowPainted(true);
		barUI.setSelectionBackground(Color.RED);
		barUI.setSelectionForeground(Color.YELLOW);
		barUI.setIllusionDirection(BarConstant.LEFT_TO_RIGHT);
		barUI.getGradientFactory().setGrad(45);
		progressBar.setUI(barUI);
		progressBar.setForeground(Color.RED);
		progressBar.setFont(new Font("Arial", Font.BOLD, 20));
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.CYAN));
	}
	
	/**
	 * Calculates frame's dimensions
	 * 
	 * @return {@link Dimension} (x,y)
	 */
	private Dimension setupBounds() {
		Dimension dim;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		int screenWidth = (int) screenSize.getWidth();
		int frameWidth = screenWidth / 2 - screenWidth / 15;
		int frameHeight = frameWidth * 3 / 4;
		setBounds(2 * screenWidth / 5, 5 * screenHeight / 11, 200, 200);
		dim = new Dimension(frameWidth, frameHeight);
		return dim;
	}
	
	private void startUpdate() {
		Updater worker;
		if (logger != null) {
			worker = new Updater(UpdateFrame.this, logger);
		} else {
			worker = new Updater(UpdateFrame.this);
		}
		worker.execute();
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public TypeOfProject getRunningType() {
		return runningType;
	}
	
	public void setRunningType(TypeOfProject updater) {
		this.runningType = updater;
	}
	
	public JButton getButtonUpdate() {
		return this.buttonUpdate;
	}
	
	public void setUpdaterObj(Class<?> class1) {
		obj = class1;
	}
}
