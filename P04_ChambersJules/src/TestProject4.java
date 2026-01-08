import java.util.*;
import java.io.*;

public class TestProject4 {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new TestProject4();
	}

	public TestProject4() throws IOException, ClassNotFoundException {
		Random rand = new Random(100); // Seed for reproducibility
		BinarySearchTree<Integer> t = new BinarySearchTree<>(new Integer[] {50,40,60,30,45,55,47,52,46,53});
		System.out.println("Ceiling of 50: " + t.ceiling(50));
		System.out.println("Ceiling of 60: " + t.ceiling(60));
		System.out.println("Ceiling of 40: " + t.ceiling(40));
		System.out.println("floor of 55: " + t.floor(55));
		System.out.println("floor of 45: " + t.floor(45));
		System.out.println("floor of 50: " + t.floor(50));
		System.out.println("lowest value: " + t.first());
		System.out.println("highest value: " + t.last());
		System.out.println("\n*****tree test*****\n");
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		for (int i = 1; i <= 50; i++) {
			tree.insert(1+rand.nextInt(100));
		}
		System.out.println("Preorder traversal:");
		tree.nonRecursivePreorder();;	
		System.out.println();
		System.out.println("Inorder traversal:");
		tree.nonRecursiveInorder();
		System.out.println();
		System.out.println("Postorder traversal:");
		tree.nonRecursivePostorder();
		System.out.println();
		System.out.println("Level order traversal:");
		tree.breadthFirstTraversal();
		System.out.println();
		System.out.println("Height of tree: " + tree.getHeight());
		System.out.println("Number of nodes: " + tree.getSize());
		tree.saveTree("tree.dat");
		BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
		tree2.loadTree("tree.dat");
		System.out.println("\n*********tree2 test\n");
		System.out.println("Height of tree: " + tree2.getHeight());
		System.out.println("Number of nodes: " + tree2.getSize());
		System.out.println("Preorder traversal:");
		tree2.nonRecursivePreorder();;
		System.out.println();
		System.out.println("Inorder traversal:");
		tree2.nonRecursiveInorder();
		System.out.println();
		System.out.println("Postorder traversal:");
		tree2.nonRecursivePostorder();
		System.out.println();
		System.out.println("Level order traversal:");
		tree2.breadthFirstTraversal();
		System.out.println();
		System.out.println("lowest value: " + tree2.first());
		System.out.println("highest value: " + tree2.last());
		System.out.println("floor of root: " + tree2.floor(tree2.getElement(tree2.getRoot())));
		System.out.println("highest of root: " + tree2.ceiling(tree2.getElement(tree2.getRoot())));
		BinarySearchTree<Integer> tree3 = new BinarySearchTree<>(new Integer[] {72,16,57,81,93,95});
		System.out.println("Set Operations");
		tree.inorder();
		System.out.println();
		tree3.inorder();
		System.out.println();
		System.out.println("Tree contains all Tree3? " + tree.containsAll(tree3));
		System.out.println("Tree removeAll all Tree3? " + tree.removeAll(tree3));
		tree.inorder();
		System.out.println();
		tree3.inorder();
		System.out.println();
		System.out.println("Cloned Tree");
		tree = tree2.clone();
		tree2.remove(1);
		tree2.remove(3);
		tree2.inorder();
		System.out.println();
		tree.inorder();
		System.out.println();
		System.out.println("\nTree retainAll all Tree3? "+tree.retainAll(tree3));
		tree.inorder();
		System.out.println();
		tree3.inorder();
		System.out.println();
		System.out.println("Testing toArray()");
		t.breadthFirstTraversal();
		System.out.println();
		System.out.println(Arrays.toString(t.toArray()));
		Integer[] array = new Integer[t.size()];
		t.toArray(array);
		System.out.println(Arrays.toString(array));
	}
}