package com.example.pet_care_shop;

public class CustomerAdInfo
{
    private String Name,Pet_name,Phone,Pet_breed ,Pet_type,Pet_age ,Pet_sex ,Times;

    public CustomerAdInfo(String name, String pet_name, String phone, String pet_breed , String pet_type, String pet_age , String pet_sex, String times) {
        Name = name;
        Pet_name = pet_name;
        Phone = phone;
        this.Pet_breed  = pet_breed ;
        Pet_type = pet_type;
        Pet_age  = pet_age ;
        Pet_sex = pet_sex;
        Times = times;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPet_name() {
        return Pet_name;
    }

    public void setPet_name(String pet_name) {
        Pet_name = pet_name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPet_breed() {
        return Pet_breed;
    }

    public void setPet_breed(String pet_breed) {
        this.Pet_breed = pet_breed;
    }

    public String getPet_type() {
        return Pet_type;
    }

    public void setPet_type(String pet_type) {
        Pet_type = pet_type;
    }

    public String getPet_age() {
        return Pet_age ;
    }

    public void setPet_age(String pet_age ) {
        Pet_age  = pet_age ;
    }

    public String getPet_sex() {
        return Pet_sex;
    }

    public void setPet_sex(String pet_sex ) {
        Pet_sex  = pet_sex ;
    }

    public String getTimes() {
        return Times;
    }

    public void setTimes(String times) {
        Times = times;
    }
}
