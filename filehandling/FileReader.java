package filehandling;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
/**
 * This class reads a file with words. The first line is the numbers of lines.
 */
class FileReader {
    public FileReader(String filename) throws FileNotFoundException {
    	readFile(filename);
    }
    /**
     *
     */
    private Scanner openFile(String filename) throws FileNotFoundException {
    	File file = new File(filename);
    	Scanner sc = new Scanner(file);
    	return sc;
    }
    /**
     * 
     */
    private String [] readFile(String filename) throws FileNotFoundException
    , InputMismatchException, NoSuchElementException {
    	String [] strings;
    	Scanner sc = openFile(filename);
    	int numberOfLines = (sc.nextInt());
    	// jump to next line so that it is ready for reading strings.
    	sc.nextLine();
    	strings = new String [numberOfLines];
    	System.out.println(strings.length);
    	for (int i = 0; i < numberOfLines; i++) {
    		strings[i] = sc.nextLine();
    	}
    	for (String s : strings)
    		System.out.println(s);
    	System.out.println(strings[0]);
    	return strings;
    }
}