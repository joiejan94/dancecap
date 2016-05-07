package performance;
import java.util.ArrayList;
import members.Performer;

import members.*;
public class Dance {
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
	
	
}
