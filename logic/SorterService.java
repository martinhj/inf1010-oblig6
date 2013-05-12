package logic;
class SorterService implements Runnable {
    String [] words;
    Sorter sorter;
    int index;
    public SorterService(String [] words, Sorter sorter) {
        this.words = words;
        this.sorter = sorter;
    }
	public void run() {
        words = sorter.sortWords(words);
    }
    public String [] getArray() {
        return words;
    }
}