//hash table class 
import java.util.*;
import java.io.*;

public class CovidApp {


	@SuppressWarnings("unchecked")
	int numElements = 0;
	public CovidApp() {

	  
	  
	  
	}
	/**
	 * @author Nicole Gathman
	 * Method loads student information into the hash table from a parsed text file
	 * Skips students with incorrect student information
	 * @return textFile is the string name of the information file given by user
	 */
	public boolean load(String textFile) {
          boolean fileCorrect = true;
	  ArrayList<String> options = new ArrayList<>(); 
	  options.add("positive");
	  options.add("negative");
	  options.add("results-pending");
	  options.add("on-campus");
	  options.add("off-campus");
	  int fileLine = 0;
	  try {
	    Scanner scan = new Scanner(new File(textFile));
	    while(scan.hasNextLine()) {
	      fileLine++;
	      String currentLine = scan.nextLine();
	      if(currentLine.contains(",") == false) {
	        System.out.println("Student information needs to be separated by commas"
	    	    + "at line number " + fileLine+ "\n" + "_______STUDENT IS SKIPPED_____");
	      }
	      else {	
	        String[] data = currentLine.split(",");
	        if(data.length != 4) {
		  System.out.println("Student information is invalid"
	              + " at line number " + fileLine + "\n" + "_______STUDENT IS SKIPPED_____");
	        }
	        // indexes....0 = ID, 1 = Name, 2 = status, 3 = residence
	        else if(containsKey(Integer.parseInt(data[0])) == true){
	          System.out.println("Student with ID: " + data[0] + " already exists" + "/n "
	              + "_______STUDENT IS SKIPPED_____");
	        }
	        else if(data[0].length() != 9){
		      System.out.println("Student with ID: " + data[0] + " at line " +fileLine +
		          " is invalid" + "\n " + "_______STUDENT IS SKIPPED_____");
	        }
	        else if(options.indexOf(data[2].toLowerCase()) == -1 || 
	            options.indexOf(data[3].toLowerCase()) == -1) {
	          System.out.println("Information entered is invalid at line number " + fileLine
	              + "\n" + "_______STUDENT IS SKIPPED_____");
	        }
	        else {
	          addStudent(Integer.parseInt(data[0]), data[1], data[2], data[3]);
	        } 
	      }
            }
	    scan.close(); 
	  }
	  catch(NoSuchElementException e) {
	    e.getMessage();
	    fileCorrect = false;
	  }
	  catch(IllegalStateException e) {
	    e.getMessage(); 
            fileCorrect = false;
	  }
          catch(FileNotFoundException e) {
	    e.getMessage();
	    fileCorrect = false;
	  }
	  finally{
            return fileCorrect;
         }


	
	
	
}
