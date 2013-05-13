//trenger en sjekk på hvor mange tråder (skal være mellom 1 og 1000)
import filehandling.*;
import logic.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
/**
 * A class for sorting strings.
 * Will use ... sort algorithm.
 */
class Sort {
	String [] sortedStrings;
    public Sort(int numberOfThreads, String fileToSort, String outputFile) {
    	String [] strings = new String [0];
		try {
			strings = new FileReader(fileToSort).getStrings();
		} catch (FileNotFoundException e) {
			System.out.println("E. flntfnd. ");
			System.exit(1);
		} catch (InputMismatchException e) {
			System.out.println("E. inputmism. ");
			System.exit(2);
		} catch (NoSuchElementException e) {
			System.out.println("E. nschlmnt. ");
			System.exit(3);
		}
		strings = new Sorter(numberOfThreads, strings).getArray();
        try {
            new WriteOutput(strings, outputFile);
        } catch (IOException e) {
            System.out.println("E. writeError. ");
            System.exit(4);
        }
    }
    public static void main (String [] args) {
        int numberOfThreads = Integer.parseInt(args[0]);
        if (numberOfThreads <= 0 || numberOfThreads > 1000) {
            System.out.println("E. Number of threads needs to be" 
             + "between 1 and 1000");
            System.exit(5);
        }
        new Sort(numberOfThreads, args[1], args[2]);
    }
}