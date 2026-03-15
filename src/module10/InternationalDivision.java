package module10;

/**
 * Represents one of a company's organizational units which is based outside the United States.
 */
public class InternationalDivision extends Division {

	private final String country;
	private final String language;
	
	public InternationalDivision(String name, int accountNumber, String country, String language) {
		super(name, accountNumber);
		this.country = country;
		this.language = language;
	}
	
	/**
	 * Gets the country this division is headquartered in. Must not be the United States.
	 * @return the country this division is headquartered in.
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Gets the language spoken by the employees of this branch. If there is no official language, an unspecified language
	 * spoken at that branch will be returned, ideally the language that will be best understood by employees if you
	 * send a written notice in that language to the branch.
	 * @return the official language for this branch, or if no official language exists, a language that will be understood.
	 */
	public String getLanguage() {
		return language;
	}
	
	@Override
	public void display() {
		String acct = Integer.toString(getAccountNumber());
		while (acct.length() < 8) acct = '0' + acct;
		System.out.println("{name: " + getName() + ", accountNumber: " + acct + ", country: " + getCountry() + ", language: " + getLanguage() + "}");
	}
}
