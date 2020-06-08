package tsf.reservation.database.models;

public class Restaurant {
    private String companyBookNumber;
    private String kindOfRestaurant;
    private String name;
    private String email;
    private String homepage;
    private String phoneNumber;
    private int postCode;
    private String town;
    private String street;
    private int houseNumber;

    public Restaurant(String companyBookNumber, String kindOfRestaurant, String name, String email, String homepage,
                      String phoneNumber, int postCode, String town, String street, int houseNumber) {
        this.companyBookNumber = companyBookNumber;
        this.kindOfRestaurant = kindOfRestaurant;
        this.name = name;
        this.email = email;
        this.homepage = homepage;
        this.phoneNumber = phoneNumber;
        this.postCode = postCode;
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Restaurant() {
    }

    public String getCompanyBookNumber() {
        return companyBookNumber;
    }

    public String getKindOfRestaurant() {
        return kindOfRestaurant;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}
