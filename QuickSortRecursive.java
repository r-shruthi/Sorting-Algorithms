import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class QuickSortRecursive {	
	static int count = 0;
	
	public static void main(String args[])
	{		
		int[] inputArray = new int[10000000];
		String fileSortInput="";	
		if(args.length > 0)
		{
			fileSortInput = args[0];
		}
		else
		{
			System.out.println("Please input the input file name");
			System.exit(-1);		
		}
		Scanner inFile;		 
		Path filePath;		
		
		try {
			filePath = Paths.get(fileSortInput);	
			inFile = new Scanner(filePath);	
			int i=0;
			while(inFile.hasNext())
			{
				inputArray[i] = inFile.nextInt();
				i++;
			}
			inFile.close();
		}
		
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		sort(inputArray);
		System.out.println("No. of Compares: " + count);		
	}
	
	public static void sort(int[] a)
	{
		shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(int[] a, int lo, int hi)
	{
		if (hi <= lo) return;
		int j = partition(a, lo, hi); 
		sort(a, lo, j-1); // Sort left part a[lo .. j-1].
		sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
	}
	
	private static int partition(int[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi+1; // left and right scan indices
		int v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (a[++i] <= v)
			{
				count++;
				if (i == hi) 
					break;
			}
			while (v<= a[--j]) 
			{
				count++;
				if (j == lo) break;
			}
			if (i >= j) 
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	private static void exch(int[] a, int lo, int j)
	{
		int temp = a[lo];
		a[lo] = a[j];
		a[j] = temp;
	}
	
	public static void shuffle(int[] a)
	{
		int N = a.length;
		for (int i = 0; i < N; i++)
		{ // Exchange a[i] with random element in a[i..N-1]
			Random rand = new Random();			
			int r = i + rand.nextInt(N-i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
}