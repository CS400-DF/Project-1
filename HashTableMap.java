
// --== CS400 File Header Information ==--
// Name: ABHAY PRAKASH PUNJABI
// Email: apunjabi@wisc.edu
// Team: DF
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author abhay
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType,ValueType> {
	private int capacity;
	private int size = 0;
	private LinkedList<Student>[] array;

	public HashTableMap(int capacity) {
		this.capacity = capacity;
		array = new LinkedList[capacity];
	}

	public HashTableMap() {
		this.capacity = 10;
		array = new LinkedList[capacity];
	}

	/**
	 * Checks if collisions occur between colliding and non-colliding elements by
	 * comparing w nodes
	 * 
	 * @param k1 KeyType of the first Node
	 * @param k2 KeyType of the second Node
	 * @return true of false whether the elements collide
	 */
	public boolean indexOfElement(KeyType k1, KeyType k2) {
		// check if keys are stored/contained
		if (!containsKey(k1)) {
			return false;
		} else if (!containsKey(k2)) {
			return false;
		}
		int count = 0;
		// checks if they hash to they same index
		if (k1.hashCode() % capacity == k2.hashCode() % capacity) {
			for (int j = 0; j < array[k1.hashCode() % capacity].size(); j++) {
				if (array[k1.hashCode() % capacity].get(j).getStudentId().equals(k1)) {
					count++;
				}
				if (array[k1.hashCode() % capacity].get(j).getStudentId().equals(k2)) {
					count++;
				}
			}
			// both hash to the same index
			if (count == 2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Rehashes and doubles the the old array by rehashing elements from the copy
	 * using the put() method
	 * 
	 * @param arrayCopy copy of the old array
	 */
	private void arrayCreate(LinkedList<Student>[] arrayCopy) {
		for (int i = 0; i < arrayCopy.length; i++) {
			// skips null references in the array
			if (arrayCopy[i] == null) {
				continue;
			}
			// puts each non null element and rehashes into the old array
			for (int j = 0; j < arrayCopy[i].size(); j++) {
				put((KeyType) arrayCopy[i].get(j).getStudentId(), (ValueType) arrayCopy[i].get(j));
			}
		}

	}

	/**
	 * load factor in the form of a double is checked if greater than 0.8
	 * 
	 * @return true if greater than or equal
	 */
	public boolean loadFactor() {
		return ((double) size / (double) capacity) >= 0.8;
	}

	/**
	 * @return capacity of the array
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Checks if the index of the hashtable is null, if null, it adds a new
	 * LinkedList of Nodes containing the new element, else it adds a node to an
	 * existing LinkedList Elements that are not valid(repeated keys) are not added
	 * 
	 * @boolean true/false if the element is added successfully
	 */
	@Override
	public boolean put(KeyType k, ValueType v) {
		Student obj = (Student)v;//fine
		// if the element is null
		if (array[Math.abs(k.hashCode()) % capacity] == null) {
			array[Math.abs(k.hashCode()) % capacity] = new LinkedList<Student>();
			array[Math.abs(k.hashCode()) % capacity].add(obj);
			// increments size
			size++;
			// if loadFactor() is >=80% rehashes and doubled
			if (loadFactor()) {
				LinkedList<Student>[] arrayCopy = new LinkedList[capacity];
				for (int i = 0; i < array.length; i++) {
					arrayCopy[i] = array[i];
				}
				// capacity doubles
				capacity = 2 * capacity;
				size = 0;
				// old array reinitailized
				array = new LinkedList[capacity];
				arrayCreate(arrayCopy);

			}
			// returns true when added successfully
			return true;
		}
		// adds to a present linkedlist/collision
		for (int i = 0; i < array[Math.abs(k.hashCode()) % capacity].size(); i++) {
			if (array[Math.abs(k.hashCode()) % capacity].get(i).getStudentId().equals(k)) {
				return false;
			}
		}
		array[Math.abs(k.hashCode()) % capacity].add(obj);
		size++;
		if (loadFactor()) {
			LinkedList<Student>[] arrayCopy = new LinkedList[capacity];
			for (int i = 0; i < array.length; i++) {
				arrayCopy[i] = array[i];
			}
			capacity = 2 * capacity;
			size = 0;
			array = new LinkedList[capacity];
			arrayCreate(arrayCopy);
		}
		// System.out.println(size);
		// float loadFactor = (float) size / (float) capacity;

		return true;
	}

	/**
	 * Gets the value stored alongside a key if the key exists
	 * 
	 * @ValueType returns the value type if present
	 * @throws NoSuchElementException when the element is not found
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// checks if key exists
		if (containsKey(key)) {
			// iterates through to find element
			for (int i = 0; i < array[Math.abs(key.hashCode()) % capacity].size(); i++) {
				if (array[Math.abs(key.hashCode()) % capacity].get(i).getStudentId().equals(key)) {
					// System.out.println("Post is" + Math.abs(key.hashCode()) % capacity);
					return (ValueType) array[Math.abs(key.hashCode()) % capacity].get(i);
				}
			}
		} else {
			// throws if no element
			throw new NoSuchElementException("No such element exists");
		}
		return null;
	}

	/**
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @boolean true/false if key is found/absent
	 */
	@Override
	public boolean containsKey(KeyType key) {
		// iterates through array
		if (array[Math.abs(key.hashCode()) % capacity] != null) {
			for (int i = 0; i < array[Math.abs(key.hashCode()) % capacity].size(); i++) {
				if (array[Math.abs(key.hashCode()) % capacity].get(i).getStudentId().equals(key)) {
					return true;
				}

			}
			return false;
		}
		return false;
	}

	/**
	 * Decrements the size whilst removing the node if present using the key
	 * 
	 * @ValueType of the deleted key, if present
	 */
	@Override
	public ValueType remove(KeyType key) {
		Student toBeDeleted = new Student();
		if (containsKey(key)) {
			for (int i = 0; i < array[Math.abs(key.hashCode()) % capacity].size(); i++) {
				if (array[Math.abs(key.hashCode()) % capacity].get(i).getStudentId().equals(key)) {
					// new node stores the node
					toBeDeleted = array[Math.abs(key.hashCode()) % capacity].get(i);
					// deleted the node
					array[Math.abs(key.hashCode()) % capacity].remove(i);
					size--;
					break;
				}
			}
			return (ValueType) toBeDeleted.getValue();
		}
		return null;

	}

	/**
	 * Clears the HashTable
	 */
	@Override
	public void clear() {
		size = 0;
		array = new LinkedList[capacity];
	}
}