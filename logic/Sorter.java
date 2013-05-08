package logic;
/**
 * This class does the sorting.
 */
public class Sorter {
	String [] array;
	int numberOfThreads;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		array = strings;
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return array;
	}
}