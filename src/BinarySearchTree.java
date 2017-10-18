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
    
    //Just a helper function.
    public int nodeCount()
    {
    	return nodeCountRecursive(root);
    }
    
    
    //recursively searches through tree. Adds one at every node.
    private int nodeCountRecursive(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return 0;
    	else
    	{
    		return 1 + nodeCountRecursive(node.left) + nodeCountRecursive(node.right);
    	}
    }
    
    
    
    public boolean isFull()
    {
    	return isFullRecursive(root);
    }
    
    private boolean isFullRecursive(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return true;
    	else if( (node.left == null) != (node.right == null) )
    		return false;
    	else
    	{
    		return( isFullRecursive(node.left) && isFullRecursive(node.right));
    	}
    }
    
    
    
    public boolean compareStructure(BinarySearchTree other)
    {
    	return compStrucRecursive(root, other.root);
    }
    
    private boolean compStrucRecursive(BinaryNode nodea, BinaryNode nodeb)
    {
    	if(nodea == null && nodeb == null)
    		return true;
    	else if(nodea == null || nodeb == null)
    		return false;
    	else
    		return compStrucRecursive(nodea.left, nodeb.left) && compStrucRecursive(nodea.right, nodeb.right);
    }
    
    
    
    public boolean equals(BinarySearchTree<AnyType> other)
    {
    	return equalsRecursive(root, other.root);
    }
    
    private boolean equalsRecursive(BinaryNode<AnyType> nodea, BinaryNode<AnyType> nodeb)
    {
    	if(nodea == null && nodeb == null)
    		return true;
    	else if(nodea == null || nodeb == null)
    		return false;
    	else if(nodea.element == nodeb.element)
    		return equalsRecursive(nodea.left, nodeb.left) && equalsRecursive(nodea.right, nodeb.right);
    	else
    		return false;
    }
    
    
    
    public BinarySearchTree<AnyType> copy()
    {
    	BinarySearchTree<AnyType> newtree = new BinarySearchTree<AnyType>();
    	newtree.root = new BinaryNode<AnyType>(root.element);
    	
    	if(root != null)
    		copyRecursive(root, newtree.root);
    	
    	return newtree;
    }
    
    private void copyRecursive(BinaryNode<AnyType> node, BinaryNode<AnyType> nodecopy)
    {
    	if(node.left != null)
    	{
    		BinaryNode<AnyType> newnode = new BinaryNode<AnyType>(node.left.element);
    		nodecopy.left = newnode;
    		copyRecursive(node.left, nodecopy.left);
    	}
    	
    	if(node.right != null)
    	{
    		BinaryNode<AnyType> newnode = new BinaryNode<AnyType>(node.right.element);
    		nodecopy.right = newnode;
    		copyRecursive(node.right, nodecopy.right);
    	}
    }
    
    
    
    public void mirror()
    {
    	mirrorRecursive(root);
    }
    
    public void mirrorRecursive(BinaryNode<AnyType> node)
    {
    	if(node == null)
    		return;
    	
    	BinaryNode<AnyType> temp = node.left;
    	node.left = node.right;
    	node.right = temp;
    	
    	mirrorRecursive(node.left);
    	mirrorRecursive(node.right);
    }
    
    
    
    public boolean isMirror(BinarySearchTree<AnyType> other) //throws Exception
    {
    	other.mirror();
    	boolean ism = equals(other);
    	other.mirror();
    	return ism;
    }
    
    
    public void rotateRight(AnyType val)
    {
    	
    }
    
    public void rotateLeft(AnyType val)
    {
    	
    }
    
    
    
    public void printLevels()
    {
    	Queue<BinaryNode<AnyType>> qa = new LinkedList<BinaryNode<AnyType>>();
    	Queue<BinaryNode<AnyType>> qb = new LinkedList<BinaryNode<AnyType>>();
    	
    	if(root != null)
    		qa.add(root);
    	
    	
    	while( !qa.isEmpty() && !qb.isEmpty() )
    	{
	    	while( !qa.isEmpty() )
	    	{
	    		BinaryNode<AnyType> node = qa.poll();
	    		
	    		System.out.print(node.element + "  ");
	    		
	    		if(node.left != null)
	    			qb.add(node.left);
	    		if(node.right != null)
	    			qb.add(node.right);
	    		
	    	}
	    	
	    	System.out.print("\n");
	    	qa = qb;
	    	qb = new LinkedList<BinaryNode<AnyType>>();
    	}
    	
    	
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
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        /*
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }
        */
    }
}