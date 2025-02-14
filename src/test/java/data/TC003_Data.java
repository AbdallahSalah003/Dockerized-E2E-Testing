package data;

public class TC003_Data {
	private String email;
	private String password;
	private String product;
	public TC003_Data(String email, String password, String product) {
		this.email = email;
		this.password = password;
		this.product = product;
	}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getProduct() {return product;}
	@Override
	public String toString() {
		return "TC003_OutOfStockTest Data: \n" + "Email: " + email + "\nPassword: " + password + "\nProduct: " + product;
	}
}
