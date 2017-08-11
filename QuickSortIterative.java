import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class QuickSortIterative {

	//number of comparisons
	static int count = 0;	
	public static void main(String args[])
	{		
		int[] inputArray = new int[10000000];
		String fileSortInput="";
		
		//get file name
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
		
		//read input from file to Array
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
		//sort
		sort(inputArray);
		
		System.out.println("No. of Compares: " + count);		
	}
	
	public static void sort(int[] a)
	{
		shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}
	
	//this function sorts the input iteratively using stack
	private static void sort(int[] a, int lo, int hi)
	{	
		int stack[] = new int[hi-lo+1];
		int top = 1;
		//push the hi and lo values into stack
		stack[0] = lo;
		stack[1] = hi;
		
		//while stack not empty
		while (top >= 0)
		{
			//pop the top 2 values
			hi = stack[top--];
			lo = stack[top--];
			if(hi-lo<2)
				continue;
			
			//partition into 2 sub-arrays
			int j = partition(a, lo, hi);
			//push the starts and ends of the two sub arrays into stack
			stack[++top ] = j + 1;
			stack[++top ] = hi;
			stack[++top] = lo;
			stack[++top] = j;
			
		}
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
				if (i >= hi) 
					break;
			}
			while (v<= a[--j]) 
			{
				count++;
				if (j <= lo) break;
			}
			if (i >= j) 
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	//exchange values at indices lo and j
	private static void exch(int[] a, int lo, int j)
	{
		int temp = a[lo];
		a[lo] = a[j];
		a[j] = temp;
	}
	
	//shuffle the input array to make it random
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
