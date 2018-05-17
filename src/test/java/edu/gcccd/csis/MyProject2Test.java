package edu.gcccd.csis;


import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.math.BigInteger;

public class MyProject2Test {



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

   public static NodeList<Integer> genNodeList(String s)
   {
       final NodeList<Integer> nodeList = new NodeList<>();
       for (final char c : s. toCharArray()) {
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

    


}
