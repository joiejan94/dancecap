package academics;
import java.util.ArrayList;

import members.*;

public class Class {
	//info says where and when class is taking place
	private String name;
	private Person instructor;
	private Style style;
	private String info;
	private ArrayList<Student> students;
	private int year;
	
	public Class(String name, Person instructor, Style style,String info, ArrayList<Student> students,int year ) {
		this.name = name;
		this.instructor = instructor;
		this.style = style;
		this.info = info;
		this.students = students;
		this.year = year;
	}
	
	private boolean addStudent(Student student) {
		if (!students.contains(student)) {
			students.add(student);
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean removeStudent(Student student) {
		if (students.contains(student)) {
			students.remove(student);
			return true;
		}
		else{
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public Person getInstructor() {
		return instructor;
	}

	public Style getStyle() {
		return style;
	}

	public String getInfo() {
		return info;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public int getYear() {
		return year;
	}

	
}
