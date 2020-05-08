
public class MergeSort {

	public static void sort(Comparable[] array)
	{
		Comparable sorted[] = new Comparable[array.length];
		sorted = mergeSort(array);
		for(int i = 0; i<array.length; i++)
		{
			array[i] = sorted[i];
		}
	}
	
	private static Comparable[] mergeSort(Comparable[] array)
	{
		Comparable[] one, two;
		if(array.length <= 1)
		{
			return array;
		}
		else
		{
			if(array.length % 2 == 0)
			{
				one = new Comparable[array.length/2];
				two = new Comparable[array.length/2];
			}
			else
			{
				one = new Comparable[array.length/2];
				two = new Comparable[array.length/2 + 1];
			}
			
			for(int i = 0; i < one.length; i++)
			{
				one[i] = array[i];
			}
			
			for(int i = 0; i < two.length; i++)
			{
				two[i] = array[i + array.length/2];
			}
			
			one = mergeSort(one);
			two = mergeSort(two);
			return merge(one, two);
		}	
	}
	
	private static Comparable[] merge(Comparable[] one, Comparable[] two)
	{
		Comparable[] result = new Comparable[(one.length + two.length)];
		int i = 0;
		int j = 0;
		int k = 0;
		
		while(k < (one.length + two.length))
		{
			while(i < one.length && j < two.length && one[i].compareTo(two[j]) <= 0)
			{
				result[k] = one[i];
				k++;
				i++;
			}
			
			while(i < one.length && j < two.length && one[i].compareTo(two[j]) > 0)
			{
				result[k] = two[j];
				k++;
				j++;
			}
		
			while(i < one.length && j >= two.length)
			{
				result[k] = one[i];
				k++;
				i++;
			}
			
			while(i >= one.length && j < two.length)
			{
				result[k] = two[j];
				k++;
				j++;
			}
		}
		return result;
	}
}
