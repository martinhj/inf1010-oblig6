package logic;
/**
 * Class that recive an string and places it in a binary tree. 
 */
public class BinaryTree {
	Node startNode;
	public BinaryTree() {
		startNode = new Node(null);
	}
	/**
	 * Places the binary tree into an array
	 */
	public String [] getArray() {
		return null;
	}
	public void add(String string) {

	}
private class Node {
	Node left;
	Node right;
	String value;
	Node(String string) {
		value = string;
	}
}	
}