import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Word 
{
	private String originalWord, endingWord;
	private Queue<Stack<String>> q;
	private Set<String> set;
	private int length;
	
	/**
	 * Constructs the solver without a word
	 */
	public Word()
	{
		originalWord = "";
		q = new LinkedList<Stack<String>>();
	}
	/**
	 * Constructs the solver with the beginning word and ending word with a desired length of the ladder
	 * @param word  the starting word
	 * @param end  the ending word
	 * @param length  the desired ladder length
	 * @throws FileNotFoundException
	 */
	public Word(String word, String end, int length) throws FileNotFoundException
	{
		originalWord = word;
		endingWord = end;
		int l = word.length();
		q = new LinkedList<Stack<String>>();
		Stack<String>temp = new Stack<String>();
		temp.push(word);
		q.offer(temp);
		Scanner scan = new Scanner(new File("dictionary.txt"));
		set = new HashSet<String>();
		while(scan.hasNextLine())
		{
			String t = scan.nextLine().toLowerCase();
			if(t.length() == l)
				set.add(t);
		}
		scan.close();
		this.length = length;
	}
	/**
	 * Constructs the solver with the beginning word and ending word with no desired ladder length
	 * @param word  the starting word
	 * @param end  the ending word
	 * @throws FileNotFoundException
	 */
	public Word(String word, String end) throws FileNotFoundException
	{
		originalWord = word;
		endingWord = end;
		int l = word.length();
		q = new LinkedList<Stack<String>>();
		Stack<String>temp = new Stack<String>();
		temp.push(word);
		q.offer(temp);
		Scanner scan = new Scanner(new File("dictionary.txt"));
		set = new HashSet<String>();
		while(scan.hasNextLine())
		{
			String t = scan.nextLine().toLowerCase();
			if(t.length() == l)
				set.add(t);
		}
		scan.close();
		this.length = -1;
	}
	/**
	 * checks if string s has only one character difference than string t
	 * @param s  current word
	 * @param t  potential word
	 * @param f  how many differences
	 * @return
	 */
	private boolean potentialPath(String s, String t, int f)
	{
		if(s.length() == 0)
			return true;
		else if(s.substring(0,1).equalsIgnoreCase(t.substring(0,1)))
		{
			return potentialPath(s.substring(1, s.length()), t.substring(1, t.length()), f);
		}
		else
		{
			if(f == 1)
				return false;
			else
				return potentialPath(s.substring(1, s.length()), t.substring(1, t.length()), f+1);
		}
	}
	/**
	 * resets the queue stack
	 */
	public void reset()
	{
		q.clear();
		Stack<String> temp = new Stack<String>();
		temp.add(originalWord);
		q.add(temp);
	}
	@SuppressWarnings("unchecked")
	/**
	 * Solves all possible ladders of a given length
	 * @param s  a stack
	 */
	public void solveAll(Stack<String> s)
	{
		if(s.peek().equalsIgnoreCase(endingWord) && s.size() == length)
		{
			System.out.println("Found a ladder! >>> " + s);
		}
		else
		{
			Iterator<String> i=set.iterator();
			if(s.size() <= length - 1)
				while(i.hasNext())
				{
					String w = i.next();
					if(!s.contains(w) && potentialPath(s.peek(), w, 0))
					{
						Stack<String> v = (Stack<String>)s.clone();
						v.push(w);
						solveAll(v);
					}
				}
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Solves for the quickest and shortest ladder
	 */
	public void solveFast()
	{
		Set<String> tempSet = new HashSet<String>();
		tempSet.addAll(set);
		boolean a = false;
		while(!a)
		{
			if(q.size() != 0 && set.contains(originalWord) && set.contains(endingWord))
			{
				Stack<String> s = q.poll();
				if(s.peek().equalsIgnoreCase(endingWord))
				{
					System.out.println("Found a ladder! >>> " + s);
					a = true;
				}
				else
				{
					Iterator<String> i = tempSet.iterator();
					while(i.hasNext())
					{
						String w = i.next();
						if(!s.contains(w) && potentialPath(s.peek(), w, 0))
						{
							i.remove();
							Stack<String> v = (Stack<String>)s.clone();
							v.push(w);
							q.offer(v);
						}
					}
				}
			}
			else
			{
				System.out.println("No ladder between " + originalWord + " and " + endingWord);
				a = true;
			}
		}
	}
	/**
	 * starts solving whether it is given a length or none
	 */
	public void start()
	{
		reset();
		if(length != -1)
			solveAll(q.poll());
		else
			solveFast();
	}
}
