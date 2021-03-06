public class MyLinkedList {
    private int size = 0;
    private Node start, end;

    public MyLinkedList() { } // no need to do anything

    public int size() {
        return size;
    }

    public boolean add(String value) {
        Node node = new Node(value);
        if (size == 0) {
            start = node;
        } else {
            end.setNext(node);
            node.setPrev(end);
        }
        end = node;

        size++;

        return true;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + "is out of bounds, use an index from 0-" + size() + " non-inclusive");
        }

        Node current;
        
        if (index < size / 2) {
            current = start;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            current = end;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
        }
        return current;
    }

    public void add(int index, String value) {
        if (index == size) {
            add(value);
            return;
        }

        Node node = new Node(value);
        if (index == 0) {
            size++;
            start.setPrev(node);
            node.setNext(start);

            start = node;
            return;
        }

        Node current = getNode(index);
        Node behind = current.getPrev();
        size++; // update size AFTER getting "current" to not mess with the backwards traversal

        behind.setNext(node);
        current.setPrev(node);
        node.setNext(current);
        node.setPrev(behind);
    }
    
    public String get(int index) {
        return getNode(index).getData();
    }

    public String set(int index, String value) {
        Node current = getNode(index);
        String s = current.getData();
        current.setData(value);
        return s;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String s = "[";
        Node current = start;
        s += current.getData();
        while (current.getNext() != null) {
            current = current.getNext();
            s += ", " + current.getData();
        }
        s += "]";
        return s;
    }

    public String toStringReversed() {
        if (size == 0) {
            return "[]";
        }
        String s = "[";
        Node current = end;
        s += current.getData();
        while (current.getPrev() != null) {
            current = current.getPrev();
            s += ", " + current.getData();
        }
        s += "]";
        return s;
    }

    public String remove(int index) {
        Node current = getNode(index);
        String out = current.getData();

        Node prev = current.getPrev();
        Node next = current.getNext();

        // if there is no prev, this must be the head
        if (prev == null) {
            start = next; // make the next node the new start
        } else {
            prev.setNext(next); // otherwise, the prev should point to the next
        }
        // if there is not next, this must be the tail
        if (next == null) {
            end = prev; // make the prev node the new end
        } else {
            next.setPrev(prev); // otherwise, the next should point to the prev
        }

        // if the start is null, end must also be null
        // if they aren't null, set their 'outer pointers' to null
        if (start != null) {
            start.setPrev(null);
            end.setNext(null);
        }
        size--;
        return out;
    }

    public void extend(MyLinkedList other) {
        if (other == null || other.size == 0) {
            return;
        }

        if (size == 0) {
            start = other.start;
        } else {
            end.setNext(other.start);
            other.start.setPrev(end);
        }

        end = other.end;
        size += other.size;

        // obliterate the other list
        other.start = null;
        other.end = null;
        other.size = 0;
    }
}