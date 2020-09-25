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
  public boolean addStudent(Student student) {
    long id = student.id; // this is the key
    return false;
  }

  public Student getStudentInformation(int key) throws NoSuchElementException {
    return new Student(123456789, "Hayden Williams", "positive", "on-campus");
  }

  public int getAmountOfStudents() {
    return hashTable.size();
  }
  
  public boolean containsStudent(int key) {
    return false;
  }
  
  public Student removeStudent(int key) {
    return new Student(123456789, "Hayden Williams", "positive", "on-campus");
  }
  
  public void clearStudents() {
    hashTable.clear();
  }



}

