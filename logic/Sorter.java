package logic;
/**
 * This class does the sorting.
 */
public class Sorter {
	String [] array;
	public Sorter(int numberOfThreads, String [] strings) {
		array = strings;
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return array;
	}
}