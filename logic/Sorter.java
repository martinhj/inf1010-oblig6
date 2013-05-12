package logic;

/**
 * This class does the sorting.
 */
public class Sorter {
	private int numberOfWords;
	private int numberOfThreads;
    private int testCount;
	private String [] allWords;
    /**
     * Constructor for the Sorter class.
     */
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		this.allWords = strings;
		this.numberOfWords = allWords.length;
        System.out.println(numberOfWords / numberOfThreads);
		allWords = sortWords(allWords);
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return allWords;
	}
    /**
     * Sorts the array with a binary tree algorithm.
     * @return a sorted array.
     */
	private String [] sortArray(String [] array) {
		return new BinaryTree(array).getArray();
	}
	private String [] fillArray(String [] oldArray, int length, int start) {
		String [] newArray = new String [length];
			for (int i = 0; i < length; i++)
				newArray[i] = oldArray[start + i];
		return newArray;
	}
    private String [] fillArray(String [] words, boolean first) {
        if (first)
            return fillArray(words, lArrayLength(words), halfLength(words));
        if (!first) return fillArray(words, halfLength(words), 0);
        return null;
    }

	private int arrayLength() {
		return numberOfWords / numberOfThreads;
	}
	private int minArrayLength() {
		return arrayLength() + 1;
	}
	/**
	 * Splits the array in two recursively until it reaches the minimum length
	 * of the arrays. Uses the method splitWords to call back to this method.
	 */
	String [] sortWords(String [] words) {
		if (words.length <= minArrayLength()) words = sortArray(words);
		if (words.length > minArrayLength()) words = splitWords(words);
		return words;
	}
    /**
     * Splits an array and sends it to two new sortWords instances.
     */
    private String [] splitWords(String [] words) {
        boolean debug = true;
        //boolean debug = false;
        SorterService lastArrayService = 
            new SorterService(fillArray(words,false), this);
        Thread sorterThread = new Thread(lastArrayService);
        if (debug) System.out.println(sorterThread.getName() + "  " 
            + sorterThread.getState());
        sorterThread.start();
        String [] firstArray = sortWords(fillArray(words,true));
        if (debug) System.out.println(sorterThread.getName() + "  " 
            + sorterThread.getState());
        try {
            sorterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(10);
        }
        String [] lastArray = lastArrayService.getArray();
        if (debug) System.out.println(sorterThread.getName() + "  " 
            + sorterThread.getState());
        return words = mergeArrays(firstArray, lastArray);
    }
    private int lArrayLength(String [] words) {
        return words.length / 2 + words.length % 2;
    }
    private int halfLength(String [] words) {
        return words.length / 2;
    }
	/**
	 * Merge two sorted arrays into one sorted array.
	 * @return a sorted array if the argument arrays is sorted.
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