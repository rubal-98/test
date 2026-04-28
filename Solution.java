import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    private static class Node {
        String customerId;
        Node prev, next;

        Node(String customerId) {
            this.customerId = customerId;
        }
    }

    private static class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(null);
            tail = new Node(null);
            head.next = tail;
            tail.prev = head;
        }

        Node addLast(String customerId) {
            Node node = new Node(customerId);
            node.prev      = tail.prev;
            node.next      = tail;
            tail.prev.next = node;
            tail.prev      = node;
            size++;
            return node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev      = null;
            node.next      = null;
            size--;
        }

        Node peekFirst() {
            return head.next == tail ? null : head.next;
        }
    }

    private final HashMap<String, Node> visitedOnceMap = new HashMap<>();
    private final HashSet<String>       visitedBefore  = new HashSet<>();
    private final DoublyLinkedList      orderList      = new DoublyLinkedList();

    public void postCustomerVisit(String customerId) {
        if (visitedBefore.contains(customerId)) {
            return;
        }
        if (visitedOnceMap.containsKey(customerId)) {
            Node node = visitedOnceMap.remove(customerId);
            orderList.remove(node);
            visitedBefore.add(customerId);
        } else {
            Node node = orderList.addLast(customerId);
            visitedOnceMap.put(customerId, node);
        }
    }

    public String getFirstOneTimeVisitor() {
        Node first = orderList.peekFirst();
        return first == null ? null : first.customerId;
    }

    public static void main(String[] args) {
        Solution service = new Solution();

        service.postCustomerVisit("c1");
        service.postCustomerVisit("c2");
        service.postCustomerVisit("c3");

        System.out.println(service.getFirstOneTimeVisitor());  // c1

        service.postCustomerVisit("c1");
        System.out.println(service.getFirstOneTimeVisitor());  // c2

        service.postCustomerVisit("c2");
        System.out.println(service.getFirstOneTimeVisitor());  // c3

        service.postCustomerVisit("c3");
        System.out.println(service.getFirstOneTimeVisitor());  // null
    }
}
