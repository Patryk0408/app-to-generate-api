package com.krawczyk.APItest.controllers;

import com.krawczyk.APItest.payload.response.CarResponse;
import com.krawczyk.APItest.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/admin/getAllCars")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> AdminGetAllCars() {
        var list = new ArrayList<CarResponse>();
        for (var car:
                carRepository.findAll()) {
            list.add(new CarResponse(car));
        }
        return ResponseEntity.ok(list);
    }
}
