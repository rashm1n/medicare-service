package com.hashini.medicare.model;

public class Patient {

    private long id;
    private String regNo;
    private String name;
    private int age;
    private int ageMonths;
    private String gender;
    private int tpNumber;
    private String nic;
    private String address;
    private String allergies;

    public Patient() {
    }

    public Patient(long id, String regNo, String name, int age, int ageMonths, String gender, String nic, int tpNumber,
                   String address, String allergies) {
        this.id = id;
        this.regNo = regNo;
        this.name = name;
        this.age = age;
        this.ageMonths = ageMonths;
        this.gender = gender;
        this.tpNumber = tpNumber;
        this.nic = nic;
        this.address = address;
        this.allergies = allergies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(int ageMonths) {
        this.ageMonths = ageMonths;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getTpNumber() {
        return tpNumber;
    }

    public void setTpNumber(int tpNumber) {
        this.tpNumber = tpNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

}