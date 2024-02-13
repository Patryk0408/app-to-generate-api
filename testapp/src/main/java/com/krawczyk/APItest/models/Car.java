package com.krawczyk.APItest.models;

import jakarta.persistence.*;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "Car")
@Table(name = "Cars")

public class Car {

    public Car ()
    {
    }
    public Car (User user, String marka, String model, Integer rok, Integer przebieg)
    {
        this.user = user;
        this.marka = marka;
        this.model = model;
        this.rok = rok;
        this.przebieg = przebieg;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public User getId_user()
    {
        return user;
    }

    public String getMarka()
    {
        return marka;
    }

    public void setMarka(String marka)
    {
        this.marka = marka;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Integer getRok(){
        return rok;
    }

    public void setRok(Integer rok){
        this.rok = rok;
    }

    public Integer getPrzebieg(){
        return przebieg;
    }

    public void setPrzebieg(Integer przebieg){
        this.przebieg = przebieg;
    }

    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )

    @Column(
            name = "Id",
            updatable = false
    )

    private Long Id;

    @Column(
            name = "marka",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String marka;

    @Column(
            name = "model",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String model;

    @Column(
            name = "rok",
            nullable = false
    )
    private Integer rok;

    @Column(
            name = "przebieg",
            nullable = false
    )
    private Integer przebieg;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
