package performance;
import java.util.ArrayList;
import java.util.Comparator;

import members.*;
public class Dance implements Comparable<Dance>{
	private int id;
	private String title;
	private Style style;
	private ArrayList<Person> choreographers;
	private ArrayList<Performer> dancers;
	private Concert concert;
	private int year;

	public Dance(int id, String title, Style style, ArrayList<Person> choreographers, ArrayList<Performer> dancers, int year) {
		this.id = id;
		this.title = title;
		this.style = style;
		this.choreographers = choreographers;
		this.dancers = dancers;
		this.year = year;
	}
	
	public Dance(int id, String title) {
		this.id = id;
		this.title = title;
		this.dancers = new ArrayList<Performer>();
		this.choreographers = new ArrayList<Person>();
		
	}

	private boolean addDancer(Performer dancer) {
		if (!dancers.contains(dancer)) {
			dancers.add(dancer);
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean removeDancer(Person dancer) {
		if (dancers.contains(dancer)) {
			dancers.remove(dancer);
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getID(){
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public Style getStyle() {
		return style;
	}

	public ArrayList<Person> getChoreographers() {
		return choreographers;
	}

	public ArrayList<Performer> getDancers() {
		return dancers;
	}

	public Concert getConcert() {
		return concert;
	}

	public int getYear() {
		return year;
	}

	@Override
	public int compareTo(Dance o) {
		if (this.id > o.id) {
			return 1;
		}
		else if (this.id < o.id) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (this.id == ((Dance)o).id) {
			return true;
		}
		else {
			return false;
		}
	}
}
