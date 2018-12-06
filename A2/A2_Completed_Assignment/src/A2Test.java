import static org.junit.Assert.*;

import org.junit.Test;

public class A2Test {

    @Test
    public void testIsDoubled() {
        assertEquals(true,  A2.isDoubled(""));
        assertEquals(false,  A2.isDoubled("b"));
        assertEquals(true,  A2.isDoubled("xxxx"));
        assertEquals(true,  A2.isDoubled("hellohello"));
        assertEquals(false, A2.isDoubled("helloworld"));
        assertEquals(false, A2.isDoubled("helloxhello"));
    }

    @Test
    public void testRemoveDups() {
        assertEquals("",    A2.removeDups(""));
        assertEquals("b",   A2.removeDups("b"));
        assertEquals("b",   A2.removeDups("bb"));
        assertEquals("abaca",  A2.removeDups("aaaabacccaaaaa"));
        assertEquals("x", A2.removeDups("xxxxxxxxxxxxxxxxx"));
    }

    @Test
    public void testReplace() {
        assertEquals("", A2.replace("", "", ""));
        assertEquals("abc", A2.replace("abc", "", ""));
        assertEquals("", A2.replace("", "bc", "dd"));
        assertEquals("hello world", A2.replace("hello world", "", ""));
        assertEquals("hello world", A2.replace("hello world", "abc", "lmn"));
        assertEquals("xelly wyrlz", A2.replace("hello world", "hod", "xyz"));
        assertEquals("..... .....", A2.replace("hello world", "helowrd", "......."));
    }

    @Test
    public void testMakeShort() {
        assertEquals("",                A2.makeShort(""));
        assertEquals("x",               A2.makeShort("x"));
        assertEquals("x",               A2.makeShort("xxxxxx"));
        assertEquals("xy",              A2.makeShort("xyxyxyxy"));
        assertEquals("xyxz",            A2.makeShort("xyxzxyxz"));
        assertEquals("hello",           A2.makeShort("hellohellohello"));
        assertEquals("hellohelloworld", A2.makeShort("hellohelloworld"));
        assertEquals("hellohel",        A2.makeShort("hellohel"));
    }

    @Test
    public void testEval() {
        assertEquals(9,   A2.eval("9"));
        assertEquals(7,   A2.eval("3 + 4"));
        assertEquals(-1,  A2.eval("3 - 4"));
        assertEquals(125, A2.eval("100 - 25+50"));
        assertEquals(679, A2.eval("   7   +   6   +    666  "));
    }

}
