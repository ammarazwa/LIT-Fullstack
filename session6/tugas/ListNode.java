package tugas;

public class ListNode {
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public void print() {
        System.out.print(this.value);
        if (this.next != null) {
            System.out.print(" -> ");
            this.next.print();
        } else {
            System.out.println();
        }
    }

    public static int count(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static int countRec(ListNode node) {
        if (node == null) return 0;
        return 1 + countRec(node.next);
    }

    public static ListNode append(ListNode head, int value) {
        if (head == null) return new ListNode(value);

        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new ListNode(value);
        return head;
    }

    public static ListNode appendRec(ListNode head, int value) {
        if (head == null) return new ListNode(value);
        if (head.next == null) {
            head.next = new ListNode(value);
        } else {
            appendRec(head.next, value);
        }
        return head;
    }

    public static int sumLL(ListNode node) {
        int total = 0;
        while (node != null) {
            total += node.value;
            node = node.next;
        }
        return total;
    }

    public static int sumLLRec(ListNode node) {
        if (node == null) return 0;
        return node.value + sumLLRec(node.next);
    }

    public static int findMax(ListNode node) {
        if (node == null) throw new IllegalArgumentException("List is empty!");
        int currMax = node.value;
        while (node != null) {
            if (node.value > currMax) currMax = node.value;
            node = node.next;
        }
        return currMax;
    }

    public static int findMaxRec(ListNode node) {
        if (node.next == null) return node.value;
        return Math.max(node.value, findMaxRec(node.next));
    }

    public static ListNode remove(ListNode head, int value) {
        ListNode sentinel = new ListNode(0, head);
        ListNode curr = sentinel;
        while (curr.next != null) {
            if (curr.next.value == value) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return sentinel.next;
    }

    public static ListNode removeRec(ListNode head, int value) {
        if (head == null) return null;
        head.next = removeRec(head.next, value);
        return head.value == value ? head.next : head;
    }

    public static boolean search(ListNode head, int target) {
        ListNode curr = head;
        while (curr != null) {
            if (curr.value == target) return true;
            curr = curr.next;
        }
        return false;
    }

    public static boolean searchRec(ListNode head, int target) {
        if (head == null) return false;
        return head.value == target || searchRec(head.next, target);
    }

    public static ListNode insert(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        if (head == null || value < head.value) {
            newNode.next = head;
            return newNode;
        }
        ListNode curr = head;
        while (curr.next != null && curr.next.value < value) {
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }

    public static ListNode insertRec(ListNode head, int value) {
        if (head == null || value < head.value) {
            return new ListNode(value, head);
        }
        head.next = insertRec(head.next, value);
        return head;
    }

    public static ListNode removeFirst(ListNode head, int target) {
        if (head == null) return null;
        if (head.value == target) return head.next;
        ListNode curr = head;
        while (curr.next != null && curr.next.value <= target) {
            if (curr.next.value == target) {
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }
        return head;
    }

    public static ListNode removeFirstRec(ListNode head, int target) {
        if (head == null) return null;
        if (head.value == target) return head.next;
        head.next = removeFirstRec(head.next, target);
        return head;
    }

    public static Integer findMiddle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    public static Integer kthFromLast(ListNode head, int k) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            if (fast != null) fast = fast.next;
            else return null;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.value;
    }

    public static ListNode sumTwoLL(ListNode head1, ListNode head2) {
        ListNode curr1 = head1, curr2 = head2;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (curr1 != null || curr2 != null) {
            int sum = 0;
            if (curr1 != null) {
                sum += curr1.value;
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                sum += curr2.value;
                curr2 = curr2.next;
            }
            tail.next = new ListNode(sum);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}