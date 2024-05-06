package com.example.pet_care_shop;

public class User
{
    private String Email;
    private String Password;
    private String UserType;

    public User()
    {

    }
    public User(String email, String password, String userType) {
        Email = email;
        Password = password;
        UserType = userType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
