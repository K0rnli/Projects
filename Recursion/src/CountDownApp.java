import java.util.*;

public class CountDownApp
{
    public static void main(String[] args)
    {
        CountDownTester cd = new CountDownTester();
        Scanner keyboard = new Scanner(System.in);
            
        System.out.print("Enter starting value --> ");
        int s = keyboard.nextInt();
            
        System.out.println(cd.countDown(s));
    }
}

class CountDownTester
{
    public int countDown(int n)
    {
    	//Prints current number
    	System.out.println(n);
    	if(n>2)
    	{
    		//Moves to the next number
    		countDown(n-1);
    	}
    	//Returns the final number
    	return 1;
    }
}