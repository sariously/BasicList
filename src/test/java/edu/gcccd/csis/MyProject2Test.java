package edu.gcccd.csis;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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

    /**
     * Adding two long integer values
     */
    @Test
    public void testAddition() {


        final NodeList<Integer> n1 = Project2.generateNumber(30);
        final NodeList<Integer> n2 = Project2.generateNumber(30);

        System.out.println("Test N1: ");
        final BigInteger N1 = genBigInteger(n1);
        System.out.println("Test N2: ");
        final BigInteger N2 = genBigInteger(n2);
        final NodeList<Integer> n3 = new MyProject2().addition(n1, n2);


        final BigInteger N3 = N1.add(N2);
        assertEquals(N3, genBigInteger(n3));

//        final BigInteger N1_times_2 = N1.add(N1);
//        final NodeList<Integer> nl_times_2 = new MyProject2().addition(n1, n1);
//
//        assertEquals(N1_times_2, genBigInteger(nl_times_2));
    }



}
