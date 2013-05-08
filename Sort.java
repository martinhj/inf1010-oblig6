import filehandling.*;
import logic.*;
import java.io.FileNotFoundException;
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

		new Sorter(strings);
    }
    public static void main (String [] args) {
        new Sort(Integer.parseInt(args[0]), args[1], args[2]);
    }
}