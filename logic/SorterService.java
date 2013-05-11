package logic;
class SorterService implements Runnable {
    String [] array;
    Sorter sorter;
    int index;
    boolean [] readyArrays;
    public SorterService(String [] array, Sorter sorter,
        boolean [] readyArrays, int index)
    {
        this.array = array;
        this.sorter = sorter;
        this.index = index;
        this.readyArrays = readyArrays;
    }
        
	public void run() {
        array = sorter.sortArray(array);
        for (String s: array) System.out.println(": " + s);
        System.out.println("===");
        readyArrays[index] = true;
    }
    public String [] getArray() {
        return array;
    }
}