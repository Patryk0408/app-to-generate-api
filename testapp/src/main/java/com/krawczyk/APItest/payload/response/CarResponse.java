package com.krawczyk.APItest.payload.response;

import com.krawczyk.APItest.models.Car;

public class CarResponse {

    private Long id;
    private Integer przebieg;
    private Integer rok;
    private String marka;
    private String model;

    public CarResponse(Car car)
    {
        id = car.getId();
        przebieg = car.getPrzebieg();
        rok = car.getRok();
        marka = car.getMarka();
        model = car.getModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
