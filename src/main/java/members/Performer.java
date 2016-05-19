package members;
import java.util.ArrayList;
import performance.Dance;

public class Performer extends Person {
	private ArrayList<Dance> dances;
	public Performer(String name, Style[] style, int ID, ArrayList<Dance> dances) {
		super(name, style, ID);
		this.dances = dances;
	}
	
	public Performer(String name, Style[] style, int ID) {
		super(name, style, ID);
		this.dances = new ArrayList<Dance>();
	}

	public ArrayList<Dance> getDances() {
		return dances;
	}

	@Override
	public boolean equals(Object o) {
		if (this.ID == ((Performer)o).ID) {
			return true;
		}
		else {
			return false;
		}
	}
}
