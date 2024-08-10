package com.dev.template.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public class PersonRequest {
    @Schema(description = "Name of the person", example = "YYLUI")
    private String name = "YYLUI";

    @Schema(description = "age", example = "29")
    private int age = 28;

    @Schema(description = "height", example = "178.1")
    private double height = 179;

    @Schema(description = "weight", example = "68.1")
    private double weight = 68;

    // setting
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // output
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }
}
