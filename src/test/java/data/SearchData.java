package data;

public class SearchData {
	private String email;
	private String password;
	private String searchQuery;
	public SearchData(String email, String password, String searchQuery) {
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
