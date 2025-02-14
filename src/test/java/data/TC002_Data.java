package data;

public class TC002_Data {
	private String email;
	private String password;
	private String searchQuery;
	public TC002_Data(String email, String password, String searchQuery) {
		this.email = email;
		this.password = password;
		this.searchQuery = searchQuery;
	}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getSearchQuery() {return searchQuery;}
	@Override
	public String toString() {
		return "TC002_SearchingTest Data: \n" + "Email: " + email + "\nPassword: " + password + "\nSearch: " + searchQuery;
	}
}
