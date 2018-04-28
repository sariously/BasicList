package edu.gcccd.csis;

import java.util.Iterator;

public class MyProject2 implements Project2 {

    @Override
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        return null;
    }

    @Override
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        return null;
    }

    @Override
    public void save(NodeList<Integer> nodeList, String fileName) {

    }

    @Override
    public NodeList<Integer> load(String fileName) {
        return null;
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
        p.save(p.addition(listOfLists.iterator()), "result.bin");
        Project2.print(p.load("result.bin"));
    }
}
