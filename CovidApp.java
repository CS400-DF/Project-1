//hash table class 
import java.util.*;
import java.io.*;

public class CovidApp {

	LinkedList<Student>[] hashTable;
	@SuppressWarnings("unchecked")
	int numElements = 0;
	public CovidApp(String textFilePath) {
	  //initially 10?
	  hashTable = new LinkedList[10];
	  
	  load(textFilePath);
	  
	}

	public void load(String textFile) {
		try {
	      Scanner scan = new Scanner(new File(textFile));
	      while(scan.hasNextLine()) {
	        String[] data = scan.nextLine().split(",");
	        // indexes....0 = ID, 1 = Name, 2 = status, 3 = residence
	        if(containsKey(Integer.parseInt(data[0])) == true){
	          System.out.println("Student with ID: " + data[0] + " already exists" + "/n "
	        		  + "_______STUDENT IS SKIPPED_____");
	        }
	        else {
	          Student student = new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]);
	          put(student); //student is put in hashTable
	        } 
	      }
	      scan.close(); 
		}
	    catch(NoSuchElementException e) {
	      e.getMessage();
	    }
	    catch(IllegalStateException e) {
	      e.getMessage();  
	    }
		catch(FileNotFoundException e) {
		  e.getMessage();
		}
    }
	//other team members...
	public boolean put(Student student) {
	    long id = student.id; // this is the key

	}
	
	
	
}
