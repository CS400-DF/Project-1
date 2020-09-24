/**
 * 
 */

/**
 * @author abhay
 *
 */
public class Student {
	private int studentId;
	private String name;
	private String healthStatus;
	private String residenceInfo;
	

	public Student(int studentId, String name, String healthStatus, String residenceInfo) {
		this.studentId = studentId;
		this.name = name;
		this.healthStatus = healthStatus;
		this.residenceInfo = residenceInfo;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the healthStatus
	 */
	public String getHealthStatus() {
		return healthStatus;
	}

	/**
	 * @return the residenceInfo
	 */
	public String getResidenceInfo() {
		return residenceInfo;
	}

	
	
}
