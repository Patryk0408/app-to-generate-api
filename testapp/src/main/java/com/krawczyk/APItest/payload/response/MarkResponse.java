package com.krawczyk.APItest.payload.response;

import com.krawczyk.APItest.models.Mark;

public class MarkResponse {

    private Long id;
    private String name;
    private Integer mark;

    public MarkResponse(Mark itmark) {
        id = itmark.getId();
        name = itmark.getName();
        mark = itmark.getMark();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
