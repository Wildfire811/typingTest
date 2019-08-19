package utilities;

import java.util.stream.IntStream;

public class WordUtility {

	public int countWords(String testFieldText) {
		// split the string on space to count words
		// the Regex s+ filters for more then one space in a row
		return testFieldText.split("\\s+").length;
	}
	
	public double wordsPerMinute(double time, int words) {
		// convert time, in milliseconds to seconds and calculate WPM
		return (double)words/((double)time/60000);
	}
	
	public float acurracy(String testString, String userInput) {
		int mistakes = (int) IntStream.range(0, testString.length())
			.filter(i -> testString.charAt(i) == userInput.charAt(i)).count();
		
		return (float) mistakes/testString.length();
		
	}
}
