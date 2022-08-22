import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class BoggleSolver
{
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	private TreeSet<String> dictionary;
	private Trie trie;
	public static long endTime;
	public BoggleSolver(String dictionaryName) throws FileNotFoundException
	{
		dictionary = new TreeSet<String>();
		trie = new Trie();
		Scanner scan = new Scanner(new File(dictionaryName));
		while(scan.hasNext())
		{
			String s = scan.next();
			trie.add(s);
			dictionary.add(s);
		}
		scan.close();
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		Iterable<String> list = new TreeSet<String>();
		long startTime = System.nanoTime();
		for(int x = 0; x < board.cols(); x++)
			for(int y = 0; y < board.rows(); y++)
			{
				//First but slower method
				//getValidWordsHelper(new Cords(x, y), new ArrayList<Cords>(), "", board, (TreeSet<String>)list);
				
				
				//Second faster method with a trie
				getValidWordsHelper(new Cords(x, y), new ArrayList<Cords>(), "", board, (TreeSet<String>)list, trie.head);
			}
		endTime = System.nanoTime() - startTime;
		System.out.println("in seconds : " + endTime / 1000000000.0);
		return list;
	}
	/**
	 * the trie way
	 * @param c
	 * @param a
	 * @param s
	 * @param b
	 * @param list
	 * @param l
	 */
	public void getValidWordsHelper(Cords c, ArrayList<Cords> a, String s, BoggleBoard b, TreeSet<String> list, Leaf l)
	{
		if(c.x > -1 && c.x < b.cols() && c.y > -1 && c.y < b.rows() && !a.contains(c))
		{
			//System.out.println(s + " " + l.letter + " " + b.getLetter(c.y, c.x) + " " + l.s);
			//if((dictionary.ceiling(s) != null && (dictionary.ceiling(s).startsWith(s))))
			if(l.s.contains(b.getLetter(c.y, c.x) + ""))
			{
				if(b.getLetter(c.y, c.x) == 'Q'  && l.getLeaf('Q').getLeaf('U') != null)
				{
					s += "QU";
				}
				else
					s += b.getLetter(c.y, c.x);
				if(dictionary.contains(s) && !list.contains(s))
					list.add(s);
				a.add(c);
				for(int i = 0; i < 9; i++)
					if(i != 4 && b.getLetter(c.y, c.x) == 'Q' && l.getLeaf('Q').getLeaf('U') != null)
						getValidWordsHelper(new Cords((c.x-1) + i%3,(c.y-1) + i/3), a, s, b, list, l.getLeaf('Q').getLeaf('U'));
					else if(i != 4)
						getValidWordsHelper(new Cords((c.x-1) + i%3,(c.y-1) + i/3), a, s, b, list, l.getLeaf(b.getLetter(c.y, c.x)));
				a.remove(c);
			}
		}
	}
	/**
	 * the normal way
	 * @param c
	 * @param a
	 * @param s
	 * @param b
	 * @param list
	 */
	public void getValidWordsHelper(Cords c, ArrayList<Cords> a, String s, BoggleBoard b, TreeSet<String> list)
	{
		if(c.x > -1 && c.x < b.cols() && c.y > -1 && c.y < b.rows() && !a.contains(c))
		{
			//System.out.println(s + " " + l.letter + " " + b.getLetter(c.y, c.x) + " " + l.s);
			if((dictionary.ceiling(s) != null && (dictionary.ceiling(s).startsWith(s))))
			{
				if(b.getLetter(c.y, c.x) == 'Q')
				{
					s += "QU";
				}
				else
					s += b.getLetter(c.y, c.x);
				if(dictionary.contains(s) && !list.contains(s))
					list.add(s);
				a.add(c);
				for(int i = 0; i < 9; i++)
					if(i != 4)
						getValidWordsHelper(new Cords((c.x-1) + i%3,(c.y-1) + i/3), a, s, b, list);
				a.remove(c);
			}
		}
	}
	private class Cords
	{
		int x;
		int y;
		public Cords(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object o) 
		{
			return x == ((Cords)o).x && y == ((Cords)o).y;
		}
		@Override
		public String toString()
		{
			return x + " " + y;
		}
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		switch(word.length())
		{
			case 0:
			case 1:
			case 2: return 0;
			case 3:
			case 4: return 1;
			case 5: return 2;
			case 6: return 3;
			case 7: return 5;
			default : return 11;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		//BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		//BoggleBoard  board  = new BoggleBoard(PATH + "board-points2000.txt");
		BoggleBoard  board  = new BoggleBoard(1000,1000);
		//BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-yawl.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84
		System.out.println("in seconds : " + endTime / 1000000000.0);

		//new BoggleGame(4, 4);
	}

}
