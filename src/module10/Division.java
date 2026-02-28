package module10;

/**
 * Represents an organizational unit of a company. This could be either a domestic or intrnational branch.
 * 
 * @see DomesticDivision
 * @see InternationalDivision
 */
public abstract class Division {
	private final String name;
	private final int accountNumber;
	
	public Division(String name, int accountNumber) {
		this.name = name;
		this.accountNumber = accountNumber;
	}
	
	/**
	 * Gets the human-readable name of this Division.
	 * @return the name of this Division.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets this Division's account number.
	 * @return the account number of this Division.
	 */
	public int getAccountNumber() {
		return accountNumber;
	}
}
