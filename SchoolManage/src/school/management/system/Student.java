package school.management.system;

public class Student {
	private int studentID;
	private String StudentName;
	private int grade;
	private int feesPaid;
	private int feesTotal;
	
	/**
	 * To create a new Student by initializing.
	 * @param sname
	 * @param sid
	 * @param grade
	 */
	
	public Student(String sname, int sid, int grade) {
		
		this.studentID = sid;
		this.StudentName = sname;
		this.grade = grade;
		this.feesPaid = 0;
		this.feesTotal = 30000;
		
	}
	
	//Do not change Student's name and ID
	/**
	 * Used to update the student's grade
	 * @param grade new grade of the student
	 */
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	/**
	 * Keep adding the fees to feesPaid field
	 * feespaid = 10,000 + 5000 + 15000
	 *  Add the fees to the fees paid
	 *  The school is going receive the funds.
	 *  
	 * @param fees the fees that the student paid
	 */
//	public void feesPaidUpdate(int fees) {
//		feesPaid += fees;
//	}
	public void payFees(int fees) {
		feesPaid += fees;
		School.updateTotalMoneyEarned(fees);
	}
	
	/**
	 * 
	 * @return ID of the student
	 */

	public int getStudentID() {
		return studentID;
	}
	
	/**
	 * 
	 * @return name of the student
	 */

	public String getStudentName() {
		return StudentName;
	}

	/**
	 * 
	 * @return grade of the student
	 */

	public int getGrade() {
		return grade;
	}
	
	/**
	 * 
	 * @return feesPaid of the student
	 */


	public int getFeesPaid() {
		return feesPaid;
	}

	/**
	 * 
	 * @return feesTotal of the student
	 */

	public int getFeesTotal() {
		return feesTotal;
	}
	
	public int getRemainingFees(int fees) {
		return feesTotal-feesPaid;
	}

	@Override
	public String toString() {
		return "Student's name: " + StudentName +
				"\nTotal tuition paid so far is $" + feesPaid ;
	}
	
	
	
	
	
}
