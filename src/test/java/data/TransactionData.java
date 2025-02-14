package data;

public class TransactionData {
	private String gender;
	private String firstName;
	private String lastName;
    private String email;
    private String password;
    private String searchQuery;
    private String filterQuery;
    private String product;
    private String quantity;
    private String country;
    private String zone;
    private String postcode;
    private String coupon;
    private String address;
	private String phone;

    public TransactionData(String gender, String firstName, String lastName, String email, String phone, String password, String searchQuery, String filterQuery, String product,
                           String quantity, String country, String zone, String postcode, String coupon, String address) {
        this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
        this.password = password;
        this.searchQuery = searchQuery;
        this.filterQuery = filterQuery;
        this.product = product;
        this.quantity = quantity;
        this.country = country;
        this.zone = zone;
        this.postcode = postcode;
        this.coupon = coupon;
        this.address = address;
		this.phone = phone;
    }
	public String getGender() {return gender;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getSearchQuery() { return searchQuery; }
    public String getFilterQuery() { return filterQuery; }
    public String getProduct() { return product; }
    public String getQuantity() { return quantity; }
    public String getCountry() { return country; }
    public String getZone() { return zone; }
    public String getPostcode() { return postcode; }
    public String getCoupon() { return coupon; }
	public String getPhone() { return phone; }
    public String getAddress() { return address; }
    @Override
    public String toString() {
        return "TransactionData{" +
				"gender='" + gender + '\'' +
				"firstname='" + firstName + '\'' +
				"lastname='" + lastName + '\'' +
                "email='" + email + '\'' +
				"phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", searchQuery='" + searchQuery + '\'' +
                ", filterQuery='" + filterQuery + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity + '\'' +
                ", country='" + country + '\'' +
                ", zone='" + zone + '\'' +
                ", postcode='" + postcode + '\'' +
                ", coupon='" + coupon + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
