//Chapter 2: Linked Lists

import java.util.HashMap;

class LinkedListNode {
	public LinkedListNode next;
	public LinkedListNode prev;
	public LinkedListNode last;
	public int data;
	public LinkedListNode(int d, LinkedListNode n, LinkedListNode p) {
		data = d;
		setNext(n);
		setPrevious(p);
	}
	
	public LinkedListNode(int d) {
		data = d;
	}	
	
	public LinkedListNode() { }
	
	public void deleteNode(LinkedListNode n) {
		if (this.prev == null) {
			
		} else {
			this.next.prev = this.prev;
			this.prev.next = this.next;
		}
	}

	public void setNext(LinkedListNode n) {
		next = n;
		if (this == last) {
			last = n;
		}
		if (n != null && n.prev != this) {
			n.setPrevious(this);
		}
	}
	
	public void setPrevious(LinkedListNode p) {
		prev = p;
		if (p != null && p.next != this) {
			p.setNext(this);
		}
	}	
	
	public String printForward() {
		//System.out.println(this);
		if (next != null) {
			return data + "->" + next.printForward();
		} else {
			return ((Integer) data).toString();
		}
	}
	
	public LinkedListNode clone() {
		LinkedListNode next2 = null;
		if (next != null) {
			next2 = next.clone();
		}
		LinkedListNode head2 = new LinkedListNode(data, next2, null);
		return head2;
	}
}

public class Linked_Lists {

	public static void removeDups (LinkedListNode n) {
		LinkedListNode tempnode1 = new LinkedListNode();
		LinkedListNode tempnode2 = new LinkedListNode();
		tempnode2 = n;
		tempnode1 = n;		
		n = n.next;
		
		while (tempnode1 != null) {
			
			while (n != null) {
				System.out.println(n.data);
				
				if (n.data == tempnode1.data) {
					tempnode1.data = -1;
				}
				
				n = n.next;
			} 
			
			tempnode1 = tempnode1.next;
			if (tempnode1.next != null) {
				n = tempnode1.next;
			} else {
				break;
			}
		}

		while(tempnode2 != null) {
			if(tempnode2.data == -1 && tempnode2.prev != null) {
				tempnode2.deleteNode(tempnode2);
			} 
			tempnode2 = tempnode2.next;
		}
	}
	
	public static void kth_to_last (LinkedListNode n, int k) {
		int counter = 1;
		LinkedListNode temp = new LinkedListNode();
		temp = n;
		while (n.next != null) {
			counter++;
			n = n.next;
		}		

		while(counter > k) {
			temp = temp.next;
			counter--;
		}
		
		System.out.println(temp.data);
	}
	
	public static boolean delete_middle_node(LinkedListNode n) {
		if (n == null || n.next == null || n.prev == null) {
			return false;
		}
		
		n.next.prev = n.prev;
		n.prev.next = n.next;
		n.next = null;
		n.prev = null;
		return true;
	}
	
	public static void partition (LinkedListNode head, int part) {
		LinkedListNode less = new LinkedListNode();
		LinkedListNode moreq = new LinkedListNode();
		LinkedListNode temp = new LinkedListNode();
		
		while (head != null) {
			if (head.data < part) {
				less.data = head.data;
				temp = new LinkedListNode();
				less.next = temp;
				temp.prev = less;
				less = less.next;
			} else if (head.data >= part) {
				moreq.data = head.data;
				temp = new LinkedListNode();
				moreq.next = temp;
				temp.prev = moreq;
				moreq = moreq.next;
			}
			
			head = head.next;
		}
		
		moreq = moreq.prev;
		moreq.next = null;
		
		while(moreq.prev != null) {
			moreq = moreq.prev;
		}
		
		less.prev.next = moreq;
		
		while(less.prev != null) {
			less = less.prev;
		}
		
		
		System.out.println(less.printForward());

	}
	
	public static LinkedListNode sum_lists (LinkedListNode n1, LinkedListNode n4) {
		
		int sum = 0;
		int carryover = 0;
		LinkedListNode res_list = new LinkedListNode();
		LinkedListNode temp_node = new LinkedListNode();
		
		while((n1 != null) || (n4 != null)) {
			
			if(n1 == null) {
				sum = (0 + n4.data) % 10 + carryover;
			} else if (n4 == null) {
				sum = (n1.data + 0) % 10 + carryover;
			} else {
				sum = (n1.data + n4.data) % 10 + carryover;
				if ((n1.data + n4.data) >= 10) {
					carryover++;
				}
			}
	
			res_list.data = sum;
			System.out.print(res_list.data + "->");
			temp_node = new LinkedListNode(0);
			res_list.setNext(temp_node);
			res_list = res_list.next;
			
			if(carryover > 0 && (n1.prev != null)) {
				carryover--;
			}
			
			if (n1 != null && n1.next != null) {
				n1 = n1.next;
			} else {
				n1 = null;
			}
			
			if (n4 != null && n4.next != null) {
				n4 = n4.next;
			} else {
				n4 = null;
			}
		}
		return res_list;
	}
	
