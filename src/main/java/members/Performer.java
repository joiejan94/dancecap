package members;
import java.util.ArrayList;
import performance.Dance;

public class Performer extends Person {
	private ArrayList<Dance> dances;
	public Performer(String name, Style[] style, int ID, ArrayList<Dance> dances) {
		super(name, style, ID);
		this.dances = dances;
	}
	
	public ArrayList<Dance> getDances() {
		return dances;
	}

	
}
