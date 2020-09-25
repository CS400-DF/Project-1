//hash table class 
import java.util.*;
import java.io.*;

public class CovidApp {

	LinkedList<Student>[] hashTable;
	@SuppressWarnings("unchecked")
	int numElements = 0;
	public CovidApp() {
	  //initially 10?
	  hashTable = new LinkedList[10];
	  
	  
	  
	}

	public void load(String textFile) {
	  ArrayList<String> options = new ArrayList<>(); 
	  options.add("positive");
	  options.add("negative");
	  options.add("results-pending");
	  options.add("on-campus");
	  options.add("off-campus");
		try {
	      Scanner scan = new Scanner(new File(textFile));
	      while(scan.hasNextLine()) {
	    	if(scan.nextLine().contains(",") == false) {
	    	  System.out.println("Student information needs to be separated by commas"
	    	      +  "\n" + "_______STUDENT IS SKIPPED_____");
	    	}
	    	else {	
	          String[] data = scan.nextLine().split(",");
	          if(data.length != 4) {
		        System.out.println("Student information is invalid"
			        +  "\n" + "_______STUDENT IS SKIPPED_____");
	          }
	          // indexes....0 = ID, 1 = Name, 2 = status, 3 = residence
	          else if(containsKey(Integer.parseInt(data[0])) == true){
	            System.out.println("Student with ID: " + data[0] + " already exists" + "/n "
	                + "_______STUDENT IS SKIPPED_____");
	          }
	          else if(data[0].length() != 9){
		        System.out.println("Student with ID: " + data[0] + " is invalid" + "\n "
		        	+ "_______STUDENT IS SKIPPED_____");
	          }
	          else if(options.contains(data[2].toLowerCase()) == false || 
	        		options.contains(data[3].toLowerCase()) == false) {
	            System.out.println("Information entered is invalid" + "\n" 
	        		+ "_______STUDENT IS SKIPPED_____");
	          }
	          else {
	        	System.out.println("add");
	            addStudent(Integer.parseInt(data[0]), data[1], data[2], data[3]);
	          } 
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
	    int id = student.id; // this is the key

	}
	
	
	
}
