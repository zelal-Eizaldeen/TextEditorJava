package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> newNode = new LLNode<E>(element);
		if (element == null) {
			throw new NullPointerException("No null values allowed!!");
		}
		
		newNode.prev = tail.prev;
		newNode.next = newNode.prev.next;
		newNode.prev.next = newNode;
		tail.prev = newNode;
		size++;
		
		//Add from the front
//		newNode.next = head.next;
//		newNode.prev = newNode.next.prev;
//		newNode.next.prev = newNode;
//		head.next = newNode;   // or we can say newElement.prev.next = newElement;
//		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index input!!");
		}
		return getNode(index).data;
	}
	
	private LLNode<E> getNode(int index) {
		  // This will get the tail node, so the bounds need to be checked outside of this method.
		  LLNode<E> curr = head.next;
		  int count = 0;
		  while(curr != tail && count < index)
		  {
			  curr = curr.next;
			  count++;
		  }
	     return curr;
	  }

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		
		if (element == null) {
			throw new NullPointerException("No null values allowed!!");
		}
		
		if (index == -1  || index < size - 1)  {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		
		LLNode<E> curr = getNode(index);
		new LLNode<E>(element, curr.prev);
		size++;
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size -1) {
			throw new IndexOutOfBoundsException("Invalid index input!!");
		}
		LLNode<E> curr = getNode(index);
		E oldData = curr.data;
		curr.data = (curr.next).data;
		curr.next.prev =  curr.prev;
		(curr.prev).next = curr.next;
		size = size-1;
				
		return oldData;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index input!!");
		}
		if (element == null) {
			throw new NullPointerException("Null!!");
		}
		getNode(index).data = element;
		return element;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E element, LLNode<E> prevNode) {
		// TODO Auto-generated constructor stub
		
		this(element);
		if(prevNode != null) {
			this.next = prevNode.next;
			if(this.next !=null) {
				(this.next).prev = this;
			}
			this.prev = prevNode;
			prevNode.next = this;
		}
	}

}
