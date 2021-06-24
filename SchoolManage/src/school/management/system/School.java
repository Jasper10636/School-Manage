package school.management.system;


import java.util.List;
/**
 * Many Student and teachers
 * Implenments teachers and student using ArrayList
 * Because the # of Student and Teacher always changing 
 * 
 * 
 * @author panjiakai
 *
 */
public class School {
	
	
	private List<Teacher> teachers;
	private List<Student> students;
	private static int TotalMoneyEarned;
	private static int TotalMoneySpent;
	
	public School(List<Teacher> teachers, List<Student> students) {
		this.teachers = teachers;
		this.students = students;
		TotalMoneyEarned = 0;
		TotalMoneySpent = 0;
	}

	/**
	 * 
	 * @return the list of teachers in the school
	 */
	public List<Teacher> getTeachers() {
		return teachers;
	}
	
//	public void setTeachers(List<Teacher> teachers) {
//		this.teachers = teachers;
//	}
	
	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public List<Student> getStudents() {
		return students;
	}

//	public void setStudents(List<Student> students) {
//		this.students = students;
//	}
	
	public void addStudent(Student student) {
		students.add(student);
	}

	public int getTotalMoneyEarned() {
		return TotalMoneyEarned;
	}

//	public void setTotalMoneyEarned(int totalMoneyEarned) {
//		TotalMoneyEarned = totalMoneyEarned;
//	}
	
	
	/**
	 * Add the total Money earned by school
	 * @param MoneyEarned money that is supposed to be added
	 */
	public static void updateTotalMoneyEarned(int MoneyEarned) {
		TotalMoneyEarned += MoneyEarned;
	}

	public int getTotalMoneySpent() {
		return TotalMoneySpent;
	}

//	public void setTotalMoneySpent(int totalMoneySpent) {
//		TotalMoneySpent = totalMoneySpent;
//	}
	
	/**
	 * update the money  that is spent by the school which
	 * is the salary given by the school to its teacher
	 * @param Moneyspent
	 */
	public static void updateTotalMoneySpent(int Moneyspent) {
		TotalMoneyEarned -= Moneyspent;
	}
	
	
	
	
	
}
