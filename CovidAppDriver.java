import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class drives the Covid Tracker Application
 * 
 * @author Dana Schneck
 */
public class CovidAppDriver {

  private final static String WELCOME_MSG =
      "~~~~~~~~ WELCOME to the Covid-19 Tracker Application ~~~~~~~~";
  private final static String GOODBYE_MSG =
      "~~~~~~~~~~~ Thank you for using this application! ~~~~~~~~~~~";
  private final static String MENU = "\nCOMMAND MENU:\n"
      + "[L] Loads a text file of Student objects into the Covid-19 Tracker App\n"
      + "[P] Puts a single new Student into the Covid-19 Tracker App.\n"
      + "[G] Gets a Student's information from the application.\n"
      + "[S] Return the amount of Students in the application.\n"
      + "[K] Check if a Student exists in the application\n" + "[R] Remove an existing Student.\n"
      + "[C] Clear all existing students from the application.\n" + "[Q] Quit the application.\n"
      + "[H] Help (display this Menu).";

  /**
   * Processes and runs the user command line
   * 
   * @param command User's command string
   * @param covidApp Instance of covidApp class that Students are being added into
   * @return False if user wants to quit midway through a command and true otherwise
   */
  private static boolean processUserCommandLine(String command, CovidApp covidApp,
      Scanner scanner) {
    String[] input = command.trim().split(" ");
    switch (input[0].toUpperCase()) {
      case "L": // load a Student text file into the application
        if (loadHelper(covidApp, scanner) == false) {
          return false;
        }
        break;

      case "P": // put a new single Student into the application
        if (putHelper(covidApp, scanner) == false) {
          return false;
        } ;
        break;

      case "G": // get a Student's information if they are in the application
        if (getHelper(covidApp, scanner) == false) {
          return false;
        }
        break;

      case "S": // return the amount of students in the application
        System.out.println("There are " + covidApp.getAmountOfStudents()
            + " students currently stored in this application.");
        break;

      case "K": // check if a Student exists in the application
        if (containsKeyHelper(covidApp, scanner) == false) {
          return false;
        }
        break;

      case "R": // remove a Student from the application
        if (removeHelper(covidApp, scanner) == false) {
          return false;
        }
        break;

      case "C": // clear all Students from the application
        covidApp.clearStudents();
        System.out.println("Cleared all students from this application.");
        break;

      case "H": // display the menu
        System.out.println(MENU);
        break;

      default: // command doesn't exist
        System.out.println("WARNING. Invalid command. Please enter H and refer to the menu.");
    }
    
    return true;
  }

  /**
   * Driver method for the Covid-19 Tracker application (reads and processes user command lines)
   */
  private static void driver() {
    // Initialize variables
    CovidApp covidHashTable = new CovidApp();
    Scanner scanner = new Scanner(System.in);
    String promptCommandLine = "\nENTER COMMAND: ";

    // initially print out the menu and command prompt
    System.out.println(MENU);
    System.out.print(promptCommandLine);
    
    // get users first input
    String line = scanner.nextLine().trim();  
    char c = line.charAt(0);
    
    while (Character.toUpperCase(c) != 'Q') {
      // parse and process the user command line
      if (processUserCommandLine(line, covidHashTable, scanner) == false) {
        break;
      }
      System.out.print(promptCommandLine);
      // read next user command line
      line = scanner.nextLine().trim();
      c = line.charAt(0);
    }

    // close the scanner
    scanner.close();
  }

  /**
   * Main method for the Covid-19 driver application
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(WELCOME_MSG); // start the application
    driver(); // run the application
    System.out.println(GOODBYE_MSG); // end the application
  }

  /**
   * Checks if the user wants to quit at any point
   * 
   * @param input User's input as a string
   * @return True if user enters "q" and false otherwise
   */
  private static boolean checkIfQuit(String input) {
    if (input.trim().equalsIgnoreCase("q")) {
      return true;
    }
    
    return false;
  }

