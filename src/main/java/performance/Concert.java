package performance;
import java.util.ArrayList;
import members.Performer;

import members.*;
public class Concert {
	
	private String title;
	private ArrayList<Person> choreographers;
	private ArrayList<Performer> dancers;
	private ArrayList<Dance> dances;
	private int year;
	public Concert(String title, ArrayList<Dance> dances, int year) {
		this.title = title;
		this.dances = dances;
		this.choreographers = new ArrayList<Person>();
		this.dancers = new ArrayList<Performer>();
		this.year = year;
		
		for (Dance dance: dances) {
			for (Person choreographer: dance.getChoreographers()) {
				if (!choreographers.contains(choreographer)) {
					choreographers.add(choreographer);
				}
			}
			for (Performer dancer: dance.getDancers()) {
				if (!dancers.contains(dancer)) {
					choreographers.add(dancer);
				}
			}
		}
		
	}
	
	private ArrayList<Dance> createConcertOrder() {
		ArrayList<Dance> order = new ArrayList<Dance>();
		return order;
	}
	
	public String getTitle() {
		return title;
	}
	public ArrayList<Person> getChoreographers() {
		return choreographers;
	}
	public ArrayList<Performer> getDancers() {
		return dancers;
	}
	public ArrayList<Dance> getDances() {
		return dances;
	}
	public int getYear() {
		return year;
	}
}
