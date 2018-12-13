package LinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class DLLTest {

	@Test    
	public void testConstructor() {
		DLL<Integer> b= new DLL<Integer>();
		assertEquals("[]", b.toString());
		assertEquals("[]", b.toStringRev());
		assertEquals(0, b.size());
	}

	@Test
	public void testAppend() {
		DLL<String> ll= new DLL<String>();
		ll.append("Sampson");
		assertEquals("[Sampson]", ll.toString());
		assertEquals("[Sampson]", ll.toStringRev());
		assertEquals(1, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.append("James");
		assertEquals("[Sampson, James]", ll.toString());
		assertEquals("[James, Sampson]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("James",ll.getLast().getValue());

		ll.append("Tony");
		assertEquals("[Sampson, James, Tony]", ll.toString());
		assertEquals("[Tony, James, Sampson]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Tony",ll.getLast().getValue());
	}

	@Test
	public void testPrepend() {
		DLL<String> ll= new DLL<String>();
		ll.prepend("Sampson");
		assertEquals("[Sampson]", ll.toString());
		assertEquals("[Sampson]", ll.toStringRev());
		assertEquals(1, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.prepend("James");
		assertEquals("[James, Sampson]", ll.toString());
		assertEquals("[Sampson, James]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("James",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());

		ll.prepend("Tony");
		assertEquals("[Tony, James, Sampson]", ll.toString());
		assertEquals("[Sampson, James, Tony]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("Tony",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
	}


	@Test 
	public void testAppendAndPrepend() {
		DLL<String> ll= new DLL<String>();
		assertEquals(null,ll.getFirst());
		assertEquals(null,ll.getLast());
		
		ll.prepend("Sampson");
		assertEquals("[Sampson]", ll.toString());
		assertEquals("[Sampson]", ll.toStringRev());
		assertEquals(1, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.prepend("James");
		assertEquals("[James, Sampson]", ll.toString());
		assertEquals("[Sampson, James]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("James",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());

		ll.prepend("Tony");
		assertEquals("[Tony, James, Sampson]", ll.toString());
		assertEquals("[Sampson, James, Tony]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("Tony",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.append("Jacob");
		assertEquals("[Tony, James, Sampson, Jacob]", ll.toString());
		assertEquals("[Jacob, Sampson, James, Tony]", ll.toStringRev());
		assertEquals(4, ll.size());		 
		assertEquals("Tony",ll.getFirst().getValue());
		assertEquals("Jacob",ll.getLast().getValue());
		
	}

	@Test
	public void testGetNode() {
		// need to test assert statements as well
		DLL<String> ll= new DLL<String>();
		ll.append("Sampson");
		assertEquals("Sampson",ll.getNode(0).getValue());
		
		ll.append("James");
		assertEquals("Sampson",ll.getNode(0).getValue());
		assertEquals("James",ll.getNode(1).getValue());

		ll.append("Tony");
		assertEquals("Sampson",ll.getNode(0).getValue());
		assertEquals("James",ll.getNode(1).getValue());
		assertEquals("Tony",ll.getNode(2).getValue());
	
	}
	
	@Test
	public void testInsertAfter() {
		// need to test assert statements as well
		DLL<String> ll= new DLL<String>();
		ll.append("Sampson");
		ll.insertAfter("James", ll.getNode(0));
		assertEquals("[Sampson, James]", ll.toString());
		assertEquals("[James, Sampson]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("James",ll.getLast().getValue());
		
		ll.insertAfter("Tony", ll.getNode(0));
		assertEquals("[Sampson, Tony, James]", ll.toString());
		assertEquals("[James, Tony, Sampson]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("James",ll.getLast().getValue());
		
		ll.insertAfter("Jacob", ll.getNode(2));
		assertEquals("[Sampson, Tony, James, Jacob]", ll.toString());
		assertEquals("[Jacob, James, Tony, Sampson]", ll.toStringRev());
		assertEquals(4, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Jacob",ll.getLast().getValue());
	}
	
	@Test
	public void testInsertBefore() {
		// need to test assert statements as well
		DLL<String> ll= new DLL<String>();
		ll.append("Sampson");
		ll.insertBefore("James", ll.getNode(0));
		assertEquals("[James, Sampson]", ll.toString());
		assertEquals("[Sampson, James]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("James",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.insertBefore("Tony", ll.getNode(1));
		assertEquals("[James, Tony, Sampson]", ll.toString());
		assertEquals("[Sampson, Tony, James]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("James",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
		
		ll.insertBefore("Jacob", ll.getNode(0));
		assertEquals("[Jacob, James, Tony, Sampson]", ll.toString());
		assertEquals("[Sampson, Tony, James, Jacob]", ll.toStringRev());
		assertEquals(4, ll.size());		 
		assertEquals("Jacob",ll.getFirst().getValue());
		assertEquals("Sampson",ll.getLast().getValue());
	}
	
	@Test
	public void removeNode() {
		// need to test assert statements as well
		DLL<String> ll= new DLL<String>();
		ll.append("Sampson");
		ll.append("James");
		ll.append("Tony");
		ll.append("Jacob");
		
		// remove middle node
		ll.remove(ll.getNode(1));
		assertEquals("[Sampson, Tony, Jacob]", ll.toString());
		assertEquals("[Jacob, Tony, Sampson]", ll.toStringRev());
		assertEquals(3, ll.size());		 
		assertEquals("Sampson",ll.getFirst().getValue());
		assertEquals("Jacob",ll.getLast().getValue());
		
		// remove first node
		assertEquals("Sampson",ll.getNode(0).getValue());
		ll.remove(ll.getNode(0));
		assertEquals("[Tony, Jacob]", ll.toString());
		assertEquals("[Jacob, Tony]", ll.toStringRev());
		assertEquals(2, ll.size());		 
		assertEquals("Tony",ll.getFirst().getValue());
		assertEquals("Jacob",ll.getLast().getValue());
		
		// remove last node
		ll.remove(ll.getNode(1));
		assertEquals("[Tony]", ll.toString());
		assertEquals("[Tony]", ll.toStringRev());
		assertEquals(1, ll.size());		 
		assertEquals("Tony",ll.getFirst().getValue());
		assertEquals("Tony",ll.getLast().getValue());
		
		// remove only node left
		ll.remove(ll.getNode(0));
		assertEquals("[]", ll.toString());
		assertEquals("[]", ll.toStringRev());
		assertEquals(0, ll.size());		 
		assertEquals(null,ll.getFirst());
		assertEquals(null,ll.getLast());

	}
}
