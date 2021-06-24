package school.management.system;

public class Teacher {
	private int TeacherID;
	private String TeacherName;
	private int salary;
	private int salaryEarned;
	
	public Teacher(String name, int tid, int salary) {
		this.TeacherID = tid;
		this.TeacherName = name;
		this.salary = salary;
	}
	
	public int getID() {
		return TeacherID;
	}
	
	public String getName() {
		return TeacherName;
	}
	
	public int getSalary() {
		return salary;
	}
	
	/**
	 * set the teacher salary
	 * @param salary
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void receiveSalary(int salary){
		salaryEarned += salary;
		School.updateTotalMoneySpent(salary);
	}

	@Override
	public String toString() {
		return "\nName of the Teacher: " + TeacherName + 
				"\nTotal Salary earned so far $" + salaryEarned;
	}
	
	
	
	
	
}
