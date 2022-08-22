import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody 
{
	Queue<Note> notes;
	/**
	 * Constructs the melody
	 * @param song  the song
	 */
	public Melody(Queue<Note> song)
	{
		notes = song;
	}
	/**
	 * calculates the duration of the song
	 * @return  the duration of the song
	 */
	public double getTotalDuration()
	{
		Queue<Note> tempSong = getNotes();
		double totalTime = 0;
		boolean repeated = false;
		while(!tempSong.isEmpty())
		{
			if(tempSong.peek().isRepeat())
			{
				repeated = !repeated;
				totalTime += tempSong.poll().getDuration() * 2;
			}
			else if(repeated)
				totalTime += tempSong.poll().getDuration() * 2;
			else
				totalTime += tempSong.poll().getDuration();
				
		}
		return totalTime;
	}
	/**
	 * The toString
	 * @return the toString
	 */
	@Override
	public String toString()
	{
		Queue<Note> tempSong = getNotes();
		String ret = "";
		while(!tempSong.isEmpty())
		{
			ret = ret + tempSong.poll() + "\n";
		}
		return ret;
	}
	
	/**
	 * changes the tempo of the melody
	 * @param tempo  the new tempo
	 */
	public void changeTempo(double tempo)
	{
		int size = notes.size();
		for(int i = 0; i < size; i++)
		{
			Note temp = notes.poll();
			temp.setDuration(temp.getDuration()* tempo);
			notes.offer(temp);
		}		
	}
	
	/**
	 * reverses the order of the melody
	 */
	public void reverse()
	{
		Queue<Note> tempSong = new LinkedList<Note>();
		Stack<Note> tempStack = new Stack<Note>();
		while(!notes.isEmpty())
			tempStack.push(notes.poll());
		while(!tempStack.isEmpty())
			tempSong.offer(tempStack.pop());
		
		notes = tempSong;
	}
	/**
	 * gets all of the notes in the song
	 * @return a queue of the notes in the song
	 */
	public Queue<Note> getNotes()
	{
		Queue<Note> tempSong = new LinkedList<Note>();
		int size = notes.size();
		for(int i = 0; i < size; i++)
		{
			Note n = notes.poll();
			tempSong.offer(n);
			notes.offer(n);
		}
		return tempSong;
	}
	/**
	 * adds another melody to the end of this melody
	 * @param other  the other melody
	 */
	public void append(Melody other)
	{
		Queue<Note> tempSong = other.getNotes();
		while(!tempSong.isEmpty())
			notes.offer(tempSong.poll());
	}
	/**
	 * pkays the song
	 */
	public void play()
	{
		Queue<Note> tempSong = getNotes();
		Queue<Note> dupe = new LinkedList<Note>();
		boolean repeat = false;
		while(!tempSong.isEmpty())
		{
			if(tempSong.peek().isRepeat() && !repeat)
			{
				repeat = true;
				dupe.offer(tempSong.peek());
				tempSong.poll().play();
			}
			else if(tempSong.peek().isRepeat() && repeat)
			{
				repeat = false;
				dupe.offer(tempSong.peek());
				tempSong.poll().play();
				while(!dupe.isEmpty())
					dupe.poll().play();
			}
			else if(repeat)
			{
				dupe.offer(tempSong.peek());
				tempSong.poll().play();
			}
			else
				tempSong.poll().play();
		}
	}
}
