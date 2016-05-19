package performance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	public class Order {
		private ArrayList<Dance> orderOfDances;
		private int numQuickChanges;
		
		public Order() {
			orderOfDances = new ArrayList<Dance>();
			numQuickChanges = 0;
		}
		
		public ArrayList<Dance> getOrderOfDances() {
			return orderOfDances;
		}
		
		public int numQuickChanges() {
			return numQuickChanges;
		}
	}
	
	
	public Order createConcertOrder(Dance first) {
		Order order = new Order();
		// get dances from concert
		HashMap<Dance,Integer> dIndex = new HashMap<Dance,Integer>();
		int[] dancesByNum = new int[dances.size()-1];
		int index = 0;
		for (int i = 0; i < dances.size(); i++){
			dIndex.put(dances.get(i),i);
			if (!dances.get(i).equals(first)) {
				dancesByNum[index] = i;
				index += 1;
			}
		}
		// creating adjacency matrix that represents graph
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
		
		// Brute force algorithm for shortest path
		ArrayList<int[]> perms = permutation(dIndex.get(first),dancesByNum);
		int[] bestOrder = new int[dances.size()]; int minChanges = 10000; int current = 0;
		for (int[] perm: perms) {
			current = calcWeights(weights,perm);
			if (current < minChanges) {
				minChanges = current;
				bestOrder = perm;
			}
		}
		
		for (int o: bestOrder) {
			order.orderOfDances.add((Dance)getKeyFromValue(dIndex,o));
		}
		order.numQuickChanges = minChanges;
		
		return order;
	}
	
	 public static Object getKeyFromValue(Map hm, Object value) {
		    for (Object o : hm.keySet()) {
		      if (hm.get(o).equals(value)) {
		        return o;
		      }
		    }
		    return null;
		  }
	 
	private int calcWeights(int[][] matrix, int[] perm) {
		int totalWeight = 0;
		for (int i = 0; i < perm.length-1; i++) {
			totalWeight += matrix[perm[i]][perm[i+1]];
		}
		return totalWeight;
	}
	
	public ArrayList<int[]> permutation(int start, int[] dancelist) { 
		ArrayList<int[]> perms = new ArrayList<int[]>();
		int[] prefix = {start};
	    permhelp(prefix, dancelist, perms); 
	    return perms;
	}

	private void permhelp(int[] prefix, int[] dancelist,ArrayList<int[]> perms) {
	    int n = dancelist.length;
	    if (n == 0) {
	    	 perms.add(prefix);
	    }
	    else {
	        for (int i = 0; i < n; i++){
	        	int[] newpre = new int[prefix.length+1];
	        	int[] temp = new int[n-1];
	        	for (int j = 0; j < n; j++) {
	        		if (j < i) {
	        			temp[j] = dancelist[j];
	        		}
	        		else if (j > i) {
	        			temp[j-1] = dancelist[j];
	        		}
	        	}
	        	
	        	for (int j = 0; j < prefix.length; j++) {
	        		newpre[j] = prefix[j];
	        	}
	        	newpre[prefix.length] = dancelist[i];
	             permhelp(newpre, temp,perms);
	        }
	    }	    
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
