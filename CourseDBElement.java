package application;

/** An element class that contains information about a course.
 *  It can also be compared to other instances of the same class
 * 
 *  @author nitan
 *
 */

public class CourseDBElement implements Comparable<CourseDBElement> {

    private String courseID;
    private int crnNumber;
    private int numberOfCredits;
    private String roomNumber;
    private String instructor;
    
    /** Default constructor that sets default values */
    public CourseDBElement() {
        this("", 0, 0, "", "");
    }
    
    /** Constructor that sets values to what was passed through
     *  
     *  @param courseID The ID of the course
     *  @param crn the CRN Number of the course
     *  @param numberOfCredits The number of credits for the course
     *  @param roomNumber the number of the room
     *  @param instructor the instructor
     */
    public CourseDBElement(String courseID, int crn, int numberOfCredits, String roomNumber, String instructor) {
        this.courseID = courseID;
        this.crnNumber = crn;
        this.numberOfCredits = numberOfCredits;
        this.roomNumber = roomNumber;
        this.instructor = instructor;
    }

    
    // Add these getter methods if they aren't already there
    public String getID() {
        return courseID;
    }

    public String getRoomNum() {
        return roomNumber;
    }
    /** Gets the course ID
     * 
     * @return The course ID
     */
    public String getCourseID() {
        return courseID;
    }

    /** Sets the course ID
     * 
     * @param courseID The ID of the course
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /** Gets the Course CRN Number
     * 
     * @return The CRN Number of the course
     */
    public int getCRN() {
        return crnNumber;
    }

    /** Sets the CRN Number
     * 
     * @param crnNumber The CRN Number
     */
    public void setCRN(int crnNumber) {
        this.crnNumber = crnNumber;
    }

    /** Gets the number of credits
     * 
     * @return The number of credits
     */
    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    /** Sets the number of credits
     * 
     * @param numberOfCredits The number of credits for the course
     */
    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    /** Gets the room number
     * 
     * @return The room number
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /** Sets the room number
     * 
     * @param roomNumber The room number
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /** Gets the instructor
     * 
     * @return The instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /** Sets the instructor
     * 
     * @param instructor The instructor
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /** Gets the hash code, which is the crn number, converted
     *  into a String, then calling on the hashCode of that string
     * 
     *  @return The hash code of the crn number
     */
    @Override
    public int hashCode() {
        return String.valueOf(crnNumber).hashCode();
    }
    
    /**
     * This interface imposes a total ordering on the objects of each 
     * class that implements it. This ordering is referred to as the 
     * class's natural ordering, and the class's compareTo method is 
     * referred to as its natural comparison method.
     * 
     * Lists (and arrays) of objects that implement this interface 
     * can be sorted automatically by Collections.sort (and Arrays.sort). 
     * 
     * @param element a CourseDBElement
     * @return a negative integer if x.compareTo(y) < 0, 
     *         zero if x.compareTo(y) == 0,
     * 	   and a positive integer if x.compareTo(y) > 0.
     */
    
    public interface Comparable {

    	int compareTo(CourseDBElement element);

    }

    /** Compares this class with another class
     *  
     *  @param element Another instance of this class
     *  
     *  @return positive number if the crnNumber of this class
     *  is greater than the crnNumber of element, a negative number if
     *  the crnNumber of this class is less than the crnNumber of element,
     *  and 0 if they're the same
     */
    @Override
    public int compareTo(CourseDBElement element) {
        return Integer.compare(this.crnNumber, element.crnNumber);
    }

    /** Gets the attributes of CourseDBElement
     * 
     * @return The info of the CourseDBElement
     */
    @Override
    public String toString() {
        return "Course:" + courseID + " CRN:" + crnNumber + " Credits:" + numberOfCredits + " Instructor:" + instructor + " Room:" + roomNumber;
    }

    /** Overriding equals method to be consistent with hashCode */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseDBElement other = (CourseDBElement) obj;
        return crnNumber == other.crnNumber;
    }
}
