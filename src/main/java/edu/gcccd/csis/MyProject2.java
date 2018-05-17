package edu.gcccd.csis;

import java.util.Iterator;
import java.io.*;

public class MyProject2 implements Project2 {

    NodeList<Integer> outputNodeList; // declare outside of method since method is recursive
    /**
     * Reverse the elements in a NodeList of Integers
     *
     * The next method gets called n times, where n is the number of Nodes in the NodeList
     *
     * @param input NodeList<Integer>
     * @return outputNodelist NodeList<Integer> -- the reverse of inputNodeList
     */
    public NodeList<Integer> reverse(Iterator<Integer> input)
    {
        outputNodeList = new NodeList<>();

        while(input.hasNext())
        {
            final int i = (input.next());
            reverse(input);
            outputNodeList.append(i);

        }

        return outputNodeList;

    }

    /**
     * Add two very long numbers
     *
     * The iterator's next method may be called (n + (n - m)) times where n is the length of the longer
     * of the two NodeList arguments and m is the length of the shorter NodeList arguments.
     * Not only must the next method be called n times to sum up each pair of digits, but next may also be called
     * up to (n - m) times to remove all leading zeroes.
     *
     *
     *
     * @param nodeList1 NodeList<Integer>;
     * @param nodeList2 NodeList<Integer>;
     * @return nodeList representing the sum (add) of nodeList1 and nodeList2, without leading '0'
     */
    @Override
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        NodeList<Integer> result = new NodeList<>();
        if (nodeList1.getLength() == 0)
        {
            result = nodeList2;
        }

        else if (nodeList2.getLength() == 0)
        {
            result = nodeList1;
        }

        else {

            // Reverse the elements to make padding with 0s easier
            nodeList1 = reverse(nodeList1.iterator());
            nodeList2 = reverse(nodeList2.iterator());

            // Pad with zeroes so that the NodeLists are the same length
            while (nodeList1.getLength() != nodeList2.getLength()) {
                if (nodeList1.getLength() < nodeList2.getLength()) {
                    nodeList1.append(0);

                } else {
                    nodeList2.append(0);

                }
            }

            // Sum the NodeLists
            Iterator<Integer> nodeIterator1 = nodeList1.iterator();
            Iterator<Integer> nodeIterator2 = nodeList2.iterator();

            int carryOver = 0;
            int remainder;
            int BASE = 10;
            while (nodeIterator1.hasNext()) {
                int digit = carryOver + nodeIterator1.next() + nodeIterator2.next();

                // Can't put digits larger than the base in an element of the NodeList
                if (digit > (BASE - 1)) {
                    remainder = digit % BASE;
                    carryOver = digit / BASE;
                } else {
                    remainder = digit;
                    carryOver = 0;
                }


                result.append(remainder);
            }

            // appends the last carryOver if we had a digit larger than the base during the last iteration
            if (carryOver != 0) {
                result.append(carryOver);
            }

            // Reverse the result NodeList to the correct order
            result = reverse(result.iterator());

            // Take out the padding (leading zeroes)
            if (result.getLength() > 1) {
                while (result.iterator().next() == 0 && result.getLength() > 1) {
                    result.remove(0);
                }
            }
        }


        return result;

    }

    /**
     * Add very long numbers
     * This while loop in this method will be iterated through n - 1 times,
     * where n is the number of NodeLists in the iterator.
     * The reason why it is called n - 1 times is because two NodeLists are passed to
     * addition(nodeList1, nodeList2) at a time.
     *
     * @param iterator NodeList<Integer>;
     * @return nodeList representing the sum (add) of all very long numbers, without leading '0'
     */
    @Override
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> result = new NodeList<>();

        //Runs as long as there is another NodeList in the Iterator
        while(iterator.hasNext())
        {
            NodeList<Integer> segment  = iterator.next(); // Get next NodeList in Iterator
            result = addition(segment, result);  // Utilizes the 2-arg addition method to add two NodeLists
                                                 // In this case, the current NodeList and the running result
        }
        return result;
    }

    @Override
    public void save(NodeList<Integer> nodeList, String fileName) {
        try
        {
            final FileOutputStream fos = new FileOutputStream(fileName);
            final DataOutputStream dos = new DataOutputStream(fos);

            for ( Integer element : nodeList)
            {
                dos.write(element);

            }

            dos.close();
        }

        catch(IOException e)
        {
          e.printStackTrace();
        }

    }

    @Override
    public NodeList<Integer> load(String fileName) {
        NodeList<Integer> result = new NodeList<>();

        try
        {
            final FileInputStream fis = new FileInputStream(fileName);
            final DataInputStream dis = new DataInputStream(fis);

            while(dis.available() > 0)
            {
                result.append(dis.read());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return result;

    }


    public static void main(final String[] args) {
        final int L = 30;

        final NodeList<Integer> n1 = Project2.generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = Project2.generateNumber(L); // (head 1st) e.g. 682

        final Project2 p = new MyProject2();

        Project2.print(p.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(Project2.generateNumber(L));
        }
        final NodeList<Integer> result = p.addition(listOfLists.iterator());
        p.save(result, "result.bin");
        System.out.println("File contents:");
        Project2.print(p.load("result.bin"));
    }
}
