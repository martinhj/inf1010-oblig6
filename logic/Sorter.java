package logic;

import java.util.Arrays;
/**
 * This class does the sorting.
 */
public class Sorter {
	String [] array;
	int numberOfThreads;
	String [][] arraysOfArrays;
	boolean [] readyArrays;
	// for debugging
		int numberOfArrays = 0;
	public Sorter(int numberOfThreads, String [] strings) {
		this.numberOfThreads = numberOfThreads;
		this.array = strings;

		// denne trengs å endre til å bruke numberOfThreads og forholde seg til
		// stringene::
		arraysOfArrays = fillArrays(arraysOfArrays, readyArrays);
		/*for (String [] a: arraysOfArrays)
			Arrays.sort(a);*/
		int numbersOfWords = 0;
		for (String [] a: arraysOfArrays) {
			System.out.println("Her: ");
			for (String s: a) {
				System.out.println(s);
				numbersOfWords++;
			}
		}
		System.out.println(":" + array.length);
		System.out.println(numbersOfWords);
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
		System.out.println(array.length);
		System.out.println(array.length % numberOfThreads);
		System.out.println(numberOfArrays);
	}
	/**
	 * @return the sorted array.
	 */
	public String [] getArray() {
		return array;
	}
	private String[][] fillArrays(String [][] allArrays, boolean [] readyArrays) {
		readyArrays = new boolean[numberOfThreads];
		for (boolean b : readyArrays)
			b = false;
		allArrays = new String[numberOfThreads][];
		// skal dette gjøres med tråder?
	/*	for (int i = 0; i < array.length; i+=arrayLength()) {
			if (beforeLastArray(i)) 
				allArrays[whichArray(i)] = fillArray(array, arrayLength(), i);
			if (lastArray(i)) 
				allArrays[whichArray(i)] = fillArray(array, lastArrayLength(), i);
		}*/

		for (int i = 0; i < array.length; i+=arrayLength()) {
			if (beforeLastArray(i)) {
				System.out.println("Legger inn " + whichArray(i));
				allArrays[whichArray(i)] = fillArray(array, arrayLength(), i);
			}
		}
		for (int i = 0; i < rest(); i++) {
			System.out.println("Legger inn " + i);
			System.out.println(allArrays[i].length);
			allArrays[i][allArrays[i].length - 1] = array[array.length - rest()];
		}
		
		return allArrays;
	}

	// 1. dele array i antallTråder deler
	// 2. rest legges på de første rest antall tråder
	// 3. de første rest antall trådene skal være lengde på 
	// antallord / antalltråder + 1
	// 4. alle tråder fylles
	// 5. rest fyller et ord inn på siste plass i de første rest antall tråder

	private String [] fillArray(String [] oldArray, int length, int start) {
		numberOfArrays++;
		String [] newArray = new String [0];
		if (numberOfArrays <= rest())
			newArray = new String [length + 1];
		if (numberOfArrays > rest())
			newArray = new String [length];
		for (int i = 0; i < length; i++)
			newArray[i] = oldArray[start + i];
		System.out.println(newArray.length);
		return newArray;
	}
	private boolean beforeLastArray(int i) {
		if (i / arrayLength() < numberOfThreads) return true;
		return false;
	}
	private boolean lastArray(int i) {
		if (i / arrayLength() == numberOfThreads - 1) return true;
		return false;
	}
	private int lastArrayLength() {
		return arrayLength() + array.length % numberOfThreads;
	}
	private int rest() {
		return array.length % numberOfThreads;
	}
	private int arrayLength() {
		return array.length / numberOfThreads;
	}
	private int whichArray(int i) {
		return i / arrayLength();
	}
}