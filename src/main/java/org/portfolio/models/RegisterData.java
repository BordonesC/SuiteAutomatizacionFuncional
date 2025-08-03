package org.portfolio.models;

public class RegisterData {
    private String name;
    private String email;
    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String address1;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobileNumber;

    public RegisterData(String name, String email, String password, String day, String month, String year, String firstName, String lastName, String address1, String country, String state, String city, String zipcode, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.day = day;
        this.month = month;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
