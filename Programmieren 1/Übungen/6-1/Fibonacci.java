
public class Fibonacci {

	public static long fib(int i)
	{
		if(i == 0)
		{
			return 0;
		}
		if(i == 1)
		{
			return 1;
		}
		else
		{
			return fib(i-1) + fib(i-2);
		}
	}
	
	public static void main(String[] args)
	{
		for (int i = 0; i<=50; i++)
		{
			System.out.println(i + "tes Folgenglied: " + fib(i));
		}
	}
}
