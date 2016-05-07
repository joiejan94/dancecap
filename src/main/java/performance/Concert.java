package performance;
import java.util.ArrayList;
import java.util.HashMap;

import members.Performer;

import members.*;
public class Concert {
	
	private int id;
	private String title;
	private ArrayList<Person> choreographers;
	private ArrayList<Performer> dancers;
	private ArrayList<Dance> dances;
	private int year;
	public Concert(int id, String title, ArrayList<Dance> dances, int year) {
		this.id = id;
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
		// get dances from concert
		HashMap<Dance,Integer> dIndex = new HashMap<Dance,Integer>();
		for (int i = 0; i < dances.size(); i++){
			dIndex.put(dances.get(i),i);
		}
		int weights[][] = new int[dances.size()][dances.size()];	// 2d array of weights
		for (Performer p: dancers){ // loop through each dancer
			if (p.getDances().size() >= 2){ // check size of dances
				for (int j = 0; j < p.getDances().size(); j++){ //loop through dancers dances
					int k = j + 1;  // looks at next dance in list
					while (k < p.getDances().size()){ // looks 
						weights[dIndex.get(p.getDances().get(j))][dIndex.get(p.getDances().get(k))] += 1;
						weights[dIndex.get(p.getDances().get(k))][dIndex.get(p.getDances().get(j))] += 1;
					} // while
				} // for dances
			} // if dance size
		} // for performers
		return order;
	}
	
	public int getID(){
		return id;
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
