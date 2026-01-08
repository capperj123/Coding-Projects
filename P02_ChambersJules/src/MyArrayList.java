
import java.util.*;
public class MyArrayList<E extends Comparable<E>> implements MyList<E> {

	private E[] list;
	private int size = 0;
	private int capacity = 10;

	@SuppressWarnings("unchecked")
	public MyArrayList() {
		size = 0;
		list = (E[]) (new Comparable[capacity]);
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		size = 0;
		capacity = initialCapacity;
		list = (E[]) (new Comparable[capacity]);
	}

	@SuppressWarnings("unchecked")
	public MyArrayList(Collection<E> c) {
		list = (E[]) (new Comparable[capacity]);
		for (E o : c) {
			this.add(o);
		}
	}
	
	// Method from MyList interface- Left as exercise
	
	@Override
	public void add(int index, E element) {
		try {
			checkIndex(index);
		} catch(IndexOutOfBoundsException e) {
			return;
		}
		
		//Make sure there is room in the list
		ensureCapacity();		
		
		//Move element to the right
		for (int i = size; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = element;
		size++;
	}

	public E get(int index) {
		try {
			checkIndex(index);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index " + index + " out of bound for length " + size);
			return null;
		}
		return list[index];
	}

	@SuppressWarnings("unchecked")
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
            if (o.equals(list[i])) {
                return i;
            }
        }
        return -1;
	}

	@SuppressWarnings("unchecked")
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
            if (o.equals(list[i])) {
                return i;
            }
        }
        return -1;
	}

	@Override
	public E remove(int index) {
		try {
			checkIndex(index);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index " + index + " out of bound for length " + size);
			return null;
		}    		
			//checks if the index is valid, 
            //but if an exception is found, 
            //then the program will stop
	    E temp = list[index];
	        
	    //Move the elements to the left
	    for (int i = index; i < size - 1; i++) {
	        list[i] = list[i + 1];
	    }
	    list[size - 1] = null;
	    size--;
	    return temp;
	}

	public E set(int index, E element) {
		try {
			checkIndex(index);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Index " + index + " out of bound for length " + size);
			return null;
		}
		E temporary = list[index];
		list[index] = element;  //assigns the element to the index
		return temporary;
	}

	/** trim capacity of this collection to size */
	public void trimToSize() {
		if (list.length > size) {
            list = Arrays.copyOf(list, size);
        }
	}

	// Method from Collection interface- Left as exercise
	
	@Override
	public boolean add(E e) {
		ensureCapacity();
        list[size++] = e;
		return true;
	}
	
	public boolean addAll(Collection<? extends E> c) {
		for (E LMNT : c) { 
						   //enhanced for loop calls hasNext to see if the method has an element
						   //can't use a regular for loop unless you use an iterator method
	  //for (int i = 0; i < LMNT.size(); i++) {
		  //this.add(c[i]);
			this.add(LMNT);
		}
		return true;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
            list[i] = null;
        }
		size = 0;
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(list[i])) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	//containsAll, removeAll, retainAll, addAll are set operations with their own collection interfaces
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for (Object cont : c) {
			if (! this.contains(cont)) { //if the list does not contain the object
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
        if (o == null || !(o instanceof MyArrayList)) {
        	return false;
        }
        MyArrayList<?> other = (MyArrayList<?>) o;
        if (size != other.size()) {
        	return false;
        }
        
        for (int i = 0; i < size; i++) {
            if (!list[i].equals(other.list[i])) {
            	return false;
            }
        }
        return true;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(Object o) {
		int in = indexOf(o);
        if (in == -1) {
        	return false;
        }
        remove(in);
        return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for (Object content : c) { 
			this.remove(content);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for (Object content : c) {
			for (int i = 0; i < size; i++) {
				if (!c.contains(list[i])) {
					remove(i);
					i--;
				}
			}
		}
		return true;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(list, size);
	}

	/**
	 * Returns an array containing all of the elements in this collection; the
	 * runtime type of the returned array is that of the specified array.
	 */
	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] array) {
		if (array.length < size) {
            return (E[]) Arrays.copyOf(list, size, array.getClass());
		}
        System.arraycopy(list, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
	}

	/** Additional method used in MyArrayList */
	/** if this collection is full, increase the capacity by 5 */
	private void ensureCapacity() {
		if (size >= list.length) {
			E[] newList = (E[])(new Comparable[size + 5]);
			System.arraycopy(list, 0, newList, 0, size);
			list = newList;
		}
	}

	/** Make deep copy of this list */
	public MyArrayList<E> clone() {
		MyArrayList<E> copied = new MyArrayList<E>(capacity);
        for (int i = 0; i < size; i++) {
            copied.add(list[i]);
        }
        return copied;
	}

	/** Split the original list in half. The original
	 * list will continue to reference the
	 * front half of the original list and the method
	 * returns a reference to a new list that stores the
	 * back half of the original list. If the number of
	 * elements is odd, the extra element should remain
	 * with the front half of the list. */
	public MyArrayList<E> split(){
		int middle = (size + 1) / 2;
        MyArrayList<E> end = new MyArrayList<>(size - middle);
        for (int i = middle; i < size; i++) {
            end.add(list[i]);
        }
        size = middle;
        trimToSize();
        return end;
	}

	// Implemented methods used in MyArrayList
	public String toString() {
		if (size == 0)
			return "[]";
		@SuppressWarnings("unchecked")
		E[] temp = (E[])(new Comparable[size]);
		System.arraycopy(list, 0, temp, 0, size);
		return Arrays.asList(temp).toString();
	}
	
	@Override
	public int size() {
		return size;
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index " + index + " out of bound for length " + size);
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements java.util.Iterator<E> {
		private int current = 0; // Current index
		@Override
		public boolean hasNext() {
			return current < size;
		}
		@Override
		public E next() {
			return (E)list[current++];
		}
		@Override // Remove the element returned by the last next()
		public void remove() {
			if (current == 0) // next() has not been called yet
				throw new IllegalStateException();
			MyArrayList.this.remove(--current);
		}
	}
}