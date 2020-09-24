import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author
 *
 */
public class CovidTracker {

	private LinkedList<Student>[] hashTable;
	private int size;
	private int capacity;
	public String[] healthStatus = new String[] { "positive", "negative", "results-pending" };
	public String[] location = new String[] { "on-campus", "off-campus" };

	public CovidTracker() {
		size = 0;
		capacity = 200;
		hashTable = new LinkedList[capacity];
	}

	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return capacity;
	}

	public void parseFile(String FileName) {
		File data;
		Scanner scnr;
		int lineCount = 0;
		try {
			data = new File(FileName);
			scnr = new Scanner(data);
			while (scnr.hasNextLine()) {
				lineCount++;
				String[] details = scnr.nextLine().split(",");
				if (details.length != 4) {
					System.out.println("Invalid line, line " + lineCount + " is skipped, with key " + details[0]);
					continue;
				} else {
					if (checkLineValid(details)) {
						if (!containsKey(Integer.parseInt(details[0]))) {
							System.out.print(
									"Duplicate key, line " + lineCount + " with key " + details[0] + " is skipped");
						} else {
							Student stu = new Student(Integer.parseInt(details[0]), details[1], details[2], details[3]);
							put(stu);
						}
					} else {
						System.out.println("The health status and/or location have not been entered correctly on line "
								+ lineCount + " with key " + details[0] + " is skipped");

					}
				}
			}
			scnr.close();
		} catch (FileNotFoundException excpt) {
			excpt.getStackTrace();
		}
	}

	public boolean checkLineValid(String[] data) {
		boolean checkStatus = false, checkLocation = false;
		for (int i = 0; i < healthStatus.length; i++) {
			if (data[2].equalsIgnoreCase(healthStatus[i])) {
				checkStatus = true;
				break;
			}
		}
		for (int j = 0; j < location.length; j++) {
			if (data[3].equalsIgnoreCase(location[j])) {
				checkStatus = true;
				break;
			}
		}
		return checkStatus && checkLocation;
	}

	public boolean put(Student student) {
		return false;

	}

	public boolean containsKey(int key) {
		return false;

	}
	
	//just to test
	public static void main() {
		CovidTracker tracker = new CovidTracker();
		tracker.parseFile("a.txt");
	}
}
