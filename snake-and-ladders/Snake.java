package snakeandladders;

public class Snake {
    private final int head;
    private final int tail;

    public Snake(int head, int tail) {
        if (tail >= head) {
            throw new IllegalArgumentException("Snake tail must be below head: head=" + head + ", tail=" + tail);
        }
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    @Override
    public String toString() {
        return "Snake[" + head + " -> " + tail + "]";
    }
}
