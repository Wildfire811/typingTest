package runner;
import java.awt.EventQueue;

import gui.TypeTestGui;
import model.TypingSample;

public class Runner {

	public static void main(String[] args) {
		TypingSample sample = new TypingSample("The quick brown fox jumps over the lazy dog");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeTestGui window = new TypeTestGui(sample);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
