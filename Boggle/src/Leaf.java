public class Leaf
	{
		char letter;
		String s;
		Leaf[] children;
		public Leaf(char c)
		{
			letter = c;
			s = "";
			children = new Leaf[26];
		}
		public boolean add(Leaf l)
		{
			if(children[((int)l.letter)-65] == null)
			{
				children[((int)l.letter)-65] = l;
				s += l.letter + "";
				return true;
			}
			return false;
				
		}
		public Leaf getLeaf(char c)
		{
			return children[(int)c-65];
		}
		@Override
		public String toString()
		{
			String r = letter + " = { ";
			for(char c : s.toCharArray())
				r += getLeaf(c) + ", ";
			r += "}";
			return r;
		}
	}