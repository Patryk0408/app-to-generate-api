package com.krawczyk.APItest.repository;

import com.krawczyk.APItest.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Long> {
}
