package module10;

/**
 * Represents one of a company's organizational units which is based in the United States.
 */
public class DomesticDivision extends Division {

	private final String state;
	
	public DomesticDivision(String name, int accountNumber, String state) {
		super(name, accountNumber);
		this.state = state;
	}
	
	/**
	 * Get the state this Division is headquartered in.
	 * @return the State the Division is headquartered in.
	 */
	public String getState() {
		return state;
	}
	
	@Override
	public String toString() {
		String acct = Integer.toString(getAccountNumber());
		while (acct.length() < 8) acct = '0' + acct;
		return "{name: " + getName() + ", accountNumber: " + acct + ", state: " + getState() + "}";
	}
}
