/**
 * A class for sorting strngs.
 * Will use ... sort algorithm.
 */
class Sort {
    public Sort(int numberOfThreads, String fileToSort, String outputFile) {
       System.out.println(numberOfThreads + " " + fileToSort + " " + outputFile); 
    }
    public static void main (String [] args) {
        new Sort(Integer.getInteger(args[0]), args[1], args[2]);
    }
}
