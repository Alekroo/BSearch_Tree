public class Testbs
{

	public static void main(String[] args) 
	{
		BSTree bs = new BSTree();

		System.out.println("Adding 10, 20, 5, 6 (removing 5)");
		bs.add(10);
		bs.add(20);
		bs.add(5);
		bs.add(6);
		bs.remove(5);
		System.out.println("Printing all currnet node values: ");
		bs.printTree();	
		System.out.println("\n");

		System.out.println("Find nearest smaller than 8: " + bs.findNearestSmallerThan(8));
		System.out.println();

		System.out.println("Adding 1,3,50,120, 111, 32, 45, 322, 84, 203, 1213, 432 into the tree");
		int[] intArr = {1,3,50,120, 111, 32, 45, 322, 84, 203, 1213, 432};
		bs.addAll(intArr);
		System.out.println("Printing all currnet node values: ");
		bs.printTree();	
		System.out.println("\nSize of binary tree: " + bs.size());
		System.out.println();

		System.out.println("Sorted array: ");
		int[] sortedArr = bs.sortedArray();
		for(int i = 0; i < sortedArr.length; i++)
		{
			System.out.print(sortedArr[i] + " ");
		}
		System.out.println();
		
		System.out.println("Finding all numbers in range of 5 - 20");
		int[] inRangeArr =  bs.findInRange(-50,100);
		for (int i = 0; i < inRangeArr.length; i++) 
		{
			System.out.print(inRangeArr[i] + " ");
		}
		System.out.println();
		}



}