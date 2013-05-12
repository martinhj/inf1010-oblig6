package logic;

/**
 * This class does the sorting.
 */
public class Sorter {
	private int numberOfWords;
	private int numberOfThreads;
	private String [] allWordsUnsorted;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		this.allWordsUnsorted = strings;
		this.numberOfWords = allWordsUnsorted.length;
		for (String s : allWordsUnsorted) System.out.println(s);
		System.out.println("-------");
		allWordsUnsorted = splitArray(allWordsUnsorted);
		for (String s: allWordsUnsorted) System.out.println(s);

		

	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return allWordsUnsorted;
	}
	private String[][] fillArrays(String [][] allArrays, boolean [] readyArrays)
	{
		readyArrays = new boolean[numberOfThreads];
		for (boolean b : readyArrays)
			b = false;
		allArrays = new String[numberOfThreads][];
		for (int i = 0; i < numberOfWords; i+=arrayLength()) {
			if (beforeRest(i))
				allArrays[whichArray(i)] =
					fillArray(allWordsUnsorted, arrayLength(), i);
		}
		for (int i = 0; i < rest(); i++) {
			allArrays[i][allArrays[i].length - 1] = 
				allWordsUnsorted[numberOfWords - rest()];
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
		String [] newArray = new String [length];
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
		return arrayLength() + numberOfWords % numberOfThreads;
	}
	private int rest() {
		return numberOfWords % numberOfThreads;
	}
	private int arrayLength() {
		return numberOfWords / numberOfThreads;
	}
	private int minArrayLength() {
		return arrayLength() + 1;
	}
	private int whichArray(int i) {
		return i / arrayLength();
	}
	/**
	 * Splits the array in two recursively until it reaches the minimum length
	 * of the arrays.
	 */
	public String [] splitArray(String [] words) {
		if (words.length > minArrayLength()) {
			String [] firstArray;
			String [] lastArray;
			firstArray = fillArray(words, words.length / 2, 0);
			for (String s: firstArray) System.out.println(s);
			System.out.println(":::");
			firstArray = splitArray(firstArray);
			lastArray = fillArray(words, words.length / 2 + words.length % 2, words.length / 2);
			for (String s: lastArray) System.out.println(s);
			System.out.println("::");
			lastArray = splitArray(lastArray);
			words = mergeArrays(firstArray, lastArray);
		}
		if (words.length <= minArrayLength()) {
			words = sortArray(words);
		}
		return words;
	}
	/**
	 * Merge two sorted arrays into one sorted array.
	 * @return a sorted array.
	 */
	private String [] mergeArrays(String [] firstArray, String [] lastArray) {
		String [] sorted = new String [firstArray.length + lastArray.length];
		int i = 0, j = 0, k = 0;
		while (j < firstArray.length && k < lastArray.length) {
			if (firstArray[j].compareTo(lastArray[k]) < 0)
				sorted[i] = firstArray[j++];
			else 
				sorted[i] = lastArray[k++];
			i++;
		}
		while (j < firstArray.length) {
			sorted[i++] = firstArray[j++];
		}
		while (k < lastArray.length) {
			sorted[i++] = lastArray[k++];
		}
		return sorted;
	}
}