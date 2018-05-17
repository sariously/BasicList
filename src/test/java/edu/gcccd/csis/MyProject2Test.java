package edu.gcccd.csis;


import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.math.BigInteger;

public class MyProject2Test {

    /**
     * Generates a a BigInteger from a NodeList
     * for testing with BigInteger add methods
     *
     * @param nodeList NodeList<Integer>
     * @return BigInteger
     */

   public static BigInteger genBigInteger(final NodeList<Integer> nodeList)
   {
       StringBuilder nodeListString = new StringBuilder(nodeList.getLength());

       for(int i : nodeList)
       {
           nodeListString.append(i);
       }

       System.out.print("Generated Number: ");
       System.out.println(nodeListString);

       return new BigInteger(nodeListString.toString());

   }


    /**
     * Generates a a NodeList from a String by iterating through
     * each character in the String (as an array of characters)
     * and appending each character's numeric value to the NodeList
     *
     * @param s String
     * @return nodeList NodeList<Integer>
     */
   public static NodeList<Integer> genNodeList(String s)
   {
       final NodeList<Integer> nodeList = new NodeList<>();
       for (final char c : s.toCharArray()) {
           nodeList.append(Character.getNumericValue(c));
       }
       return nodeList;
   }

    /**
     * Adding two long integer values represented in two NodeLists
     * Test taken from Project 2 discussion board
     */
    @Test
    public void testAddition() {


        final NodeList<Integer> n1 = Project2.generateNumber(30);
        final NodeList<Integer> n2 = Project2.generateNumber(30);


        final BigInteger N1 = genBigInteger(n1);
        final BigInteger N2 = genBigInteger(n2);
        final NodeList<Integer> n3 = new MyProject2().addition(n1, n2);


        final BigInteger N3 = N1.add(N2);
        assertEquals(N3, genBigInteger(n3));

        final BigInteger N1_times_2 = N1.add(N1);
        final NodeList<Integer> nl_times_2 = new MyProject2().addition(n1, n1);
        assertEquals(N1_times_2, genBigInteger(nl_times_2));
    }

    /**
     * Adding several long integer values represented by an iterator
     */
    @Test

    public void testAdditionIterator() {
        final int NUM_NODE_LISTS = 100; // Number of NodeLists to generate
        final int VALUE = 12345679;     // Integer value represented by each NodeList
        final int TEST = NUM_NODE_LISTS * VALUE;  // Value the addition method should return

        final NodeList<NodeList<Integer>> list = new NodeList<>();
        for (int i = 0; i < NUM_NODE_LISTS; i++) {
            list.append(genNodeList("" + VALUE));
        }
        final NodeList<Integer> result = new MyProject2().addition(list.iterator());
        assertEquals(new BigInteger("" + TEST), genBigInteger(result)); // 12345679 * 100 = 1234567900
    }

    @Test
    public void testAdditionCornerCases()
    {
        // What if there are leading zeroes in the represented Integer?
        NodeList<Integer> n1 = genNodeList("014");
        NodeList<Integer> n2 = genNodeList("900");

        NodeList<Integer> n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("914"), genBigInteger(n3));

        // How about if both lists represent 0?
        // Should sum to 0
        n1 = genNodeList("0");
        n2 = genNodeList("0");

        n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("0"), genBigInteger(n3));

        // What if there are just a bunch of zeroes?
        // Should still sum to zero

        n1 = genNodeList("000000000");
        n2 = genNodeList("0000000000");

         n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("0"), genBigInteger(n3));


        // What happens if both NodeLists are empty?
        // Should not create an error -- Length of sum NodeList should be zero

        n1 = new NodeList<>();
        n2 = new NodeList<>();
        n3 = new MyProject2().addition(n1, n2);
        assertEquals(n3.getLength(), 0);

        // Adding one NodeList to an empty NodeList should give the original NodeList
        // Regardless of which NodeList is empty (first or second)
        n2 = genNodeList("0");
        n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("0"), genBigInteger(n3));

        n1 = n2;
        n2 = new NodeList<>();
        n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("0"), genBigInteger(n3));





    }

    @Test

    public void testSaveAndLoad()
    {

        final int L = 30;

        final NodeList<Integer> n1 = Project2.generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = Project2.generateNumber(L); // (head 1st) e.g. 682

        final Project2 p = new MyProject2();

        Project2.print(p.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(Project2.generateNumber(L));
        }
        final NodeList<Integer> in = p.addition(listOfLists.iterator());
        p.save(in, "result.bin");
        System.out.println("File contents:");

        final NodeList<Integer> out = p.load("result.bin");

        Project2.print(out);

        assertEquals(genBigInteger(in), genBigInteger(out) );
    }




}
