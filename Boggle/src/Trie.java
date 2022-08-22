import java.util.ArrayList;

public class Trie 
{
	public Leaf head;
	public Trie()
	{
		head = new Leaf(' ');
	}
	public void add(String s)
	{
		addHelper(s, head);
	}
	public void addHelper(String s, Leaf l)
	{
		if(s.length() != 0)
		{
			l.add(new Leaf(s.charAt(0)));
			addHelper(s.substring(1), l.getLeaf(s.charAt(0)));
		}
	}
	public boolean search(String s)
	{
		return searchHelper(s, head);
	}
	public boolean searchHelper(String s, Leaf l)
	{
		if(s.length() == 0)
			return true;
		else if(l.getLeaf(s.charAt(0)) != null)
			return searchHelper(s.substring(1), l.getLeaf(s.charAt(0)));
		return false;
	}
	@Override
	public String toString()
	{
		return head.toString();
	}
	public static void main(String args[])
	{
		Trie t = new Trie();
		t.add("ALPHA");
		t.add("ALPGF");
		System.out.println(t);
		System.out.println(t.search("ALFHA"));
	}
}
