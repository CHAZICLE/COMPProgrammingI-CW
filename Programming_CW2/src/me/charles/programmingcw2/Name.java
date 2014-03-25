package me.charles.programmingcw2;

/**
 * A class to represent the name of a private individual customer
 * 
 * @author charles
 */
public class Name {
	private final String title;
	private final String initials;
	private final String surname;

	public Name(String title, String initials, String sirname) {
		this.title = title;
		this.initials = initials;
		this.surname = sirname;
	}

	public String getTitle() {
		return title;
	}

	public String getInitials() {
		return initials;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(title).append(". ").append(initials).append(" ").append(surname).toString();
	}
}
