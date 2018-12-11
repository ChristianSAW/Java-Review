/* Completed by: Christian Welling */

/** A collection of static String utility functions. All methods assume that
 * String parameters are non-null.
 *
 * If any method is called with arguments that do not satisfy the preconditions,
 * the behavior is undefined (it can do anything you want). You can,
 * (but do not have to) use assert statements to test preconditions. */
public class A2 {
    /* Implementation notes:
     *
     * Wherever possible, prefer library functions to writing your own loops.
     *
     * The more complicated your loops become, the more important it is to
     * explain the logic in comments.
     *
     * See the JavaHyperText entries for if-statement, while-loop, for-loop,
     * switch, break, and continue if you think you need them.
 *
 *   Look at pinned Piazza note "A2 FAQs" regularly for updates.*/

    /**Return either s1 + s2 or s1 - s2, depending on b. If b is true, return
     * the sum, otherwise return the difference. */
    public static int sumDif(boolean b, int s1, int s2) {
        // This method is already implemented; it is here to give you something
        // to test, and to show you different ways of writing return statements.
        if (b) {
            int s;
            s = s1 + s2;
            return s;

            // equivalently:
            // int s = s1 + s2;
            // return s;

            // or simply: return s1 + s2;
        }

        // b is false;
        return s1 - s2;
    }

    /** Return true iff the first half of s is the same as the second half of s.
     * Examples: For s = "" return true
     * For s = "xxx" return false (if the length is odd, it's false)
     * For s = "xxxx" return true
     * For s = "hellohello" return true
     * For s = "helloolleh" return false */
    public static boolean isDoubled(String s) {
        // TODO 1. There is no need for a loop. Do not use a loop.
        // In all methods, use s1.equals(s2) and NOT s1 == s2 to test
        // equality of s1 and s2.
    	int len = s.length();
    	
    	// Check if length is even
    	if (len%2 == 0) {
    		if (len == 0) {
    			return true;
    		}
    		else {
    			String s1 = s.substring(0, (len/2)-1);
        		String s2 = s.substring(len/2,len-1);
        		if (s1.equals(s2)) {
        			return true; 
        		}
    		}
    	}
        return false;
    }

    /** Return s with adjacent equal characters removed.
     * Examples: for s = "", return ""
     * For s = "b", return "b"
     * For s = "aaaabaccc", return "abac" */
    public static String removeDups(String s) {
        // TODO 2.
    	int i = 1;
    	while (i < s.length()) {
    		if (s.charAt(i) == (s.charAt(i-1))) { // use == b/c char is primitive type
    			s = removeChar(s,i);
    		} else {
    			i++; // only increment if not not having removed character
    		}
    	}
        return s;
    }
    /** Returns s with the character of index idx removed
     */
    private static String removeChar(String s, int idx) {
    	assert idx < s.length() && idx >= 0;    // check that index is in range
    	return s.substring(0, idx) + s.substring(idx + 1);
    }

    /** Return s but with each occurrence of a character in input replaced by the
     * corresponding character in output. A character of s that does not appear in
     * input is unchanged.
     *
     * Precondition: input and output are the same length. No character in input
     * appears in output (otherwise, the idea of replacement might be ambiguous,
     * depending on the order in which replacements are done).
     *
     * Examples: replace("hello world", "", "") = "hello world"
     * replace("hello world", "abc", "lmn") = "hello world"
     * replace("hello world", "hod", "xyz") = "xelly wyrlz"
     * replace("hello world", "helowrd", ".......") = "..... ....."   */
    public static String replace(String s, String input, String output) {
        // TODO 3 This needs only ONE for-loop with a single statement in the
        // loop body. Look for a suitable String method!
    	for (int i = 0; i < input.length(); i++) { 
    		s = s.replace((input.charAt(i)), output.charAt(i));
    	}
        return s;
    }

    /** Return the shortest substring x of s such that s = x + x + ⋯ + x.
     * Examples:
     * For s = "" return ""
     * For s = "xxxxxxxxx" return "x"
     * For s = "xyxyxyxy" return "xy"
     * For s = "hellohellohello" return "hello"
     * For s = "hellohelloworld" return "hellohelloworld"
     * For s = "hellohel" return "hellohel" */
    public static String makeShort(String s) {
        // TODO 4. This can be written using nested loops. But it is better
        // to add a helper function, with a suitable specification,
        // in order to remove the need for nested loops. It is easier
        // to write and easier to read/understand.
    	int lenSS = 0;
    	int numOccur = 0;
    	int i = 0;
    	String k = s;
    	//System.out.println(s.length());
    	while (lenSS*numOccur != s.length()) {
    		//System.out.println("Got to this point");
    		k = s.substring(0,i+1);
    		//System.out.println("Got to this point 2");
    		lenSS = k.length();
    		numOccur = numAppear(s,k);
    		i++;
    		//System.out.println(k);
    	}
        return k;
    }
    
    /** Returns the number of times substring k appears in s 
     * 
     */
    public static int numAppear(String s, String k) {
    	assert s.length() >= k.length();
    	int indOccur = 0; 
        int numOccur = 0;          // count number of appearances of k in s
        while (indOccur != -1) {
        	indOccur = s.indexOf(k, indOccur);
        	//System.out.println(indOccur);
        	if (indOccur !=-1) {
        		//System.out.println("Gets Here");
        		numOccur++;
        		indOccur += k.length();
        	}
        }
    	return numOccur;
    }
    /** Return the value of the expression given by s.
     *  Precondition: s consists of a series of (at least one) integers
     *  separated by the operators '+' or '-'. The expression may contain
     *  any number space characters between the integers and operators.
     *
     * Examples: eval("3")                returns 3
     *           eval("3 +    4")         returns 7
     *           eval("100 + 4-2  ")      returns 102
     *           eval("0-9")              returns -9
     *           eval("   7   +   7   +    7  ") returns 21
     */
    public static int eval(String s) {
        // TODO 5. You can use Integer.parseInt to convert a string
        // (like "12345") to the corresponding integer (12345).
    	
    	// remove white space
    	s = s.replace(" ", "");
    	System.out.println(s);
    	
    	int result = 0;
    	int secTerm = 0;
    	int ind1 = 0;
    	boolean opIsAdd = true;
    	boolean stringIs1Number = true;
    	
    	// loop through string 
    	for (int i = 0; i < s.length(); i++) {
    		// check if value is operator
    		if (Character.isDigit(s.charAt(i)) == false) {
    			stringIs1Number = false;
    			// set secTerm
    			secTerm = Integer.parseInt(s.substring(ind1, i));
    			ind1 = i+1;
    			// add previous terms
    			if (opIsAdd == true) {
    				result += secTerm;
    			} else {
    				result -= secTerm;
    			}
    			// update operator 
    			char op = s.charAt(i);
    			if (op == '+') {
    				opIsAdd = true;
    			} else {
    				opIsAdd = false;
    			}
    		}
    	}
    	// check if string was 1 number and return that number if so
    	if (stringIs1Number) {
    		result = Integer.parseInt(s);
    	} else {
    		// update second term to last number in string
    		secTerm = Integer.parseInt(s.substring(ind1,s.length()));
    		// add/subtract the last 2 terms
    		if (opIsAdd == true) {
    			result += secTerm;
    		} else {
    			result -= secTerm;
    		}
    	}
        return result;
    }

}
