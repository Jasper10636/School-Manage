package school.management.system;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Teacher lizzy = new Teacher("Lizzy", 1, 500);
		Teacher mellisa = new Teacher("Mellisa", 2, 700);
		Teacher pan = new Teacher("Pan", 3, 900);
		
		List<Teacher> teacherlist = new ArrayList<>();
		teacherlist.add(mellisa);
		teacherlist.add(lizzy);
		teacherlist.add(pan);
		
		Student Jasper = new Student("Jasper",1, 4);
		Student John = new Student("John", 2, 3);
		Student Jack = new Student("Jack", 3, 5);
		
		List<Student> studentlist = new ArrayList<>();
		studentlist.add(Jasper);
		studentlist.add(John);
		studentlist.add(Jack);
		
		School utd = new School(teacherlist, studentlist);
		
		Teacher Junior = new Teacher("Junior", 4, 1000);
		utd.addTeacher(Junior);
		
		Jasper.payFees(5000);
		John.payFees(6000);
		System.out.println("UTD has earned "+ utd.getTotalMoneyEarned());
		
		System.out.println("\n===== Making UTD PAY Salary=====\n");
		lizzy.receiveSalary(lizzy.getSalary());
		System.out.println("UTD has pay salary to "+ lizzy.getName() + " and now has " + utd.getTotalMoneyEarned());
		System.out.println(lizzy.getName() + " now get "+ lizzy.getSalary() + "\n");
		
		pan.receiveSalary(pan.getSalary());
		System.out.println("UTD has pay salary to "+ pan.getName() + " and now has " + utd.getTotalMoneyEarned());
		System.out.println(pan.getName() + " now get "+ pan.getSalary() + "\n");
		
		System.out.println(Jasper);
		System.out.println(lizzy);
		System.out.println(mellisa);
	}
}
