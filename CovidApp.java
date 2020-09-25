import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CovidApp {

  HashTableMap<Integer, Student> hashTable;

  public CovidApp() {
    hashTable = new HashTableMap<Integer, Student>();
  }

  public void loadFile(String textFile) {
  }

  // other team members...
  public boolean addStudent(int id, String name, String healthStatus, String housingStatus) {
    long id = student.id; // this is the key
    return false;
  }

  public Student getStudentInformation(int id) throws NoSuchElementException {
    return new Student(123456789, "Hayden Williams", "positive", "on-campus");
  }

  public int getAmountOfStudents() {
    return hashTable.size();
  }
  
  public boolean containsStudent(int id) {
    return false;
  }
  
  public Student removeStudent(int id) {
    return new Student(123456789, "Hayden Williams", "positive", "on-campus");
  }
  
  public void clearStudents() {
    hashTable.clear();
  }



}

