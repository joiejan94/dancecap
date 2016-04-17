package performance;
import java.util.ArrayList;

import members.*;
public class Dance {
	private String title;
	private Style style;
	private ArrayList<Person> choreographers;
	private ArrayList<Person> dancers;
	private Concert concert;
	private int year;

	public Dance(String title, Style style, ArrayList<Person> choreographers, ArrayList<Person> dancers, int year) {
		this.title = title;
		this.style = style;
		this.choreographers = choreographers;
		this.dancers = dancers;
		this.year = year;
	}

	private boolean addDancer(Person dancer) {
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
	
	public String getTitle() {
		return title;
	}

	public Style getStyle() {
		return style;
	}

	public ArrayList<Person> getChoreographers() {
		return choreographers;
	}

	public ArrayList<Person> getDancers() {
		return dancers;
	}

	public Concert getConcert() {
		return concert;
	}

	public int getYear() {
		return year;
	}
	
	
}
