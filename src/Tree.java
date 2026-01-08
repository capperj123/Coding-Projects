import java.util.*;

public interface Tree<E> extends Collection<E> {

	/** Return true if the element is in the tree */
	public boolean search(E e);
	
	/** Insert element e into the binary tree
	 * Return true if the element is inserted successfully */
	public boolean insert(E e);

	/** Delete the specified element from the tree
	 * Return true if the element is deleted successfully */
	public boolean delete(E e);

	/** Get the number of elements in the tree */
	public int getSize();

	/** Inorder traversal from the root*/
	public void inorder() ;

	/** Inorder non recursive form root */
	public void nonRecursiveInorder(); // Left as exercise

	/** Postorder traversal from the root */
	public void postorder();

	/** Postorder non recursive form root */
	public void nonRecursivePostorder() ; // Customize Eoolbar...

	/** Preorder traversal from the root */
	public void preorder();

	/** Preorder non recursive form root */
	public void nonRecursivePreorder(); // Left as exercise

	/** Return the height of this binary tree */
	public int height(); // Left as exercise

	/** BreadthFirstSearch traversal from the root */
	public void breadthFirstTraversal(); // Left as exercise

	@Override /** Return true if the tree is empty */
	public default boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public default boolean contains(Object e) {
		return search((E)e);
	}

	@Override
	public default boolean add(E e) {
		return insert(e);
	}

	@Override
	public boolean remove(Object e);
	
	@Override
	public default int size() {
		return getSize();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean containsAll(Collection<?> c); // Left as exercise

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> c); // Left as exercise

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c); // Left as exercise

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c); // Left as exercise

	@Override
	public Object[] toArray(); // Left as exercise

	@SuppressWarnings("unchecked")
	public default <E> E[] toArray(E[] array) { // Left as exercise
		return null;
	}
}