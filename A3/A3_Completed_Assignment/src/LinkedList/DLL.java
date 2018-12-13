package LinkedList;
/* Completed by: Christian Welling */

import LinkedList.DLL.Node;

/** An instance is a doubly linked list. */
public class DLL<E> {
    private int size;   // Number of values in the linked list.
    private Node first; // first node of linked list (null if none)
    private Node last;  // last node of linked list (null if none)
    
    /** Constructor: an empty linked list. */
    public DLL() {
    }

    /** Return the number of values in this list.
     *  This function takes constant time. */
    public int size() {
        return size;
    }

    /** Return the first node of the list (null if the list is empty). */
    public Node getFirst() {
        return first;
    }

    /** Return the last node of the list (null if the list is empty). */
    public Node getLast() {
        return last;
    }

    /** Return the value of node n of this list.
     * Precondition: n is a node of this list; it may not be null. */
    public E valueOf(Node n) {
        assert n != null;
        return n.val;
    }
    
    /** Return a representation of this list: its values, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.<br>
     * E.g. for the list containing 6 3 8 in that order, the result it "[6, 3, 8]". */
    public String toString() {
        String res= "[";
        Node n= first;
        // inv: res contains values of nodes before node n (all of them if n = null),
        //      with ", " after each (except for the last value)
        while (n != null) {
            res= res + n.val;
            n= n.next;
            if (n != null) {
                res= res + ", ";
            }
        }

        return res + "]";
    }

    /** Return a representation of this list: its values in reverse, with adjacent
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Takes time proportional to the length of this list.
     * E.g. for the list containing 6 3 8 in that order, the result is "[8, 3, 6]".
     * E.g. for the list containing ""  "" in that order, the result is "[ , ]". */
    public String toStringRev() {
        //TODO 1. Look at toString to see how that was written.
        //        Use the same sort of scheme. Extreme case to watch out for:
        //        E is String and value are the empty string.
        //        You can't test this fully until #2, append, is written.
        String res = "[";
        Node n = last;
        // inv: res contains values of nodes after node n (all of them if n = null),
        //      with ", " after each (except for the last value)
        while (n != null) {
        	res = res + n.val;
        	n = n.prev;
        	if (n != null) {
        		res = res + ", ";
        	}
        }
        
        return res + "]";
    }

    /** add value v in a new node at the end of the list.
     *  This operation takes constant time. */
    public void append(E v) {
        //TODO 2. After writing this method, test this method and
        //        toStringRev thoroughly before starting on the next
        //        method. These two must be correct in order to be
        //        able to write and test all the others.
    	
    	// Test Cases:
    	// 1) the list is empty 
		// 2) the list is not empty

		Node a2 = null;
    	if (size < 1) { // (1) list is empty 
    		Node a1 = null; 
    		Node n = new Node(a1, a2, v);
    		first = n;
    		last = n;
    	} else {        // (2) the list is not empty
    		Node a1 = last;
    		Node n = new Node(a1, a2, v);
    		last = n;
    		a1.next = n;
    	}
		size++;
    }
 
    /** Add value v in a new node at the beginning of the list.
     * This operation takes constant time. */
    public void prepend(E v) {
        //TODO 3.
    	
    	// Test Cases:
    	// 1) the list is empty 
		// 2) the list is not empty

		Node a1 = null; 
    	if (size < 1) { // (1) list is empty 
    		Node a2 = null;
    		Node n = new Node(a1, a2, v);
    		first = n;
    		last = n;
    	} else {        // (2) the list is not empty
    		Node a2 = first;
    		Node n = new Node(a1, a2, v);
    		first = n;
    		a2.prev = n;
    	}
    	size++;
    }
    
    /** Return node number k. 
     *  Precondition: 0 <= k < size of the list.
     *  Example. Suppose list is [8, 6, 7].
     *  If k is 0, return first node; if k = 1, return second node, ... */
    public Node getNode(int k) {
        //TODO 4. This method should take time proportional to min(k, size-k).
        // For example, if k <= size/2, search from the beginning of the
        // list, otherwise search from the end of the list.
        assert 0 <= k && k < size;
        
        int i;
        Node n;
        if (k <= size/2) { // search from beginning of LL
        	i = 0;
        	n = first;
        	while (i != k) {
        		i++;
        		n = n.next;
        	}
        } else {           // search from end of LL
        	i = size - 1;
        	n = last;
        	while (i != k) {
        		i--;
        		n = n.prev;
        	}
        }
        return n;
    }

    /** Insert value v in a new node after node n.
     * This operation takes constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void insertAfter(E v, Node n) {
        //TODO 5. Make sure this method takes constant time. 
    	
    	Node a1 = n.next;
    	Node a2 = new Node(n, a1, v);
    	n.next = a2;
    	
        if (a1 == null) { // check if inserted node is last node. 
        	last = a2;
        } else {
        	a1.prev = a2;
        }
        size++;
    }
    
    /** Insert value v in a new node before node n.
     * This operation takes constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void insertBefore(E v, Node n) {
        //TODO 6. Make sure this method takes constant time. 
        
    	Node a1 = n.prev;
    	Node a2 = new Node(a1, n, v);
    	n.prev = a2;
    	
        if (a1 == null) { // check if inserted node is first node. 
        	first = a2;
        } else {
        	a1.next = a2;
        }
        size++;
    }

    /** Remove node n from this list.
     * This operation must take constant time.
     * Precondition: n must be a node of this list; it may not be null. */
    public void remove(Node n) {
        //TODO 7. Make sure this method takes constant time. 
        
		assert(n != null);
		
		Node a1 = n.next;
		Node a2 = n.prev;
		
		if (size == 1) {		// if one node in list
			first = null;
			last = null;
		}			
		else if (n == first) {  // if node is first
			first = a1;
			a1.prev = null;
		}	
		else if (n == last) {   // if node is last
			last = a2;
			a2.next = null;
		}	
		else { 		            // if node is in middle of list
			a1.prev = a2;
			a2.next = a1;
		}
		size--;
    } 
    
    /*********************/

    /** An instance is a node of this list. */
    public class Node {
        private Node prev; // Previous node on list (null if this is first node)
        private E val;   // The value of this element
        private Node next; // Next node on list. (null if this is last node)

        /** Constructor: an instance with previous node p (can be null),
         * next node n (can be null), and value v. */
        Node(Node p, Node n, E v) {
            prev= p;
            next= n;
            val= v;
        }

        /** Return the value of this node. */
        public E getValue() {
            return val;
        }
        
        /** Return the node previous to this one (null if this is the
         * first node of the list). */
        public Node prev() {
            return prev;
        }

        /** Return the next node in this list (null if this is the
         * last node of this list). */
        public Node next() {
            return next;
        }
    }

}