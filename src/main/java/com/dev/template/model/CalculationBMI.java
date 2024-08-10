package com.dev.template.model;

import org.springframework.stereotype.Service;
import com.dev.template.dto.PersonRequest;


@Service
public class CalculationBMI{

    private double bmi;

    public void exec(PersonRequest personRequest) {
        this.bmi = compute(personRequest.getHeight(), personRequest.getWeight());
    }

    public double compute(double height, double weight) {
        return weight / Math.pow(height / 100, 2);
    }

    // output
    public double getBMI() {
        return bmi;
    }

}