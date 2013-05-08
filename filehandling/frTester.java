package filehandling;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

class frTester {
	static String [] strings = new String [0];
	public static void main (String [] args) {
		FileReader fr;
		try {
			fr = new FileReader("filehandling/names.txt");
			strings = fr.getStrings();
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
		for (String s : strings)
			System.out.println(s);
	}
}