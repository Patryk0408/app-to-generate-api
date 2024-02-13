package com.krawczyk.APItest.controllers;

import com.krawczyk.APItest.payload.response.MarkResponse;
import com.krawczyk.APItest.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MarkController {
    @Autowired
    MarkRepository markRepository;

    @GetMapping("/admin/getAllMarks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> AdminGetAllCars() {
        var list = new ArrayList<MarkResponse>();
        for (var mark:
                markRepository.findAll()) {
            list.add(new MarkResponse(mark));
        }
        return ResponseEntity.ok(list);
    }
}
