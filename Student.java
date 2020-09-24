
public class Student {
	int id;
	String name;
	String healthStatus;
	String residence;
	
	public Student(int id, String name, String healthStatus, String residence){
	  this.id = id;
	  this.name = name;
	  this.healthStatus = healthStatus;
	  this.residence = residence;
	}
	
	public int getID() {
	  return this.id;
	}
	
	public String getName() {
	  return this.name;
	}
	
	public String getHealthStatus() {
	  return this.healthStatus;
	}
	
	public String getResidence() {
	  return this.residence;
	}
	public void printStudent() {
	  System.out.println("Name: " + this.name + " /nHealth status: " + this.healthStatus + " /nResidence: " + 
			  this.residence);	  
	}

}
