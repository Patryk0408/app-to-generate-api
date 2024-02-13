package com.krawczyk.APItest.repository;

import com.krawczyk.APItest.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository <Car, Long> {
}
