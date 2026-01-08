import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> implements Collection<E>, Cloneable, Serializable {

    protected TreeNode<E> root;
    protected int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public BinarySearchTree(E[] objects) {
        for (E e : objects) insert(e);
    }

    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return true;
        }
        return false;
    }

    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                int cmp = e.compareTo(current.element);
                if (cmp < 0) { parent = current; current = current.left; }
                else if (cmp > 0) { parent = current; current = current.right; }
                else return false;
            }
            if (e.compareTo(parent.element) < 0) parent.left = createNewNode(e);
            else parent.right = createNewNode(e);
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    public boolean remove(Object e) {
        return delete((E)e);
    }

    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.element);
            if (cmp < 0) { parent = current; current = current.left; }
            else if (cmp > 0) { parent = current; current = current.right; }
            else break;
        }
        if (current == null) return false;

        if (current.left == null) {
            if (parent == null) root = current.right;
            else if (parent.left == current) parent.left = current.right;
            else parent.right = current.right;
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) parentOfRightMost.right = rightMost.left;
            else parentOfRightMost.left = rightMost.left;
        }
        size--;
        return true;
    }

    public int getSize() { return size; }
    public TreeNode<E> getRoot() { return root; }
    public E getElement(TreeNode<E> node) { return node.element; }

    public int getHeight() {
        if (root == null) return 0;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode<E> node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            height++;
        }
        return height;
    }

    public void inorder() { inorder(root); }
    private void inorder(TreeNode<E> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.element + " ");
        inorder(node.right);
    }

    public void preorder() { preorder(root); }
    private void preorder(TreeNode<E> node) {
        if (node == null) return;
        System.out.print(node.element + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder() { postorder(root); }
    private void postorder(TreeNode<E> node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.element + " ");
    }

    public void breadthFirstTraversal() {
        if (root == null) return;
        Queue<TreeNode<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode<E> node = q.poll();
            System.out.print(node.element + " ");
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
    }

    public E first() {
        if (root == null) return null;
        TreeNode<E> node = root;
        while (node.left != null) node = node.left;
        return node.element;
    }

    public E last() {
        if (root == null) return null;
        TreeNode<E> node = root;
        while (node.right != null) node = node.right;
        return node.element;
    }

    public E ceiling(E e) {
        TreeNode<E> curr = root;
        E res = null;
        boolean found = false;
        while (curr != null) {
            int cmp = e.compareTo(curr.element);
            if (cmp < 0) { 
            	res = curr.element; 
            	curr = curr.left; 
            } else if (cmp > 0) {
            	curr = curr.right;
            } else {
            	found = true;
            	break;
            }
        }
        
        if (found) {
        	if (curr.right == null) {
        		return null;
        	}
        	TreeNode<E> node = curr.right;
        	while (node.left != null) {
        		node = node.left;
        	}
        	return node.element;
        }
        return res;
    }

    public E floor(E e) {
        TreeNode<E> curr = root;
        E res = null;
        boolean found = false;
        
        while (curr != null) {
            int cmp = e.compareTo(curr.element);
            if (cmp > 0) { 
            	res = curr.element; 
            	curr = curr.right; 
            } else if (cmp < 0) {
            	curr = curr.left;
            } else {
            	found = true;
            	break;
            }
        }
        
        if (found) {
        	if (curr.left == null) {
        		return null;
        	}
        	TreeNode<E> node = curr.left;
        	while (node.right != null) {
        		node = node.right;
        	}
        	return node.element;
        }
        return res;
    }

    public void saveTree(String filename) throws IOException {
    	try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            saveNode(root, out);
        }
    }

    private void saveNode(TreeNode<E> node, ObjectOutputStream out) throws IOException {
        if (node == null) {
            out.writeBoolean(false); // marker for null
            return;
        }
        out.writeBoolean(true);      // marker for valid node
        out.writeObject(node.element);
        saveNode(node.left, out);
        saveNode(node.right, out);
    }

    @SuppressWarnings("unchecked")
    public void loadTree(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            root = loadNode(in);
            size = computeSize(root);
        }
    }

    private TreeNode<E> loadNode(ObjectInputStream in) throws IOException, ClassNotFoundException {
        if (!in.readBoolean()) return null; // null marker
        E element = (E) in.readObject();
        TreeNode<E> node = new TreeNode<>(element);
        node.left = loadNode(in);
        node.right = loadNode(in);
        return node;
    }

    private int computeSize(TreeNode<E> node) {
        if (node == null) return 0;
        return 1 + computeSize(node.left) + computeSize(node.right);
    }
    
    @Override
    public BinarySearchTree<E> clone() {
        BinarySearchTree<E> copy = new BinarySearchTree<>();
        copy.root = cloneSubtree(root);
        copy.size = size;
        return copy;
    }

    private TreeNode<E> cloneSubtree(TreeNode<E> node) {
        if (node == null) return null;
        TreeNode<E> newNode = new TreeNode<>(node.element);
        newNode.left = cloneSubtree(node.left);
        newNode.right = cloneSubtree(node.right);
        return newNode;
    }

    @Override
    public boolean contains(Object o) { return search((E)o); }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) if (!contains(o)) return false;
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) { boolean changed = false; for (E e : c) changed |= add(e); return changed; }
    @Override
    public boolean removeAll(Collection<?> c) { boolean changed = false; for (Object o : c) changed |= remove(o); return changed; }
    @Override
    public boolean retainAll(Collection<?> c) {
        List<E> toRemove = new ArrayList<>();
        for (E e : this) if (!c.contains(e)) toRemove.add(e);
        for (E e : toRemove) remove(e);
        return !toRemove.isEmpty();
    }

    @Override
    public Object[] toArray() {
        List<E> list = new ArrayList<>();
        Queue<TreeNode<E>> q = new LinkedList<>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            TreeNode<E> node = q.poll();
            list.add(node.element);
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] arr = toArray();
        if (a.length < arr.length) a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), arr.length);
        System.arraycopy(arr, 0, a, 0, arr.length);
        return a;
    }

    @Override
    public boolean isEmpty() { return size == 0; }
    @Override
    public int size() { return size; }
    @Override
    public boolean add(E e) { return insert(e); }
    @Override
    public void clear() { root = null; size = 0; }
    @Override
    public Iterator<E> iterator() {
        List<E> list = new ArrayList<>();
        inorderCollect(root, list);
        return list.iterator();
    }

    private void inorderCollect(TreeNode<E> node, List<E> list) {
        if (node == null) return;
        inorderCollect(node.left, list);
        list.add(node.element);
        inorderCollect(node.right, list);
    }

    // Non-recursive traversals for TestProject4
    public void nonRecursiveInorder() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> current = root;
        List<E> list = new ArrayList<>();
        while (!stack.isEmpty() || current != null) {
            while (current != null) { stack.push(current); current = current.left; }
            current = stack.pop();
            list.add(current.element);
            current = current.right;
        }
        System.out.println(list);
    }

    public void nonRecursivePreorder() {
        if (root == null) { System.out.println("[]"); return; }
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        List<E> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            list.add(node.element);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        System.out.println(list);
    }

    public void nonRecursivePostorder() {
        if (root == null) { System.out.println("[]"); return; }
        Stack<TreeNode<E>> s1 = new Stack<>();
        Stack<TreeNode<E>> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            TreeNode<E> node = s1.pop();
            s2.push(node);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }
        List<E> list = new ArrayList<>();
        while (!s2.isEmpty()) list.add(s2.pop().element);
        System.out.println(list);
    }

    protected static class TreeNode<E> implements Serializable {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;
        public TreeNode(E e) { element = e; }
        public String toString() { return element.toString(); }
    }
}