  /**
   * Checks if the user has entered a valid ID number
   * 
   * @param id User's ID number input as a string
   * @return True if user enters a valid ID and false otherwise 
   */
  private static boolean checkIfValidID(String id) {
    try {
      Integer.parseInt(id);
    } catch (NumberFormatException e) {
      System.out.println("Invalid ID format. Student ID number must be a single 9 digit number.");
      return false;
    }
    if (id.length() != 9) {
      System.out.println("Invalid ID format. Student ID number must be a single 9 digit number.");
      return false;
    }
    
    return true;
  }
  /**
   * Helps with the functionality of loading a text file of Students into the application.
   * 
   * @param covidApp Instance of CovidApp class that Students are being added into
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean loadHelper(CovidApp covidApp, Scanner scanner) {
    String fileName = null;
    System.out.print(
        "Please enter the name of the text file of Students you would like to load into the application in the form <FileName>.txt : ");
    fileName = scanner.nextLine().trim();
    
    // check if user has entered 'q' to quit
    if (checkIfQuit(fileName)) {
      return false;
    }
    
    // Try to load file of Students into the system
    if (covidApp.loadFile(fileName) == false) {
      System.out.println("File of sftudents uncessessfully loaded into the application. Please hit [L] and try again.");
    }
    else {
      System.out.println("File of students successfully loaded into the application.");
    }
    
    return true;
  }

  /**
   * Helps with the functionality of adding a Student to the application.
   * 
   * @param covidApp Instance of the CovidApp class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean putHelper(CovidApp covidApp, Scanner scanner) {
    boolean correctIdInput = false;
    
    // keep polling user for valid ID number
    while (correctIdInput == false) {
      // initialize variables
      String id = null;
      String name = null;
      String housingStatus = null;
      String healthStatus = null;
      
      System.out.print("Enter 9 digit student ID number: ");
      id = scanner.nextLine().trim();
      
      // check if user has entered 'q' to quit
      if (checkIfQuit(id)) { 
        return false;
      }
      
      // check for correct ID input
      if (checkIfValidID(id) == false) {
        continue;
      }
      
      // check if user has already been entered into the system
      if (covidApp.containsStudent(Integer.parseInt(id)) == true) {
        System.out.println("A student with that ID already exists in the hashtable.");
      
      // else add the Student into the system
      } else {
        System.out.print("Enter Student's name: ");
        name = scanner.nextLine();
        
        // check if user entered 'q' to quit
        if (checkIfQuit(name)) { 
          return false;
        }
        
        boolean correctHealthStatusInput = false;
        
        // keep polling user for a valid healthStatus input
        while (correctHealthStatusInput == false) { 
          System.out.print(
              "Enter Student's health status (either \"positive\", \"negative\", or \"results-pending\": ");
          healthStatus = scanner.nextLine().trim();
          
          // check if user entered 'q' to quit
          if (checkIfQuit(healthStatus)) { 
            return false;
          }
          
          // check for correct healthStatus input
          if (!healthStatus.equalsIgnoreCase("positive")
              && !healthStatus.equalsIgnoreCase("negative")
              && !healthStatus.equalsIgnoreCase("results-pending")) {
            System.out.println("Student's health status invalid.");
            continue;
          }
          correctHealthStatusInput = true;
        }
        
        boolean correctHousingStatusInput = false;
        
        // keep polling user for a valid housingStatus input
        while (correctHousingStatusInput == false) { 
          System.out
              .print("Enter Student's housing status (either \"on-campus\" or \"off-campus\"): ");
          housingStatus = scanner.nextLine().trim();
          
          // check if user entered 'q' to quit
          if (checkIfQuit(housingStatus)) {
            return false;
          }
          
          // check for correct housingStatus input
          if (!housingStatus.equalsIgnoreCase("off-campus")
              && !housingStatus.equalsIgnoreCase("on-campus")) {
            System.out.println("Student's housing status invalid.");
            continue;
          }
          correctHousingStatusInput = true;
        }
        
        // add correctly formatted Student to the application
        covidApp.addStudent(Integer.parseInt(id), name, healthStatus, housingStatus);
        System.out.println("New student successfully added.");
      }
      correctIdInput = true;
    }
    
    return true;
  }

  /**
   * Assists with the functionality of getting a Student Object
   * 
   * @param covidApp Instance of the CovidApp class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean getHelper(CovidApp covidApp, Scanner scanner) {
    boolean correctIdInput = false;
    String id = null;
    
    // keep polling user for a valid ID number
    while (correctIdInput == false) { 
      System.out.print("Enter 9 digit student ID number: ");
      id = scanner.nextLine().trim();
      
      // check if user has entered 'q' to quit
      if (checkIfQuit(id)) { 
        return false;
      }
      
      // check for correct ID input
      if (checkIfValidID(id) == false) {
        continue;
      }
      
      correctIdInput = true;
    }
    
    // try to get Student information
    try {
      covidApp.getStudentInformation(Integer.parseInt(id)).printStudent();
    } catch (NoSuchElementException e) {
      System.out.println("There is no existing student with that ID number.");
    }
    
    return true;
  }

  /**
   * Assists with the functionality of checking if a Student exists in the application.
   * 
   * @param covidApp Instance of the CovidApp class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  public static boolean containsKeyHelper(CovidApp covidApp, Scanner scanner) {
    boolean correctIdInput = false;
    String id = null;
    
    // keep polling user for a valid ID number
    while (correctIdInput == false) { 
      System.out.print("Enter 9 digit student ID number: ");
      id = scanner.nextLine().trim();
      
      // check if user has entered 'q' to quit
      if (checkIfQuit(id)) { 
        return false;
      }
      
      // check for correct ID input
      if (checkIfValidID(id) == false) {
        continue;
      }
      correctIdInput = true;
    }
    
    // check if Student exists in the system
    if (covidApp.containsStudent(Integer.parseInt(id)) == true) {
      System.out.println("A student with this ID number exists in the system.");
    } else {
      System.out.println("A student with this ID number does not exist in the system.");
    }
    
    return true;
  }

  /**
   * Helps with the functionality of removing a student from the application
   * 
   * @param covidApp Instance of the CovidApp class
   * @param scanner Scanner used to read user input
   * @return False if user wants to quit midway through this command and true otherwise
   */
  private static boolean removeHelper(CovidApp covidApp, Scanner scanner) {
    boolean correctIdInput = false;
    String id = null;
    
    // keep polling user for a valid ID number
    while (correctIdInput == false) { 
      System.out.print("Enter 9 digit student ID number: ");
      id = scanner.nextLine().trim();
      
      // check if user has entered 'q' to quit
      if (checkIfQuit(id)) { 
        return false;
      }
      
      // check for correct ID input
      if (checkIfValidID(id) == false) {
        continue;
      }
      correctIdInput = true;
    }
    
    // try to remove Student from the system if possible
    if (covidApp.removeStudent(Integer.parseInt(id)) == null) {
      System.out.println(
          "Student with this ID number doesn't exist in the system and therefore cannot be deleted.");
    } else {
      System.out.println("Student with this ID number has been deleted from the system");
    }
    
    return true;
  }
}
