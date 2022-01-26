package com.hashini.medicare.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medicinetype")
public class MedicineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "medicineType")
    private Set<Medicine> medicines;

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
}
