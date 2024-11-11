package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that uses a Hash Table to hold data and get data
 * 
 * @author nitan
 */
public class CourseDBStructure implements CourseDBStructureInterface {

    private int tableSize;
    LinkedList<CourseDBElement>[] hashTable;

    /** 
     * Constructor that sets up the hash table based on the number of courses.
     * 
     * @param numOfCourses The estimated number of courses
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(int numOfCourses) {
        // Calculate the table size by finding the next 4K+3 prime greater than numOfCourses / 1.5
        int size = (int) Math.ceil(numOfCourses / 1.5); // Apply load factor of 1.5
        tableSize = getNextPrime(size); // Find the next prime number greater than size
        hashTable = new LinkedList[tableSize]; // Initialize the hash table with the calculated size
    }

    /** 
     * Constructor for testing purposes that sets up the hash table with a specified size.
     * 
     * @param testing Unused variable (for testing purposes)
     * @param numOfCourses The size of the hash table
     */
    public CourseDBStructure(String testing, int numOfCourses) {
        this(numOfCourses); // Call the main constructor with numOfCourses
    }

    /** 
     * Adds a CourseDBElement to the hash table.
     * 
     * @param element The CourseDBElement to add
     */
    @Override
    public void add(CourseDBElement element) {
        boolean placeIntoTable = true;
        // Calculate the index for the element based on its hash code
        int index = Math.abs(element.hashCode() % hashTable.length);

        // If the bucket is empty, initialize a new linked list
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        // Check if the element already exists in the list
        for (CourseDBElement listElement : hashTable[index]) {
            if (listElement.compareTo(element) == 0) {
                placeIntoTable = false; // Element already exists, don't add it
            }
        }

        // If the element doesn't exist, add it to the list
        if (placeIntoTable) {
            hashTable[index].add(element);
        }
    }

    /** 
     * Retrieves a CourseDBElement by its CRN from the hash table.
     * 
     * @param crn The CRN to look up
     * @return The CourseDBElement with the given CRN
     * @throws IOException If the CRN is not found in the hash table
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        CourseDBElement toReturn = null;

        // Iterate through the hash table to find the matching CRN
        for (LinkedList<CourseDBElement> list : hashTable) {
            if (list != null) {
                for (CourseDBElement listElement : list) {
                    if (listElement.compareTo(new CourseDBElement("", crn, 0, "", "")) == 0) {
                        toReturn = listElement; // Found the element with the matching CRN
                    }
                }
            }
        }

        // If the element is not found, throw an exception
        if (toReturn == null) {
            throw new IOException("Couldn't find " + crn);
        }

        return toReturn;
    }

    /** 
     * Returns the size of the hash table.
     * 
     * @return The size of the table
     */
    @Override
    public int getTableSize() {
        return tableSize;
    }

    /** 
     * Helper method to find the next prime number greater than a given number.
     * 
     * @param number The number to find the next prime greater than it
     * @return The next prime number greater than the input number
     */
    private int getNextPrime(int number) {
        int prime = number;
        while (true) {
            if (isPrime(prime)) {
                return prime; // Return the first prime number found
            }
            prime++;
        }
    }

    /** 
     * Helper method to check if a number is prime.
     * 
     * @param num The number to check
     * @return true if the number is prime, false otherwise
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false; // Numbers less than or equal to 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Found a divisor, not prime
            }
        }
        return true; // No divisors found, the number is prime
    }

	@Override
	public ArrayList<String> showAll() {
		// TODO Auto-generated method stub
		return null;
	}
}