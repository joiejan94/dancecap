package members;

public class Faculty extends Person{
	private String department;
	private Style[] style;
	private int ID;
	public Faculty(String name, Style[] style, int ID, String department) {
		super(name,style,ID);
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public Style[] getStyle() {
		return style;
	}

	public int getID() {
		return ID;
	}

	
}
