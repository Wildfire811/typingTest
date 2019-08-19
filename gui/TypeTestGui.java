package gui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import model.TypingSample;
import utilities.Stopwatch;
import utilities.WordUtility;

import java.awt.Color;

public class TypeTestGui {

	public JFrame frame;
	private JTextField testField;
	private Stopwatch sw = new Stopwatch();
	private WordUtility wu = new WordUtility();

	/**
	 * Create the application.
	 */
	public TypeTestGui(TypingSample sample) {
		initialize(sample);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(TypingSample sample) {
		frame = new JFrame();
		frame.setBounds(100, 100, 708, 127);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel exampleString = new JLabel(sample.getSampleText());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weightx = 3.0;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(exampleString, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.ipady = 10;
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel beginPrompt = new JLabel("Begin typing to start the test.");
		panel.add(beginPrompt);
		
		JLabel stopwatchField = new JLabel("Time: 0.0 seconds");
		panel.add(stopwatchField);
		
		JLabel wordCountField = new JLabel("Words: 0");
		panel.add(wordCountField);
		
		JLabel accuracyField = new JLabel("0%");
		accuracyField.setVisible(false);
		panel.add(accuracyField);
		
		JLabel wrdPerMinField = new JLabel("WPM: ");
		wrdPerMinField.setVisible(false);
		panel.add(wrdPerMinField);
		// Swing timer to refresh the timer display
		Timer stopwatchTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double timeElapsed = sw.timeElapsed()/1000;
				stopwatchField.setText(String.format("Time: %.1f seconds", (float)timeElapsed));
			}
		});
		
		
		testField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weighty = 1.0;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(testField, gbc_textField);
		testField.setColumns(10);
		testField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) { // perform the following actions on every key release
				if (!stopwatchTimer.isRunning()) {
					sw = new Stopwatch(); // make a new instance of Stopwatch
					stopwatchTimer.start(); // start the Swing timer
				}
				int wordCount = wu.countWords(testField.getText()); // get a count of the words
				wordCountField.setText(String.format("Words: %d",wordCount)); //refresh the word count field
				if (testField.getText().length() >= sample.getCharacterCount()) { // When the typed input is the same length as the sample string
					testField.setEnabled(false); // disable the text input 
					stopwatchTimer.stop(); // stop the timer to stop refreshing the clock
					sw.setStop(); // set the end time of the Stopwatch to get a final duration of the test
					float acurracyPercent = wu.acurracy(sample.getSampleText(),testField.getText())*100; // calculate the accuracy of the input
					accuracyField.setText(String.format("Acuracy: %.2f%%", acurracyPercent)); // Set the accuracy field text
					double wordsPerMin = wu.wordsPerMinute(sw.getTotalTime(), wordCount); //calculate words per minute
					wrdPerMinField.setText(String.format("WPM: %.2f", wordsPerMin)); // set the WPM field text
					// make WPM and Accuracy to viable
					wrdPerMinField.setVisible(true);
					accuracyField.setVisible(true);
				}
				
			}
		});
	}
	
}