	public static boolean palindrome (LinkedListNode n) {
		
		LinkedListNode forward = new LinkedListNode();
		LinkedListNode backward = new LinkedListNode();
		
		forward = n;
		backward = n;
		int length = 1;
		
		if (forward.next == null) {
			return true;
		}
		
		do {
			length++;
			backward = backward.next;
		} while (backward.next != null);
		
		while(length > 0) {
			if (forward.data != backward.data) {
				return false;
			}
			forward = forward.next;
			backward = backward.prev;
			length -= 2;
		}
		return true;
	}
	
	public static boolean intersection (LinkedListNode n1, LinkedListNode n2) {
		LinkedListNode head1 = n1;
		LinkedListNode head2 = n2;
		int diff = 0;
		int length1 = 1;
		int length2 = 1;
		
		LinkedListNode longer = new LinkedListNode();
		LinkedListNode shorter = new LinkedListNode();

		do {
			n1 = n1.next;
			length1++;
		} while(n1.next != null);
		
		do {
			n2 = n2.next;
			length2++;
		} while(n2.next != null);
		
		diff = Math.abs(length1 - length2);
		
		if (length1 >= length2) {
			longer = head1;
			shorter = head2;
			while (diff > 0) {
				longer = longer.next;
				diff--;
			}
		} else {
			longer = head2;
			shorter = head1;
			while (diff > 0) {
				longer = longer.next;
				diff--;
			}
		} 
		
		while (longer != null && shorter != null) {
			if(longer == shorter) {
				System.out.println(longer);
				System.out.println(longer.data);
				return true;
			}
			
			shorter = shorter.next;
			longer = longer.next;
		}
		return false;
	}
	
	public static LinkedListNode loop_detection(LinkedListNode n) {
		
	    HashMap<LinkedListNode, Boolean> nodesHash = new HashMap<LinkedListNode, Boolean>();	    
	    
	    while(nodesHash.get(n) == null && n != null) {
	    	
		    nodesHash.put(n, true);    	
	    	n = n.next;
	    }
	    
	    if (nodesHash.get(n) != null) {
	    	System.out.println("Loop detected at value " + n.data);
	    	return n;
	    } else {
	    	System.out.println("No loop detected.");
	    }
		
		return null;
	}

	public static void main(String[] args) {
		
		LinkedListNode n1 = new LinkedListNode(1);
		LinkedListNode n2 = new LinkedListNode(2, null, n1);
		n1.setNext(n2);
		LinkedListNode n3 = new LinkedListNode(557, null, n2);
		n2.setNext(n3);
		LinkedListNode n4 = new LinkedListNode(4, null, n3);
		n3.setNext(n4);
		LinkedListNode n5 = new LinkedListNode(5, null, n4);
		n4.setNext(n5);
//		LinkedListNode n6 = new LinkedListNode(2, null, n5);
//		n5.setNext(n6);
//		LinkedListNode n7 = new LinkedListNode(6, null, n6);
//		n6.setNext(n7);
//		LinkedListNode n8 = new LinkedListNode(3, null, n7);
//		n7.setNext(n8);
//		LinkedListNode n9 = new LinkedListNode(3, null, n8);
//		n8.setNext(n9);
//		LinkedListNode n10 = new LinkedListNode(0, null, n9);
//		n9.setNext(n10);
//
		LinkedListNode l21 = new LinkedListNode(4);
		LinkedListNode l22 = new LinkedListNode(5);
		LinkedListNode l23 = new LinkedListNode(6);
		LinkedListNode l24 = new LinkedListNode(7);

		
		l21.setNext(l22);
		l22.setNext(l23);
		l23.setNext(l24);
		l24.setNext(l22);
		
//		removeDups(n1);
//		while (n1.data == -1) {
//			n1 = n1.next;
//			System.out.println("Linked list: " + n1.printForward());
//		} 
		
		
//		LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
//		LinkedListNode head = first;
//		LinkedListNode second = first;
//		for (int i = 1; i < 8; i++) {
//			second = new LinkedListNode(i + 2, null, null);
//			first.setNext(second);
//			second.setPrevious(first);
//			first = second;
//		}
		//System.out.println(head.printForward());
		
		//kth_to_last(head, 6);
		
//		System.out.println(n1.printForward());
//		delete_middle_node(n6);
//		System.out.println(n1.printForward());
//		delete_middle_node(n9);
//		System.out.println(n1.printForward());
		
//		partition(n1, 7);
//		sum_lists(n1, n4);
		//System.out.println(palindrome(n1));
//		System.out.println(intersection(n1,l21));
		System.out.println(loop_detection(l21));
	}
}
