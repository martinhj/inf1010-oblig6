package logic;
/**
 * This class does the sorting.
 */
public class Sorter {
	String [] array;
	int numberOfThreads;
	String [][] arraysOfArrays;
	// for debugging
		int numberOfArrays = 0;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		array = strings;
		// denne trengs å endre til å bruke numberOfThreads og forholde seg til
		// stringene::
		arraysOfArrays = new String[4][];
		for (int i = 0; i < strings.length; i+=numberOfArrays()) {
			if (beforeLastArray(i)) fillArray(strings, arrayLength(), i);
			if (lastArray(i)) fillArray(strings, lastArrayLength(), i);
			}
		// 1. dele opp arrayet i like mange array som tråder
		//		sette opp disse i et array med pekere til disse
		//		sette opp et bool-array med true / false
		//
		// 2. starte opp sortering i hver tråd.
		// 3. når en tråd er ferdig sjekker den om det er noen andre ferdige, sjekk
		// bool-array..
		// 4. hvis ingen andre ferdige wait.
		// 5. hvis andre ferdige notify.
		// 6. hvis melding fra en annen om at det er på tide å våkne, sjekke
		//		bool-array med hvilken som er ferdig.
		// 7. hvis ingen andre ferdige, vent
		// 8. 
		System.out.println(numberOfArrays);
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return array;
	}

	private int numberOfArrays() {
		return array.length / numberOfThreads;
	}
	private boolean beforeLastArray(int i) {
		if (i / arrayLength() < numberOfThreads - 1) return true;
		return false;
	}
	private boolean lastArray(int i) {
		if (i / arrayLength() == numberOfThreads - 1) return true;
		return false;
	}
	private int lastArrayLength() {
		return arrayLength() + array.length % numberOfThreads;
	}
	private int arrayLength() {
		return array.length / numberOfThreads;
	}
	private String [] fillArray(String [] oldArray, int length, int start) {
		System.out.println("...       : " + oldArray.length);
		System.out.println("Length    : " + length);
		System.out.println("Startpoint: " + start);
		System.out.println("");
		numberOfArrays++;
		return null;
	}
    private int [] fillArray(int [] oldArray, int length, int start) {
        //System.out.println("The old array's size: " + oldArray.length);
        //System.out.println("Point to start: " + start);
        int [] newArray = new int[length];
        for (int i = 0; i < length; i++)
            newArray[i] = oldArray[start + i];
        //for (int i = start; i < length; i++)
        //System.out.println("The new array's size: " + newArray.length);
        return newArray;
    }
}