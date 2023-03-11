//Chapter 4: Trees and Graphs

package trees_and_Graphs;

import java.util.NoSuchElementException;
import java.util.Arrays;

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

class treeNode{
	public String name;
	public int value;
	public int numOfChildren = 0;
	public treeNode leftChild;
	public treeNode rightChild;
	public treeNode[] children = {null, null};
	
	public void addChild(treeNode x) {
		if (this.leftChild == null) {
			this.leftChild = x;
		} else {
			this.rightChild = x;
		}
		this.children[this.numOfChildren] = x;
		this.numOfChildren++;
	}
	
	public treeNode() {
	}

	public treeNode(int value) {
		this.value = value;
	}
}

class Node {
    private Node adjacent[];
    public int adjacentCount;
    private String vertex;
    public Boolean marked = false;
    public Node next;
//    public Question.State state;
    public Node(String vertex, int adjacentLength) {
        this.vertex = vertex;                
        adjacentCount = 0;        
        adjacent = new Node[adjacentLength];
    }
    
    public void addAdjacent(Node x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }
    public Node[] getAdjacent() {
        return adjacent;
    }
    public String getVertex() {
        return vertex;
    }
}

class Queue {
	private Node first;
	private Node last;

	public void add(Node item) {
		if (last != null) {
			last.next = item;
		}
		last = item;
		if (first == null) {
			first = last;
		}
	} 

	public Node remove() {
		if (first == null) throw new NoSuchElementException();
		Node n = first;
		first = first.next;
		if (first == null) {
			last = null;
		}
		return n;
	}
	
	public Node peek() {
		if (first == null) throw new NoSuchElementException();
		return first;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
}
class Graph {
	public static int MAX_VERTICES = 6;
	private Node vertices[];
	public int count;
	public Graph() {
		vertices = new Node[MAX_VERTICES];
		count = 0;
    }
	
    public void addNode(Node x) {
		if (count < vertices.length) {
			vertices[count] = x;
			count++;
		} else {
			System.out.print("Graph full");
		}
    }
    
    public Node[] getNodes() {
        return vertices;
    }
}

public class Trees_and_Graphs {
	
	public static boolean route_between_nodes(Node root, Node dest) {
		Queue queue = new Queue();
		root.marked = true;
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node r = queue.remove();
			if(r == dest) {
				System.out.println("There is a path between " + root.getVertex() + " and " + dest.getVertex());
				return true;
			}
			for(int i = 0; i < r.adjacentCount; i++){
				if(r.getAdjacent()[i].marked == false);
					r.getAdjacent()[i].marked = true;
					queue.add(r.getAdjacent()[i]);
			}
		}
		System.out.println("There is no path between the two nodes " + root.getVertex() + " and " + dest.getVertex());
		return false;
	}
	
	//my solution:
	public static treeNode MinimalTree(int[] ar, int start, int end) {
		treeNode root = new treeNode();
		
		if (ar.length > 1) {
			root = new treeNode(ar[ar.length / 2]);
			root.leftChild = MinimalTree(Arrays.copyOfRange(ar, start, (end / 2) + 1), start, (end / 2));
			root.rightChild = MinimalTree(Arrays.copyOfRange(ar, (ar.length / 2) + 1, end + 1), (ar.length / 2) + 1, end);
			return root;
		} //else if (side == Side.left){
//			root.leftChild = new treeNode(ar[0]);
//		} else if (side == Side.right){
////			root.rightChild = new treeNode(ar[0]);
//		}		
		return null;
	}
	
	public static treeNode createMinimalBST(int array[]) {
		 return createMinimalBST(array, 0, array.length - 1);
	 }
	public static treeNode createMinimalBST(int arr[], int start, int end) {
		if (end< start) {
			return null;
		}
		int mid= (start + end)/ 2;
		treeNode n = new treeNode(arr[mid]);
		n.leftChild = createMinimalBST(arr, start, mid - 1);
		n.rightChild = createMinimalBST(arr, mid+ 1, end);
		return n;
	}
	
	public static treeNode listOfDepths (treeNode root, int depth) {
		LinkedListNode node_list = new LinkedListNode();
		LinkedListNode new_node = new LinkedListNode();
		
		if(depth == 1) {
			node_list = new LinkedListNode(root.value);
			System.out.println("Node(s) on depth " + depth + " is/are: " + node_list.data);
			node_list = new LinkedListNode();
		}
		
		if(root.leftChild != null) {
			new_node = new LinkedListNode(listOfDepths(root.leftChild, depth + 1).value);
			node_list.setNext(new_node);
			node_list = node_list.next;
		}
		
		if(root.rightChild != null) {
			new_node = new LinkedListNode(listOfDepths(root.rightChild, depth + 1).value);
			node_list.setNext(new_node);
			node_list = node_list.next;
		}
		
		if(root.leftChild == null && root.rightChild == null) {
			return root;
		}
		
		while(node_list.prev.prev != null) {
			node_list = node_list.prev;
		}
		
		System.out.print("Node(s) on depth " + (depth + 1) + " is/are: ");
		while(node_list != null) {
			System.out.print(node_list.data + ", ");
			node_list = node_list.next;
		}
		System.out.println("");
		
		return root;
	}
	
	public static void main(String[] args) {

//		Graph g = new Graph();
//		Node n1 = new Node("A", 1);
//		Node n2 = new Node("B", 2);
//		Node n3 = new Node("C", 3);
//		Node n4 = new Node("D", 4);
//		Node n5 = new Node("E", 5);
//		Node n6 = new Node("F", 6);
//		n1.addAdjacent(n2);
//		n2.addAdjacent(n3);
//		n3.addAdjacent(n5);
//		n5.addAdjacent(n6);
//		n5.addAdjacent(n4);
//		n4.addAdjacent(n2);
//
//		g.addNode(n1);
//		g.addNode(n2);
//		g.addNode(n3);
//		g.addNode(n4);
//		g.addNode(n5);
//		g.addNode(n6);
//		System.out.println(g.getNodes()[0].getVertex());
//		System.out.println(g.getNodes()[0].getAdjacent()[0].getVertex());
//		System.out.println(g.getNodes()[1].getVertex());
//		System.out.println(g.getNodes()[2].getVertex());
//		System.out.println(route_between_nodes(n6,n2));
//		int[] ar = {1,2,3,4,5,6,7,8,9,10};
 		
 		
// 		int start = 0;
// 		int end = 9;
// 		int[] new_ar = Arrays.copyOfRange(ar, (ar.length / 2) + 1, end + 1);
// 		
//		System.out.println(ar, start, (end / 2) + 1);
//		for (int element: new_ar) {
//            System.out.println(element);
//      }
//		treeNode root = MinimalTree(ar, 0, ar.length - 1);
//		treeNode root2 = createMinimalBST(ar);
		treeNode root = new treeNode(3);
		treeNode ch1 = new treeNode(2);
		treeNode ch2 = new treeNode(1);
		treeNode ch3 = new treeNode(4);
		treeNode ch4 = new treeNode(5);
		treeNode ch5 = new treeNode(6);
		treeNode ch6 = new treeNode(7);

		root.addChild(ch1);
		root.addChild(ch2);
		ch1.addChild(ch3);
		ch1.addChild(ch4);
		ch2.addChild(ch5);
		ch5.addChild(ch6);
		System.out.println(root.leftChild.value);
		System.out.println(root.rightChild.value);
		System.out.println(root.children[0].value);
		System.out.println(root.children[1].value);
		listOfDepths(root, 1);

	}
}
