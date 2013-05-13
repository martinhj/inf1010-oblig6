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
            System.out.println(words.length);
            fw.write("" + words.length);
            for (String s: words)
                fw.write("\n" + s);
			fw.close();
	}
	/**
	 * @return En string med alle løsningene.
	 */
	String createOutPut(String [] words) {
		String outPut = "";
        outPut += words.length + "\n";
		for (int i = 0; i < words.length; i++) {
            outPut += words[i] + "\n";
		}
		return outPut;
	}
}
