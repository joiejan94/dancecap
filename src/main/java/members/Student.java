package members;
import members.Year; 

public class Student extends Person {
	private int ID;
	private String[] major;
	private int year;
	
	public Student(String name, Style[] style, int ID, String[] major, int year) {
		super(name,style,ID);
		this.major = major;
		this.year = year;
	}

	public int getID() {
		return ID;
	}

	public String[] getMajor() {
		return major;
	}

	public int getYear() {
		return year;
	}

}
