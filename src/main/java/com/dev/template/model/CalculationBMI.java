package com.dev.template.model;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.template.dto.PersonRequest;

@Service
public class CalculationBMI {

    public Map<String, Object> process(PersonRequest personRequest){
        Map<String, Object> retData = new HashMap<>();
        double BMI = compute(personRequest.getHeight(), personRequest.getWeight());
        retData.put("BMI", BMI);
        return retData;
    }

    public double compute(double height, double weight) {
        return weight/Math.pow(height/100, 2);
    }
}