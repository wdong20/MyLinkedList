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
        } catch (Exception e) {
            System.out.println("An exception ocurred!");
            Tester.printBad('A');
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