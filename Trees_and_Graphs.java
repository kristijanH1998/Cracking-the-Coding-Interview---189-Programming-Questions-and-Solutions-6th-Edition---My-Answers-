package trees_and_Graphs;

import java.util.NoSuchElementException;
import java.util.ArrayList;
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

class project{
	private String name;
	public ArrayList<project> dependent_on = new ArrayList<project>();
	public ArrayList<project> required_for = new ArrayList<project>();
	public int num_of_prereqs = 0;
	public int num_of_dependents = 0;
	
	public void addPrereq (project x) {
		this.dependent_on.add(x);
		x.required_for.add(this);
		this.num_of_prereqs++;
		x.num_of_dependents++;
	}
	
	public project(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class treeNode{
	public String name;
	public boolean visited;
	public boolean marked;
	public int value;
	public int numOfChildren = 0;
	public treeNode leftChild;
	public treeNode rightChild;
	public treeNode[] children = {null, null};
	public treeNode next;
	public treeNode parent;
	
	public void addLeftChild(treeNode x) {
		this.leftChild = x;
		this.children[this.numOfChildren] = x;
		this.numOfChildren++;
		x.parent = this;
	}
	
	public void addRightChild(treeNode x) {
		this.rightChild = x;
		this.children[this.numOfChildren] = x;
		this.numOfChildren++;
		x.parent = this;
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
	private treeNode first;
	private treeNode last;

	public void add(treeNode item) {
		if (last != null) {
			last.next = item;
		}
		last = item;
		if (first == null) {
			first = last;
		}
	} 

	public treeNode remove() {
		if (first == null) throw new NoSuchElementException();
		treeNode n = first;
		first = first.next;
		if (first == null) {
			last = null;
		}
		return n;
	}
	
	public treeNode peek() {
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
	
//	public static boolean route_between_nodes(Node root, Node dest) {
//		Queue queue = new Queue();
//		root.marked = true;
//		queue.add(root);
//		
//		while(!queue.isEmpty()) {
//			Node r = queue.remove();
//			if(r == dest) {
//				System.out.println("There is a path between " + root.getVertex() + " and " + dest.getVertex());
//				return true;
//			}
//			for(int i = 0; i < r.adjacentCount; i++){
//				if(r.getAdjacent()[i].marked == false);
//					r.getAdjacent()[i].marked = true;
//					queue.add(r.getAdjacent()[i]);
//			}
//		}
//		System.out.println("There is no path between the two nodes " + root.getVertex() + " and " + dest.getVertex());
//		return false;
//	}
	
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
	
	public static boolean checkBalanced(treeNode root) {
		
		Queue queue = new Queue();
		root.marked = true;
		queue.add(root);
		
		while(!queue.isEmpty()) {
			root = queue.remove();
			if (Math.abs(checkDepth(root.leftChild, 0, 0) - checkDepth(root.rightChild, 0, 0)) > 1) {
				return false;
			}
			for(treeNode n: root.children) {
				if(n!= null && n.marked == false) {
					n.marked = true;
					queue.add(n);
				}
			}
		}
		return true;
	}
	
	public static int checkDepth(treeNode root, int depth, int temp) {
		if(root == null) return depth;
		
		root.visited = true;
		
		for (treeNode n: root.children) {
			if(n != null && n.visited == false) {
				temp = checkDepth(n, depth + 1, temp);
			}
		}
		if (depth > temp) {
			temp = depth;
		}
		root.visited = false;
		return temp;
	}
	
	public static boolean validateBST(treeNode root) {
		if (root.leftChild == null && root.rightChild == null) return true;
		boolean bst;
		
		if(root.leftChild == null) {
			bst = root.value < minNode(root.rightChild);
			return bst && validateBST(root.rightChild);

		} else if (root.rightChild == null) {
			bst = root.value >= maxNode(root.leftChild);
			return bst && validateBST(root.leftChild);
		} else {
			bst = root.value >= maxNode(root.leftChild) && (root.value < minNode(root.rightChild));
			return bst && (validateBST(root.leftChild) && validateBST(root.rightChild));
		} 	
	}
	
	public static int maxNode (treeNode root) {
		if(root.leftChild == null && root.rightChild == null) {
			return root.value;
		}
		int max_value = root.value;
		int temp = 0;
		
		root.visited = true;
		for (treeNode n: root.children) {
			if(n != null && n.visited == false){
				temp = maxNode(n);
				if(root.value < temp && max_value < temp) {
					max_value = temp;
				} 
			}
		}
		
		root.visited = false;
		return max_value;
	}
	
	public static int minNode (treeNode root) {
		if(root.leftChild == null && root.rightChild == null) {
			return root.value;
		}
		int min_value = root.value;
		int temp = 0;
		
		root.visited = true;
		for (treeNode n: root.children) {
			if(n != null && n.visited == false){
				temp = minNode(n);
				if(root.value >= temp && min_value >= temp) {
					min_value = temp;
				} 
			}
		}
		
		root.visited = false;
		return min_value;
	}
	
	public static treeNode successor (treeNode root) {
		if(root.rightChild == null && root.leftChild == null) {
			if (root.value <= root.parent.value) {
				root = root.parent;
			} else {
				System.out.println("No successor node.");
				return null;
			}
		}
		
		if(root.rightChild != null) {
			root = root.rightChild;
		} else {
			System.out.println("No successor node.");
			return null;
		}
		
		while(root.leftChild != null) {
			root = root.leftChild;
		}
		return root;
	}
	
	public static ArrayList<project> buildOrder (ArrayList<project> projects) {
		int i = 0;
		ArrayList<project> order = new ArrayList<project>();
		while(i < projects.size()) {
			if(projects.get(i).dependent_on.size() == 0) {
				order.add(projects.get(i));
				projects.remove(i);
				i--;
			}
			i++;
		}
		i = 0;
		while(projects.size() != 0) {
			for(project p: order.get(i).required_for) {
				
				p.dependent_on.remove(order.get(i));
				
				if(p.dependent_on.size() == 0) {
					order.add(p);
					projects.remove(p);
				} 
			}
			i++;	
		}
		return order;
	}
	
	public static int node_depth = 0;
	
	public static void findNodeDepth(treeNode root, treeNode node, int depth) {
		
		if(root != null) {
			if(root == node) {
				node_depth = depth;
			}
			depth++;
			findNodeDepth(root.leftChild, node, depth);
			findNodeDepth(root.rightChild, node, depth);
		}
	}

	public static treeNode commonAncestor(treeNode root,treeNode node1, treeNode node2) {
		if(node1 == node2) {
			System.out.println("The two input nodes are the same node");
			return null;
		}
		findNodeDepth(root, node1,0);
		int depth1 = node_depth;
		findNodeDepth(root, node2,0);
		int depth2 = node_depth;
		int depth_difference = Math.abs(depth1-depth2);
		
		if(depth1 < depth2) {
			while(depth_difference != 0) {
				node2 = node2.parent;
				depth_difference--;
			}
		} else if (depth2 < depth1) {
			while(depth_difference != 0) {
				node1 = node1.parent;
				depth_difference--;
			}
		}
		while(node1 != node2) {
			node1 = node1.parent;
			node2 = node2.parent;
		} 
		return node1;
	}
	
	
	
	/*checkSubtree, addSubtree, and preOrderTraverse are three methods that together form a 
	solution to problem 4.10 Check Subtree */
	public static boolean checkSubtree(treeNode rootT1, treeNode rootT2) {
		
		ArrayList<String> list_of_paths = new ArrayList<String>();
		addSubtree(rootT1, list_of_paths); 
		for(String path: list_of_paths) {
			System.out.println(path);
		}
		
		String T2Path = preOrderTraverse(rootT2, "");
		System.out.println(T2Path);
		
		for(String path: list_of_paths) {
			if(T2Path.equals(path)) {
				return true;
			}
		}
		return false;
	}
	
	public static void addSubtree(treeNode root, ArrayList<String> list) {
		
		if(root != null) {
			if(!root.visited) {
				list.add(preOrderTraverse(root, ""));
			}
			root.visited = true;
			addSubtree(root.leftChild, list);
			addSubtree(root.rightChild, list);
			root.visited = false;
		}
	}
	
	public static String preOrderTraverse(treeNode node, String path) {
		
		if(node != null) {
			if(!node.visited) {
				path += (node.value + " ");
			}
			node.visited = true;
			path = preOrderTraverse(node.leftChild, path);
			path = preOrderTraverse(node.rightChild, path);
			node.visited = false;
		}
		return path;
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
//		treeNode root = new treeNode(3);
//		treeNode ch1 = new treeNode(2);
//		treeNode ch2 = new treeNode(1);
//		treeNode ch3 = new treeNode(4);
//		treeNode ch4 = new treeNode(5);
//		treeNode ch5 = new treeNode(6);
//		treeNode ch6 = new treeNode(7);
//
//		root.addChild(ch1);
//		root.addChild(ch2);
//		ch1.addChild(ch3);
//		ch1.addChild(ch4);
//		ch2.addChild(ch5);
//		ch5.addChild(ch6);
//		System.out.println(root.leftChild.value);
//		System.out.println(root.rightChild.value);
//		System.out.println(root.children[0].value);
//		System.out.println(root.children[1].value);
//		listOfDepths(root, 1);
		
		treeNode root = new treeNode(3);
		treeNode n1 = new treeNode(1);
		treeNode n2 = new treeNode(10);
		treeNode n3 = new treeNode(6);
		treeNode n4 = new treeNode(17);
		treeNode n5 = new treeNode(20);
		treeNode n6 = new treeNode(5);
		treeNode n7 = new treeNode(4);
		treeNode n8 = new treeNode(14);
		root.addLeftChild(n1);
		root.addRightChild(n2);
		n2.addLeftChild(n3);
		n2.addRightChild(n4);
		n4.addRightChild(n5);
		n3.addLeftChild(n6);
		n6.addLeftChild(n7);
		n4.addLeftChild(n8);
		
		treeNode testNode1 = new treeNode(1);
		treeNode testNode2 = new treeNode(2);
		treeNode testNode3 = new treeNode(3);
		testNode1.addLeftChild(testNode2);
		testNode1.addRightChild(testNode3);

//		for(treeNode n: n2.children) {
//			System.out.println(n.value);
//		}
		
//		System.out.println(checkDepth(root, 0, 0));
//		System.out.println(checkDepth(root.rightChild, 0, 0));
//		System.out.println(checkBalanced(n1));
				
//		validateBST(root);
//		System.out.println(maxNode(root));
//		System.out.println(minNode(root));
//		System.out.println(validateBST(root));
//		System.out.println(n4.parent.value);
//		System.out.println(n3.parent.value);
		
//		System.out.println(successor(root).value);
		
//		project a = new project("a");
//		project b = new project("b");
//		project c = new project("c");
//		project d = new project("d");
//		project e = new project("e");
//		project f = new project("f");
//		project g = new project("g");
//		
//		b.addPrereq(f);
//		c.addPrereq(f);
//		a.addPrereq(f);
//		a.addPrereq(c);
//		a.addPrereq(b);
//		e.addPrereq(a);
//		e.addPrereq(b);
//		g.addPrereq(d);

//		project[] project_ar = {a,b,c,d,e,f,g};
//		ArrayList<project> projects = new ArrayList<project>();
//		for(int i = 0; i < project_ar.length; i++) {
//			projects.add(project_ar[i]);
//		}
//		System.out.println(d.dependent_on.get(0).getName());
//		System.out.println(d.dependent_on.get(1).getName());
//		System.out.println(f.required_for.get(0).getName());
//		System.out.println(f.required_for.get(1).getName());

//		projects = buildOrder(projects);
//		for(int i = 0; i < projects.size(); i++) {
//			System.out.print(projects.get(i).getName() + " ");
//		}
		
//		System.out.println(commonAncestor(root, n6,n4).value);
		String result = checkSubtree(root, testNode1) ? "Yes, T2 is a subtree in T1" : "No, T2 is"
				+ " not a subtree in T1";
		System.out.println(result);
	}
}
