package com.hashini.medicare.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class PatientDTO {

    private long id;
    private String regNo;
    private String name;
    private int age;
    private String gender;
    private int tpNumber;
    private String nic;
    private String address;
    private String allergies;

    private OffsetDateTime createdTime;
    private OffsetDateTime updatedTime;
    private List<PrescriptionDTO> prescriptions;

    public PatientDTO() {
    }

    public PatientDTO(long id, String regNo, String name, int age, String gender, String nic, int tpNumber,
                      String address, String allergies, OffsetDateTime createdTime, OffsetDateTime updatedTime) {
        this.id = id;
        this.regNo = regNo;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tpNumber = tpNumber;
        this.nic = nic;
        this.address = address;
        this.allergies = allergies;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
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

    public OffsetDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(OffsetDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public OffsetDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(OffsetDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<PrescriptionDTO> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<PrescriptionDTO> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
