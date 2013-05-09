package logic;
/**
 * Class that recive an string and places it in a binary tree. 
 */
public class BinaryTree {
	Node startNode;
	public BinaryTree() {
	}
	/**
	 * Places the binary tree into an array.
	 */
	public String [] getArray() {
		return null;
	}
	public void printOut() {
		startNode.printOut(0);
	}
	private boolean lessThan(Node nodeOne, Node nodeTwo) {
		if (nodeOne.value.compareTo(nodeTwo.value) < 0) return true;
		return false;
	}
	public void add(String string) {
		Node node = new Node(string);
		if (startNode != null) startNode.add(node);
		if (startNode == null) startNode = node;
	}
private class Node {
	Node left;
	Node right;
	String value;
	Node(String string) {
		value = string;
	}
	/**
	 * Adds a string to the binary tree.
	 */
	public void add(Node node) {
		System.out.println("Adding:    " + node.value);
		if (lessThan(node, this)) {
			if (left != null) left.add(node);
			if (left == null) left = node;
		}
		if (!lessThan(node, this)) {
			if (right != null) right.add(node);
			if (right == null) right = node;
		}
	}
	public int printOut(int index) {
		if (left != null) index = left.printOut(index);
		System.out.print(index++ + ": ");
		if (value != null) System.out.println(value);
		if (right != null) index = right.printOut(index);
		return index;
	}
}	
}