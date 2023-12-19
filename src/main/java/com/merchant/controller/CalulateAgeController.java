package com.merchant.controller;

import com.merchant.model.AgeRequest;
import com.merchant.model.ResponseData;
import com.merchant.service.CalculateAgeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/age")
@AllArgsConstructor
public class CalulateAgeController {

    private CalculateAgeService calculateAgeService;

    @PostMapping("calculate")
    public ResponseEntity<ResponseData> calulate(@Valid @RequestBody AgeRequest request){
        return calculateAgeService.calculate(request);
    }
}
