package logic;

import java.util.HashMap;
/**
 * This class does the sorting.
 */
public class Sorter {
	private String [] allWordsUnsorted;
	private int numbersOfWords;
	private int numberOfThreads;
	private String [][] arraysOfArrays;
	private boolean [] readyArrays;
	private int numberOfArrays = 0;
    private SSLLcontainer<String,String> sortedWords =
        new SSLLcontainer<String,String>();
	private String [] allWordsSorted;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		this.allWordsUnsorted = strings;
		this.numbersOfWords = allWordsUnsorted.length;
		arraysOfArrays = fillArrays(arraysOfArrays, readyArrays);
		
		// Splitt opp i forskjellige tråder her.
		sortArrays(arraysOfArrays);
		for (int i = 0; i < arraysOfArrays.length; i++) {
			arraysOfArrays[i] = sortArray(arraysOfArrays[i]);
		}
		
		
		
		
		allWordsSorted = new String [numbersOfWords];
		int wordIndex = 0;
		for (int i = 0; i < arraysOfArrays.length; i++)
			for (int j = 0; j < arraysOfArrays[i].length; j++)
                sortedWords.insert(arraysOfArrays[i][j], arraysOfArrays[i][j]);
	//	for (String s : allWordsSorted)
	//			System.out.println(s);

		// debug printout:::
		/*for (String [] a: arraysOfArrays)
		int numbersOfWords = 0;
		for (String [] a: arraysOfArrays) {
			System.out.println("Her: ");
			for (String s: a) {
				System.out.println(s);
				numbersOfWords++;
			}
		}*/
		/*System.out.println(":" + array.length);*/
		//System.out.println(numbersOfWords);
		//ok 1. dele opp arrayet i like mange array som tråder
		//ok		sette opp disse i et array med pekere til disse
		//ok		sette opp et bool-array med true / false
		// SÅ: hver tråd må ha peil på hvilket array den forholder seg til 
		// (array index - denne skal brukes opp mot string-array og bool-array).
		// 2. starte opp sortering i hver tråd.
		
		// 3. når en tråd er ferdig sjekker den om det er noen andre ferdige, sjekk
		// bool-array..
		// 4. hvis ingen andre ferdige wait.
		// 5. hvis andre ferdige notify.
		// 6. hvis melding fra en annen om at det er på tide å våkne, sjekke
		//		bool-array med hvilken som er ferdig.
		// 7. hvis ingen andre ferdige, vent
		// 8. 
	}

	private void insertInSortedArray(String [] insertArray, String wordToInsert)
	{
		int i = 0;
		while (insertArray[i++] != null) {
			//if (wordToInsert.compareTo(insertArray[i]) > 0)
		}
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return allWordsUnsorted;
	}
	private String[][] fillArrays(String [][] allArrays, boolean [] readyArrays) {
		readyArrays = new boolean[numberOfThreads];
		for (boolean b : readyArrays)
			b = false;
		allArrays = new String[numberOfThreads][];
		for (int i = 0; i < numbersOfWords; i+=arrayLength()) {
			if (beforeRest(i))
				allArrays[whichArray(i)] =
					fillArray(allWordsUnsorted, arrayLength(), i);
		}
		for (int i = 0; i < rest(); i++) {
			allArrays[i][allArrays[i].length - 1] = 
				allWordsUnsorted[numbersOfWords - rest()];
		}
		return allArrays;
	}
	public String [] sortArray(String [] array) {
		return new BinaryTree(array).getArray();
	}
	private String [][] sortArrays(String [][] allWords) {
		new SorterMonitor(allWords, this).getArray();
		return null;
	}

	private String [] fillArray(String [] oldArray, int length, int start) {
		numberOfArrays++;
		String [] newArray = new String [0];
		if (numberOfArrays <= rest())
			newArray = new String [length + 1];
		if (numberOfArrays > rest())
			newArray = new String [length];
		for (int i = 0; i < length; i++)
			newArray[i] = oldArray[start + i];
		return newArray;
	}
	private boolean beforeRest(int i) {
		if (i / arrayLength() < numberOfThreads) return true;
		return false;
	}
	private boolean lastArray(int i) {
		if (i / arrayLength() == numberOfThreads - 1) return true;
		return false;
	}
	private int lastArrayLength() {
		return arrayLength() + numbersOfWords % numberOfThreads;
	}
	private int rest() {
		return numbersOfWords % numberOfThreads;
	}
	private int arrayLength() {
		return numbersOfWords / numberOfThreads;
	}
	private int whichArray(int i) {
		return i / arrayLength();
	}
}