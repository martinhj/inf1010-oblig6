package filehandling;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

class frTester {
	public static void main (String [] args) {
		try {
			new FileReader("filehandling/names.txt");
		} catch (FileNotFoundException e) {
			System.out.println("E. flntfnd. ");
		} catch (InputMismatchException e) {
			System.out.println("E. inputmism. ");
		} catch (NoSuchElementException e) {
			System.out.println("E. nschlmnt. ");
		}
	}
}