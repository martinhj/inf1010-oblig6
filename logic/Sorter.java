package logic;

/**
 * This class does the sorting.
 */
public class Sorter {
	private int numberOfWords;
	private int numberOfThreads;
	private String [] allWords;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		this.allWords = strings;
		this.numberOfWords = allWords.length;
		for (String s : allWords) System.out.println(s);
		System.out.println("-------");
		allWords = sortWords(allWords);
		System.out.println("-------");
		for (String s: allWords) System.out.println(s);
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return allWords;
	}
	public String [] sortArray(String [] array) {
		return new BinaryTree(array).getArray();
	}
	private String [] fillArray(String [] oldArray, int length, int start) {
		String [] newArray = new String [length];
			for (int i = 0; i < length; i++)
				newArray[i] = oldArray[start + i];
		return newArray;
	}
	private int arrayLength() {
		return numberOfWords / numberOfThreads;
	}
	private int minArrayLength() {
		return arrayLength() + 1;
	}
	/**
	 * Splits the array in two recursively until it reaches the minimum length
	 * of the arrays.
	 */
	public String [] sortWords(String [] words) {
		if (words.length > minArrayLength()) {
			String [] firstArray = fillArray(words, words.length / 2, 0);
			firstArray = sortWords(firstArray);
			String [] lastArray =
			    fillArray(words,
                          words.length / 2 + words.length % 2,
                          words.length / 2);
			lastArray = sortWords(lastArray);
			words = mergeArrays(firstArray, lastArray);
		}
		if (words.length <= minArrayLength()) words = sortArray(words);
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