/*
 * This program performs merge sort bottom up on input file elements
 * To execute
 	javac MergeSortBottomUp.java
	java MergeSortBottomUp <file_name>
 */
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergeSortBottomUp {

	static int count = 0;

	public static void main(String[] args) {
		String fileSortInput="";	
		List<Integer> inputList = new ArrayList<Integer>();
		
		//get name of input file
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
		
		//read input elements from file into List
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
		//sort
		sort(inputList);		
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
	

	//this function call merge iteratively to sort the subarrays
	public static void sort(List<Integer> a)
	{
		int N = a.size();
		List<Integer> aux = new ArrayList<Integer>();
		//increase size of sub-array iteratively
		for (int sz = 1; sz < N; sz = sz+sz)
			for (int lo = 0; lo < N-sz; lo += sz+sz)
				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
	}

}
