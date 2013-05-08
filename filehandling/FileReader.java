package filehandling;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
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
    private void readFile(String filename) 
    	throws FileNotFoundException, InputMismatchException {
    	Scanner sc = openFile(filename);
    		int numberOfLines = (sc.nextInt());
    	System.out.println(numberOfLines);
    	/*while (sc.hasNextLine()) {
    		System.out.println(sc.nextLine());
    	}*/
    }
}