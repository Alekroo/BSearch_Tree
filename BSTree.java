import java.util.Arrays;

//Oblig 1 IN2010
class BSTree implements BSTOper
{
	private Node root;

	public BSTree()
	{
		root = null;
	}

	private class Node
	{
		Node left, right; // verdier i venstre subtre er < verdien i noden selv
						  // verdier i høyre subtre er > verdien i noden selv	
		int value;

		Node(int v)
		{
			value = v;
			left = null;
			right = null;
		}
	}

	public void add(int value) //legger til en node med verdi value
	{
		root = add(value, root);
	}

	private Node add(int value, Node n)	//plasserer node med verdi value på riktig sted
	{
		if(n == null)
		{
			return new Node(value);
		}
		else if(value < n.value)
		{
			n.left = add(value, n.left);
		}
		else if(value > n.value)
		{
			n.right = add(value, n.right);	
		}
		return n;
	}

	public boolean remove(int value)	//fjerner noden med verdi value
	{
		if(root == null)
		{
			return false;
		}
		root = remove(value, root);
		return true;
	}	

	private Node remove(int value, Node n)	//fjerner noden med verdi value og i noen tilfeller finner en erstatning for den.
	{
		if(n == null)
		{
			return n;
		}
		else if(value < n.value)
		{
			n.left = remove(value, n.left);
		}
		else if(value > n.value)
		{
			n.right = remove(value, n.right);	
		}	
		else if(value == n.value)
		{
			if(n.left != null && root.right != null)
			{
				Node tmp = root;
				Node currentMin = lowestValueNode(tmp.right);
				root.value = currentMin.value;
				remove(currentMin.value,n.right);
			}
			else if(n.left == null)
			{
				return n.right;
			}
			else if(n.right == null)
			{
				return n.left;
			}
		}	
		return n;
	}

	private Node lowestValueNode(Node n) //finner noden med lavest verdi
	{
		if(n.left == null)
		{
			return n;
		}
		else
		{
			return lowestValueNode(n.left);
		}
	}

	public int size()	//returnerer størrelsen på treet
	{
		return size(root);
	}

	private int size(Node n)
	{
		if(n == null)
		{
			return 0;
		}
		return 1 + size(n.left) + size(n.right);
	}

	public boolean existsInTree(int value) //returnerer true hvis value finnes i treet, false hvis ikke
	{
		if(root == null)
		{
			return false;
		}
		Node n = searchTree(value, root);
		if(n == null){return false;}
		return true;
	}

	private Node searchTree(int value, Node tree)	//søker etter verdien i treet
	{
		Node pointer = null;
		if(tree == null)
			pointer = null;
		else if(value < tree.value)
			pointer = searchTree(value, tree.left);
		else if(value > tree.value)
			pointer = searchTree(value, tree.right);
		else if(value == tree.value)
			pointer = tree;
		return pointer;
	}

	public int findNearestSmallerThan(int value) //finner nærmeste mindre tallet til value
	{
		if(root == null)
		{
			return -1;
		}
		int[] nodeArray = new int[size()];
		putInArray(0, nodeArray, root);
		int currentNearestSmallerThan = 0;

		for(int i = 0; i < nodeArray.length; i++)
		{
			if(nodeArray[i] <= value && nodeArray[i] > currentNearestSmallerThan)
			{
				currentNearestSmallerThan = nodeArray[i];
			}
		}

		return currentNearestSmallerThan;
	}

	public void addAll(int[] integers) //legger alle elementene i integers inn i det binære søketreet.
	{
		int arraySize = integers.length;
		for(int i = 0; i < arraySize; i++)
		{
			add(integers[i]);
		}
	}

	public int[] sortedArray()  // returnerer en heltallsarray med verdiene i treet i stigende orden (sortert).
	{
		if(root == null)
		{
			return null;
		}
		int[] nodeArray = new int[size()];
		putInArray(0,nodeArray,root);
		Arrays.sort(nodeArray);

		return nodeArray; 
	}

	private int putInArray(int index, int[] array, Node n) //legger noder i en array
	{
		if(n.left != null)
		{
			index = putInArray(index, array, n.left);
		}
		if(n.right != null)
		{
			index = putInArray(index, array, n.right);
		}

		array[index] = n.value;

		return index + 1; 
	}

    public int[] findInRange (int low, int high) // finne alle verdier mellom low og high i treet ditt
    {
    	if(root == null)
    	{
    		return null;
    	}
    	int[] mySortedArray = sortedArray();
    	int index = 0;
    	int newArraySize = 0;

    	for(int i = 0; i < mySortedArray.length; i++)
    	{
    		if(mySortedArray[i] > low && mySortedArray[i] < high)
    		{
    			newArraySize++;
    		}
    	}
    	int[] rangeArray = new int[newArraySize];
    	index = 0;

    	for(int i =0; i < mySortedArray.length; i++)
    	{
    		if(mySortedArray[i] > low && mySortedArray[i] < high)
    		{
    			rangeArray[index] = mySortedArray[i];
    			index++;
    		}
    	}
    	return rangeArray;
    }

	public void printTree()	//skriver ut alle nodenes verdi 
	{
		printTree(root);
	}

	private Node printTree(Node n)	//går gjennom hver node og skriver ut deres verdier
	{
		Node p = n;
		Node x = null;
		if(p == null)
		{
			return null;
		}
		System.out.print(p.value + " ");
		if(p.left != null)
		{
			x = printTree(p.left);
		}
		if(p.right != null)
		{
			x = printTree(p.right);
		}
		return x;
	}

	private Node findParent(Node n)	//finner foreldre noden til n
	{ 
		Node parent = findParent(root,n);
		return parent;
	}

	private Node findParent(Node n, Node nodeImAfter)	//finner foreldre noden
	{
		Node p = null;

		if(n.left != null)
		{
			if(n.left == nodeImAfter)
			{
				return n;
			}
			p = findParent(n.left, nodeImAfter);	
		}
		if(n.right != null)
		{
			if(n.right == nodeImAfter)
			{
				return n;
			}	
			p = findParent(n.right, nodeImAfter);	
		}
		return p;
	}

	private Node findGrandparent(Node n)	//returnerer besteforeldre noden til n
	{ 
		Node parent = findParent(n);
		Node grandParent = findParent(parent);

		return grandParent; 
	}

	private Node find(int value)	//søker etter node med verdi value
	{ 
		if(root == null)
		{
			return null;
		}
		Node foundNode = searchTree(value, root);
		return foundNode;
	}

}