/**
 * @author abhay
 *
 */
import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CovidApp {

	public HashTableMap<Integer, Student> map;
	public String[] healthStatus = new String[] { "positive", "negative", "results-pending" };
	public String[] location = new String[] { "off-campus", "on-campus" };

	public CovidApp() {
		this.map = new HashTableMap();
	}

	/**
	 * This method add a student's information into the map
	 * 
	 * @return true if the student is successfully added, false otherwise
	 */
	public boolean addStudent(Integer id, String name, String healthStatus, String housingStatus) {
		Student student = new Student(id, name, healthStatus, housingStatus);
		return map.put(id, student);
	}

	/**
	 * This method returns a student object based on the given ID number
	 * 
	 * @param key - student ID number
	 * @return student object
	 * @throws NoSuchElementException
	 */
	public Student getStudentInformation(Integer id) throws NoSuchElementException {
		if (!(map.containsKey(id))) {
			throw new NoSuchElementException();
		} else {
			return (Student) map.get(id);
		}

	}

	/**
	 * This methods gets the number of student objects stored in the map
	 * 
	 * @return the number of student objects stored in the map
	 */
	public int getAmountOfStudents() {
		return map.size();

	}

	/**
	 * This method checks if a student already has information in the map
	 * 
	 * @param key - the student ID number
	 * @return true if the student is in the map, false otherwise
	 */
	public boolean containsStudent(int id) {
		return map.containsKey(id);

	}

	/**
	 * This method removes a student from the map given the ID number
	 * 
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
	 * This method is responsible for parsing a text-file, each line is checked for
	 * its validity. Valid lines are parsed and stored in the hashtable.
	 * 
	 * @param FileName
	 * @return true or false depending on whether the file was parsed correctly
	 */
	public boolean load(String FileName) {
		boolean fileFound = true;
		File data;
		Scanner scnr;
		int lineCount = 0;
		try {
			// checks if file exists
			
			data = new File(FileName);
			
			scnr = new Scanner(data);
			while (scnr.hasNextLine()) {
				
				// file is parsed
				lineCount++;
				// lines are counted to indicate errors to the user
				// each line is split
				String[] details = scnr.nextLine().split(",");
				if (details.length != 4) {
					// if the string array does not contain 4 elements
					System.out.println("Invalid line, line " + lineCount + " is skipped, with key " + details[0]);
					// moves on to the next line
					continue;
				} else {
					// line validity checked
					if (checkLineValid(details)) {
							// student is added if line is valid
							addStudent((Integer.parseInt(details[0])), details[1], details[2], details[3]);
						
					} else {

						System.out.println("The health status and/or location have not been entered correctly on line "
								+ lineCount + " with key " + details[0] + " is skipped");

					}
				}
			}
			scnr.close();
		} catch (FileNotFoundException excpt) {
			// FNFE exception
			fileFound = false;
			excpt.getStackTrace();
		}
		// returns true/false if file was parsed successfully
		return fileFound;
	}

	/**
	 * A string array containing the details parsed from a single line is checked
	 * 
	 * @param data
	 * @return true/false if line is valid
	 */
	public boolean checkLineValid(String[] data) {
		boolean checkStatus = false, checkLocation = false, checkIdSize = false;
		
		for (int i = 0; i < healthStatus.length; i++) {
			//checks if the status falls under one of the 3 possible statuses
			if (data[2].equalsIgnoreCase(healthStatus[i])) {
				checkStatus = true;
				break;
			}
		}
		for (int j = 0; j < location.length; j++) {
			//checks if the status falls under one of the 2 possible residential statuses
			if (data[3].equalsIgnoreCase(location[j])) {				
				checkLocation = true;
				break;
			}
		}
		//length of the id must be 9
		if (data[0].length() == 9) {			
			checkIdSize = true;
		}
		//System.out.print(checkIdSize && checkStatus && checkLocation);
		return checkStatus && checkLocation && checkIdSize;
	}

}
