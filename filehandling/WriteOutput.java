package filehandling;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
/**
 * Class to write the sorted words to file.
 */
public class WriteOutput {
    /**
     * The filewriter constructor.
     * @arg words - array with words to write to file.
     * @arg filename to write to.
     */
    String [] words;
    String filename;
    public WriteOutput (String [] words, String filename) throws IOException {
        this.words = words;
        this.filename = filename;
        writeWords();
    }
	/**
	 * Skriver stringen til gitt fil.
	 */
	void writeWords() throws IOException {
		File file = new File(filename);
			FileWriter fw 
			= new FileWriter(file);
            fw.write("" + words.length);
            for (String s: words)
                fw.write("\n" + s);
			fw.close();
	}
}
