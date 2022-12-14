import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> 
{
	public Character character;	// The character of this node
	public Integer weight;		// The weight of this tree
	public Node left, right;	// The left and right nodes of this node
	public boolean leaf;			// If the node is a leaf or an internal node
	/**
	 * Constructs a Node object with the passed character and weight
	 * @param character	The character of this node 
	 * @param weight	The weight of this node
	 */
	public Node(Character character, Integer weight) 
	{
		this.character = character;
		this.weight = weight;
		this.left = null;
		this.right = null;
		this.leaf = true;
	}
	/**
	 * Update the current node to have a new character and weight
	 * of that from the passed variables.
	 * @param character	The character to change this node to be equal to
	 * @param weight	The weight to change this node to be equal to
	 */
	public void updateCurrent(Character character, Integer weight) 
	{
		this.character = character;
		this.weight = weight;
		this.left = null;
		this.right = null;
		this.leaf = true;
	}
	/**
	 * Update the right node of this node to be the passed
	 * value of right. Update the weight and change the type.
	 * @param right	The node that is being added to the right
	 * 				of the current node
	 */
	public void updateRight(Node right) 
	{
		this.weight += right.weight;
		this.right = right;
		this.leaf = false;
	}
	/**
	 * Update the left node of this node to be the passed
	 * value of left. Update the weight and change the type.
	 * @param left	The node that is being added to the left
	 * 				of the current node
	 */
	public void updateLeft(Node left) 
	{
		this.weight += left.weight;
		this.left = left;
		this.leaf = false;
	}
	/**
	 * Determines if the current node is a leaf
	 * @return	If the current node is a leaf
	 */
	public boolean isLeaf() 
	{ 
		return this.leaf; 
	}
	@Override
	public int compareTo(Node other) 
	{
		return this.weight.compareTo(other.weight);
	}
	@Override
	public String toString() 
	{
		return "" + (this.isLeaf() ? "" + (char) this.character : this.weight);
	}
}

public class HuffmanTree 
{
	public Node root;
	/**
	 * Constructs a HuffmanTree with the passed counts of
	 * characters and the number of occurrences that there are
	 * @param count	An array of integers where the index i indicates
	 * 				the ASCII value of a character and the value
	 * 				at the index indicates the number of occurrences
	 */
	public HuffmanTree(int[] count) 
	{
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		for(int i = 0; i < count.length; i++) 
		{
			int numCharacters = count[i];
			if(numCharacters > 0)
				nodes.add(new Node((char) i, numCharacters));
		}
		nodes.add(new Node((char) 256, 1));
		while(nodes.size() != 1) 
		{
			Node newNode = new Node(null, 0);
			newNode.updateLeft(nodes.poll());
			newNode.updateRight(nodes.poll());
			nodes.add(newNode);
		}
		this.root = nodes.poll();
	}
	/**
	 * Constructor that will reconstruct the tree from a
	 * file. This is used for decoding an encoded message.
	 * @param codeFile		The name of the file we are reading in from
	 * @throws IOException	
	 */
	public HuffmanTree(String codeFile) throws IOException 
	{
		Scanner scan = new Scanner(new File(codeFile));
		this.buildTree(scan);
	}
	/**
	 * Reads from the scanner and instantiates the tree 
	 * using the path and ascii values from the input.
	 * @param scan	The scanner where we are reading in input from
	 */
	private void buildTree(Scanner scan) 
	{
		this.root = new Node(null, 0);
		while(scan.hasNextLine()) 
		{
			int asciiValue = Integer.parseInt(scan.nextLine());
			String[] path = scan.nextLine().split("");
			Node current = this.root;
			for(String choice : path) 
			{
				if(choice.equals("0") && current.left == null) 
					current.updateLeft(new Node(null, 0)); 
				else if(current.right == null)
					current.updateRight(new Node(null, 0));
				current = choice.equals("0") ? current.left : current.right;
			}
			current.updateCurrent((char) asciiValue, 0);
		}
		
	}
	/**
	 * Writes the Huffman encoding tree in a file using 
	 * a standard format to the specified filename
	 * @param filename	The name of the file to output the 
	 * 					decoder of the current HuffmanTree
	 * @throws FileNotFoundException
	 */
	public void write(String filename) throws FileNotFoundException 
	{
		PrintWriter out = new PrintWriter(new File(filename));
		this.recursiveSearch(out, this.root, "");
		out.close();
	}
	/**
	 * Helper method that recursively searches the HuffmanTree searching 
	 * as far left as possible and then printing the current element 
	 * followed by going right.
	 * @param out	The PrintWriter used to output to a file 
	 * @param curr	The current node being searched 
	 * @param path	The current path represented as a string of 1's and 0's
	 */
	private void recursiveSearch(PrintWriter out, Node curr, String path) 
	{
		if(curr != null) 
		{
			this.recursiveSearch(out, curr.left, path+"0");
			if(curr.isLeaf()) 
			{
				out.println((int) curr.character);
				out.println(path);
			}
			this.recursiveSearch(out, curr.right, path+"1");
		}
	}
	/**
	 * Takes the input stream and outputs the decoded message into 
	 * the passed filename of outFile
	 * @param in		The input stream of the decoded message
	 * @param outFile	The name of the file to output to
	 * @throws FileNotFoundException
	 */
	public void decode(BitInputStream in, String outFile) throws FileNotFoundException 
	{
		PrintWriter out = new PrintWriter(new File(outFile));
		int currBit = in.readBit();
		Node current = this.root;
		while(currBit != -1) 
		{
			current = currBit == 0 ? current.left : current.right;
			if(current.isLeaf()) 
			{
				if(current.character != 256) 
				{
					out.print(current.character);
					current = this.root;
				}
				else 
				{
					out.close();
					return;
				}
				
			}
			currBit = in.readBit();
		}
	}
}