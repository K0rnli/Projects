import java.util.*;

public class FibonacciApp
{
    public static void main(String[] args)
    {
        FibonacciTester f = new FibonacciTester();
        Scanner keyboard = new Scanner(System.in);
           
        System.out.print("Enter number -->");
        int n = keyboard.nextInt();
        int result = f.fibonacci(n);
        System.out.println("Fibonacci("+n+") = " + result);
    }
}

class FibonacciTester
{
    public int fibonacci(int n)
    {
    	//Adds the 2 previous numbers that are not the first 2 numbers in the fibonacci series
    	//The first 2 integers in the fibonacci series are set to 1
        if(n>2)
        	return fibonacci(n-1) + fibonacci(n-2);
        else
        	return 1;
    }
}