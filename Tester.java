public class Tester {
    public static void main(String[] ababa) {
        Tester.printSection("A - Node object");
        try {
            Node node1 = new Node("ABC");
            Tester.printAssert(node1.getData().equals("ABC"), 'A', "Node getData()");

            node1.setData("abcD");
            Tester.printAssert(node1.getData().equals("abcD"), 'A', "Node setData()");

            Node node0 = new Node("prev");
            Node node2 = new Node("next");

            node1.setPrev(node0);
            node1.setNext(node2);
            Tester.printAssert(node1.getPrev().getData().equals("prev"), 'A', "Node prev");
            Tester.printAssert(node1.getNext().getData().equals("next"), 'A', "Node next");
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('A');
        }

        Tester.printSection("B - LinkedList constructor, add(value), and size()");
        try {
            MyLinkedList list = new MyLinkedList();
            Tester.printAssert(list.size() == 0, 'B', "LinkedList size() starts at 0");

            list.add("A");
            list.add("B");
            Tester.printAssert(list.size() == 2, 'B', "LinkedList add(value) increases the size()");
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('B');
        }

        Tester.printSection("C - LinkedList add(index, value) and get(index)");
        try {
            MyLinkedList list = new MyLinkedList();
            list.add("x");
            list.add("y");
            list.add("z");
            Tester.printAssert("y".equals(list.get(1)), 'C', "LinkedList get(index)");

            for (int i = 0; i < 5; i++) {
                list.add(0, "front");
            }
            Tester.printAssert(list.size() == 8, 'C', "LinkedList add(index, value) increases the size()");
            Tester.printAssert("front".equals(list.get(0)), 'C',
                    "LinkedList add(index, value) appends to front correctly");
            Tester.printAssert("x".equals(list.get(5)), 'C', "LinkedList add(index, value) appends to front correctly");

            list.add(4, "middle");
            Tester.printAssert("middle".equals(list.get(4)), 'C',
                    "LinkedList add(index, value) inserts in the middle correctly");

            list.add(list.size(), "end");
            Tester.printAssert("end".equals(list.get(list.size() - 1)), 'C',
                    "LinkedList add(index, value) appends to end correctly");

            try {
                list.get(-2);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('C');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("get() IOOBException caught!");
            }

            try {
                list.get(10);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('C');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("get() IOOBException caught!");
            }
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('C');
        }

        Tester.printSection("D - LinkedList set(index, value)");
        try {
            MyLinkedList list = new MyLinkedList();
            for (int i = 0; i < 100; i++) {
                list.add(Integer.toString(i));
            }

            String x = list.set(7, "Yay");
            Tester.printAssert(list.size() == 100, 'D', "LinkedList set(index, value) doesn't change size");
            Tester.printAssert("7".equals(x), 'D', "LinkedList set(index, value) returns value");
            Tester.printAssert("Yay".equals(list.get(7)), 'D', "LinkedList set(index, value) sets value");

            try {
                list.set(-2, null);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('D');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("set() IOOBException caught!");
            }

            try {
                list.set(108, null);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('D');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("set() IOOBException caught!");
            }
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('D');
        }

        Tester.printSection("E - LinkedList toString()");
        try {
            MyLinkedList list = new MyLinkedList();
            list.add("a");
            list.add("b");
            list.add("c");

            String s = list.toString();
            String rev = list.toStringReversed();
            Tester.printAssert("[a, b, c]".equals(s), 'E', "LinkedList toString() works correctly");
            Tester.printAssert("[c, b, a]".equals(rev), 'E', "LinkedList toString() reversed works correctly");
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('E');
        }

        Tester.printSection("F - LinkedList remove()");
        try {
            MyLinkedList list = new MyLinkedList();

            try {
                list.remove(0);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('F');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("remove() IOOBException caught!");
            }

            for (int i = 0; i < 10; i++) {
                list.add("" + i);
            }
            Tester.printAssert("4".equals(list.remove(4)), 'F', "LinkedList remove() returns value");
            Tester.printAssert(list.size() == 9, 'F', "LinkedList remove() decreases size");

            try {
                list.remove(-1);
                System.out.println("Error: No exception thrown!");
                Tester.printBad('F');
            } catch (IndexOutOfBoundsException e) {
                Tester.printGood("remove() IOOBException caught!");
            }

            Tester.printAssert("0".equals(list.remove(0)), 'F', "LinkedList remove() head");
            Tester.printAssert(list.size() == 8, 'F', "LinkedList remove() decreases size");

            Tester.printAssert("9".equals(list.remove(list.size() - 1)), 'F', "LinkedList remove() tail");
            Tester.printAssert(list.size() == 7, 'F', "LinkedList remove() decreases size");
            Tester.printAssert("[1, 2, 3, 5, 6, 7, 8]".equals(list.toString()), 'G', "remove() + toString()");
            Tester.printAssert("[8, 7, 6, 5, 3, 2, 1]".equals(list.toStringReversed()), 'G', "remove() + toStringReversed()");

            while (list.size() > 0) {
                list.remove(0);
            }
            Tester.printAssert(list.size() == 0, 'F', "LinkedList remove() till empty");
            Tester.printAssert("[]".equals(list.toString()), 'G', "remove() + toString()");
            Tester.printAssert("[]".equals(list.toStringReversed()), 'G', "remove() + toStringReversed()");
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('F');
        }

        Tester.printSection("G - LinkedList extend()");
        try {
            MyLinkedList a = new MyLinkedList();
            MyLinkedList b = new MyLinkedList();

            a.add("a");
            a.add("b");
            a.add("c");
            b.add("D");
            b.add("E");
            b.add("F");

            a.extend(b);
            Tester.printAssert(a.size() == 6, 'G', "extend() changes size");
            Tester.printAssert(b.size() == 0, 'G', "extend() changes size");

            Tester.printAssert("[a, b, c, D, E, F]".equals(a.toString()), 'G', "extend() + toString()");
            Tester.printAssert("[F, E, D, c, b, a]".equals(a.toStringReversed()), 'G', "extend() + toStringReversed()");
            Tester.printAssert("[]".equals(b.toString()), 'G', "extend() + toString()");
            Tester.printAssert("[]".equals(b.toStringReversed()), 'G', "extend() + toStringReversed()");

            // dump into empty list test
            b.extend(a);
            Tester.printAssert("[a, b, c, D, E, F]".equals(b.toString()), 'G', "extend() + toString()");
            Tester.printAssert("[F, E, D, c, b, a]".equals(b.toStringReversed()), 'G', "extend() + toStringReversed()");
        } catch (RuntimeException e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('F');
        }
    }

    public static void printAssert(boolean assertion, char section, String comment) {
        if (assertion) {
            printGood(comment);
        } else {
            printBad(section);
        }
    }

    public static void printGood(String comment) {
        System.out.println("✔ " + comment);
    }

    public static void printBad(char section) {
        System.out.println("✘ Whoops! Something went wrong in section " + section);
    }

    public static void printSection(String section) {
        System.out.println("\nSection " + section + ":");
    }
}