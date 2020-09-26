import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class will be responsible for having an instance of a HashTableMap and calling the various
 * operations on it along with some other basic tasks in regards to managing Student
 * 
 * @author Zihan Wang, Nicole Gathman
 *
 */
public class CovidApp {
  private HashTableMap map;
  
  public CovidApp(){
    this.map = new HashTableMap();
  }
  /**
   * This method add a student's information into the map
   * @return true if the student is successfully added, false otherwise
   */
  public boolean addStudent(int id, String name, String healthStatus, String housingStatus) {
    Student student = new Student(id, name, healthStatus, housingStatus);
    return map.put(id, student);
  }
  
  /**
   * This method returns a student object based on the given ID number
   * @param key - student ID number
   * @return student object
   * @throws NoSuchElementException
   */
  public Student getStudentInformation(int id) throws NoSuchElementException{
    if(!map.containsKey(id)) {
      throw new NoSuchElementException();
    }
    else {
      return (Student) map.get(id);
    }
    
  }
  
  /**
   * This methods gets the number of student objects stored in the map
   * @return the number of student objects stored in the map
   */
  public int getAmountOfStudents() {
    return map.size();
    
  }
  
  /**
   * This method checks if a student already has information in the map
   * @param key - the student ID number
   * @return true if the student is in the map, false otherwise
   */
  public boolean containsStudent(int id) {
    return map.containsKey(id);
    
  }
  
  /**
   * This method removes a student from the map given the ID number
   * @param key - student ID number
   * @return the Student object being removed
   */
  public Student removeStudent(int id) {
    return (Student) map.remove(id);
  }
  
  /**
   * This method clears all the students in the map
   */
  public void clearStudents() {
    map.clear();
  }
  
  /**
   * This method loads information from a file to the map
   * @param textFile
   */
  public void loadFile(String textFile) {
    ArrayList<String> options = new ArrayList<>();
    options.add("positive");
    options.add("negative");
    options.add("results-pending");
    options.add("on-campus");
    options.add("off-campus");
    try {
      Scanner scan = new Scanner(new File(textFile));
      while (scan.hasNextLine()) {
        if (scan.nextLine().contains(",") == false) {
          System.out.println("Student information needs to be separated by commas" + "\n"
              + "_______STUDENT IS SKIPPED_____");
        } else {
          String[] data = scan.nextLine().split(",");
          if (data.length != 4) {
            System.out.println(
                "Student information is invalid" + "\n" + "_______STUDENT IS SKIPPED_____");
          }
          // indexes....0 = ID, 1 = Name, 2 = status, 3 = residence
          else if (containsStudent(Integer.parseInt(data[0])) == true) {
            System.out.println("Student with ID: " + data[0] + " already exists" + "/n "
                + "_______STUDENT IS SKIPPED_____");
          } else if (data[0].length() != 9) {
            System.out.println("Student with ID: " + data[0] + " is invalid" + "\n "
                + "_______STUDENT IS SKIPPED_____");
          } else if (options.contains(data[2].toLowerCase()) == false
              || options.contains(data[3].toLowerCase()) == false) {
            System.out.println(
                "Information entered is invalid" + "\n" + "_______STUDENT IS SKIPPED_____");
          } else {
            System.out.println("add");
            addStudent(Integer.parseInt(data[0]), data[1], data[2], data[3]);
          }
        }
      }
      scan.close();
    } catch (NoSuchElementException e) {
      e.getMessage();
    } catch (IllegalStateException e) {
      e.getMessage();
    } catch (FileNotFoundException e) {
      e.getMessage();
    }
    
    
  }


}
