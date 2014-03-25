package me.charles.programmingcw2;

/**
 * A class to represent the full name of a private individual customer
 * 
 * @author charles
 */
public class Name {
	private final String title;
	private final String initials;
	private final String surname;

	public Name(String title, String initials, String surname) {
		this.title = title;
		this.initials = initials;
		this.surname = surname;
	}

	/**
	 * @return The title of the full name
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return The initials
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * @return The surname
	 */
	public String getSurname() {
		return surname;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(title).append(". ").append(initials).append(" ").append(surname).toString();
	}
}
