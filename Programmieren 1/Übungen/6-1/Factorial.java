
public class Factorial {
	
	public static long factorial(int n)
	{
		if(n == 1)
		{
			return 1;
		}
		else
		{
			return n * factorial(n-1);
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			int fac = Integer.parseInt(args[0]);
			System.out.println(factorial(fac));
		}
		catch(java.lang.NumberFormatException e)
		{
			System.out.println("Invalid input.");
		}
	}
}
