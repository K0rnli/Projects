import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Queue<Disk> q = new PriorityQueue<Disk>();
		q.add(new Disk());
		Scanner scan = new Scanner(new File("input20.txt"));
		ArrayList<Integer> a = new ArrayList<Integer>();
		while (scan.hasNextInt())
		{
			int d = scan.nextInt();
			if(q.peek().fit(d))
			{
				q.peek().insert(d);
				q.add(q.poll());
			}
			else
			{
				Disk v = new Disk();
				v.insert(d);
				q.add(v);
			}
			
		}
		Queue<Disk> tempQ = new PriorityQueue<Disk>();
		double s = 0;
		while (!q.isEmpty())
		{
			s += q.peek().total()/1000000.0;
		    tempQ.offer(q.poll());
		}
		System.out.printf("Total size = %.6f GB\nDisks req'd = %d\n\n", s, (tempQ.peek().getCount()-1));
		while (!tempQ.isEmpty())
		    System.out.println(tempQ.poll());
	}
}
