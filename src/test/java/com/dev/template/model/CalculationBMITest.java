package com.dev.template.model;

import com.dev.template.dto.PersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CalculationBMITest {

    @Autowired
    private CalculationBMI calculationBMI;

    @Test
    public void testComputeBMI() {
        // question
        double height = 170.0; // cm
        double weight = 65.0;  // kg
        // answer
        double expectedBMI = weight / Math.pow(height / 100, 2);

        // testing
        double actualBMI = calculationBMI.compute(height, weight);
        assertEquals(expectedBMI, actualBMI, 0.01);
    }

    @Test
    public void testExec() {
        // question
        PersonRequest personRequest = new PersonRequest();
        personRequest.setHeight(170.0);
        personRequest.setWeight(65.0);
        // answer
        calculationBMI.exec(personRequest);

        // testing
        double expectedBMI = calculationBMI.compute(personRequest.getHeight(), personRequest.getWeight());
        assertEquals(expectedBMI, calculationBMI.getBMI(), 0.01);
    }
    
}
