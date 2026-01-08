import java.util.*;

public class MyCircularDoubleLinkedList<E extends Comparable<E>> implements MyList<E>{
	private Node<E> head, tail;	
	private int size;
	
	private static class Node<E> {
	      E element;
	      Node<E> next;
	      Node<E> previous;
	   
	      public Node(E element) {
	         this.element = element;
	      }
	}
	
	/** Create a default list */
	public MyCircularDoubleLinkedList() {
		head = tail = null;
		size = 0;
	}

	/** Create a list from an array of Objects */
	public MyCircularDoubleLinkedList(E[] Objects) {
		// Left as exercise
		for (int i = 0; i < Objects.length; i++) {
			addLast(Objects[i]);
		}
		if (tail != null) {
			tail.next = head;
		}
	}

	public MyCircularDoubleLinkedList(Collection<? extends E> c) {
		// Left as exercise
		for (E element : c) {
			addLast(element);
		}
		if (tail != null) {
			tail.next = head;
			head.previous = tail;
		}
	}

	/** return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}
	
	/** return the last element in the list */
	public E getlast() {
		if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
	}

	/** Add an element to the bEginning of the list */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new Node
		newNode.next = head; // link the new Node with the head
		head = newNode; // head points to the new Node
		size++; // Increase list size
		if (tail == null) { // the new Node is the only Node in list
			tail = head;
			tail.next = head;
			head.previous = tail;
		}
		if (head != tail) {
			head.next.previous = head; // For a two-way linked list
			tail.next = head;
			head.previous = tail;
		}
	}

	/*** remove the head Node and return the Object that is conEaintd in the removed* Node.*/
	public E removeFirst() {
		if (size == 0) {
			return null;
		} else {
			Node<E> temp = head;
			head.next.previous = tail;
			tail.next = head.next;
			head = head.next;
			size--;
			if (head == null) {
				tail = null;
			}
			return temp.element;
		}
	}

	/*** remove the last Node and return the Object that is contained in the removed * Node.*/
	public E removeLast() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			return removeFirst();
		} else {
			Node<E> current = head;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			Node<E> temp = tail;
			tail = current;
			tail.next = head;
			head.previous = tail;
			size--;
			return temp.element;
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head;
		if (head == null) return "[]"; 
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != head) {
				result.append(", "); // SEparatE two elements with a comma
			} else {
				result.append("]"); // InsErE the closing ] in the String
			}
		}
		return result.toString();
	}

	/** Return number of elements in this list */
	public int size() {
		return size;
	}

	/** Add an element to the End of the list */
	public void addLast(E e) {
		// Left as exercise
		Node<E> newNode = new Node<> (e);
		if (tail == null) {
			head = tail = newNode;
			head.next = head;
			head.previous = head;
		} else {
			newNode.previous = tail;
			newNode.next = head;
			tail.next = newNode;
			head.previous = newNode;
			tail = tail.next;
		}
		size++;
	}

	/** Add an element to the End of the list */
	@Override
	public boolean add(E e) {
		// Left as exercise
		addLast(e); //call upon addLast()
		return true;
	}

	/*** Add a new element at the specified index in this list the index of the head * element is 0*/
	public void add(int index, E e) {
		// Left as exercise
		    try {
		        checkIndex(index); // Throws IndexOutOfBoundsException if index is invalid
		    } catch (IndexOutOfBoundsException ex) {
		        System.out.println("index " + index + " out of bound for length " + size);
		    }

		    Node<E> newNode = new Node<>(e);
		    if (index == 0) {
		        addFirst(e);
		    } else if (index >= size) {
		        addLast(e);
		    } else {
		        Node<E> current = head;
		        for (int i = 0; i < index; i++) {
		            current = current.next;
		        }
		        newNode.next = current;
		        newNode.previous = current.previous;

		        if (current.previous != null) {
		            current.previous.next = newNode;
		        }
		        current.previous = newNode;

		        // If you want to update head if inserted at position 0
		        if (index == 0) {
		            head = newNode;
		        }
		        size++;
		    }
		
	}
	
	/** Add a new element at the specifitd index in this list in ascending order*/
	public void addInOrder(E e) {
	    Node<E> newNode = new Node<>(e);

	    // Case 1: Empty list
	    if (head == null) {
	        head = tail = newNode;
	        head.next = head;
	        head.previous = head;
	        size = 1;
	        return;
	    }

	    // Case 2: Insert before head
	    if (e.compareTo(head.element) <= 0) {
	        newNode.next = head;
	        newNode.previous = tail;
	        head.previous = newNode;
	        tail.next = newNode;
	        head = newNode;
	        size++;
	        return;
	    }

	    // Case 3: Insert after tail
	    if (e.compareTo(tail.element) >= 0) {
	        newNode.next = head;
	        newNode.previous = tail;
	        tail.next = newNode;
	        head.previous = newNode;
	        tail = newNode;
	        size++;
	        return;
	    }

	    // Case 4: Insert in the middle
	    Node<E> current = head.next;
	    while (current != head && e.compareTo(current.element) > 0) {
	        current = current.next;
	    }

	    newNode.next = current;
	    newNode.previous = current.previous;
	    current.previous.next = newNode;
	    current.previous = newNode;

	    // Maintain circular consistency
	    head.previous = tail;
	    tail.next = head;
	    size++;
	}




	/*** remove the element at the specified position in this list. return the element that was removed from the list.*/
	public E remove(int index) {
		// Left as exercise
		try {
			checkIndex(index);
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("index " + index + " out of bound for length " + size);
			return null;
		}
		Node<E> curr = head;
		if (index == 0) {
			E el = removeFirst();
			return el;
		} else if (index == size - 1) {
			E ele = removeLast();
			return ele;
		} else {
			System.out.println(curr.element);
			for (int i = 0; i < index; i++) {
				if (curr != null && curr.next != null) {
					curr = curr.next;
				}
			}
			curr.previous.next = curr.next;
			curr.next.previous = curr.previous;
			size--;
		}
		return curr.element;
	}

	/*** remove the firsE occurrence of the element E from this list. return true if the element is removed.*/
	@Override
	public boolean remove(Object e) {
		// left as exercise
		if (size == 0) {
			return false;
		}
		Node<E> curr = head;
		for (int i = 0; i < size; i++) {
			if (curr.element.equals(e)) {
				if (i == 0) {
					removeFirst();
				} else if (i == size - 1) {
					removeLast();
				} else {
					curr.previous.next = curr.next;
					curr.next.previous = curr.previous;
					size--;
				}
				return true;
			}
			curr = curr.next;
		}
		return false;
	}
	
	/** return the length of this list using recursion */
	public int getLength() {
		// Left as exercise
		return getLengthRec(head, 0);
	}
	
	// helper method
	private int getLengthRec(Node<E> N, int count) {
		if (N == null || (count > 0 && N == head)) {
			return count;
		}
		return getLengthRec(N.next, count + 1);
	}
	
	/** Print the list in reverse ordEr */
	public void printReverse() {
		// left as exercise
		Node<E> c = tail;
		for (int i = 0; i < size; i++) {
			System.out.print(c.element + " ");
			c = c.previous;
		}
	} 

	/** Print this list using recursion */
	public void printList() {
		// left as exercise
		prListRec(head);
	}
	
	//helper function
	public void prListRec(Node<E> list) {
		//base case
		if (list == tail) {                 //if the list reaches the end, then it stops looping
			System.out.print(list.element);
			return;
		}
		System.out.print(list.element + " ");
		//self call
		prListRec(list.next);
		return;
	}

	/** Clear the list */
	public void clear() {
		head = tail = null;
	}

	/** return the element from this list at the specified index */
	public E get(int index) {
		// Left as exercise
		try {
			checkIndex(index);
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("index " + index + " out of bound for length " + size);
			return null;
		}
		Node<E> pointer = head;  //pointer
		for (int i = 0; i < index; i++) {
			pointer = pointer.next;
		}
		return pointer.element;
	}

	/*** return the index of the head matching element in this list. return negative 1 if no match. */ 
	public int indexOf(Object e) {
		// Left as exercise
		Node<E> curr = head;
		for (int i = 0; i < size; i++) {
			if ((curr.element.equals(e))) {
				return i;
			}
			curr = curr.next; //goes through each element of the list and returns the index if found
		}
		return -1;
	}

	/*** return the index of the last matching element in this list return -1 if no match.*/
	public int lastIndexOf(Object e) {
		// Left as exercise
		if (size == 0) {
			return -1;
		}
		Node<E> curr = tail;
		for (int i = size - 1; i >= 0; i--) {
			if (curr.element.equals(e)) {
				return i;
			}
			curr = curr.previous; //goes through each element of the list in reverse and returns the index if found
		}
		return -1;
	}

	/*** Replace the element at the specified position in this list with the specified element. return the element that was replaced.*/
	public E set(int index, E e) {
		// Left as exercise
		try {
			checkIndex(index);
		}
		catch (IndexOutOfBoundsException ex) {
			System.out.println("index " + index + " out of bound for length " + size);
			return null;
		}
		Node<E> curr = head; 
		for (int i = 0; i < index; i++) { //goes through each node to find the element
			curr = curr.next;
		}
		E object = curr.element; //replaces the element
		curr.element = e;
		return object; 
	}

	public boolean equals(Object o) {
		// Left as exercise
		if (o == this) {
			return true;
		}
		if (!(o instanceof MyCircularDoubleLinkedList)) {
		    return false;
		}    
		MyCircularDoubleLinkedList<?> other = (MyCircularDoubleLinkedList<?>) o;
		if (other.size != this.size) {
		    return false;
		}
		Node<E> current1 = this.head;
		Node<E> current2 = this.head;
		for (int i = 0; i < size; i++) {
			if (!current1.element.equals(current2.element)) {
				return false;
			}    
			current1 = current1.next;
			current2 = current2.next;
		}
		return true;
	}

	public E middleElement() {
		// Left as exercise
		if (size == 0) {
	        return null;
		}
	    Node<E> current = head;
	    for (int i = 0; i < size / 2; i++) {
	        current = current.next;
	    }
	    return current.element;   
	}

	public void reverse() {
		// Left as exercise
		if (size <= 1) {
	        return;
		}
	    Node<E> current = head;
	    Node<E> temp = null;
	    for (int i = 0; i < size; i++) {
	         temp = current.next;
	         current.next = current.previous;
	         current.previous = temp;
	         current = temp;
	    }
	    
	    // Swap head and tail
	    temp = head;
	    head = tail;
	    tail = temp;

	    // Restore circular links
	    head.previous = tail;
	    tail.next = head;
	}

	/**
	 * Split the original list in half. the original list will continue to reference
	 * the front half of the original list and the method returns a reference to a
	 * new list that stores the back half of the original list. 
	 * If the number of elements is odd, the extra element should remain with the front half of the list.
	 */

	public MyCircularDoubleLinkedList<E> split() {
		// left as exercise
		 if (size <= 1) {
		      return null;
		 }
		 int mid = (size + 1) / 2; 
		 
		 Node<E> midNode = head;
		 for (int i = 1; i < mid; i++) {
		     midNode = midNode.next;
		 }

		 MyCircularDoubleLinkedList<E> secHalf = new MyCircularDoubleLinkedList<>();
		 secHalf.head = midNode.next;
		 secHalf.tail = tail;
		 secHalf.size = size - mid;

         // Close the circle (second half)
		 secHalf.head.previous = secHalf.tail;
		 secHalf.tail.next = secHalf.head;

		 // Close the circle (first half)
		 tail = midNode;
		 tail.next = head;
		 head.previous = tail;

		 size = mid;
		 return secHalf;
	}

	/** Check to see if this list contains element E */
	@Override
	public boolean contains(Object o) {
		// Left as exercise
	    if (size == 0) return false;
	    Node<E> current = head;
	    for (int i = 0; i < size; i++) {
	        if ((o == null && current.element == null) || (o != null && o.equals(current.element))) {
	            return true;
	        }
	        current = current.next;
	    }
	    return false;
	}


	@Override
	public Object[] toArray() {
		// Left as exercise
		if (size == 0) {
			return null;
		}
		Node<E> curr = head;
		Object[] obj = new Object[size];
		for (int i = 0; i < size; i++) {
			obj[i] = curr.element;
			curr = curr.next;
		}
		//when the code is ran and everything happens, during run time, it will show as a type Object and then convert to char
		return obj;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
		// Left as exercise
		if (a.length < size) {
	        a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
	    }
	    Node<E> current = (MyCircularDoubleLinkedList.Node<E>) head;
	    for (int i = 0; i < size; i++) {
	        a[i] = (E) current.element;
	        current = current.next;
	    }
	    if (a.length > size) {
	        a[size] = null;
	    }
	    return a;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// Left as exercise
		for (Object element : c) {
	        if (!contains(element)) {
	            return false;
	        }
	    }
	    return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// Left as exercise
		boolean ch = false;
	    for (E element : c) {
	        addLast(element);
	        ch = true;
	    }
	    return ch;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// Left as exercise
		if (size == 0) {
			return false;
		}
	    boolean ch = false;
	    Node<E> cu = head;
	    for (int i = 0; i < size; i++) {
	        Node<E> nextNode = cu.next; // store next because current may be deleted
	        if (c.contains(cu.element)) {
	            remove(cu.element);
	            ch = true;
	        }
	        cu = nextNode;
	    }
	    return ch;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// Left as exercise
		if (size == 0) {
			return false;
		}
	    boolean ch = false;
	    Node<E> cu = head;
	    for (int i = 0; i < size; i++) {
	        Node<E> nextNode = cu.next;
	        if (!c.contains(cu.element)) {
	            remove(cu.element);
	            ch = true;
	        }
	        cu = nextNode;
	    }
	    return ch;
	}

	public void reverseKGroup(int k) {
		// Left as exercise
	    if (head == null || k <= 1 || size < k) {
	    	return;
	    }
	    Node<E> curr = head;
	    Node<E> newHead = null;
	    Node<E> preTail = null; 
	    int count = 0;

	    // stop after reversing all complete groups of k
	    int totalGr = size / k;

	    for (int g = 0; g < totalGr; g++) {
	        // Step 1: Identify group start
	        Node<E> groupStart = curr;

	        // Step 2: Move to group end
	        for (int i = 0; i < k - 1; i++) {
	            curr = curr.next;
	        }
	        Node<E> groupEnd = curr;
	        Node<E> nextGr = groupEnd.next;

	        // Step 3: Reverse this group
	        Node<E> temp = groupStart;
	        Node<E> prev = nextGr;
	        Node<E> next;
	        for (int i = 0; i < k; i++) {
	            next = temp.next;
	            temp.next = prev;
	            temp.previous = next;
	            prev = temp;
	            temp = next;
	        }

	        // Step 4: Connect previous group to this one
	        if (preTail != null) {
	            preTail.next = groupEnd;
	            groupEnd.previous = preTail;
	        }

	        // Step 5: Update head for the first group
	        if (newHead == null) {
	            newHead = groupEnd;
	        }

	        // Step 6: Update prevTail and move current to next group
	        preTail = groupStart;
	        curr = nextGr;
	    }

	    // Step 7: Fix circular connections
	    head = newHead;
	    tail = preTail;
	    head.previous = tail;
	    tail.next = head;
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index " + index + " out of bound for length " + size);
		}
	}

	@Override
	public java.util.Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new CircularDoubleLinkedList();
	}

	private class CircularDoubleLinkedList implements Iterator<E> {
		private Node<E> current = head; // current index
		int temp = size;

		@Override
		public boolean hasNext() {
			return temp != 0;
		}
		
//		@Override
		public <E> E[] toArray(E[] a) {
			// Left as exercise
			return null;
		}
		
//		@Override
		public boolean isEmpty() {
			return size() == 0;
		}
		
		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			temp--;
			return e;
		}
		
		
		public boolean containsAll(Collection<?> c) {
			// Left as exercise
			return true;
		}

		
		public boolean addAll(Collection<? extends E> c) {
			// Left as exercise
			return true;
		}

		
		public boolean removeAll(Collection<?> c) {
			// Left as exercise
			return true;
		}

