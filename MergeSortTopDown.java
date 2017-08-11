/*
 * This program performs merge sort using recursion top down on input file elements
 * To execute
 	javac MergeSortTopDown.java
	java MergeSortTopDown <file_name>
 */
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MergeSortTopDown {
	//no. of comparisons
	static int count = 0;

	public static void main(String[] args) {
		String fileSortInput="";	
		List<Integer> inputList = new ArrayList<Integer>();
		
		//get input file name
		if(args.length > 0)
		{
			fileSortInput = args[0];
		}
		else
		{
			System.out.println("Please input the file name");
			System.exit(-1);		
		}
		Scanner inFile;		 
		Path filePath;		
		
		//read input elements into List 
		try {
			filePath = Paths.get(fileSortInput);	
			inFile = new Scanner(filePath);		
			while(inFile.hasNext())
			{
				inputList.add(inFile.nextInt());
			}
			inFile.close();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		//auxiliary array for copying input
		List<Integer> aux = new ArrayList<Integer>();
		
		sort(inputList, aux, 0, inputList.size() - 1);
		
		System.out.println("No. of comparisons: "+count);
	}
	
	//merge the sub-arrays
	private static void merge(List<Integer> inputList, List<Integer> aux, int lo, int mid, int hi)
	{
		//copy input to auxiliary array
		for (int k = lo; k <= hi; k++)
			aux.add(inputList.get(k));
		
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++)
		{
			if (i > mid)
				inputList.set(k,aux.get(j++));
			else if (j > hi)			
				inputList.set(k,aux.get(i++));
			//get the smaller of the two elements into inputList
			else if (aux.get(j) < aux.get(i)) 
			{
				inputList.set(k,aux.get(j++));
				count++;
			}
			else
			{
				inputList.set(k , aux.get(i++));
				count++;
			}
		}
	}
	
	
	//this function call sort recursively until hi=lo
	private static void sort(List<Integer> inputList, List<Integer> aux, int lo, int hi)
	{
		if (hi <= lo) return;
		int mid = lo + (hi - lo) / 2;
		sort(inputList, aux, lo, mid);
		sort(inputList, aux, mid+1, hi);
		merge(inputList, aux, lo, mid, hi);
	}
	
	
}
