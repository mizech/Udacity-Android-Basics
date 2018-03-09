package michael.example.com.triertourguide;

public class Location {
    private String title;
    private String street;
    private String phoneNumber;
    private String email;
    private String website;

    public Location(String title, String street, String phoneNumber, String email, String website) {
        this.title = title;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
