package chapter4.linkedlist;
/**
 * 203. Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5
 * @author Lei
 *
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        
        while (head != null) {
            while (head != null && head.val == val) { // If equals, need to skip
                head = head.next;
            }
            pre.next = head;
            pre = head;
            
            if (head != null) {
                head = head.next;
            }
        }
        
        return dummy.next;
    }
}