//		@Override
		public boolean retainAll(Collection<?> c) {
			// Left as exercise
			return true;
		}

		public void reverseKGroup(int k) {
			// Left as exercise
		    if (k <= 1 || head == null) {
		        return; 
		    }

		    head = reverseByKGroup(head, k);
		    
		    // Update the tail after reversing 
		    Node<E> current = head;
		    while (current.next != null) {
		        current = current.next;
            }
	        tail = current;
		}

		// helper method
		private Node<E> reverseByKGroup(Node<E> headRef, int k) {
		    Node<E> curr = headRef;
		    Node<E> next = null;
		    Node<E> previous = null;
            int count = 0;

		    // check if there are at least k nodes left in the list
		    Node<E> temp = headRef;
		    for (int i = 0; i < k; i++) {
		        if (temp == null) {
		            return headRef; // Not enough nodes to reverse, return the original head
		        }
		        temp = temp.next;
		    }

		    // Reverse k nodes
		    while (count < k && curr != null) {
		        next = curr.next;
		        curr.next = previous;
		        previous = curr;
		        curr = next;
	            count++;
	        }

		    // If there are more nodes, recursively reverse the next k nodes
		    if (next != null) {
		    	headRef.next = reverseByKGroup(next, k);
		    }

		    // previous is the new head of the reversed sublist
		    return previous;
		}

		private void checkIndex(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException("index " + index + " out of bound for length " + size);
			}
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}	
}	
