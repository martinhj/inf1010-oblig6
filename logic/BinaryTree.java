package logic;
import java.util.ArrayList;
/**
 * Class that recive an string and places it in a binary tree.
 */
public class BinaryTree {
	Node startNode;
	ArrayList<String> al = new ArrayList<String>();
	/**
	 * Constructs a empty Binary Tree object.
	 */
	public BinaryTree() {
	}
	/**
	 * Constructs the binary tree object from an array with strings.
	 */
	public BinaryTree(String [] strings) {
		add(strings);
	}
	/**
	 * Places the binary tree into an array.
	 */
	public String [] getArray() {
		startNode.toArray(0);
		return al.toArray(new String[0]);
	}
	/**
	 * Adds a string in the binary tree.
	 */
	public void add(String string) {
		Node node = new Node(string);
		if (startNode != null) startNode.add(node);
		if (startNode == null) startNode = node;
	}
	/**
	 * Adds an array with strings to the binary tree.
	 */
	public void add(String [] strings) {
		for (String s : strings)
			add(s);
	}
/**
 * A inner class in the binary tree that stores the value and values to the
 * right and left of the node.
 */
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
	private void add(Node node) {
		if (lessThan(node, this)) {
			if (left != null) left.add(node);
			if (left == null) left = node;
		}
		if (!lessThan(node, this)) {
			if (right != null) right.add(node);
			if (right == null) right = node;
		}
	}
	/**
	 * Converts the binary tree to a ArrayList recursively.
	 */
	private int toArray(int index) {
		if (left != null) index = left.toArray(index);
		if (value != null) al.add(index++, value);
		if (right != null) index = right.toArray(index);
		return index;
	}
	/**
	 * checks if value in nodeOne is less than value in nodeTwo.
	 */
	private boolean lessThan(Node nodeOne, Node nodeTwo) {
		if (nodeOne.value.compareTo(nodeTwo.value) < 0) return true;
		return false;
	}
}	
}