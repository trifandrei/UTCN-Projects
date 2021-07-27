package Agent;

public class AgentHelper {
	private final String[] nounGround = { "foundation", "floor", "earth", "land", "base","ground" };
	private final String[] vbGround= {"build","create","set up","make","forge"};
	
	private final String[] nounHouse = { "house", "residence", "habitation", "home", "dwelling" };
	private final String[] vbHouse= {"build","create","set up","make","forge"};
	
	private final String[] vbHydro= {"build","create","set up","make","forge"};
	private final String[] nounHydro= {"river","watercourse","stream","water","hydro"};
	
	private final String[] vbShop= {"build","create","set up","make","forge"};
	private final String[] nounShop= {"shop","store","outlet"};
	
	public AgentHelper() {
		
	}

	public String[] getNounGround() {
		return nounGround;
	}

	public String[] getVbGround() {
		return vbGround;
	}

	public String[] getNounHouse() {
		return nounHouse;
	}

	public String[] getVbHouse() {
		return vbHouse;
	}

	public String[] getVbHydro() {
		return vbHydro;
	}

	public String[] getNounHydro() {
		return nounHydro;
	}

	public String[] getVbShop() {
		return vbShop;
	}

	public String[] getNounShop() {
		return nounShop;
	}
	
}
