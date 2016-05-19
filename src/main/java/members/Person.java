package members;
import members.Style;

public class Person implements Comparable<Person>{
	private String name;
	private Style[] style;
	protected int ID;
	public Person(String name, Style[] style, int ID) {
		this.name = name;
		this.style = style;
		this.ID = ID;
	}
	
	public String getName() {
		return this.name;
	}

	public Style[] getStyle() {
		return style;
	}
	
	public int getID() {
		return ID;
	}

	@Override
	public int compareTo(Person o) {
		if (this.getID() > o.getID()) {
			return 1;
		}
		else if (this.getID() < o.getID()) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
