/**
 * @author abhay
 *
 */
public class Student {
	private Integer studentId;
	private String name;
	private String healthStatus;
	private String residenceInfo;

	public Student() {

	}
	public Student(Integer studentId, String name, String healthStatus, String residenceInfo) {
		this.studentId = studentId;
		this.name = name;
		this.healthStatus = healthStatus;
		this.residenceInfo = residenceInfo;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the healthStatus
	 */
	public String getHealthStatus() {
		return this.healthStatus;
	}

	/**
	 * @return the residenceInfo
	 */
	public String getResidenceInfo() {
		return this.residenceInfo;
	}

	/**
	 * Student details are printed
	 */
	public void printStudent() {
		System.out.println(
				"Name: " + this.name + " \nHealth status: " + this.healthStatus + "\nResidence: " + this.residenceInfo);
	}
	
	/**
	 * @return String containing student details
	 */
	public String getValue() {
		return (String)(getName()+" "+getHealthStatus()+" "+getResidenceInfo()+" ");
	}

}