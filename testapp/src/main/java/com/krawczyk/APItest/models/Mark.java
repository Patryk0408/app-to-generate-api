package com.krawczyk.APItest.models;

import jakarta.persistence.*;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "Mark")
@Table(name = "Marks")
public class Mark {

    public Mark()
    {
    }
    public Mark (User user, String name, Integer mark)
    {
        this.user = user;
        this.name = name;
        this.mark = mark;
    }

    public long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public User getId_user()
    {
        return user;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getMark()
    {
        return mark;
    }

    public void setMark(Integer mark)
    {
        this.mark = mark;
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
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String name;

    @Column(
            name = "mark",
            nullable = false
    )

    private Integer mark;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
