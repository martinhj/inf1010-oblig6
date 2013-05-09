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
	 * Places the binary tree into an array.
	 */
	public String [] getArray() {
		return null;
	}

	private boolean lessThan(Node nodeOne, Node nodeTwo) {
		if (nodeOne.value.compareTo(nodeTwo.value) > 0) return true;
		return false;
	}
	public void add(String string) {
		Node node = new Node(string);
		startNode.add(node);
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
		if (lessThan(node, this)) {
			if (left == null) left = node;
			if (left != null) left.add(node);
		}
		if (!lessThan(node, this)) {
			if (right == null) right = node;
			if (right != null) right.add(node);
		}
	}
}	
}