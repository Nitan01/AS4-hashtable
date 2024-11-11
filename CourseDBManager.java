package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The CourseDBManager handles operations on the course database.
 * It implements the CourseDBManagerInterface and interacts with CourseDBStructure.
 * This allows the user to read courses from a file, add courses manually, 
 * and display the database contents.
 * 
 * @author nitan
 */

public class CourseDBManager implements CourseDBManagerInterface {

    private CourseDBStructure structure;

    /** Constructor that creates the structure */
    public CourseDBManager() {
        structure = new CourseDBStructure(500); // Initializes with 500 slots in the structure
    }

    /** 
     * Adds a course element to the structure.
     * 
     * @param id        The course ID
     * @param crn       The course CRN number
     * @param credits   The number of credits for the course
     * @param roomNum   The room number for the course
     * @param instructor The instructor for the course
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        // Create a new CourseDBElement and add it to the structure
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }

    /** 
     * Retrieves a course element from the structure using its CRN.
     * 
     * @param crn The CRN of the course to retrieve
     * @return The CourseDBElement for the course with the given CRN
     */
    @Override
    public CourseDBElement get(int crn) {
        CourseDBElement toReturn = new CourseDBElement("", 0, 0, "", "");
        try {
            toReturn = structure.get(crn); // Attempts to get the course from the structure
        } catch (IOException e) {
            System.err.println(crn + " Not found");
        }
        return toReturn; // Returns the found course or the default empty element
    }

    /** 
     * Reads a file and adds all courses in the file to the structure.
     * 
     * @param input The file to read courses from
     * @throws FileNotFoundException If the file does not exist
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        // Check if the file exists before reading it
        if (!input.exists()) {
            throw new FileNotFoundException("File not found: " + input.getPath());
        }

        // Create a list to hold data from the file
        ArrayList<String> dataFromFile = new ArrayList<>();

        // Use a scanner to read the file line by line
        Scanner fileScanner = new Scanner(input);
        while (fileScanner.hasNextLine()) {
            String next = fileScanner.nextLine();
            if (!next.isEmpty()) {
                dataFromFile.add(next); // Add non-empty lines to the list
            }
        }
        fileScanner.close(); // Close the scanner when done

        // Process each line in the file
        for (String data : dataFromFile) {
            // Split the data line into parts
            String[] dataArr = data.split(" ");
            String courseID = dataArr[0];
            int crnNumber = Integer.parseInt(dataArr[1]);
            int numOfCredits = Integer.parseInt(dataArr[2]);
            String roomNumber = dataArr[3];
            String instructor = String.join(" ", Arrays.copyOfRange(dataArr, 4, dataArr.length));

            // Add the course to the structure
            add(courseID, crnNumber, numOfCredits, roomNumber, instructor);
        }
    }

    /** 
     * Returns an ArrayList of strings representing all courses in the structure.
     * 
     * @return An ArrayList containing string representations of all courses
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> data = new ArrayList<>();

        // Traverse the hashTable to collect all course elements
        for (LinkedList<CourseDBElement> list : structure.hashTable) {
            if (list != null) {
                for (CourseDBElement listElement : list) {
                    data.add("\n" + listElement.toString()); // Add each course's string representation
                }
            }
        }
        return data;
    }
}
