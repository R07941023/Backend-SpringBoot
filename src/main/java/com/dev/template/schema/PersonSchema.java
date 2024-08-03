package com.dev.template.schema;
import io.swagger.v3.oas.annotations.media.Schema;

public class PersonSchema {
    @Schema(description = "Name of the person", example = "YYLUI")
    private String name = "YYLUI";

    @Schema(description = "age", example = "29")
    private int age = 28;

    @Schema(description = "height", example = "178.1")
    private double height = 179;

    @Schema(description = "weight", example = "68.1")
    private double weight = 68;

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
