import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordChain 
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner scan = new Scanner(new File("input.txt"));
		while(scan.hasNextLine())
		{
			
			Word app = new Word(scan.next(), scan.next());
			//Can find all possible solutions of a given length if the next constructor is uncommented and the previous one is comment out
			//Word app = new Word(scan.next(), scan.next(), 6);
			app.start();
		}
		scan.close();
	}
}
