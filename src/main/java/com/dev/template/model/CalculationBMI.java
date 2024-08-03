package com.dev.template.model;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import com.dev.template.schema.*;

@Service
public class CalculationBMI {

    public Map<String, Object> process(PersonSchema personSchema){
        Map<String, Object> retData = new HashMap<>();
        double BMI = compute(personSchema.getHeight(), personSchema.getWeight());
        retData.put("BMI", BMI);
        return retData;
    }

    public double compute(double height, double weight) {
        return weight/Math.pow(height/100, 2);
    }
}