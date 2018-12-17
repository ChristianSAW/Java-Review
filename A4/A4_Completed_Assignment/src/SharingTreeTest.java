import static org.junit.Assert.*;
import static common.JUnitUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class SharingTreeTest {

    private static Network n;
    private static Person[] people;

    @BeforeClass
    public static void setup(){
        n= new Network();
        people= new Person[]{
                new Person("A", n, 0), new Person("B", n, 0), new Person("C", n, 0),
                new Person("D", n, 0), new Person("E", n, 0), new Person("F", n, 0),
                new Person("G", n, 0), new Person("H", n, 0), new Person("I", n, 0),
                new Person("J", n, 0), new Person("K", n, 0), new Person("L", n, 0)
        };
    }

    @Test
    public void testInsert() {
        SharingTree st= new SharingTree(people[1]); 

        //Test add to root --check also the returned value
        SharingTree st2= st.insert(people[1], people[2]);
        assertEquals("B[C]", toStringBrief(st));
        assertEquals(people[1], st.getRoot());
        
        // Test insert non-root
        SharingTree st3= st.insert(people[2], people[3]);
        assertEquals(people[2],st2.getRoot());
        assertEquals(people[3],st3.getRoot());
        assertEquals(0,st3.getChildrenCount());
        assertEquals(1,st.getChildrenCount());
        
        // Test insert second child
        SharingTree st4= st.insert(people[1], people[4]);
        assertEquals(people[4], st4.getRoot());
        assertEquals(2,st.getChildrenCount());

        // Test null cases
        try {st.insert(null, people[1]); fail();}       // p is null
		catch (IllegalArgumentException e) {}
        try {st.insert(people[4], null); fail();}       // c is null
		catch (IllegalArgumentException e) {}
        try {st.insert(people[1], people[3]); fail();}  // c is already in tree
		catch (IllegalArgumentException e) {}
        try {st.insert(people[5], people[6]); fail();}  // p is not in tree
		catch (IllegalArgumentException e) {}
      
    }
    
    @Test
    public void testSize() {
        SharingTree st= new SharingTree(people[0]);         
        
        // case with no children
        assertEquals(1, st.size());
        
        // case with children
        st.insert(people[0], people[1]);
        assertEquals(2, st.size());
        st.insert(people[0], people[2]);
        assertEquals(3, st.size());
        st.insert(people[0], people[3]);
        assertEquals(4, st.size());
        st.insert(people[3], people[4]);
        assertEquals(5, st.size());
        st.insert(people[3], people[5]);
        assertEquals(6, st.size());
    }
    
    @Test
    public void testDepth() {
        SharingTree st= new SharingTree(people[0]); 
        
        // test with 1 person
        assertEquals(0, st.depth(people[0]));
        
        // test with multiple people
        st.insert(people[0], people[1]);
        st.insert(people[0], people[2]);
        st.insert(people[0], people[3]);
        st.insert(people[3], people[4]);
        st.insert(people[3], people[5]);
        st.insert(people[5], people[6]);
        st.insert(people[6], people[7]);
        assertEquals(1, st.depth(people[1]));
        assertEquals(2, st.depth(people[4]));
        assertEquals(4, st.depth(people[7]));
    }
    
    @Test
    public void testWidthAtDepth() {
        SharingTree st= new SharingTree(people[0]); 
        assertEquals(1, st.widthAtDepth(0));
        
        // test if d > depth possible
        assertEquals(0,st.widthAtDepth(3));
        
        // test if d < depth possible
        try {st.widthAtDepth(-2); fail();}
        catch (IllegalArgumentException e) {}
        
        st.insert(people[0], people[1]);
        st.insert(people[0], people[2]);
        st.insert(people[0], people[3]);
        st.insert(people[3], people[4]);
        st.insert(people[3], people[5]);
        st.insert(people[5], people[6]);
        st.insert(people[6], people[7]);
        assertEquals(3, st.widthAtDepth(1));
    }
    
    @Test
    public void testGetSharingRoute() {     
        SharingTree st= new SharingTree(people[0]); 
        List<Person> lp= new LinkedList<>();
        lp.add(people[0]);
        
        // test if 1 node 
        assertEquals(lp, st.getSharingRoute(people[0]));
        st.insert(people[0], people[1]);
        st.insert(people[0], people[2]);
        st.insert(people[0], people[3]);
        st.insert(people[3], people[4]);
        st.insert(people[3], people[5]);
        st.insert(people[5], people[6]);
        st.insert(people[6], people[7]);
        
        // test level 2
        lp.add(people[2]);
        assertEquals(lp, st.getSharingRoute(people[2]));   // 0 --> 2
        List<Person> lp2 = new LinkedList<>();
        lp2.add(people[3]);
        lp2.add(people[4]);
        SharingTree st2 = st.getTree(people[3]);
        assertEquals(lp2, st2.getSharingRoute(people[4])); // 3 --> 4
        
        // test entire tree 
        assertEquals(("[" + people[0] + ", " + people[3]  + ", " + people[5] 
        		+ ", " +  people[6] + ", " + people[7] + "]"),
        		st.getSharingRoute(people[7]).toString());
        
        // test for null case (c not in tree)
        assertEquals(null,st2.getSharingRoute(people[1]));
    }
    
    @Test
    public void testGetSharedAncestor() {       
        SharingTree st= new SharingTree(people[0]);
        SharingTree s4 = new SharingTree(people[0]);
        
        // populate tree S4
        s4.insert(people[0], people[2]);
        s4.insert(people[0], people[5]);
        s4.insert(people[2], people[6]);
        
        
        // test if 1 node
        assertEquals(people[0], st.getSharedAncestor(people[0], people[0]));
        st.insert(people[0], people[1]);
        st.insert(people[0], people[2]);
        st.insert(people[2], people[8]);
        st.insert(people[0], people[3]);
        st.insert(people[3], people[4]);
        st.insert(people[3], people[5]);
        st.insert(people[5], people[6]);
        st.insert(people[6], people[7]);
        st.insert(people[4], people[9]);
        
        // test this case 
        assertEquals(null, st.getSharedAncestor(people[0], null));
        assertEquals(null, st.getSharedAncestor(null, people[9]));  
        
        // test if ancestor is there
        assertEquals(people[0], st.getSharedAncestor(people[0], people[0]));
        assertEquals(people[0], st.getSharedAncestor(people[0], people[1]));
        assertEquals(people[0], st.getSharedAncestor(people[1], people[0]));
        assertEquals(people[0], st.getSharedAncestor(people[1], people[2]));
        assertEquals(people[3], st.getSharedAncestor(people[9], people[7]));
        
        // test if children are not in same tree 
        assertEquals(null,s4.getSharedAncestor(people[0], people[9] ));
    }
    
    @Test
    public void testEquals() {
    	// create trees 
        SharingTree st= new SharingTree(people[0]);
        SharingTree sl = new SharingTree(people[0]);
        SharingTree s1 = new SharingTree(people[0]);
        SharingTree s2 = new SharingTree(people[0]);
        SharingTree s3 = new SharingTree(people[0]);
        SharingTree s4 = new SharingTree(people[0]);
        
        
        // test with no children
    	// test if give same tree 
        assertEquals(true, st.equals(st));
        
        // add children
        // ST
        st.insert(people[0], people[1]);
        st.insert(people[0], people[2]);
        st.insert(people[2], people[8]);
        st.insert(people[0], people[3]);
        st.insert(people[3], people[4]);
        st.insert(people[3], people[5]);
        st.insert(people[5], people[6]);
        st.insert(people[6], people[7]);
        st.insert(people[4], people[9]);
        
        // SL
        sl.insert(people[0], people[1]);
        sl.insert(people[0], people[2]);
        sl.insert(people[2], people[8]);
        sl.insert(people[0], people[3]);
        sl.insert(people[3], people[4]);
        sl.insert(people[3], people[5]);
        sl.insert(people[5], people[6]);
        sl.insert(people[6], people[7]);
        
        // S1
        s1.insert(people[0], people[1]);
        s1.insert(people[0], people[2]);
        s1.insert(people[2], people[8]);
        
        // S2
        s2.insert(people[0], people[1]);
        s2.insert(people[0], people[2]);
        s2.insert(people[2], people[8]);
        
        // S3
        s3.insert(people[0], people[2]);
        s3.insert(people[0], people[1]);
        s3.insert(people[2], people[8]);
        
        // S4
        s4.insert(people[0], people[2]);
        s4.insert(people[0], people[5]);
        s4.insert(people[2], people[6]);
    
        // test with children
        assertEquals(true, st.equals(st)); // same tree, same pointer
        assertEquals(true, s2.equals(s1)); // same tree, different pointers 
        assertEquals(true, s1.equals(s2)); // same tree, different pointers, reverse order
        assertEquals(true, s3.equals(s1)); // same tree, different branch order
        
        // test if not equals 
        assertEquals(false,st.equals(sl)); // different number of children
        assertEquals(false,st.equals(s2)); // different number of children
        assertEquals(false,st.equals(n));  // different object class
        assertEquals(false,s3.equals(s4)); // different children objects, same number of children
    }
    
    
    /** Return a representation of this tree. This representation is:
     * (1) the name of the Person at the root, followed by
     * (2) the representations of the children (in alphabetical
     *     order of the children's names).
     * There are two cases concerning the children.
     *
     * No children? Their representation is the empty string.
     * Children? Their representation is the representation of each child, with
     * a blank between adjacent ones and delimited by "[" and "]".
     * Examples:
     * One-node tree: "A"
     * root A with children B, C, D: "A[B C D]"
     * root A with children B, C, D and B has a child F: "A[B[F] C D]"
     */
    public static String toStringBrief(SharingTree t) {
        String res= t.getRoot().getName();

        Object[] childs= t.getChildren().toArray();
        if (childs.length == 0) return res;
        res= res + "[";
        selectionSort1(childs);

        for (int k= 0; k < childs.length; k= k+1) {
            if (k > 0) res= res + " ";
            res= res + toStringBrief(((SharingTree)childs[k]));
        }
        return res + "]";
    }

    /** Sort b --put its elements in ascending order.
     * Sort on the name of the Person at the root of each SharingTree
     * Throw a cast-class exception if b's elements are not SharingTrees */
    public static void selectionSort1(Object[] b) {
        int j= 0;
        // {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
        //          0----------- j---------------b.length
        // inv : b | sorted, <= |       >=      |
        //          ----------------------------
        while (j != b.length) {
            // Put into p the index of smallest element in b[j..]
            int p= j;
            for (int i= j+1; i != b.length; i++) {
                String bi= ((SharingTree)b[i]).getRoot().getName();
                String bp= ((SharingTree)b[p]).getRoot().getName();
                if (bi.compareTo(bp) < 0) {
                    p= i;
                }
            }
            
            // Swap b[j] and b[p]
            Object t= b[j]; b[j]= b[p]; b[p]= t;
            
            j= j+1;
        }
    }

}
