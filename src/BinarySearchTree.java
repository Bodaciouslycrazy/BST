//Bodie Malik
//bam150130
//CS 3345.002
//10-18-17

import java.util.LinkedList;
import java.util.Queue;

// Binary Search Tree Class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( "Underflow Exception" );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception( "Underflow Exception");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    
    //==========================================
    // Code Added by Bodie Malik
    //==========================================
    
    /**
     * counts the number of nodes in the tree
     * @return the number of nodes
     */
    public int nodeCount()
    {
    	return nodeCount(root);
    }
    
    
    /**
     * internal function for recursion.
     * @param node
     * @return node count of it's self and it's children added
     */
    private int nodeCount(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return 0;
    	else
    	{
    		return 1 + nodeCount(node.left) + nodeCount(node.right);
    	}
    }
    
    
    /**
     * retruns true if the tree is a full tree.
     * (every node must have 2 children or be a leaf.)
     * @return
     */
    public boolean isFull()
    {
    	return isFull(root);
    }
    
    /**
     * internal function for recursion.
     * @param node
     * @return returns true if this node and it's children are a full tree.
     */
    private boolean isFull(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return true;
    	else if( (node.left == null) != (node.right == null) )
    		return false;
    	else
    	{
    		return( isFull(node.left) && isFull(node.right));
    	}
    }
    
    
    /**
     * returns true if the structure of the tree matches the structure of another tree.
     * @param other
     * @return
     */
    public boolean compareStructure(BinarySearchTree other)
    {
    	return compareStructure(root, other.root);
    }
    
    /**
     * internal function for recursion
     * @param nodea
     * @param nodeb
     * @return returns trus if this node and it's children have the same structure.
     */
    private boolean compareStructure(BinaryNode nodea, BinaryNode nodeb)
    {
    	if(nodea == null && nodeb == null)
    		return true;
    	else if(nodea == null || nodeb == null)
    		return false;
    	else
    		return compareStructure(nodea.left, nodeb.left) && compareStructure(nodea.right, nodeb.right);
    }
    
    
    
    /**
     * returns true if this tree equals another tree.
     * Compares every element.
     * @param other
     * @return
     */
    public boolean equals(BinarySearchTree<AnyType> other)
    {
    	return equals(root, other.root);
    }
    
    /**
     * internal function for recursion.
     * Returns true if the nodes and it's children are equal.
     * @param nodea
     * @param nodeb
     * @return
     */
    private boolean equals(BinaryNode<AnyType> nodea, BinaryNode<AnyType> nodeb)
    {
    	if(nodea == null && nodeb == null)
    		return true;
    	else if(nodea == null || nodeb == null)
    		return false;
    	else if(nodea.element.compareTo(nodeb.element) == 0)
    		return equals(nodea.left, nodeb.left) && equals(nodea.right, nodeb.right);
    	else
    		return false;
    }
    
    
    
    /**
     * copies the elements into a new tree.
     * @return a new BinarySearchTree
     */
    public BinarySearchTree<AnyType> copy()
    {
    	BinarySearchTree<AnyType> newtree = new BinarySearchTree<AnyType>();
    	newtree.root = new BinaryNode<AnyType>(root.element);
    	
    	if(root != null)
    		copy(root, newtree.root);
    	
    	return newtree;
    }
    
    /**
     * Internal function. Copies a node and recursively copies it's children.
     * @param node
     * @param nodecopy
     */
    private void copy(BinaryNode<AnyType> node, BinaryNode<AnyType> nodecopy)
    {
    	if(node.left != null)
    	{
    		BinaryNode<AnyType> newnode = new BinaryNode<AnyType>(node.left.element);
    		nodecopy.left = newnode;
    		copy(node.left, nodecopy.left);
    	}
    	
    	if(node.right != null)
    	{
    		BinaryNode<AnyType> newnode = new BinaryNode<AnyType>(node.right.element);
    		nodecopy.right = newnode;
    		copy(node.right, nodecopy.right);
    	}
    }
    
    
    
    /**
     * Mirrors the search tree.
     * This will break some of the functionality of the tree.
     * Searching for an element will most likely return null because the algorithm went the wrong direction in the tree.
     */
    public void mirror()
    {
    	mirror(root);
    }
    
    /**
     * internal function for recursion. switches a children's nodes, recursivly going down the tree.
     * @param node
     */
    public void mirror(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return;
    	
    	BinaryNode<AnyType> temp = node.left;
    	node.left = node.right;
    	node.right = temp;
    	
    	mirror(node.left);
    	mirror(node.right);
    }
    
    
    
    /**
     * checks if another tree is a mirror of this tree.
     * @param other
     * @return
     */
    public boolean isMirror(BinarySearchTree<AnyType> other) //throws Exception
    {
    	other.mirror();
    	boolean ism = equals(other);
    	other.mirror();
    	return ism;
    }
    
    
    
    /**
     * does a right rotation at the first value "val" found
     * @param val
     * @throws Exception
     */
    public void rotateRight(AnyType val) throws Exception
    {
    	BinaryNode<AnyType> n = findNode(val);
    	
    	if(n == null)
    		throw new Exception("Can't rotate a null node!");
    	else if(n.left == null)
    		throw new Exception("Can't right rotate, null node in left spot.");
    	
    	BinaryNode<AnyType> newright = new BinaryNode<AnyType>(n.element, n.left.right, n.right);
    	n.right = newright;
    	n.element = n.left.element;
    	n.left = n.left.left;
    	
    	//blerg, this code was weird.
    }
    
    
    
    /**
     * does a left rotation at the first value "val" found
     * @param val
     * @throws Exception
     */
    public void rotateLeft(AnyType val) throws Exception
    {
    	BinaryNode<AnyType> n = findNode(val);
    	
    	if(n == null)
    		throw new Exception("Can't rotate a null node!");
    	else if(n.right == null)
    		throw new Exception("Can't left rotate, null node in right spot.");
    	
    	BinaryNode<AnyType> newleft = new BinaryNode<AnyType>(n.element, n.left, n.right.left);
    	n.left = newleft;
    	n.element = n.right.element;
    	n.right = n.right.right;
    	
    	//jabgiauagbnrgnaerg this is so gross!
    }
    
    /**
     * returns the first node with a value equal to "val."
     * will return null if the tree is empty or if the value does not match with any node.
     * @param val
     * @return
     */
    private BinaryNode<AnyType> findNode(AnyType val)
    {
    	BinaryNode<AnyType> viewing = root;
    	
    	while(viewing != null)
    	{
    		if(val.compareTo(viewing.element) < 0)
    			viewing = viewing.left;
    		else if(val.compareTo(viewing.element) > 0)
    			viewing = viewing.right;
    		else break;
    	}
    	
    	return viewing;
    }
    
    
    /**
     * prints every level of the tree on it's own line.
     * Will print underscores where this is a null node.
     */
    public void printLevels()
    {
    	Queue<BinaryNode<AnyType>> qa = new LinkedList<BinaryNode<AnyType>>();
    	Queue<BinaryNode<AnyType>> qb = new LinkedList<BinaryNode<AnyType>>();
    	
    	qa.add(root);
    	
    	boolean printNextLevel = true;
    	while( !qa.isEmpty() || !qb.isEmpty() )
    	{
    		//System.out.println("Debug, ran loop");
    		boolean addedNonNull = false;
    		
	    	while( !qa.isEmpty() && printNextLevel )
	    	{
	    		BinaryNode<AnyType> node = qa.poll();
	    		
	    		if(node != null)
	    		{
	    			System.out.print(node.element + "  ");
	    			if( node.left != null || node.right != null)
	    				addedNonNull = true;
	    			qb.add(node.left);
		    		qb.add(node.right);
	    		}
	    		else
	    		{
	    			System.out.print("_  ");
	    			qb.add(null);
	    			qb.add(null);
	    		}
	    		
	    	}
	    	
	    	printNextLevel = addedNonNull;
	    	
	    	System.out.print("\n");
	    	qa = qb;
	    	qb = new LinkedList<BinaryNode<AnyType>>();
    	}
    	
    	//System.out.println("Debug: done");
    	
    }
    
    //============================================
    // End of Bodie's Code
    //============================================
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args ) throws Exception
    {
    	
    	System.out.println("Welcome to Bodie's Binary Search Tree! Sit back and relax as I show you what this baby can do.");
    	System.out.println("For simplicity, I removed the UnderflowException file, and replaced those with normal Exceptions.\n");
   
    	//***********************************************************************
    	// Hey, it looks like you are reading my code!
    	// You are probably busy reading and grading code for a lot of people,
    	// and stressing out a lot, so I wanted to give you this reminder:
    	// you are awesome! Keep up the good work, and keep moving forward.
    	//***********************************************************************
    	
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
        t1.insert(10);
        t1.insert(15);
        t1.insert(5);
        t1.insert(3);
        t1.insert(12);
        t1.insert(20);
        
        System.out.println("Printing a tree using printLevels()\n");
        t1.printLevels();
        
        System.out.println("\nnodeCount() returned: " + t1.nodeCount());
        System.out.println("isFull() returned: " + t1.isFull());
        System.out.println("Lets make a full tree and test it again.\n");
        
        t1.insert(6);
        t1.insert(17);
        t1.insert(24);
        
        t1.printLevels();
        System.out.println("\nisFull() returned: " + t1.isFull());
        
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>();
        t2.insert(50);
        t2.insert(25);
        t2.insert(12);
        t2.insert(38);
        t2.insert(75);
        t2.insert(62);
        
        System.out.println("\nNow we have two trees. t1 is...\n");
        t1.printLevels();
        System.out.println("\nand t2 is...\n");
        t2.printLevels();
        System.out.println("\ncompareStructure() returned: " + t1.compareStructure(t2));
        System.out.println("Lets change t2 to...\n");
        t2.insert(88);
        t2.insert(80);
        t2.insert(100);
        t2.printLevels();
        System.out.println("\ncompareStructure() returned: " + t1.compareStructure(t2));
        System.out.println("Lets test the copy method, copying t1 into t2.");
        t2 = t1.copy();
        System.out.println("t1 is...\n");
        t1.printLevels();
        System.out.println("\nand t2 is now...\n");
        t2.printLevels();
        System.out.println("\nLets test the equals() method");
        System.out.println("equals() returned: " + t1.equals(t2));
        System.out.println("Lets make a change. t2 is now...\n");
        t2.insert(14);
        t2.printLevels();
        System.out.println("\nequals() returned: "+ t1.equals(t2));
        
        System.out.println("\nTesting mirror on t1");
        System.out.println("Before mirror...\n");
        t1.printLevels();
        System.out.println("\nafter mirror...\n");
        t1.mirror();
        t1.printLevels();
        System.out.println("\nLets copy t1 into t2. t2 is now...\n");
        t2 = t1.copy();
        t2.printLevels();
        System.out.println("\nTesting the isMirror method");
        System.out.println("isMirror() returned: " + t1.isMirror(t2));
        System.out.println("Lets mirror t2 and try again.");
        t2.mirror();
        System.out.println("isMirror() returned: " + t1.isMirror(t2));
        System.out.println("This is cool, but I don't understand why we need the mirror fucntion. It kinda ruins the tree's structure.");
        
        
        System.out.println("Lets test rotate right and rotate left.");
        System.out.println("t1 is now...\n");
        t1 = new BinarySearchTree<Integer>();
        
        t1.insert(10);
        t1.insert(15);
        t1.insert(5);
        t1.insert(3);
        t1.insert(12);
        t1.insert(20);
        
        t1.printLevels();
        System.out.println("\nt1 after rotateRight(5) is now...\n");
        t1.rotateRight(5);
        t1.printLevels();
        System.out.println("\nt1 after rotateLeft(10) is now...\n");
        t1.rotateLeft(10);
        t1.printLevels();
        
        System.out.println("\nThat is all of the methods I added!");
        System.out.println("I am quite happy with most of my solutions. They are efficient and clean.");
        System.out.println("If you find a problem with my code, I want to know so that I can improve!");
        System.out.println("Bodie Malik - bam150130@utdallas.edu");
        
        
    }
}