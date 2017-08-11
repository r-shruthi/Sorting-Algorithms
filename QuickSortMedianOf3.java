import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuickSortMedianOf3 {

	static int count = 0;
			
	public static void main(String args[])
	{
		List<Integer> inputList = new ArrayList<Integer>();
		
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
				inputList.add(inFile.nextInt());
				i++;
			}
			inFile.close();
		}
		
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//shuffle(inputList);
		long start = System.currentTimeMillis();
		sort(inputList);
		long now = System.currentTimeMillis();
		double timeElapsed = (now - start) / 1000.0;
		System.out.println("No. of Compares: " + count);	
		System.out.println("Time elapsed in ms:"+timeElapsed);
	}
	
	public static void sort(List<Integer> a)
	{
		//shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.size() - 1);
	}
	
	private static void sort(List<Integer> a, int lo, int hi)
	{
		if (hi <= lo) return;
		int j = partition(a, lo, hi); 
		sort(a, lo, j-1); // Sort left part a[lo .. j-1].
		sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
	}
	
	private static int partition(List<Integer> a, int lo, int hi)
	{ 
		//get median of 3 lo, hi, mid and 
		//swap median with a[lo]
		int mid = (lo + hi)/2;
		if (a.get(lo) > a.get(mid)) {
			  if (a.get(mid) > a.get(hi)) {
			    exch(a,lo,mid);
			  } else if (a.get(lo) > a.get(hi)) {
				  exch(a,lo,hi);
			  } 
			} 
		else {
			  if (a.get(mid) < a.get(hi)) {
				  exch(a,lo,mid);
			  } else if (a.get(lo) < a.get(hi)) {
				  exch(a,lo,hi);
			  } 
			}
		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi+1; // left and right scan indices
		int v = a.get(lo); // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (a.get(++i) <= v)
			{
				count++;
				if (i == hi) 
					break;
			}
			while (v<= a.get(--j)) 
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
	
	private static void exch(List<Integer> a, int lo, int j)
	{
		int temp = a.get(lo);
		a.set(lo, a.get(j));
		a.set(j, temp);
	}
	
	public static void shuffle(List<Integer> a)
	{
		int N = a.size();
		for (int i = 0; i < N; i++)
		{ // Exchange a[i] with random element in a[i..N-1]
			Random rand = new Random();			
			int r = i + rand.nextInt(N-i);
			int temp = a.get(i);
			a.set(i, a.get(r));
			a.set(r, temp);
		}
	}
}