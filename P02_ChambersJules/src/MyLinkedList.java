
public class MyLinkedList<E extends Comparable<E>> {
   private Node<E> head, tail;
   private int size; // Number of elements in the list
   
   /** Default constructor */
   public MyLinkedList() {
      head=tail=null;
      size=0;
   }
   
   /** Return the head element in the list */
   public E getFirst() {
      if (size == 0) {
         return null;
      }
      else {
         return head.element;
      }
   }

   /** Return the last element in the list */
   public E getLast() {
      if (size == 0) {
         return null;
      }
      else {
         return tail.element;
      }
   }
  
   /** Add an element to the beginning of the list */
   public void addFirst(E e) {
     Node<E> newNode = new Node<>(e);
     newNode.next = head;
     head = newNode;
     size++;
     if (tail == null) {
        tail = head;
     }
   }

   /** Add an element to the end of the list */
   public void addLast(E e) {
      Node<E> newNode = new Node<>(e);
      if (tail == null)
         head = tail = newNode;
      else {
         tail.next = newNode;
         tail = newNode;
      }
      size++;
   }
    
  /** Add a new element at the specified position*/
   public void add(int index, E e){
      if (index == 0)
         addFirst(e);
      else if (index >= size)
         addLast(e);
      else {
         Node<E> newNode = new Node(e);
         Node<E> current = head;
         for (int i = 1; i < index; i++) {
            current = current.next;
         }
         Node<E> temp = current.next;
         current.next = newNode;
         newNode.next = temp;
         size++;
      } 
   }
   
   /** Remove the head node and
    *  return the object that is contained in the removed node. */
   public E removeFirst() {
      if (size == 0)
         return null;
      else {
         Node<E> temp = head;
         head = head.next;
         size--;
         if (head == null)
            tail = null;
         return temp.element;
      }
   }

   /** Remove the last node and
    * return the object that is contained in the removed node. */
   public E removeLast() {
      if (size == 0)
         return null;
         
      else if (size == 1) {
        Node<E> temp = head;
        head = tail = null;
        size = 0;
        return temp.element;
      
      } else {
        Node<E> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        
        Node<E> temp = tail;
        tail = current;
        tail.next = null;
        size--;

        return temp.element;
      }
    }
    
   /** Add a new element at the specified index in this list in ascending order **/
   public void addInOrder(E e) {
      if (size == 0)
         addFirst(e);
      else if (e.compareTo(this.head.element) < 0)
         addFirst(e);
      else if (e.compareTo(this.tail.element) > 0)
         addLast(e);
      else {
         Node<E> prev = null;
         Node<E> current = head;
         
         while (current.element.compareTo(e) <= 0) {
            prev = current;
            current = current.next;
         }
         
         Node<E> newNode = new Node<>(e);
         prev.next = newNode;
         newNode.next = current;
         size++;
      }
   }   

   /** Remove the element at the specified position in this 
    *  list. Return the element that was removed from the list. */
   public E remove(int index) {   
      if (index < 0 || index >= size)
         return null;
      else if (index == 0)
         return removeFirst();
      else if (index == size - 1)
         return removeLast();
      else {
         Node<E> previous = head;
         for (int i = 1; i < index; i++)
            previous = previous.next;
         Node<E> current = previous.next;
         previous.next = current.next;
         size--;
         return current.element;
      }
   }
   
   /** Reverse the linked list in groups of size k */
   public void reverseByKGroup(int k) {
        if (k <= 1 || head == null) {
            return; // No need to reverse if k is 1 or less, or if the list is empty
        }

        head = reverseByKGroup(head, k);
        // Update the tail after reversing
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        tail = current;
    }

    /** Helper method to reverse a sublist of size k */
    private Node<E> reverseByKGroup(Node<E> headRef, int k) {
        Node<E> current = headRef;
        Node<E> next = null;
        Node<E> prev = null;
        int count = 0;

        // First, check if there are at least k nodes left in the list
        Node<E> temp = headRef;
        for (int i = 0; i < k; i++) {
            if (temp == null) {
                return headRef; // Not enough nodes to reverse, return the original head
            }
            temp = temp.next;
        }

        // Reverse k nodes
        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        // If there are more nodes, recursively reverse the next k nodes
        if (next != null) {
            headRef.next = reverseByKGroup(next, k);
        }

        // prev is the new head of the reversed sublist
        return prev;
    }
   
   /** Override toString() to return elements in the list in [] separated by , */
   public String toString() {
      StringBuilder result = new StringBuilder("[");
   
      Node<E> current = head;
      for (int i = 0; i < size; i++) {
         result.append(current.element);
         current = current.next;
         if (current != null) {
            result.append(", "); // Separate two elements with a comma
         }
         else {
            result.append("]"); // Insert the closing ] in the string
         }
      }
   
      return result.toString();
   }
   
   // print the list
   public void printList(){
      printList(head);
   }
   
   // Helper method
   private void printList(Node<E> headRef) {
      if (headRef == null) // base case
         return;
      else {
         System.out.print(headRef.element + " ");
         printList(headRef.next);
      }
   }
   
   public void writeBackWard(){
      writeBackWard(head);
   }
   
   // Helper method
   private void writeBackWard(Node<E> headRef){
      if (headRef == null) // base case
         return;
      else {
         writeBackWard(headRef.next);
         System.out.print(headRef.element + " ");
      }
   }

   /** Clear the list */
   public void clear() {
      size = 0;
      head = tail = null;
   }
   
   /** Return the number of elements in this list */
   public int size() {
      return size;
   }
   
   /** Return true if this list contains no elements */
   public boolean isEmpty() {
      return size==0;
   }
     
   private static class Node<E> {
      E element;
      Node<E> next;
   
      public Node(E element) {
         this.element = element;
      }
   }
}