// exception for to many elements
package filehandling;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import util.ToManyElementsException;
/**
 * This class reads a file with words. The first line is the numbers of lines.
 */
public class FileReader {
    String [] strings;
    public FileReader(String filename) throws FileNotFoundException {
    	strings = readFile(filename);
    }
    /**
     * Creates a scanner object with a connected file.
     * @arg a filename with a correct path.
     * @return a scanner object with a connected file.
     */
    private Scanner openFile(String filename) throws FileNotFoundException {
    	File file = new File(filename);
    	Scanner sc = new Scanner(file);
    	return sc;
    }
    /**
     * Reads a file with a number of lines as the first line and puts all
     * the lines in a string array.
     * @arg the filename of the file to be read with a correct path.
     * @return a array with the lines in the file as strings.
     */
    private String [] readFile(String filename) throws FileNotFoundException
    , InputMismatchException, NoSuchElementException {
    	String [] strings;
    	Scanner sc = openFile(filename);
    	int numberOfLines = (sc.nextInt());
    	// jump to next line so that it is ready for reading strings.
    	sc.nextLine();
    	strings = new String [numberOfLines];
    	for (int i = 0; i < numberOfLines; i++) {
    		strings[i] = sc.nextLine();
    	}
    	// legge inn en ToManyElementsException og lage en warning hvis det er
    	// for mange elementer i fila.
    	return strings;
    }
    public String [] getStrings() {
        return strings;
    }
}