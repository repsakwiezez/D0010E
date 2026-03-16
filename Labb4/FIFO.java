import java.util.ArrayList;
import java.util.NoSuchElementException;


public class FIFO implements Queue {
	
	private ArrayList<Object> list;
	private int maxSize;
	
	public FIFO() {					//Constructor for FIFO class
		list = new ArrayList<>();	//Creates list and variable for max size
		maxSize = 0;
	
	}
	public void add(Object item) {
		list.add(item);
			
		if (list.size() > maxSize) {	//Checks to see if list size is bigger than maxSize
			maxSize = list.size();		//If so, updates maxSize so biggest size is saved
		}
	}
	
	public void removeFirst() {
		if (isEmpty()) {						//Checks if the list is empty,
			throw new NoSuchElementException();	//and throws the appropriate error code
		}
		
		list.remove(0);							//Removes element at index 0, the first one
	}
	
	public Object first() {
		if (isEmpty()) {						//Again checks for empty list
			throw new NoSuchElementException();
		}
		
		return list.get(0);						//Returns the element at index 0
	}
	
	public int maxSize() {	//Returns the maximum size the list has been
		
		return maxSize;
		
	}
	
	public boolean isEmpty() {	//Checks to see if the list is empty
		
		return list.isEmpty();	//Returns boolean value
		
	}
	
	public int size() {			//Returns the current size of the list
		
		return list.size();
	}
	
	public String toString() {		//Returns all the current element in the list
		String s = "Queue: ";		//Saves the string as a variable
		
		for(Object obj : list) {	//For loop  that goes through every element (or object) in the list
			s += "(" + String.valueOf(obj) + ")" + " ";	//And then adds them in order to the variable s as a string value
		}
		return s;
		
	}
	
	public boolean equals(Object f) {
		if(!(f instanceof FIFO)) {		//Checks if f is the same type as FIFO
			throw new ClassCastException();	//If not, throws a error code
		}
		
		FIFO other = (FIFO) f;	//Declares other as a FIFO type
		
		if (this.size() != other.size()) {	//Checks if list and other list is the same size
			return false;					//If not, returns false
		}
		
		for (int i = 0; i < list.size(); i++) {	//Goes through every element
			
			if (list.get(i) == null && other.list.get(i) != null) {	//Checks if list and other has null
				return false;
			}
			
			if (list.get(i) != null && !list.get(i).equals(other.list.get(i))) {	//Checks that lists element is not null and then checks if others element is the same value
				return false;
			}
			
			
		}
		
		return true;
	}

}