package com.example.pet_care_shop;

public class CstReviews
{
    private String Name, Address, Review;

    public CstReviews(String name, String address, String review) {
        Name = name;
        Address = address;
        Review = review;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }
}
